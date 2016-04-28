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

  public static float SILVER_KNIFE_SPEED = 400;
  public static float SILVER_KNIFE_DOUBLE_SPEED = 2*SILVER_KNIFE_SPEED;

  public static float SCORE_X_POS = 30;

  public static float LIFE_1_X_POS = 26;
  public static float LIFE_2_X_POS = 23;
  public static float LIFE_3_X_POS = 20;
  public static float LIFE_4_X_POS = 17;
  public static float LIFE_5_X_POS = 14;

  public static float LIFE_SIZE_Y = 4;
  public static float LIFE_SIZE_X = LIFE_SIZE_Y*11/14;

}
