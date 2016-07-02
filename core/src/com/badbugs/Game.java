package com.badbugs;

import com.badbugs.baseframework.FontRenderers;
import com.badbugs.baseframework.MusicPlayer;
import com.badbugs.baseframework.SoundPlayer;
import com.badbugs.creators.SpritesCreator;
import com.badbugs.util.Inputs;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ashrinag on 4/29/2016.
 */
public class Game extends com.badlogic.gdx.Game
{

  public static float cam_height = 100;
  public static float cam_width = 100;

  public static int screenWidth;
  public static float screenHeight;

  public static OrthographicCamera cam;

  public static SpriteBatch batch;

  /**
   * Called when the {@link Application} is first created.
   */
  @Override
  public void create()
  {
    batch = new SpriteBatch();

    screenWidth = Gdx.graphics.getWidth();
    screenHeight = Gdx.graphics.getHeight();

    float HeightVsWidth = screenHeight / screenWidth;
    cam_height = cam_width * HeightVsWidth;

    // Constructs a new OrthographicCamera, using the given viewport width and height
    // Height is multiplied by aspect ratio.
    cam = new OrthographicCamera(cam_width, cam_height);

    cam.update();
    SpritesCreator.loadAllTextures();
    FontRenderers.loadAllFonts();

    MainGameScreen.load();
    ShopScreen.load();
    SoundPlayer.loadAllSounds();
    Gdx.input.setInputProcessor(new Inputs());
    Gdx.input.setCatchBackKey(true);
    setScreen(new MainMenuScreen(this));

  }

  @Override
  public void render()
  {
    cam.update();
    Game.batch.setProjectionMatrix(Game.cam.combined);
    Game.batch.begin();
    super.render();
    Game.batch.end();

  }

  @Override
  public void dispose() {
    super.dispose();
    Game.batch.dispose();
    SpritesCreator.disposeAll();
    MusicPlayer.dispose();
  }
}
