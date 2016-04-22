package com.badbugs.objects.bugs;

import com.badbugs.objects.BasicObjectImpl;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/24/2016.
 * Marker Interface
 */

public abstract class Bug extends BasicObjectImpl {

  public int id;
  public float speed;
  public boolean dead;
  public boolean hit;
//  public int BUG_POS; //-1 OR 1
  public Vector2 state;

  public abstract float getInitialAngle();

  public Bug(Texture texture) {
    super(texture);
  }

  public boolean compareState(int x, int y)
  {
    if(x == state.x && y == state.y)
    {
      return true;
    }
    return false;
  }

//  public void setState(int x, int y)
//  {
//    state = new Vector2(x,y);
//  }

}
