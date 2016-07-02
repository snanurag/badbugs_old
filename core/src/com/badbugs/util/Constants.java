package com.badbugs.util;

import com.badbugs.Game;
import com.badbugs.objects.ObjectsCord;

/**
 * Created by ashrinag on 3/24/2016.
 */

public class Constants {

    public static final float BLOOD_SPOT_FADE_TIME = 3f;
    public static final float GAME_OVER_FADE_IN_TIME = 2f;
    public static final float MAIN_MENU_SWITCH_TIME = 5f;

    public static final float KNIFE_BOUNDARY_PENETRATION = 1f;
    public static final float GAME_OVER_BACKGROUND_WIDTH = 50;
    public static final float GAME_OVER_BACKGROUND_HEIGHT = 30;
    public static final float GAME_OVER_TEXT_X_POS = -15;
    public static final float GAME_OVER_TEXT_Y_POS = 7;
    public static final float SCORE_TEXT_X_POS = -10;
    public static final float SCORE_TEXT_Y_POS = -5;
    public static final float HOME_SCREEN_W = 2560;
    public static final float HOME_SCREEN_H = 1440;
    public static final float KNIFE_BOOSTER_TEXT_X = -7;
    public static final float KNIFE_BOOSTER_TEXT_Y = 13.5f;
    public static float XLimit = Game.cam_width / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;
    public static float YLimit = Game.cam_height / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;
    public static int MAX_BUG_LEVEL = 4; //starting from 0
    public static float BUG_SPEED[] = {8, 10, 15, 20, 25};
    public static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};
    public static int FREEZE_FRAME_COUNTS = 20;
    public static float COLLISION_AVOIDING_X = Game.cam_width / 2 - ObjectsCord.BED_BUG_WIDTH / 2;
    public static float COLLISION_AVOIDING_Y = Game.cam_height / 2 - ObjectsCord.BED_BUG_HEIGHT / 2;
    public static float SILVER_KNIFE_SPEED = 400;
    public static float SILVER_KNIFE_DOUBLE_SPEED = 2 * SILVER_KNIFE_SPEED;
    public static float SCORE_X_POS = 25 + 5;
    public static float LIFE_1_X_POS = 21 + 5;
    public static float LIFE_2_X_POS = 18 + 5;
    public static float LIFE_3_X_POS = 15 + 5;
    public static float LIFE_4_X_POS = 12 + 5;
    public static float LIFE_5_X_POS = 9 + 5;
    public static float SCORE_SCALE = 0.15f;
    public static float GAME_OVER_SCALE = 0.2f;
    public static float LIFE_SIZE_Y = 4;
    public static float LIFE_SIZE_X = LIFE_SIZE_Y * 11 / 14;
    public static String double_speed = "doublespeed";
    public static int PLAY_H, PLAY_W, SHOP_H, SHOP_W, BACK_H, BACK_W, QUIT_H, QUIT_W, SOUND_H, SOUND_W, MUSIC_H, MUSIC_W, KNIFE_BOOSTER_H, KNIFE_BOOSTER_W;
    public static int PLAY_LEFT, PLAY_TOP, SHOP_LEFT, SHOP_TOP, BACK_LEFT, BACK_TOP, QUIT_LEFT, QUIT_TOP, SOUND_LEFT, SOUND_TOP, MUSIC_LEFT, MUSIC_TOP, KNIFE_BOOSTER_LEFT, KNIFE_BOOSTER_TOP;

    static {
        QUIT_H = QUIT_W = SOUND_W = SOUND_H = MUSIC_H = MUSIC_W = 150;
        QUIT_LEFT = SOUND_LEFT = MUSIC_LEFT = 2308;
        QUIT_TOP = 103;
        SOUND_TOP = 306;
        MUSIC_TOP = 492;
        PLAY_H = SHOP_H = 193;
        PLAY_W = SHOP_W = 484;
        PLAY_LEFT = 668;
        PLAY_TOP = 1048;
        SHOP_LEFT = 1332;
        SHOP_TOP = 1048;
        BACK_H = 116;
        BACK_W = 270;
        BACK_LEFT = 2032;
        BACK_TOP = 1156;

        KNIFE_BOOSTER_W = 938;
        KNIFE_BOOSTER_H = 150;
        KNIFE_BOOSTER_LEFT = 0;
        KNIFE_BOOSTER_TOP = 390;

    }
}
