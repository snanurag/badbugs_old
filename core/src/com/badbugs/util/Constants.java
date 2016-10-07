package com.badbugs.util;

import com.badbugs.Game;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ashrinag on 3/24/2016.
 */

public class Constants {

    //Demo settings
    public static final boolean DEMO = false;
    public static final int DEMO_BUGS = 30;
    public static final String google_play_uri = "https://play.google.com/store/apps/details?id=com.badbugs2";

    //In-app products
    public static String double_speed = "doublespeed";
    public static String bronze_knife = "bronzeknife";
    public static String steel_knife = "steelknife";
    public static final Set<String> availabaleIdentifiers = new HashSet<String>();

    // time limits
    public static final float BLOOD_SPOT_FADE_TIME = 3f;
    public static final float GAME_OVER_FADE_IN_TIME = 2f;
    public static final float MAIN_MENU_SWITCH_TIME = 5f;

    public static final float KNIFE_BOUNDARY_PENETRATION = 1f;
    public static float XLimit = Game.cam_width / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;
    public static float YLimit = Game.cam_height / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;

    // All related to speeds and frame rates
    public static float BOOSTER = 1.5f;
    public static int MAX_BUG_LEVEL = 4; //starting from 0
    public static float BUG_SPEED[] = {8, 10, 15, 20, 25};
    // orignal
//    public static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};
    public static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 130f, 1 / 150f, 1 / 170f, 1 / 200f};
    public static int FREEZE_FRAME_COUNTS = 20;
    public static int PANEL_SPEED = 50;

    //Text
    public static String BUY_FULL_VERSION = "Buy full version";
    public static enum KNIFE_TYPE{STONE, BRONZE, STEEL}
    public static enum PANEL {
        EMPTY, STONE, BRONZE, STEEL, STONE_BRONZE, BRONZE_STEEL, STONE_STEEL, STONE_BRONZE_STEEL
    }
    public static enum BUG_TYPE{BED, LADY, BLACK, BRONZE, STEEL}

    // ALL X-Y and Width-Height
    public static final float HOME_SCREEN_W = 2560;
    public static final float HOME_SCREEN_H = 1440;
    public static final float MAX_BLOOD_LENGTH = 8;
    public static final float BLOOD_DOT_RADIUS = .25f;
    public static final float GAME_OVER_BACKGROUND_WIDTH = 50;
    public static final float GAME_OVER_BACKGROUND_HEIGHT = 30;
    public static final float GAME_OVER_TEXT_X_POS = -220;
    public static final float GAME_OVER_TEXT_Y_POS = 200;
    public static final float FULL_VER_TEXT_X_POS = -23;
    public static final float FULL_VER_TEXT_Y_POS = 7;
    public static final float SCORE_TEXT_X_POS = -150;
    public static final float SCORE_TEXT_Y_POS = -5;
    public static final float KNIFE_BOOSTER_TEXT_X = -800* 2;
    public static final float KNIFE_BOOSTER_TEXT_Y = 270 *2;
    public static final float BRONZE_KNIFE_TEXT_X = 300* 2;
    public static final float BRONZE_KNIFE_TEXT_Y = 270 *2;
    public static final float STEEL_KNIFE_TEXT_X = -800* 2;
    public static final float STEEL_KNIFE_TEXT_Y = -270 *2;
//    public static float COLLISION_AVOIDING_X = Game.cam_width / 2 - ObjectsCord.BED_BUG_WIDTH / 2;
//    public static float COLLISION_AVOIDING_Y = Game.cam_height / 2 - ObjectsCord.BED_BUG_HEIGHT / 2;
    public static float SCORE_X_POS = 725;
    public static float SCORE_Y_POS = 700;
    public static float LIFE_1_X_POS = 21 + 3;
    public static float LIFE_2_X_POS = 18 + 3;
    public static float LIFE_3_X_POS = 15 + 3;
    public static float LIFE_4_X_POS = 12 + 3;
    public static float LIFE_5_X_POS = 9 + 3;
    public static float LIFE_SIZE_Y = 4;
    public static float LIFE_SIZE_X = LIFE_SIZE_Y * 11 / 14;
    public static float PANEL_WIDTH = 12;
    public static float PANEL_ARROW_WIDTH = 5f;
    public static float PANEL_ARROW_HEIGHT = 5F;
    public static float[] PANEL_STONE_KNIFE = {0f, 2.5f};
    public static float[] PANEL_BRONZE_KNIFE = {0f, 21.5f};
    public static float[] PANEL_STEEL_KNIFE = {0f, 40.5f};

    public static float STEEL_KNIFE_HEIGHT = 2;
    public static float STEEL_KNIFE_WIDTH = 21;
    public static float BRONZE_KNIFE_HEIGHT = 3;
    public static float BRONZE_KNIFE_WIDTH = 17;
    public static float STONE_KNIFE_HEIGHT = 3;
    public static float STONE_KNIFE_WIDTH = 17;

    public static float BLOOD_SPOT_WIDTH = 8.4f;
    public static float METAL_SCRATCH_WIDTH = 1f;

    public static int PLAY_H, PLAY_W, SHOP_H, SHOP_W, BACK_H, BACK_W, QUIT_H, QUIT_W, SOUND_H, SOUND_W, MUSIC_H, MUSIC_W,
            KNIFE_BOOSTER_H, KNIFE_BOOSTER_W, BRONZE_KNIFE_W, BRONZE_KNIFE_H, STEEL_KNIFE_W, STEEL_KNIFE_H, GOOGLE_PLAY_W, GOOGLE_PLAY_H;
    public static int PLAY_LEFT, PLAY_TOP, SHOP_LEFT, SHOP_TOP, BACK_LEFT, BACK_TOP, QUIT_LEFT, QUIT_TOP, SOUND_LEFT,
            SOUND_TOP, MUSIC_LEFT, MUSIC_TOP, KNIFE_BOOSTER_LEFT, KNIFE_BOOSTER_TOP, BRONZE_KNIFE_LEFT, BRONZE_KNIFE_TOP, STEEL_KNIFE_LEFT, STEEL_KNIFE_TOP,
            GOOGLE_PLAY_LEFT, GOOGLE_PLAY_TOP;

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

        KNIFE_BOOSTER_W = 700;
        KNIFE_BOOSTER_H = 90;
        KNIFE_BOOSTER_LEFT = 450;
        KNIFE_BOOSTER_TOP = 290;

        BRONZE_KNIFE_W = 700;
        BRONZE_KNIFE_H = 90;
        BRONZE_KNIFE_LEFT = 1500;
        BRONZE_KNIFE_TOP = 290;

        STEEL_KNIFE_W = 700;
        STEEL_KNIFE_H = 90;
        STEEL_KNIFE_LEFT = 450;
        STEEL_KNIFE_TOP = 830;

        GOOGLE_PLAY_W = 566;
        GOOGLE_PLAY_H = 170;
        GOOGLE_PLAY_LEFT = 1240;
        GOOGLE_PLAY_TOP = 760;
    }
}
