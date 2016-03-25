package com.badbugs.baseframework;

import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class SpritesCreator {

  static Texture knifeTexture = new Texture(Gdx.files.internal("knife.png"));
  static TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
  static Animation bugAnimations = new Animation(1 / 60f, textureAtlas.getRegions());

  public static BasicObject loadSilverKnife() throws Exception {

    BasicObjectImpl silverKnife = new SilverKnife(knifeTexture);
//    ObjectsStore.addBug(silverKnife);
    silverKnife.getPolygon().setPosition(0 ,0);
    silverKnife.getPolygon().setOrigin(0,0);
    return silverKnife;

    //    knifeSprite = new Sprite(knifeTexture);

  }

  public static BedBug loadBedBug() throws Exception {

    BedBug bedBug = new BedBug(null);

  //  ObjectsStore.addBug(bedBug);
    bedBug.getPolygon().setOrigin(bedBug.getCameraDimensions()[0] / 2, bedBug.getCameraDimensions()[1] / 2);

    return bedBug;

  }

  public static BloodSprite loadBloodSpot()
  {
    Texture bloodTexture = new Texture(Gdx.files.internal("Bloodspot.png"));
    BloodSprite bloodSprite = new BloodSprite(bloodTexture);

    return bloodSprite;
  }

  public void disposeAll()
  {
    textureAtlas.dispose();
    knifeTexture.dispose();
  }
}
