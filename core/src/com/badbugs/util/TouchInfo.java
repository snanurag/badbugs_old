package com.badbugs.util;

/**
 * Created by ashrinag on 3/6/2016.
 */
public class TouchInfo implements Comparable {

  long time = System.currentTimeMillis();

  @Override public int compareTo(Object another) {
    if(this.time < ((TouchInfo)another).time)
      return -1;
    else
      return 1;
  }

  public float touchX;

  public float touchY;
}
