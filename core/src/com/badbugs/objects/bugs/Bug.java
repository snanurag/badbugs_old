package com.badbugs.objects.bugs;

import com.badbugs.objects.BasicObjectImpl;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 3/24/2016.
 * Marker Interface
 */

public abstract class Bug extends BasicObjectImpl {

  public int id;
  public float speed;
  public boolean dead;
  public boolean hit;

  public abstract float getInitialAngle();

  public Bug(Texture texture) {
    super(texture);
  }
}
