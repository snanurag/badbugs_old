package com.badbugs.baseframework;

import com.badbugs.MainClass;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
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
  static Animation bugAnimations;
  static Texture floorTexture;
  static TextureRegion bloodTextureRegion;

  public static void loadAllTextures()
  {
    knifeTexture = new Texture(Gdx.files.internal("knife.png"));
    textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
    bugAnimations = new Animation(1 / 60f, textureAtlas.getRegions());
    floorTexture = new Texture(Gdx.files.internal("floor.png"));

    //TIP : TextureRegion worked for blood not Texture
    Texture bloodTexture = new Texture(Gdx.files.internal("data/Bloodspot3.png"));
    bloodTextureRegion = new TextureRegion(bloodTexture);
  }

  public static BasicObject loadSilverKnife() throws Exception {

    BasicObjectImpl silverKnife = new SilverKnife(knifeTexture);
//    ObjectsStore.addBug(silverKnife);
    silverKnife.getPolygon().setPosition(0 , 0);
    silverKnife.getPolygon().setOrigin(0,silverKnife.getCameraDimensions()[1]/2);
    return silverKnife;

    //    knifeSprite = new Sprite(knifeTexture);

  }

  public static BedBug loadBedBug() throws Exception {

    BedBug bedBug = new BedBug(null);

  //  ObjectsStore.addBug(bedBug);
    bedBug.getPolygon().setPosition(-MainClass.cam_width / 2, -MainClass.cam_height / 2);
    bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

    return bedBug;

  }

  public static BloodSprite loadBloodSpot()
  {
    BloodSprite bloodSprite = new BloodSprite(bloodTextureRegion.getTexture());

    return bloodSprite;
  }

  public static void disposeAll()
  {
    textureAtlas.dispose();
    knifeTexture.dispose();
    bloodTextureRegion.getTexture().dispose();
  }
}
