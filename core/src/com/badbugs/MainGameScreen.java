package com.badbugs;

import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.baseframework.renderers.FontRenderers;
import com.badbugs.baseframework.renderers.ImageRenderers;
import com.badbugs.baseframework.sounds.MusicPlayer;
import com.badbugs.baseframework.sounds.SoundPlayer;
import com.badbugs.creators.BugGenerator;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.dynamics.movement.ScratchMovement;
import com.badbugs.dynamics.panel.PanelMotion;
import com.badbugs.listers.Inputs;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Constants;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MainGameScreen extends ScreenAdapter
{
  private Game game;
  private BugGenerator bugGenerator;

  public static boolean isPaused;
  private boolean gameOverSoundPlayed;
  private float[] GOOGLE_PLAY_BUTTON = new float[]{Constants.GOOGLE_PLAY_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
          Constants.GOOGLE_PLAY_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
          Constants.GOOGLE_PLAY_W * Game.screenWidth / Constants.HOME_SCREEN_W,
          Constants.GOOGLE_PLAY_H * Game.screenHeight / Constants.HOME_SCREEN_H};

  private ShapeRenderer shapeRenderer;
  private GameOver gameoverBackground;
  private Bug[] lives;
  private BasicObject floor;
  private BasicObject googlePlay;

  MainGameScreen(Game game)
  {
    this.game = game;
    init();
  }

  private void init() {
    initObjects();
    initStates();
  }

  private void initObjects(){
    lives = ObjectsStore.getLives();
    gameoverBackground = ObjectsStore.getGameoverBackground();
    floor = ObjectsStore.getFloor();
    googlePlay = ObjectsStore.getGooglePlay();
    shapeRenderer = new ShapeRenderer();
  }

  private void initStates()
  {
    ObjectsStore.bugMissed = 0;
    ObjectsStore.score = 0;
    ObjectsStore.getBugList().clear();
    gameoverBackground.elapsedTime = 0;
    gameOverSoundPlayed = false;
    if (Constants.DEMO)
      GameStates.startDemo();
    bugGenerator = new BugGenerator();
    bugGenerator.start();
    MusicPlayer.playNatureMusic();
    Inputs.leftSwipe = false;
  }

  @Override
  public void render(float delta)
  {
    if(Inputs.backPressed)
    {
      Inputs.backPressed = false;
      SoundPlayer.playButtonClick();
      swtichToMainMenu();
    }
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //    ImageRenderers.shapeRenderer.begin();

    TouchInfo threadsTouchEvent = threadTouchEvent();

    try
    {
      if(!isPaused)
      {
        allStateUpdate(threadsTouchEvent);
      }
      allRendering(threadsTouchEvent);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    //    ImageRenderers.shapeRenderer.end();
  }

  private void allStateUpdate(TouchInfo touchInfo) throws Exception
  {
    if(!PanelMotion.panelTriggered(touchInfo))
      KnifeMovement.updatePolygon(touchInfo);
    BugMovement.upgradeEveryBugState();
    ScratchMovement.updateScratchPositions();
    PanelMotion.updatePanelState();
  }

  private void allRendering(TouchInfo touchInfo) throws Exception {

    ImageRenderers.renderBasicObject(Game.batch, floor);
    ImageRenderers.renderBugs(Game.batch);
    ImageRenderers.renderScratches(Game.batch);
//    ImageRenderers.renderKnifeShadow(Game.batch, GameStates.getSelectedKnife());
    ImageRenderers.renderKnife(Game.batch, GameStates.getSelectedKnife());
    ImageRenderers.renderBasicObject(Game.batch, GameStates.getPanel());
    FontRenderers.renderScore(Game.batch, ObjectsStore.score);
    ImageRenderers.renderLives(Game.batch, lives);

    if (Util.checkIfGameOverConditionMet()) {
      if (!gameOverSoundPlayed) {
        SoundPlayer.playGameOver();
        gameOverSoundPlayed = true;
      }
      attemptGameOver();
    }

    if (GameStates.checkIfDemoOver()) {
      popupBuyOption(touchInfo);
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

  private void popupBuyOption(TouchInfo touchInfo) throws Exception {
    ImageRenderers.renderGameOverBackground(Game.batch, gameoverBackground);
    FontRenderers.renderBuyOptionText(Game.batch, gameoverBackground);
    ImageRenderers.renderBasicObject(Game.batch, googlePlay);
    Rectangle googlePlayBounds = new Rectangle(GOOGLE_PLAY_BUTTON[0], GOOGLE_PLAY_BUTTON[1], GOOGLE_PLAY_BUTTON[2], GOOGLE_PLAY_BUTTON[3]);
    if(touchInfo != null && googlePlayBounds.contains(touchInfo.touchX, touchInfo.touchY))
    {
      Gdx.net.openURI(Constants.google_play_uri);
    }
  }

  private void swtichToMainMenu()
  {
    dispose();
    game.setScreen(new MainMenuScreen(game));
  }

  private TouchInfo threadTouchEvent()
  {
    return Util.doTouchEventsQueueEmpty();
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

