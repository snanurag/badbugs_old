package com.badbugs;

import com.badbugs.baseframework.Fonts;
import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.creators.BugGenerator;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Inputs;
import com.badbugs.util.ObjectsStore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGameScreen extends ScreenAdapter {

  private static ShapeRenderer shapeRenderer;
  private static SilverKnife silverKnife;

  public static void load() {

    Gdx.input.setInputProcessor(new Inputs());
    shapeRenderer = new ShapeRenderer();

    SpritesCreator.loadAllTextures();

    try {
      silverKnife = (SilverKnife) SpritesCreator.loadSilverKnife();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Fonts.loadAllFonts();

    new BugGenerator().start();

  }

  @Override public void render(float delta) {

    //  System.out.println("fps -> "+Gdx.graphics.getFramesPerSecond());


    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //    Renderers.shapeRenderer.begin();

    try {

      Renderers.renderFloor(Game.batch);

      Renderers.renderBugs(Game.batch, silverKnife);

      KnifeMovement.updatePolygon(silverKnife);

      Renderers.renderKnife(Game.batch, (SilverKnife) silverKnife);

      Fonts.renderScore(Game.batch, ObjectsStore.score);

      Renderers.renderLives(Game.batch);

    } catch (Exception e) {
      e.printStackTrace();
    }

    //    Renderers.shapeRenderer.end();
  }

  @Override public void resize(int width, int height) {
  }

  @Override public void pause() {
  }

  @Override public void resume() {
  }

  @Override public void dispose() {
    Game.batch.dispose();
    SpritesCreator.disposeAll();
  }

}

