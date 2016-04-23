package com.badbugs.util;

import com.badbugs.MainClass;
import com.badbugs.objects.ObjectsCord;

/**
 * Created by ashrinag on 3/24/2016.
 */

public class Constants {

  public static final float BLOOD_SPOT_FADE_TIME = 3f;
  public static final float KNIFE_BOUNDARY_PENETRATION = 1f;
  public static float XLimit = MainClass.cam_width / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;
  public static float YLimit = MainClass.cam_height / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;

  public static int MAX_BUG_LEVEL = 4; //starting from 0
  public static float BUG_SPEED[] = { 8,10, 15, 20, 25 };
  public static float BUG_FRAME_RATE[] = { 1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f };

  public static int FREEZE_FRAME_COUNTS = 20;

  public static float COLLISION_AVOIDING_X = MainClass.cam_width / 2 - ObjectsCord.BED_BUG_WIDTH/2;
  public static float COLLISION_AVOIDING_Y = MainClass.cam_height / 2 - ObjectsCord.BED_BUG_HEIGHT/2;



}
