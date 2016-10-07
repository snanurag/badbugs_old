package com.badbugs.creators;

import com.badbugs.Game;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.objects.AbstractBasicObject;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.bugs.*;
import com.badbugs.objects.knives.BronzeKnife;
import com.badbugs.objects.knives.SteelKnife;
import com.badbugs.objects.knives.StoneKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class SpritesCreator {

    private static TextureAtlas texAtlasBedBug;
    private static TextureAtlas texAtlasLadyBug;
    private static TextureAtlas texAtlasBlackBug;
    private static TextureAtlas texAtlasBronzeBug;
    private static TextureAtlas texAtlasSteelBug;
    private static Texture floorTexture;
    private static Texture bloodTextureMedium;
    private static Texture bloodTextureSmall;
    private static Texture bloodDotTexture;
    private static Texture oilTextureMedium;
    private static Texture oilTextureSmall;
    private static Texture oilDotTexture;
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
    private static Texture bronzeScratchTexture;
    private static Texture steelScratchTexture;
    private static Texture bronzeKnifeTexture;
    private static Texture steelKnifeTexture;

    public static void loadAllTextures() {
        texAtlasBedBug = new TextureAtlas(Gdx.files.internal("sprites/bed_bug.atlas"));
        texAtlasLadyBug = new TextureAtlas(Gdx.files.internal("sprites/lady_bug.atlas"));
        texAtlasBlackBug = new TextureAtlas(Gdx.files.internal("sprites/black_bug.atlas"));
        texAtlasBronzeBug = new TextureAtlas(Gdx.files.internal("sprites/bronze_bug.atlas"));
        texAtlasSteelBug = new TextureAtlas(Gdx.files.internal("sprites/steel_bug.atlas"));
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
        bloodTextureMedium = new Texture(Gdx.files.internal("Bloodspot_medium.png"));
        bloodTextureSmall = new Texture(Gdx.files.internal("Bloodspot_small.png"));
        oilDotTexture  = new Texture(Gdx.files.internal("oil_dot.png"));
        oilTextureMedium = new Texture(Gdx.files.internal("oil_medium.png"));
        oilTextureSmall = new Texture(Gdx.files.internal("oil_small.png"));
        bugNoMovementTexture = new Texture(Gdx.files.internal("life.png"));
        bronzeScratchTexture = new Texture(Gdx.files.internal("bronze_scratch.png"));
        steelScratchTexture = new Texture(Gdx.files.internal("iron_scratch.png"));
        bronzeKnifeTexture = new Texture(Gdx.files.internal("bronze_knife.png"));
        steelKnifeTexture = new Texture(Gdx.files.internal("steel_knife.png"));

        try{
            createKnives(new Texture(Gdx.files.internal("stone_knife.png")), bronzeKnifeTexture, steelKnifeTexture);
            createSidePanels(new Texture(Gdx.files.internal("panels/empty.png")), new Texture(Gdx.files.internal
                    ("panels/stone.png")), new Texture(Gdx.files.internal("panels/bronze.png")), new Texture(Gdx.files
                    .internal("panels/steel.png")), new Texture(Gdx.files.internal("panels/stone_bronze.png")), new
                    Texture(Gdx.files.internal("panels/bronze_steel.png")), new Texture(Gdx.files.internal
                    ("panels/stone_steel.png")));
            createLives();
            createGameOverBackground();
            createGameFloor();
            createGooglePlayIcon();
            createShop();
            createShopBackButton();
            createKnifeBooster();
            createSoundButton();
            createMainMenuBackground();
            createQuitButton();
            createMusicButton();
            createPlayButton();
            createShopButton();
            createBronzeKnifeForShop();
            createSteelKnifeForShop();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void createKnives(Texture... knifeTextures) throws Exception {
        ObjectsStore.add(Constants.KNIFE_TYPE.STONE, new StoneKnife(knifeTextures[0]));
        ObjectsStore.add(Constants.KNIFE_TYPE.BRONZE, new BronzeKnife(knifeTextures[1]));
        ObjectsStore.add(Constants.KNIFE_TYPE.STEEL, new SteelKnife(knifeTextures[2]));
        GameStates.setSelectedKnife(Constants.KNIFE_TYPE.STONE);
    }

    private static void createSidePanels(Texture... panelTextures) throws Exception
    {
        ObjectsStore.add(Constants.PANEL.EMPTY, new StoneKnife(panelTextures[0]));
        ObjectsStore.add(Constants.PANEL.STONE, new BronzeKnife(panelTextures[1]));
        ObjectsStore.add(Constants.PANEL.BRONZE, new SteelKnife(panelTextures[2]));
        ObjectsStore.add(Constants.PANEL.STEEL, new SteelKnife(panelTextures[3]));
        ObjectsStore.add(Constants.PANEL.STONE_BRONZE, new SteelKnife(panelTextures[4]));
        ObjectsStore.add(Constants.PANEL.BRONZE_STEEL, new SteelKnife(panelTextures[5]));
        ObjectsStore.add(Constants.PANEL.STONE_STEEL, new SteelKnife(panelTextures[6]));
        Util.setPanelForStoneKnifeSelection();
    }

    public static Bug createBug(Constants.BUG_TYPE t, int level ) throws Exception{
        Bug b = null;
        TextureAtlas texAtlas = null;

        switch (t)
        {
            case BED : b = new BedBug(null); texAtlas = texAtlasBedBug; break;
            case LADY : b = new LadyBug(null); texAtlas = texAtlasLadyBug; break;
            case BLACK : b = new BlackBug(null); texAtlas = texAtlasBlackBug; break;
            case BRONZE: b = new BronzeBug(null); texAtlas = texAtlasBronzeBug; break;
            case STEEL: b = new SteelBug(null); texAtlas = texAtlasSteelBug; break;
        }

        if(b != null && texAtlas != null){
            b.animation = new Animation(b.getFrameRate()[level], texAtlas.getRegions());

            b.getPolygon().setPosition(0, 0);
            b.getPolygon().setOrigin(b.getCameraDimensions()[0] / 2, b.getCameraDimensions()[1] / 2);
        }

        return b;
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
//        ObjectsCord.BED_BUG_HEIGHT = 11;
//        ObjectsCord.BED_BUG_WIDTH =  9;

        //iron bug
//        ObjectsCord.BED_BUG_HEIGHT = 12;
//        ObjectsCord.BED_BUG_WIDTH =  8;

        bedBug.getPolygon().setPosition(0, 0);
        bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

        return bedBug;
    }

    private static void createLives() throws Exception {
        ObjectsStore.setLives(new Bug[]{SpritesCreator.loadLife(Constants.LIFE_1_X_POS),
                SpritesCreator.loadLife(Constants.LIFE_2_X_POS), SpritesCreator.loadLife(Constants.LIFE_3_X_POS),
                SpritesCreator.loadLife(Constants.LIFE_4_X_POS), SpritesCreator.loadLife(Constants.LIFE_5_X_POS)});
    }

    private static Bug loadLife(float x) throws Exception {
        Bug bug = new BedBug(lifeTexture);
        bug.getPolygon().setPosition(x, Game.cam_height / 2 - Constants.LIFE_SIZE_Y);
        bug.setCameraDimensions(new float[]{Constants.LIFE_SIZE_X, Constants.LIFE_SIZE_Y});
        return bug;
    }

    private static void createGameOverBackground() throws Exception {
        GameOver gameOver = new GameOver(gameOverBackgroundTexture);
        gameOver.setCameraDimensions(
                new float[]{Constants.GAME_OVER_BACKGROUND_WIDTH, Constants.GAME_OVER_BACKGROUND_HEIGHT});
        ObjectsStore.setGameoverBackground(gameOver);
    }

    public static void createShop() throws Exception {
        AbstractBasicObject shopScreen = new BasicObjectImpl(floorTexture);
        shopScreen.setCameraDimensions(new float[]{Game.cam_width, Game.cam_height});
        shopScreen.getPolygon().setPosition(-Game.cam_width / 2, -Game.cam_height / 2);
        ObjectsStore.setShop(shopScreen);
    }

    public static  void createMainMenuBackground() throws Exception {
        AbstractBasicObject mainMenu = new BasicObjectImpl(mainMenuTexture);
        mainMenu.setCameraDimensions(new float[]{Game.cam_width, Game.cam_height});
        mainMenu.getPolygon().setPosition(-Game.cam_width / 2, -Game.cam_height / 2);
        ObjectsStore.setMainMenuBackGround(mainMenu);
    }

    public static void createGameFloor() throws Exception {
        AbstractBasicObject floor = new BasicObjectImpl(floorTexture);
        floor.setCameraDimensions(new float[]{Game.cam_width, Game.cam_height});
        floor.getPolygon().setPosition(-Game.cam_width / 2, -Game.cam_height / 2);
        ObjectsStore.setFloor(floor);
    }

    //TODO : Fix it -> On Main Menu, y axis is working from top to bottom
    public static void createQuitButton() throws Exception {
        AbstractBasicObject s = new BasicObjectImpl(quitTexture);
        s.setCameraDimensions(new float[]{Game.cam_width * Constants.QUIT_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.QUIT_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.QUIT_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.QUIT_TOP + Constants.QUIT_H)) / Constants.HOME_SCREEN_H;
        s.getPolygon().setPosition(x, y);
        ObjectsStore.setQuitButton(s);
    }

    public static void createSoundButton() throws Exception {

        AbstractBasicObject s;
        if (GameStates.isSoundOn())
            s = new BasicObjectImpl(soundEnabledTexture);
        else
            s = new BasicObjectImpl(soundDisabledTexture);
        s.setCameraDimensions(new float[]{Game.cam_width * Constants.SOUND_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.SOUND_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.SOUND_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.SOUND_TOP + Constants.SOUND_H)) / Constants.HOME_SCREEN_H;
        s.getPolygon().setPosition(x, y);
        ObjectsStore.setSoundButton(s);
    }

    public static void createMusicButton() throws Exception {
        AbstractBasicObject m;
        if (GameStates.isMusicOn())
            m = new BasicObjectImpl(musicEnabledTexture);
        else
            m = new BasicObjectImpl(musicDisabledTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.MUSIC_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.MUSIC_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.MUSIC_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.MUSIC_TOP + Constants.MUSIC_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setMusicButton(m);
    }

    public static void createPlayButton() throws Exception {
        AbstractBasicObject m = new BasicObjectImpl(playTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.PLAY_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.PLAY_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.PLAY_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.PLAY_TOP + Constants.PLAY_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setPlayButton(m);
    }

    public static void createShopButton() throws Exception {
        AbstractBasicObject m = new BasicObjectImpl(shopTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.SHOP_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.SHOP_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.SHOP_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.SHOP_TOP + Constants.SHOP_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setShopButton(m);
    }

    public static void createShopBackButton() throws Exception {
        AbstractBasicObject m = new BasicObjectImpl(backTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.BACK_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.BACK_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.BACK_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.BACK_TOP + Constants.BACK_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setBack(m);
    }

    public static void createKnifeBooster() throws Exception {
        AbstractBasicObject m = new BasicObjectImpl(knifeBoosterTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.KNIFE_BOOSTER_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.KNIFE_BOOSTER_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.KNIFE_BOOSTER_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.KNIFE_BOOSTER_TOP + Constants.KNIFE_BOOSTER_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setKnifeBooster(m);
    }

    public static void createBronzeKnifeForShop() throws Exception {
        AbstractBasicObject m = new BasicObjectImpl(bronzeKnifeTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.BRONZE_KNIFE_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.BRONZE_KNIFE_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.BRONZE_KNIFE_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.BRONZE_KNIFE_TOP + Constants.BRONZE_KNIFE_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setBronzeKnifeForShop(m);
    }

    public static void createSteelKnifeForShop() throws Exception {
        AbstractBasicObject m = new BasicObjectImpl(steelKnifeTexture);
        m.setCameraDimensions(new float[]{Game.cam_width * Constants.STEEL_KNIFE_W/ Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.STEEL_KNIFE_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.STEEL_KNIFE_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.STEEL_KNIFE_TOP + Constants.STEEL_KNIFE_H))
                / Constants.HOME_SCREEN_H;
        m.getPolygon().setPosition(x, y);
        ObjectsStore.setSteelKnifeForShop(m);
    }

    private static void createGooglePlayIcon() throws Exception {
        AbstractBasicObject googlePlay = new BasicObjectImpl(googlePlayTexture);
        googlePlay.setCameraDimensions(new float[]{Game.cam_width * Constants.GOOGLE_PLAY_W / Constants.HOME_SCREEN_W,
                Game.cam_height * Constants.GOOGLE_PLAY_H / Constants.HOME_SCREEN_H});
        float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.GOOGLE_PLAY_LEFT) / Constants.HOME_SCREEN_W;
        float y = Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.GOOGLE_PLAY_TOP + Constants.GOOGLE_PLAY_H))
                / Constants.HOME_SCREEN_H;
        googlePlay.getPolygon().setPosition(x, y);
        ObjectsStore.setGooglePlay(googlePlay);
    }

    public static BasicObject getBloodSpot(float len) {
        AbstractBasicObject bloodSprite = new BasicObjectImpl(getRightBloodTex(len));
        return bloodSprite;
    }

    public static BasicObject getOilSpot(float len) {
        AbstractBasicObject bloodSprite = new BasicObjectImpl(getRightOilTex(len));
        return bloodSprite;
    }

    public static BasicObject getBronzeScratch(){
        return new BasicObjectImpl(bronzeScratchTexture);
    }

    public static BasicObject getIronScratch(){
        return new BasicObjectImpl(steelScratchTexture);
    }

    public static BasicObject getBloodDot()
    {
        AbstractBasicObject bloodSprite = new BasicObjectImpl(bloodDotTexture);
        return bloodSprite;
    }

    public static BasicObject getOilDot()
    {
        AbstractBasicObject bloodSprite = new BasicObjectImpl(oilDotTexture);
        return bloodSprite;
    }

    public static void switchSoundSprites(BasicObject sound) {
        if (sound.getTexture() == soundEnabledTexture) {
            sound.setTexture(soundDisabledTexture);
        } else {
            sound.setTexture(soundEnabledTexture);
        }
    }

    public static void switchMusicSprites(BasicObject music) {
        if (music.getTexture() == musicEnabledTexture) {
            music.setTexture(musicDisabledTexture);
        } else {
            music.setTexture(musicEnabledTexture);
        }
    }

    public static void disposeAll() {
        texAtlasBedBug.dispose();
        ObjectsStore.dispose();
        bloodTextureMedium.dispose();
        bloodTextureSmall.dispose();
        bloodDotTexture.dispose();
        oilTextureMedium.dispose();
        oilTextureSmall.dispose();
        oilDotTexture.dispose();
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

    }

    private static Texture getRightBloodTex(float bloodSpotLen) {
        if (bloodSpotLen < 6 && bloodSpotLen > 2 )
            return bloodTextureMedium;
        else
            return bloodTextureSmall;
    }

    private static Texture getRightOilTex(float bloodSpotLen) {
        if (bloodSpotLen < 6 && bloodSpotLen > 2 )
            return oilTextureMedium;
        else
            return oilTextureSmall;
    }
}
