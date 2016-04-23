package com.badbugs.baseframework;

import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class SpritesCreator {

  static Texture knifeTexture;
  static TextureAtlas textureAtlas;
//  static Animation bugAnimations;
  static Texture floorTexture;
  static Texture bloodTextureLong;
  static Texture bloodTextureMedium;
  static Texture bloodTextureSmall;

  static TextureRegion bloodTextureRegionLong;
  static TextureRegion bloodTextureRegionMedium;
  static TextureRegion bloodTextureRegionSmall;

  public static void loadAllTextures() {
    knifeTexture = new Texture(Gdx.files.internal("knife.png"));
    textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
    floorTexture = new Texture(Gdx.files.internal("floor.png"));

    //TIP : TextureRegion worked for blood not Texture
    bloodTextureLong = new Texture(Gdx.files.internal("Bloodspot_small_1.png"));
    bloodTextureMedium = new Texture(Gdx.files.internal("Bloodspot_medium_1.png"));
    bloodTextureSmall = new Texture(Gdx.files.internal("Bloodspot_small_1.png"));

    bloodTextureRegionLong = new TextureRegion(bloodTextureLong);
    bloodTextureRegionMedium = new TextureRegion(bloodTextureMedium);
    bloodTextureRegionSmall = new TextureRegion(bloodTextureSmall);
  }

  public static BasicObject loadSilverKnife() throws Exception {

    BasicObjectImpl silverKnife = new SilverKnife(knifeTexture);
    silverKnife.getPolygon().setPosition(0, 0);
    silverKnife.getPolygon().setOrigin(0, silverKnife.getCameraDimensions()[1] / 2);
    return silverKnife;
  }

  public static BedBug loadBedBug() throws Exception {

    BedBug bedBug = new BedBug(null);

    bedBug.getPolygon().setPosition(0, 0);
    bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

    return bedBug;
  }

  public static BedBug loadBedBug(int level) throws Exception {

    BedBug bedBug = new BedBug(null);
    bedBug.animation = new Animation(Constants.BUG_FRAME_RATE[level], textureAtlas.getRegions());

    bedBug.getPolygon().setPosition(0, 0);
    bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

    return bedBug;
  }

  public static BloodSprite loadBloodSpot() {

    BloodSprite bloodSprite = new BloodSprite(null);

    return bloodSprite;
  }

  public static void disposeAll() {
    textureAtlas.dispose();
    knifeTexture.dispose();
    bloodTextureLong.dispose();
    bloodTextureMedium.dispose();
    bloodTextureSmall.dispose();
  }

}
