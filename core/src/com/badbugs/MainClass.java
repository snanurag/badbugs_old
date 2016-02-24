package com.badbugs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import sun.rmi.runtime.Log;

public class MainClass extends ApplicationAdapter {

  private SpriteBatch batch;
  private TextureAtlas textureAtlas;
  private Animation animation;
  private float elapsedTime = 0;
  private OrthographicCamera cam;
  private int BUG_SPEED = 25;

  @Override public void create() {


    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    // Constructs a new OrthographicCamera, using the given viewport width and height
    // Height is multiplied by aspect ratio.
    cam = new OrthographicCamera(100, 100 * (h / w));

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

//    batch.draw(animation.getKeyFrame(elapsedTime, true), -20*11/(14*2), -10,0,0, 20*11/14, 20, 1, 1,90);
    float pos_y = 50* Gdx.graphics.getHeight()/Gdx.graphics.getWidth() -BUG_SPEED*elapsedTime%(100* Gdx.graphics.getHeight()/Gdx.graphics.getWidth());

    batch.draw(animation.getKeyFrame(elapsedTime, true), pos_y,0 ,0,0, 20*11/14, 20, 1, 1,90);

    batch.end();
  }

  @Override public void resize(int width, int height) {
  }

  @Override public void pause() {
  }

  @Override public void resume() {
  }
}
