package com.badbugs;

import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.baseframework.renderers.ImageRenderers;
import com.badbugs.baseframework.sounds.MusicPlayer;
import com.badbugs.baseframework.sounds.SoundPlayer;
import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
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

    private float[] PLAY_BUTTON = new float[]{Constants.PLAY_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.PLAY_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.PLAY_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.PLAY_H * Game.screenHeight / Constants.HOME_SCREEN_H};
    private float[] SHOP_BUTTON = new float[]{Constants.SHOP_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.SHOP_TOP * Game.screenHeight / Constants.HOME_SCREEN_H, Constants.SHOP_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.SHOP_H * Game.screenHeight / Constants.HOME_SCREEN_H};
    private float[] QUIT_BUTTON = new float[]{Constants.QUIT_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.QUIT_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.QUIT_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.QUIT_H * Game.screenHeight / Constants.HOME_SCREEN_H};
    private float[] SOUND_BUTTON = new float[]{Constants.SOUND_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.SOUND_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.SOUND_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.SOUND_H * Game.screenHeight / Constants.HOME_SCREEN_H};
    private float[] MUSIC_BUTTON = new float[]{Constants.MUSIC_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.MUSIC_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.MUSIC_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.MUSIC_H * Game.screenHeight / Constants.HOME_SCREEN_H};

    private Rectangle soundBounds = new Rectangle(SOUND_BUTTON[0], SOUND_BUTTON[1], SOUND_BUTTON[2], SOUND_BUTTON[3]);
    private Rectangle playBounds = new Rectangle(PLAY_BUTTON[0], PLAY_BUTTON[1], PLAY_BUTTON[2], PLAY_BUTTON[3]);
    private Rectangle quitBounds = new Rectangle(QUIT_BUTTON[0], QUIT_BUTTON[1], QUIT_BUTTON[2], QUIT_BUTTON[3]);
    private Rectangle shopBounds = new Rectangle(SHOP_BUTTON[0], SHOP_BUTTON[1], SHOP_BUTTON[2], SHOP_BUTTON[3]);
    private Rectangle musicBounds = new Rectangle(MUSIC_BUTTON[0], MUSIC_BUTTON[1], MUSIC_BUTTON[2], MUSIC_BUTTON[3]);

    private BasicObject soundButton;
    private BasicObject musicButton;
    private BasicObject playButton;
    private BasicObject shopButton;
    private BasicObject quitButton;
    private BasicObject mainMenu;
    private ScreenAdapter mainGameScreen;

    private Game game;

    MainMenuScreen(Game game) {
        this.game = game;
        MusicPlayer.playIntroMusic();

        Util.globalLogger()
                .info("Print cords " + PLAY_BUTTON[0] + " " + PLAY_BUTTON[1] + " " + PLAY_BUTTON[2] + " " + PLAY_BUTTON[3]);
        soundButton = ObjectsStore.getSoundButton();
        musicButton = ObjectsStore.getMusicButton();
        playButton = ObjectsStore.getPlayButton();
        shopButton = ObjectsStore.getShopButton();
        quitButton = ObjectsStore.getQuitButton();
        mainMenu = ObjectsStore.getMainMenuBackGround();
    }

    @Override
    public void render(float delta) {
        try {
            ImageRenderers.renderBasicObject(Game.batch, mainMenu);
            ImageRenderers.renderBasicObject(Game.batch, soundButton);
            ImageRenderers.renderBasicObject(Game.batch, musicButton);
            ImageRenderers.renderBasicObject(Game.batch, playButton);
            ImageRenderers.renderBasicObject(Game.batch, shopButton);
            ImageRenderers.renderBasicObject(Game.batch, quitButton);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TouchInfo touchInfo
                = Util.doTouchEventsQueueEmpty();

        if (touchInfo != null) {
            Util.globalLogger().info("touchX and touchY " + touchInfo.touchX + " " + touchInfo.touchY);
            Vector3 touchPoint = Game.cam.unproject(new Vector3(touchInfo.touchX, touchInfo.touchY, 0));
                       Util.globalLogger().info("touchX and touchY "+touchPoint.x+" "+touchPoint.y);
            if (playBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                MusicPlayer.stopIntroMusic();
                mainGameScreen = new MainGameScreen(game);
                game.setScreen(mainGameScreen);
            } else if (!Constants.DEMO && shopBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                MusicPlayer.stopIntroMusic();
                game.setScreen(new ShopScreen(game));
            } else if (quitBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                MusicPlayer.stopIntroMusic();
                dispose();
                Gdx.app.exit();
            } else if (soundBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                GameStates.switchSound();
                SpritesCreator.switchSoundSprites(soundButton);
            } else if (musicBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                GameStates.switchMusic();
                SpritesCreator.switchMusicSprites(musicButton);
                if(GameStates.isMusicOn())
                    MusicPlayer.playIntroMusic();
                else
                    MusicPlayer.pauseIntroMusic();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}