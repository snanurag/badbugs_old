package com.badbugs;

import com.badbugs.baseframework.ImageRenderers;
import com.badbugs.baseframework.SoundPlayer;
import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.Button;
import com.badbugs.util.Constants;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 4/29/2016.
 */
public class MainMenuScreen extends ScreenAdapter {
    static float[] PLAY_BUTTON;
    static float[] SHOP_BUTTON;
    static float[] QUIT_BUTTON;
    static float[] SOUND_BUTTON;
    static float[] MUSIC_BUTTON;
    Rectangle soundBounds;
    Rectangle playBounds;
    Rectangle quitBounds;
    Rectangle shopBounds;
    Rectangle musicBounds;
    Game game;
    TouchInfo touchInfo;
    Button sound;
    Button music;
    Button play;
    Button shop;
    Button quit;
    BasicObjectImpl mainMenu;
    ScreenAdapter mainGameScreen;

    MainMenuScreen(Game game) {
        this.game = game;
//        Gdx.input.setInputProcessor(new Inputs());
        try {
            sound = SpritesCreator.loadSound();
            music = SpritesCreator.loadMusic();
            play = SpritesCreator.loadPlayButton();
            shop = SpritesCreator.loadShopButton();
            quit = SpritesCreator.loadQuit();
            mainMenu = SpritesCreator.loadMainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top left coordinates are the pivots
        PLAY_BUTTON = new float[]{Constants.PLAY_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.PLAY_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
                Constants.PLAY_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.PLAY_H * Game.screenHeight / Constants.HOME_SCREEN_H};

        SHOP_BUTTON = new float[]{Constants.SHOP_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.SHOP_TOP * Game.screenHeight / Constants.HOME_SCREEN_H, Constants.SHOP_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.SHOP_H * Game.screenHeight / Constants.HOME_SCREEN_H};

        QUIT_BUTTON = new float[]{Constants.QUIT_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.QUIT_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
                Constants.QUIT_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.QUIT_H * Game.screenHeight / Constants.HOME_SCREEN_H};

        SOUND_BUTTON = new float[]{Constants.SOUND_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.SOUND_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
                Constants.SOUND_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.SOUND_H * Game.screenHeight / Constants.HOME_SCREEN_H};

        MUSIC_BUTTON = new float[]{Constants.MUSIC_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.MUSIC_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
                Constants.MUSIC_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.MUSIC_H * Game.screenHeight / Constants.HOME_SCREEN_H};

        playBounds = new Rectangle(PLAY_BUTTON[0], PLAY_BUTTON[1], PLAY_BUTTON[2], PLAY_BUTTON[3]);
        shopBounds = new Rectangle(SHOP_BUTTON[0], SHOP_BUTTON[1], SHOP_BUTTON[2], SHOP_BUTTON[3]);
        quitBounds = new Rectangle(QUIT_BUTTON[0], QUIT_BUTTON[1], QUIT_BUTTON[2], QUIT_BUTTON[3]);
        soundBounds = new Rectangle(SOUND_BUTTON[0], SOUND_BUTTON[1], SOUND_BUTTON[2], SOUND_BUTTON[3]);
        musicBounds = new Rectangle(MUSIC_BUTTON[0], MUSIC_BUTTON[1], MUSIC_BUTTON[2], MUSIC_BUTTON[3]);

        //    Util.globalLogger().info("Print cords "+playBoundRectCord.x +" "+playBoundRectCord.y +" "+ playBoundRectDimensions.x+ " "+playBoundRectDimensions.y);
        Util.globalLogger()
                .info("Print cords " + PLAY_BUTTON[0] + " " + PLAY_BUTTON[1] + " " + PLAY_BUTTON[2] + " " + PLAY_BUTTON[3]);
    }

    @Override
    public void render(float delta) {
        try {
            ImageRenderers.renderBasicObject(Game.batch, mainMenu);
            ImageRenderers.renderBasicObject(Game.batch, sound);
            ImageRenderers.renderBasicObject(Game.batch, music);
            ImageRenderers.renderBasicObject(Game.batch, play);
            ImageRenderers.renderBasicObject(Game.batch, shop);
            ImageRenderers.renderBasicObject(Game.batch, quit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        touchInfo = Util.getFromTouchEventsQueue();

        if (touchInfo != null) {
            Util.globalLogger().info("touchX and touchY " + touchInfo.touchX + " " + touchInfo.touchY);
            Vector3 touchPoint = Game.cam.unproject(new Vector3(touchInfo.touchX, touchInfo.touchY, 0));
            //           Util.globalLogger().info("touchX and touchY "+touchPoint.x+" "+touchPoint.y);
            if (playBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                mainGameScreen = new MainGameScreen(game);
                game.setScreen(mainGameScreen);
            } else if (shopBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                game.setScreen(new ShopScreen(game));
            } else if (quitBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                dispose();
                Gdx.app.exit();
            } else if (soundBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                Util.switchSound();
                SpritesCreator.switchSoundSprites(sound);
            } else if (musicBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                Util.switchMusic();
                SpritesCreator.switchMusicSprites(music);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}