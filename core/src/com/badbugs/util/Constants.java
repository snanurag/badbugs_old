package com.badbugs.util;

import com.badbugs.MainClass;

/**
 * Created by ashrinag on 3/24/2016.
 */
public class Constants {

  public static final float BLOOD_SPOT_FADE_TIME = 3f;
  public static final float KNIFE_BOUNDARY_PENETRATION = 1f;
  public static float XLimit = MainClass.cam_width / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;
  public static float YLimit = MainClass.cam_height / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;

}
