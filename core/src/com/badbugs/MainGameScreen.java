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

public class MainGameScreen extends ScreenAdapter {

  private static ShapeRenderer shapeRenderer;
  private static Knife knife;

  public static void load() {

    Gdx.input.setInputProcessor(new Inputs());
    shapeRenderer = new ShapeRenderer();

    SpritesCreator.loadAllTextures();

    try {
      knife = (SilverKnife) SpritesCreator.loadSilverKnife();
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

      BugMovement.upgradeEveryBugState(Game.batch);

      KnifeMovement.updatePolygon(knife);

      Renderers.renderKnife(Game.batch, knife);

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

