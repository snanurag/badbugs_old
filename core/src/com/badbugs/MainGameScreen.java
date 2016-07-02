package com.badbugs;

import com.badbugs.baseframework.FontRenderers;
import com.badbugs.baseframework.ImageRenderers;
import com.badbugs.baseframework.MusicPlayer;
import com.badbugs.baseframework.SoundPlayer;
import com.badbugs.creators.SpritesCreator;
import com.badbugs.creators.BugGenerator;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.MainGame;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.Inputs;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGameScreen extends ScreenAdapter
{
  public static boolean isPaused;
  private static ShapeRenderer shapeRenderer;
  private static Knife knife;
  private static GameOver gameoverBackground;
  private static Bug[] lives;
  private static MainGame mainGame;
  private static boolean gameOverSoundPlayed;

  private Game game;
  private BugGenerator bugGenerator;

  MainGameScreen(Game game)
  {
    this.game = game;
//    Gdx.input.setInputProcessor(new Inputs());
    init();
  }

  private void init()
  {
    ObjectsStore.bugMissed = 0;
    ObjectsStore.score = 0;
    ObjectsStore.getBugList().clear();
    gameoverBackground.elapsedTime = 0;
    gameOverSoundPlayed = false;
    bugGenerator = new BugGenerator();
    bugGenerator.start();
    MusicPlayer.playNatureMusic();
  }

  public static void load()
  {
    shapeRenderer = new ShapeRenderer();
    try
    {
      knife = (SilverKnife) SpritesCreator.loadSilverKnife();
      lives = new Bug[] { SpritesCreator.loadLife(Constants.LIFE_1_X_POS),
          SpritesCreator.loadLife(Constants.LIFE_2_X_POS), SpritesCreator.loadLife(Constants.LIFE_3_X_POS),
          SpritesCreator.loadLife(Constants.LIFE_4_X_POS), SpritesCreator.loadLife(Constants.LIFE_5_X_POS) };
      gameoverBackground = SpritesCreator.loadGameOverBackground();
      mainGame = SpritesCreator.loadMainGame();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void render(float delta)
  {
    if(Inputs.backPressed)
    {
      Inputs.backPressed = false;
      swtichToMainMenu();
    }
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //    ImageRenderers.shapeRenderer.begin();
    try
    {
      if(!isPaused)
        allStateUpdate();
      allRendering();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    //    ImageRenderers.shapeRenderer.end();
  }

  private void allStateUpdate() throws Exception
  {
    KnifeMovement.updatePolygon(knife);
    BugMovement.upgradeEveryBugState();
  }

  private void allRendering() throws Exception
  {
    ImageRenderers.renderBasicObject(Game.batch, mainGame);
    ImageRenderers.renderBugs(Game.batch);
    ImageRenderers.renderBloods(Game.batch);
    ImageRenderers.renderKnife(Game.batch, knife);
    FontRenderers.renderScore(Game.batch, ObjectsStore.score);
    ImageRenderers.renderLives(Game.batch, lives);
    if (Util.checkIfGameOverConditionMet())
    {
      if(!gameOverSoundPlayed)
      {
        SoundPlayer.playGameOver();
        gameOverSoundPlayed = true;
      }
      attemptGameOver();
    }
  }

  private void attemptGameOver() throws Exception
  {
    ImageRenderers.renderGameOverBackground(Game.batch, gameoverBackground);
    FontRenderers.rendGameOverText(Game.batch, gameoverBackground, ObjectsStore.score);
    if (gameoverBackground.elapsedTime > Constants.MAIN_MENU_SWITCH_TIME)
    {
      swtichToMainMenu();
    }
  }

  private void swtichToMainMenu()
  {
    dispose();
    game.setScreen(new MainMenuScreen(game));
  }
  @Override
  public void resize(int width, int height)
  {
  }

  @Override
  public void pause()
  {
    isPaused = true;
  }

  @Override
  public void resume()
  {
    isPaused = false;

  }

  @Override
  public void dispose()
  {
    bugGenerator.terminateBugGenerator();
    MusicPlayer.stopNatureMusic();
  }

}

