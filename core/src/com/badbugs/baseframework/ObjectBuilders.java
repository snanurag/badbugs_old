package com.badbugs.baseframework;

import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class ObjectBuilders {

  static Texture knifeTexture = new Texture(Gdx.files.internal("knife.png"));
  static TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("sprite.atlas"));
  static Animation bugAnimations = new Animation(1 / 60f, textureAtlas.getRegions());

  public static BasicObject loadSilverKnife() {

    BasicObjectImpl silverKnife = new SilverKnife(knifeTexture);

    ObjectsStore.addBug(silverKnife);

    return silverKnife;

    //    knifeSprite = new Sprite(knifeTexture);

  }

  public static BasicObject loadBedBug()
  {

    BasicObjectImpl bedBug = new BedBug(null);

    ObjectsStore.addBug(bedBug);

    return bedBug;

  }

  public void disposeAll()
  {
    textureAtlas.dispose();
    knifeTexture.dispose();
  }
}
