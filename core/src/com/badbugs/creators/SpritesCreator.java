package com.badbugs.creators;

import com.badbugs.Game;
import com.badbugs.objects.*;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.w3c.dom.Text;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class SpritesCreator {

    private static Texture knifeTexture;
    private static TextureAtlas textureAtlas;
    private static Texture floorTexture;
    private static Texture bloodTextureLong;
    private static Texture bloodTextureMedium;
    private static Texture bloodTextureSmall;
    private static Texture bloodDotTexture;
    private static Texture lifeTexture;
    private static Texture mainMenuTexture;
    private static Texture gameOverBackgroundTexture;
    private static Texture quitTexture;
    private static Texture soundEnabledTexture;
    private static Texture soundDisabledTexture;
    private static Texture musicEnabledTexture;
    private static Texture musicDisabledTexture;
    private static Texture playTexture;
    private static Texture shopTexture;
    private static Texture backTexture;
    private static Texture knifeBoosterTexture;
    private static Texture googlePlayTexture;
    private static Texture bugNoMovementTexture;

    public static void loadAllTextures() {
//        knifeTexture = new Texture(Gdx.files.internal("steel_knife.png"));
         knifeTexture = new Texture(Gdx.files.internal("bronze_knife.png"));
//        knifeTexture = new Texture(Gdx.files.internal("stone_knife.png"));
        textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
        floorTexture = new Texture(Gdx.files.internal("floor.png"));
        lifeTexture = new Texture(Gdx.files.internal("life.png"));
        mainMenuTexture = new Texture(Gdx.files.internal("home_page.png"));
        gameOverBackgroundTexture = new Texture(Gdx.files.internal("game_over_background.png"));
        quitTexture = new Texture(Gdx.files.internal("quit.png"));
        soundEnabledTexture = new Texture(Gdx.files.internal("sound_enabled.png"));
        soundDisabledTexture = new Texture(Gdx.files.internal("sound_disabled.png"));
        musicEnabledTexture = new Texture(Gdx.files.internal("music_enabled.png"));
        musicDisabledTexture = new Texture(Gdx.files.internal("music_disabled.png"));
        playTexture = new Texture(Gdx.files.internal("play_button.png"));
        shopTexture = new Texture(Gdx.files.internal("shop_button.png"));
        backTexture = new Texture(Gdx.files.internal("back_button.png"));
        knifeBoosterTexture = new Texture(Gdx.files.internal("knife_booster.png"));
        googlePlayTexture = new Texture(Gdx.files.internal("google_play.png"));
        bloodDotTexture  = new Texture(Gdx.files.internal("blood_dot.png"));
        bloodTextureLong = new Texture(Gdx.files.internal("Bloodspot_small_1.png"));
        bloodTextureMedium = new Texture(Gdx.files.internal("Bloodspot_medium_1.png"));
        bloodTextureSmall = new Texture(Gdx.files.internal("Bloodspot_small_1.png"));
        bugNoMovementTexture = new Texture(Gdx.files.internal("bronze_bug.png"));
    }

    public static BasicObject loadSilverKnife() throws Exception {
        BasicObjectImpl silverKnife = new SilverKnife(knifeTexture);
        silverKnife.getPolygon().setPosition(0, 0);
        silverKnife.getPolygon().setOrigin(0, silverKnife.getCameraDimensions()[1] / 2);
        return silverKnife;
    }

    public static BedBug loadBedBug(int level) throws Exception {
        BedBug bedBug = new BedBug(null);
        bedBug.animation = new Animation(Constants.BUG_FRAME_RATE[level], textureAtlas.getRegions());

        bedBug.getPolygon().setPosition(0, 0);
        bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

        return bedBug;
    }

    /**
     * This function is for testing. In production, it doesn't have any use.
     * @param level
     * @return
     * @throws Exception
     */
    public static BedBug loadBugNoLegMovement(int level) throws Exception {

        BedBug bedBug = new BedBug(bugNoMovementTexture);

        //black bug
//        ObjectsCord.BED_BUG_HEIGHT = 10;
//        ObjectsCord.BED_BUG_WIDTH =  9;

        //lady bug
//        ObjectsCord.BED_BUG_HEIGHT = 10;
//        ObjectsCord.BED_BUG_WIDTH =  8;

        //bronze bug
        ObjectsCord.BED_BUG_HEIGHT = 11;
        ObjectsCord.BED_BUG_WIDTH =  9;

        //iron bug
//        ObjectsCord.BED_BUG_HEIGHT = 12;
//        ObjectsCord.BED_BUG_WIDTH =  8;

        bedBug.getPolygon().setPosition(0, 0);
        bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

        return bedBug;
    }

    public static Bug loadLife(float x) throws Exception {
        Bug bug = new BedBug(lifeTexture);
        bug.getPolygon().setPosition(x, Game.cam_height / 2 - Constants.LIFE_SIZE_Y);
        bug.setCameraDimensions(new float[]{Constants.LIFE_SIZE_X, Constants.LIFE_SIZE_Y});
        return bug;
    }

    public static GameOver loadGameOverBackground() throws Exception {
        GameOver gameOver = new GameOver(gameOverBackgroundTexture);
        gameOver.setCameraDimensions(
                new float[]{Constants.GAME_OVER_BACKGROUND_WIDTH, Constants.GAME_OVER_BACKGROUND_HEIGHT});
        return gameOver;
    }

    public static Shop loadShop() throws Exception {
        Shop shopScreen = new Shop(floorTexture);
        shopScreen.setCameraDimensions(new float[]{Game.cam_width, Game.cam_height});
        shopScreen.getPolygon().setPosition(-Game.cam_width / 2, -Game.cam_height / 2);
        return shopScreen;
    }

    public static MainMenu loadMainMenu() throws Exception {
        MainMenu mainMenu = new MainMenu(mainMenuTexture);
        mainMenu.setCameraDimensions(new float[]{Game.cam_width, Game.cam_height});
        mainMenu.getPolygon().setPosition(-Game.cam_width / 2, -Game.cam_height / 2);
        return mainMenu;
    }

    public static MainGame loadMainGame() throws Exception {
        MainGame mainGame = new MainGame(floorTexture);
        mainGame.setCameraDimensions(new float[]{Game.cam_width, Game.cam_height});
        mainGame.getPolygon().setPosition(-Game.cam_width / 2, -Game.cam_height / 2);
        return mainGame;
    }

    //TODO : Fix it -> On Main Menu, y axis is working from top to bottom
    public static Button loadQuit() throws Exception {
        Button s = new Button(quitTexture);
        s.setCameraDimensions(new float[]{Game.cam_width * Constants.QUIT_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.QUIT_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.QUIT_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.QUIT_TOP + Constants.QUIT_H)) / Constants.HOME_SCREEN_H;
        s.getPolygon().setPosition(x, y);

        return s;
    }

    public static Button loadSound() throws Exception {

        Button s;
        if (Util.isSoundOn())
            s = new Button(soundEnabledTexture);
        else
            s = new Button(soundDisabledTexture);
        s.setCameraDimensions(new float[]{Game.cam_width * Constants.SOUND_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.SOUND_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.SOUND_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.SOUND_TOP + Constants.SOUND_H)) / Constants.HOME_SCREEN_H;
        s.getPolygon().setPosition(x, y);

        return s;
    }

    public static Button loadMusic() throws Exception {
        Button m;
        if (Util.isMusicOn())
            m = new Button(musicEnabledTexture);
        else
            m = new Button(musicDisabledTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.MUSIC_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.MUSIC_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.MUSIC_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.MUSIC_TOP + Constants.MUSIC_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);

        return m;
    }

    public static Button loadPlayButton() throws Exception {
        Button m = new Button(playTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.PLAY_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.PLAY_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.PLAY_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.PLAY_TOP + Constants.PLAY_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);

        return m;
    }

    public static Button loadShopButton() throws Exception {
        Button m = new Button(shopTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.SHOP_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.SHOP_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.SHOP_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.SHOP_TOP + Constants.SHOP_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);

        return m;
    }

    public static Button loadBackButton() throws Exception {
        Button m = new Button(backTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.BACK_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.BACK_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.BACK_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.BACK_TOP + Constants.BACK_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);

        return m;
    }

    public static Button loadKnifeBooster() throws Exception {
        Button m = new Button(knifeBoosterTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.KNIFE_BOOSTER_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.KNIFE_BOOSTER_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.KNIFE_BOOSTER_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.KNIFE_BOOSTER_TOP + Constants.KNIFE_BOOSTER_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);

        return m;
    }

    public static Button loadGooglePlay() throws Exception {
        Button googlePlay = new Button(googlePlayTexture);
        googlePlay.setCameraDimensions(new float[]{Game.cam_width * Constants.GOOGLE_PLAY_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.GOOGLE_PLAY_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.GOOGLE_PLAY_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.GOOGLE_PLAY_TOP + Constants.GOOGLE_PLAY_H))
                / Constants.HOME_SCREEN_H;
        googlePlay.getPolygon().setPosition(x, y);

        return googlePlay;
    }

    public static BloodSprite loadBloodSpot(float len) {
        BloodSprite bloodSprite = new BloodSprite(getProperBloodTex(len));
        return bloodSprite;
    }

    private static Texture getProperBloodTex(float bloodSpotLen) {
        if (bloodSpotLen < 2)
            return SpritesCreator.bloodTextureSmall;
        else if (bloodSpotLen < 6)
            return SpritesCreator.bloodTextureMedium;
        else
            return SpritesCreator.bloodTextureLong;
    }

    public static BloodSprite loadBloodDot()
    {
        BloodSprite bloodSprite = new BloodSprite(bloodDotTexture);
        return bloodSprite;
    }

    public static void switchSoundSprites(Button sound) {
        if (sound.getTexture() == soundEnabledTexture) {
            sound.setTexture(soundDisabledTexture);
        } else {
            sound.setTexture(soundEnabledTexture);
        }
    }

    public static void switchMusicSprites(Button music) {
        if (music.getTexture() == musicEnabledTexture) {
            music.setTexture(musicDisabledTexture);
        } else {
            music.setTexture(musicEnabledTexture);
        }
    }

    public static void disposeAll() {
        textureAtlas.dispose();
        knifeTexture.dispose();
        bloodTextureLong.dispose();
        bloodTextureMedium.dispose();
        bloodTextureSmall.dispose();
        lifeTexture.dispose();
        mainMenuTexture.dispose();
        gameOverBackgroundTexture.dispose();
        soundEnabledTexture.dispose();
        soundDisabledTexture.dispose();
        musicEnabledTexture.dispose();
        musicDisabledTexture.dispose();
        floorTexture.dispose();
        lifeTexture.dispose();
        quitTexture.dispose();
        shopTexture.dispose();
        playTexture.dispose();
        backTexture.dispose();
        knifeBoosterTexture.dispose();
        googlePlayTexture.dispose();
        bloodDotTexture.dispose();
    }

}
