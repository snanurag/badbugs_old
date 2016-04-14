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

  public static int MAX_BUG_LEVEL = 4; //starting from 0
  public static float BUG_SPEED[] = { 10, 40, 50, 60, 70 };
  public static float BUG_FRAME_RATE[] = { 1 / 60f, 1 / 60f, 1 / 60f, 1 / 60f, 1 / 60f };

}
