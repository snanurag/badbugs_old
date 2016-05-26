package com.badbugs;

import com.badbugs.baseframework.Renderers;
import com.badbugs.util.Constants;
import com.badbugs.util.Inputs;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 4/29/2016.
 */
public class MainMenuScreen extends ScreenAdapter
{
  Rectangle soundBounds;
  Rectangle playBounds;
  Rectangle highscoresBounds;
  Rectangle shopBounds;
  Rectangle musicBounds;
  Game game;
  TouchInfo touchInfo;

  static float[] PLAY_BUTTON;
  static float[] SHOP_BUTTON;
  static float[] QUIT_BUTTON;
  static float[] SOUND_BUTTON;
  static float[] MUSIC_BUTTON;

  MainMenuScreen(Game game)
  {
    this.game = game;
    Gdx.input.setInputProcessor(new Inputs());

    // Top left coordinates are the pivots
    PLAY_BUTTON = new float[] { 668 * Game.screenWidth / Constants.HOME_SCREEN_W,
        1048 * Game.screenHeight / Constants.HOME_SCREEN_H, 480 * Game.screenWidth / Constants.HOME_SCREEN_W,
        188 * Game.screenHeight / Constants.HOME_SCREEN_H };

    SHOP_BUTTON = new float[] { 1332 * Game.screenWidth / Constants.HOME_SCREEN_W,
        1048 * Game.screenHeight / Constants.HOME_SCREEN_H, 480 * Game.screenWidth / Constants.HOME_SCREEN_W,
        188 * Game.screenHeight / Constants.HOME_SCREEN_H };

    QUIT_BUTTON = new float[] { 2308 * Game.screenWidth / Constants.HOME_SCREEN_W,
        (Constants.HOME_SCREEN_H - 209) * Game.screenHeight / Constants.HOME_SCREEN_H,
        104 * Game.screenWidth / Constants.HOME_SCREEN_W, 104 * Game.screenHeight / Constants.HOME_SCREEN_H };

    SOUND_BUTTON = new float[] { 2308 * Game.screenWidth / Constants.HOME_SCREEN_W,
        (Constants.HOME_SCREEN_H - 380) * Game.screenHeight / Constants.HOME_SCREEN_H,
        104 * Game.screenWidth / Constants.HOME_SCREEN_W, 104 * Game.screenHeight / Constants.HOME_SCREEN_H };

    MUSIC_BUTTON = new float[] { 2308 * Game.screenWidth / Constants.HOME_SCREEN_W,
        (Constants.HOME_SCREEN_H - 536) * Game.screenHeight / Constants.HOME_SCREEN_H,
        104 * Game.screenWidth / Constants.HOME_SCREEN_W, 104 * Game.screenHeight / Constants.HOME_SCREEN_H };

    playBounds = new Rectangle(PLAY_BUTTON[0], PLAY_BUTTON[1], PLAY_BUTTON[2], PLAY_BUTTON[3]);
    shopBounds = new Rectangle(SHOP_BUTTON[0], SHOP_BUTTON[1], SHOP_BUTTON[2], SHOP_BUTTON[3]);

    //    Util.globalLogger().info("Print cords "+playBoundRectCord.x +" "+playBoundRectCord.y +" "+ playBoundRectDimensions.x+ " "+playBoundRectDimensions.y);
    Util.globalLogger()
        .info("Print cords " + PLAY_BUTTON[0] + " " + PLAY_BUTTON[1] + " " + PLAY_BUTTON[2] + " " + PLAY_BUTTON[3]);
  }

  @Override
  public void render(float delta)
  {
    Renderers.renderHomePage(Game.batch);

    touchInfo = Util.getFromTouchEventsQueue();

    if (touchInfo != null)
    {
      Util.globalLogger().info("touchX and touchY " + touchInfo.touchX + " " + touchInfo.touchY);
      Vector3 touchPoint = Game.cam.unproject(new Vector3(touchInfo.touchX, touchInfo.touchY, 0));
      //           Util.globalLogger().info("touchX and touchY "+touchPoint.x+" "+touchPoint.y);
      if (playBounds.contains(touchInfo.touchX, touchInfo.touchY))
      {
        //TODO click sound here.
        game.setScreen(new MainGameScreen(game));
        return;
      } else if (shopBounds.contains(touchInfo.touchX, touchInfo.touchY))
      {
        //TODO click sound here.
        game.setScreen(new ShopScreen(game));
        return;
      }
    }
  }

  @Override
  public void dispose()
  {
    super.dispose();
  }
}