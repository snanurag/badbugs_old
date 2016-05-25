package com.badbugs;

import com.badbugs.baseframework.Fonts;
import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.creators.BugGenerator;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.knives.Knife;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Inputs;
import com.badbugs.util.ObjectsStore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGameScreen extends ScreenAdapter
{
  private static ShapeRenderer shapeRenderer;
  private static Knife knife;
  private static Stage stage;
  private static Dialog gameOverDialog;

  public static void load()
  {

    Gdx.input.setInputProcessor(new Inputs());
    shapeRenderer = new ShapeRenderer();

    SpritesCreator.loadAllTextures();

    try
    {
      knife = (SilverKnife) SpritesCreator.loadSilverKnife();
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    Fonts.loadAllFonts();

    new BugGenerator().start();

    stage =  new Stage();
    stage.getViewport().setScreenSize((int)Game.screenWidth,(int)Game.screenHeight);
    stage.getViewport().setCamera(Game.cam);
    gameOverDialog = new Dialog( "", new Skin(Gdx.files.internal("data/uiskin.json")));
    gameOverDialog.setPosition(-20,-5);
    gameOverDialog.setModal(true);
    gameOverDialog.center();
    gameOverDialog.text("Game over");

    gameOverDialog.setWidth(40);
    gameOverDialog.setHeight(10);
  //  gameOverDialog.dra
    stage.addActor(gameOverDialog);

  }

  @Override
  public void render(float delta)
  {
    //  System.out.println("fps -> "+Gdx.graphics.getFramesPerSecond());

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //    Renderers.shapeRenderer.begin();
    try
    {
      allStateUpdate();
      allRendering();
      checkAndAttemptGameOver();
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    //    Renderers.shapeRenderer.end();
  }

  private void checkAndAttemptGameOver()
  {
    if(ObjectsStore.bugMissed >= 5)
    {
      Gdx.input.setInputProcessor(null);
      Game.batch.setColor(1, 1, 1, 0.3f);
      stage.draw();
      Game.batch.setColor(1, 1, 1, 1);
    }
  }
  private void allStateUpdate() throws Exception
  {
    KnifeMovement.updatePolygon(knife);
    BugMovement.upgradeEveryBugState();
  }

  private void allRendering() throws Exception
  {
    Renderers.renderFloor(Game.batch);
    Renderers.renderBugs(Game.batch);
    Renderers.renderBloods(Game.batch);
    Renderers.renderKnife(Game.batch, knife);
    Fonts.renderScore(Game.batch, ObjectsStore.score);
    Renderers.renderLives(Game.batch);
  }

  @Override
  public void resize(int width, int height)
  {
  }

  @Override
  public void pause()
  {
  }

  @Override
  public void resume()
  {
  }

  @Override
  public void dispose()
  {
    Game.batch.dispose();
    SpritesCreator.disposeAll();
  }

}

