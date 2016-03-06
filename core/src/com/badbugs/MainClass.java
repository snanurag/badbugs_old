package com.badbugs;

import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

public class MainClass extends ApplicationAdapter {

  private SpriteBatch batch;
  private TextureAtlas textureAtlas;
  private Animation animation;
  private float elapsedTime = 0;
  private OrthographicCamera cam;
  private int BUG_SPEED = 25;
  public static float cam_height = 100;
  public static float cam_width = 100;
  private float HeightVsWidth;

  private Sprite knifeSprite;
  private Texture knifeTexture;

  private Sprite floorSprite;
  private Texture floorTexture;

  public static float screenWidth;
  public static float screenHeight;

  public Polygon bugPolygon;
  public Polygon knifePolygon;

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
    animation = new Animation(1 / 150f, textureAtlas.getRegions());
    Gdx.input.setInputProcessor(new Inputs());
    loadFloor();
    loadKnife();
  }

  @Override public void dispose() {
    batch.dispose();
    textureAtlas.dispose();
    knifeTexture.dispose();
  }

  @Override public void render() {

    cam.update();
    batch.setProjectionMatrix(cam.combined);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    renderFloor();
    renderBug();
    renderKnife();

    if(Intersector.intersectPolygons(bugPolygon,knifePolygon,null))
    {
      System.out.println("knife hit bug");
    }

    batch.end();
  }

  @Override public void resize(int width, int height) {
  }

  @Override public void pause() {
  }

  @Override public void resume() {
  }

  private void renderBug() {
    elapsedTime += Gdx.graphics.getDeltaTime();

    float pos_y = cam_height / 2 - BUG_SPEED * elapsedTime % (cam_height);

    BasicObject basicObject = new BedBug();

    bugPolygon = new Polygon(basicObject.getCameraCoords());
    bugPolygon.setOrigin(basicObject.getCameraDimentions()[0] / 2, basicObject.getCameraDimentions()[1] / 2);
    //p.setRotation(90);
    bugPolygon.setPosition(-cam_width/2, -pos_y);

    batch.draw(animation.getKeyFrame(elapsedTime, true), bugPolygon.getX(), bugPolygon.getY(), bugPolygon.getOriginX(), bugPolygon.getOriginY(),
        basicObject.getCameraDimentions()[0], basicObject.getCameraDimentions()[1], 1, 1, 0);
  }

  private void loadKnife() {
    knifeTexture = new Texture(Gdx.files.internal("knife.png"));
    knifeSprite = new Sprite(knifeTexture);

  }

  private void renderKnife() {
    elapsedTime += Gdx.graphics.getDeltaTime();

    float pos_x = cam_width / 2 - BUG_SPEED * elapsedTime % (cam_width);

    BasicObjectImpl silverKnife = new SilverKnife();
    TouchInfo touchInfoInstance = Util.getFromTouchEventsQueue();
    if(touchInfoInstance!= null)
    {
      System.out.println(touchInfoInstance.touchX + " "+touchInfoInstance.touchY);
    }
    knifePolygon = new Polygon(silverKnife.getCameraCoords());
    knifePolygon.setOrigin(silverKnife.getCameraDimentions()[0] / 2, silverKnife.getCameraDimentions()[1] / 2);
    knifePolygon.setPosition(pos_x, 0);

    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), knifePolygon.getOriginX(), knifePolygon.getOriginY(), silverKnife.getCameraDimentions()[0],silverKnife.getCameraDimentions()[1], 1, 1, 0, 0, 0, silverKnife.getPixelDimensions()[0], silverKnife.getPixelDimensions()[1], false,
        false);
//    batch.draw(knifeTexture, -cam_width / 2, -cam_height / 2);
  }

  private void loadFloor() {
    floorTexture = new Texture(Gdx.files.internal("floor.png"));
    floorSprite = new Sprite(floorTexture);
  }

  private void renderFloor() {
    batch.draw(floorTexture, -cam_width / 2, -cam_height / 2, cam_width * floorTexture.getWidth() / screenWidth,
        cam_height * floorTexture.getHeight() / screenHeight);
  }

}
