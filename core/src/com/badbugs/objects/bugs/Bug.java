package com.badbugs.objects.bugs;

import com.badbugs.objects.AbstractBasicObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/24/2016.
 * Marker Interface
 */

public abstract class Bug extends AbstractBasicObject {

  public int id;
  public float speed;
  public boolean dead;
  public boolean hit;
  public int hitCount;
  public int freeze_frame_count = -1;
  public Vector2 state;
  public Animation animation;

  public abstract float getInitialAngle();

  public Bug(Texture texture) {
    super(texture);
  }

  public boolean isStateChanged(float x, float y)
  {
    if(state ==null || x == state.x && y == state.y)
    {
      return false;
    }
    return true;
  }

  public abstract float getBugHeight();

  public abstract float getBugWidth();

  public abstract float[] getFrameRate();
}
