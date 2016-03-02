package com.badbugs;

import com.badbugs.bugs.BedBug;
import com.badbugs.bugs.Bug;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;

public class MainClass extends ApplicationAdapter {

  private SpriteBatch batch;
  private TextureAtlas textureAtlas;
  private Animation animation;
  private float elapsedTime = 0;
  private OrthographicCamera cam;
  private int BUG_SPEED = 25;
  private float cam_height = 100;
  private float cam_width = 100;
  private float HeightVsWidth;

  private Sprite knifeSprite;
  private Texture knifeTexture;

  private Sprite floorSprite;
  private Texture floorTexture;

  private float screenWidth;
  private float screenHeight;

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

    float pos_x = cam_width / 2 - BUG_SPEED * elapsedTime % (cam_width);

    Bug bug = new BedBug();

    Polygon p = new Polygon(bug.getBugCameraCoords());
    p.setOrigin(bug.getCameraDimentions()[0] / 2, bug.getCameraDimentions()[1] / 2);
    p.setRotation(90);
    p.setPosition(pos_x, -cam_height / 2);

    batch.draw(animation.getKeyFrame(elapsedTime, true), p.getX(), p.getY(), p.getOriginX(), p.getOriginY(),
        bug.getCameraDimentions()[0], bug.getCameraDimentions()[1], 1, 1, 90);
  }

  private void loadKnife() {
    knifeTexture = new Texture(Gdx.files.internal("knife.png"));
    knifeSprite = new Sprite(knifeTexture);

  }

  private void renderKnife() {
    Polygon p = new Polygon();
    p.setOrigin(0, 1);
    p.setPosition(0, 0);

    batch.draw(knifeTexture, p.getX(), p.getY(), p.getOriginX(), p.getOriginY(), 27, 3, 1, 1, 0, 0, 0, 1115, 100, false,
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
