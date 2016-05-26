package com.badbugs.baseframework;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.ShopScreen;
import com.badbugs.objects.*;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.w3c.dom.Text;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class SpritesCreator
{

  private static Texture knifeTexture;
  private static TextureAtlas textureAtlas;
  //  static Animation bugAnimations;
  static Texture floorTexture;
  private static Texture bloodTextureLong;
  private static Texture bloodTextureMedium;
  private static Texture bloodTextureSmall;
  private static Texture lifeTexture;
  static Texture mainMenuTexture;
  private static Texture gameOverBackgroundTexture;
  private static Texture shopScreenTexture;
  private static Texture soundEnabledTexture;
  private static Texture soundDisabledTexture;
  private static Texture musicEnabledTexture;
  private static Texture musicDisabledTexture;

  static TextureRegion bloodTextureRegionLong;
  static TextureRegion bloodTextureRegionMedium;
  static TextureRegion bloodTextureRegionSmall;

  public static void loadAllTextures()
  {
    knifeTexture = new Texture(Gdx.files.internal("knife.png"));
    textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
    floorTexture = new Texture(Gdx.files.internal("floor.png"));
    lifeTexture = new Texture(Gdx.files.internal("life.png"));
    mainMenuTexture = new Texture(Gdx.files.internal("home_page.png"));
    gameOverBackgroundTexture = new Texture(Gdx.files.internal("game_over_background.png"));
    shopScreenTexture = new Texture(Gdx.files.internal("shop_screen.png"));
    soundEnabledTexture = new Texture(Gdx.files.internal("sound_enabled.png"));
    soundDisabledTexture = new Texture(Gdx.files.internal("sound_disabled.png"));
    musicEnabledTexture = new Texture(Gdx.files.internal("music_enabled.png"));
    musicDisabledTexture = new Texture(Gdx.files.internal("music_disabled.png"));

    //TIP : TextureRegion worked for blood not Texture
    bloodTextureLong = new Texture(Gdx.files.internal("Bloodspot_small_1.png"));
    bloodTextureMedium = new Texture(Gdx.files.internal("Bloodspot_medium_1.png"));
    bloodTextureSmall = new Texture(Gdx.files.internal("Bloodspot_small_1.png"));

    bloodTextureRegionLong = new TextureRegion(bloodTextureLong);
    bloodTextureRegionMedium = new TextureRegion(bloodTextureMedium);
    bloodTextureRegionSmall = new TextureRegion(bloodTextureSmall);
  }

  public static BasicObject loadSilverKnife() throws Exception
  {
    BasicObjectImpl silverKnife = new SilverKnife(knifeTexture);
    silverKnife.getPolygon().setPosition(0, 0);
    silverKnife.getPolygon().setOrigin(0, silverKnife.getCameraDimensions()[1] / 2);
    return silverKnife;
  }

  public static BedBug loadBedBug(int level) throws Exception
  {
    BedBug bedBug = new BedBug(null);
    bedBug.animation = new Animation(Constants.BUG_FRAME_RATE[level], textureAtlas.getRegions());

    bedBug.getPolygon().setPosition(0, 0);
    bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

    return bedBug;
  }

  public static Bug loadLife(float x) throws Exception
  {
    Bug bug = new BedBug(lifeTexture);
    bug.getPolygon().setPosition(x, Game.cam_height / 2 - Constants.LIFE_SIZE_Y);
    bug.setCameraDimensions(new float[] { Constants.LIFE_SIZE_X, Constants.LIFE_SIZE_Y });
    return bug;
  }

  public static GameOver loadGameOverBackground() throws Exception
  {
    GameOver gameOver = new GameOver(gameOverBackgroundTexture);
    gameOver.setCameraDimensions(
        new float[] { Constants.GAME_OVER_BACKGROUND_WIDTH, Constants.GAME_OVER_BACKGROUND_HEIGHT });
    return gameOver;
  }

  public static Shop loadShop() throws Exception
  {
    Shop shopScreen = new Shop(shopScreenTexture);
    shopScreen.setCameraDimensions(new float[] { Game.cam_width, Game.cam_height });
    return shopScreen;
  }

  //TODO : Fix it -> On Main Menu, y axis is working from top to bottom
  public static Sound loadSound() throws Exception
  {
    Sound s = new Sound(soundEnabledTexture);
    s.setCameraDimensions(new float[] { Game.cam_width * Constants.SOUND_W / Constants.HOME_SCREEN_W,
        Game.cam_height * Constants.SOUND_H / Constants.HOME_SCREEN_H });
    float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.SOUND_LEFT) / Constants.HOME_SCREEN_W;
    float y = -Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.SOUND_TOP + Constants.SOUND_H))/ Constants.HOME_SCREEN_H;
    s.getPolygon().setPosition(x, y);

    return s;
  }

  //TODO : Fix it -> On Main Menu, y axis is working from top to bottom
  public static Music loadMusic() throws Exception
  {
    Music m = new Music(musicEnabledTexture);
    m.setCameraDimensions(new float[] { Game.cam_width * Constants.MUSIC_W / Constants.HOME_SCREEN_W,
        Game.cam_height * Constants.MUSIC_H / Constants.HOME_SCREEN_H });
    float x = Game.cam_width * (-Constants.HOME_SCREEN_W / 2 + Constants.MUSIC_LEFT) / Constants.HOME_SCREEN_W;
    float y = -Game.cam_height * (Constants.HOME_SCREEN_H / 2 - (Constants.MUSIC_TOP + Constants.MUSIC_H))
        / Constants.HOME_SCREEN_H;
    m.getPolygon().setPosition(x, y);

    return m;
  }

  public static BloodSprite loadBloodSpot()
  {
    BloodSprite bloodSprite = new BloodSprite(null);
    return bloodSprite;
  }

  public static void disposeAll()
  {
    textureAtlas.dispose();
    knifeTexture.dispose();
    bloodTextureLong.dispose();
    bloodTextureMedium.dispose();
    bloodTextureSmall.dispose();
    mainMenuTexture.dispose();
  }

  public static void switchSoundSprites(Sound sound)
  {
    if (sound.getTexture() == soundEnabledTexture)
    {
      sound.setTexture(soundDisabledTexture);
    } else
    {
      sound.setTexture(soundEnabledTexture);
    }
  }

  public static void switchMusicSprites(Music music)
  {
    if (music.getTexture() == musicEnabledTexture)
    {
      music.setTexture(musicDisabledTexture);
    } else
    {
      music.setTexture(musicEnabledTexture);
    }
  }

}
