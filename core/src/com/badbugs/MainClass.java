package com.badbugs;

import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.dynamics.BloodSpot;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Inputs;
import com.badbugs.util.ObjectsStore;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;

public class MainClass extends ApplicationAdapter {

  private SpriteBatch batch;
  private TextureAtlas textureAtlas;
  private Animation animation;
  public static OrthographicCamera cam;
  public static int BUG_SPEED = 25;
  public static float cam_height = 100;
  public static float cam_width = 100;
  private float HeightVsWidth;

  private Texture knifeTexture;

  private Texture floorTexture;

  public static float screenWidth;
  public static float screenHeight;

  private ShapeRenderer shapeRenderer;
  private SilverKnife silverKnife;
  private BedBug bedBug;

  private CalculationThread calculationThread;

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
      //TODO Use BugGenerator here
      bedBug = SpritesCreator.loadBedBug();

    } catch (Exception e) {
      e.printStackTrace();
    }

    calculationThread = new CalculationThread();
  }

  @Override public void render() {

    //  System.out.println("fps -> "+Gdx.graphics.getFramesPerSecond());
    cam.update();
    batch.setProjectionMatrix(cam.combined);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();

    try {

      calculationThread.run();

      Renderers.renderFloor(batch);

      Renderers.renderBug(batch, bedBug);

      if (ObjectsStore.getBloodSpot(bedBug) != null
          && ObjectsStore.getBloodSprite(ObjectsStore.getBloodSpot(bedBug)).getCameraDimensions()[0] != 0) {
        Renderers.renderBlood(batch, bedBug);
      }

      Renderers.renderKnife(batch, (SilverKnife) silverKnife);

    } catch (Exception e) {
      e.printStackTrace();
    }

    batch.end();
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

  class CalculationThread extends Thread {
    public void run() {
      try {

        while (true) {
          Thread.sleep(1);
          KnifeMovement.updatePolygon(silverKnife);

          if (Intersector.overlapConvexPolygons(bedBug.getPolygon(), silverKnife.getPolygon())) {
            System.out.println("Knife overlapped with bug.");
            if (ObjectsStore.getBloodSpot(bedBug) == null) {
              ObjectsStore.add(bedBug, new BloodSpot(bedBug, silverKnife));
              System.out.println("BloodSpot created for this bug.");
            }
            ObjectsStore.getBloodSpot(bedBug).updateBloodSpotDimensions();
          }
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

}

