package com.badbugs;

import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.baseframework.Renderers;
import com.badbugs.dynamics.BloodSpot;
import com.badbugs.objects.BasicObject;
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
  private BasicObject silverKnife;
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
    textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
    animation = new Animation(1 / 60f, textureAtlas.getRegions());
    Gdx.input.setInputProcessor(new Inputs());
    shapeRenderer = new ShapeRenderer();
    loadFloor();
    try {
      silverKnife = SpritesCreator.loadSilverKnife();
      //TODO Use BugGenerator here
      bedBug = SpritesCreator.loadBedBug();
 //     ObjectsStore.add(bedBug, new BloodSpot(bedBug, silverKnife.getPolygon().getRotation()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public void render() {

    cam.update();
    batch.setProjectionMatrix(cam.combined);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    try {
      renderFloor();
      Renderers.renderBug(batch, bedBug);
      Renderers.renderKnife(batch, (SilverKnife) silverKnife);

      if (Intersector.overlapConvexPolygons(bedBug.getPolygon(), silverKnife.getPolygon())) {
        if (ObjectsStore.getBloodSpot(bedBug) == null) {
          ObjectsStore.add(bedBug, new BloodSpot(bedBug, silverKnife.getPolygon().getRotation()));
        }
      }
        if(ObjectsStore.getBloodSpot(bedBug)!= null)
        {
          ObjectsStore.getBloodSpot(bedBug).spill(batch);
        }
    } catch (Exception e) {
      e.printStackTrace();
    }

    batch.end();
  }

  //  private void loadKnife() {
  //    knifeTexture = new Texture(Gdx.files.internal("knife.png"));
  ////    knifeSprite = new Sprite(knifeTexture);
  //
  //  }

  private void loadFloor() {
    floorTexture = new Texture(Gdx.files.internal("floor.png"));
    //  floorSprite = new Sprite(floorTexture);
  }

  private void renderFloor() {
    batch.draw(floorTexture, -cam_width / 2, -cam_height / 2, cam_width * floorTexture.getWidth() / screenWidth,
        cam_height * floorTexture.getHeight() / screenHeight);
  }

  @Override public void resize(int width, int height) {
  }

  @Override public void pause() {
  }

  @Override public void resume() {
  }

  @Override public void dispose() {
    batch.dispose();
    textureAtlas.dispose();
    knifeTexture.dispose();
  }

}
