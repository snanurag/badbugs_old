package com.badbugs;

import com.badbugs.bugs.BedBug;
import com.badbugs.bugs.Bug;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
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

  @Override public void create() {

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    HeightVsWidth = h / w;
    cam_height = cam_width * HeightVsWidth;
    System.out.println("height " + h + " width " + w);
    // Constructs a new OrthographicCamera, using the given viewport width and height
    // Height is multiplied by aspect ratio.
    cam = new OrthographicCamera(cam_width, cam_height);

    cam.update();
    batch = new SpriteBatch();
    textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
    animation = new Animation(1 / 150f, textureAtlas.getRegions());

  }

  @Override public void dispose() {
    batch.dispose();
    textureAtlas.dispose();
  }

  @Override public void render() {

    cam.update();
    batch.setProjectionMatrix(cam.combined);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    //sprite.draw(batch);
    elapsedTime += Gdx.graphics.getDeltaTime();

    float pos_x = cam_width / 2 - BUG_SPEED * elapsedTime % (cam_width);

    Bug bug = new BedBug();

    Polygon p = new Polygon(bug.getBugCameraCoords());
    p.setOrigin(bug.getCameraDimentions()[0] / 2,
        bug.getCameraDimentions()[1] / 2);
    p.setRotation(90);
    p.setPosition(pos_x, -cam_height/2);

    batch.draw(animation.getKeyFrame(elapsedTime, true), p.getX(), p.getY(), p.getOriginX(),p.getOriginY(), bug.getCameraDimentions()[0], bug.getCameraDimentions()[1], 1, 1, 90);

    batch.end();
  }


  @Override public void resize(int width, int height) {
  }

  @Override public void pause() {
  }

  @Override public void resume() {
  }
}
