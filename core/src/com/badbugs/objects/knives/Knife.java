package com.badbugs.objects.knives;

import com.badbugs.objects.BasicObjectImpl;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 3/24/2016.
 */
public abstract class Knife extends BasicObjectImpl {

  public Knife(Texture texture) {
    super(texture);
  }

  public abstract float getInitialAngle();

}
