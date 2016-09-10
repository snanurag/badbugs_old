package com.badbugs.objects.knives;

import com.badbugs.objects.AbstractBasicObject;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 3/24/2016.
 */
public abstract class Knife extends AbstractBasicObject {

  public Knife(Texture texture) {
    super(texture);
  }

  public abstract float getInitialAngle();

}
