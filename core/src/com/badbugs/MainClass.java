package com.badbugs;

import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.creators.BugGenerator;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Inputs;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

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

  //  private CalculationThread calculationThread;

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

    textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
    animation = new Animation(1 / 60f, textureAtlas.getRegions());
    Gdx.input.setInputProcessor(new Inputs());
    shapeRenderer = new ShapeRenderer();

    SpritesCreator.loadAllTextures();

    try {
      silverKnife = (SilverKnife) SpritesCreator.loadSilverKnife();

      //      //TODO Use BugGenerator here
      //      bedBug = SpritesCreator.loadBedBug();
      //
      //      ObjectsStore.add(bedBug);

    } catch (Exception e) {
      e.printStackTrace();
    }

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

      List<Bug> bugList = ObjectsStore.getBugList();

      synchronized (bugList) {
        Iterator<Bug> itr = bugList.iterator();
        while (itr.hasNext()) {
          Bug bedBug = itr.next();
          Util.globalLogger().debug(
              "Bug position of bug : " + bedBug.id + " x " + bedBug.getPolygon().getX() + " and y " + bedBug
                  .getPolygon().getY());

          if (bedBug.dead) {
            itr.remove();
            continue;
          }

          Renderers.renderBug(batch, bedBug);
          Vector2 bugCenter = Util.getPolygonCenter(bedBug.getPolygon());

          Vector2 currentState = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, silverKnife.getPolygon());

          if (!bedBug.hit && bedBug.state != null && !bedBug.compareState((int) currentState.x, (int) currentState.y)) {

            bedBug.hit = true;
          }

          if (bedBug.hit) {
            Renderers.renderBlood(batch, bedBug);
            continue;
          }

          BugMovement.applyMovement(bedBug);
        }

      }

      KnifeMovement.updatePolygon(silverKnife);

      Renderers.renderKnife(batch, (SilverKnife) silverKnife);

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

