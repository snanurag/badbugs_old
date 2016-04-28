package com.badbugs;

import com.badbugs.baseframework.Fonts;
import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.creators.BugGenerator;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.Inputs;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.List;

public class MainClass extends ApplicationAdapter {

  private SpriteBatch batch;
  private TextureAtlas textureAtlas;
  private Animation animation;
  public static OrthographicCamera cam;
  public static float cam_height = 100;
  public static float cam_width = 100;
  private float HeightVsWidth;
  public static float screenWidth;
  public static float screenHeight;

  private ShapeRenderer shapeRenderer;
  private SilverKnife silverKnife;
  private BedBug bedBug;

  @Override public void create() {

    screenWidth = Gdx.graphics.getWidth();
    screenHeight = Gdx.graphics.getHeight();

    HeightVsWidth = screenHeight / screenWidth;
    cam_height = cam_width * HeightVsWidth;

    // Constructs a new OrthographicCamera, using the given viewport width and height
    // Height is multiplied by aspect ratio.
    cam = new OrthographicCamera(cam_width, cam_height);

    cam.update();

    batch = new SpriteBatch();

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

  @Override public void render() {

    //  System.out.println("fps -> "+Gdx.graphics.getFramesPerSecond());
    cam.update();
    batch.setProjectionMatrix(cam.combined);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    //    Renderers.shapeRenderer.begin();

    try {

      Renderers.renderFloor(batch);

      Renderers.renderBugs(batch, silverKnife);

      KnifeMovement.updatePolygon(silverKnife);

      Renderers.renderKnife(batch, (SilverKnife) silverKnife);

      Fonts.renderScore(batch, ObjectsStore.score);

      Renderers.renderLives(batch);

    } catch (Exception e) {
      e.printStackTrace();
    }

    batch.end();
    //    Renderers.shapeRenderer.end();
  }

  @Override public void resize(int width, int height) {
  }

  @Override public void pause() {
  }

  @Override public void resume() {
  }

  @Override public void dispose() {
    batch.dispose();
    SpritesCreator.disposeAll();
  }

}

