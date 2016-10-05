package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/27/2016.
 */
public class LadyBug extends Bug{

    //Pixels
    private static int[][] BUG_CORDS = {{210, -433 + 443}, {237, -430 + 443}, {271, -413 + 443}, {303, -388 + 443},
            {333, -352 + 443}, {346, -318 + 443}, {353, -273 + 443}, {347, -211 + 443}, {329, -166 + 443}, {310, -139
            + 443}, {292, -114 + 443}, {285, -83 + 443}, {275, -64 + 443}, {246, -43 + 443}, {208, -20 + 443}, {169,
            -46 + 443}, {145, -64 + 443}, {131, -84 + 443}, {126, -114 + 443}, {107, -141 + 443}, {87, -167 + 443},
            {68, -217 + 443}, {62, -258 + 443}, {68, -311 + 443}, {87, -355 + 443}, {109, -384 + 443}, {148, -415 +
            443}, {182, -431 + 443}};

    //Pixels
    private static int[] BUG_CENTER = { 210, 221 };

    private static float BUG_HEIGHT = 10;
    private static float BUG_WIDTH = 9.5f;

    private static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};

    public LadyBug(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { BUG_CENTER[0] * 2, BUG_CENTER[1] * 2 };
        cameraDimensions = new float[] {BUG_WIDTH, BUG_HEIGHT};
        screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
                cameraDimensions[1] * Game.screenHeight / Game.cam_height };

        init();
    }

    @Override public int[][] getPixelCoords(){
        return BUG_CORDS;
    }

    public float getInitialAngle()
    {
        return -90;
    }

    @Override
    public float getBugHeight() {
        return BUG_HEIGHT;
    }

    @Override
    public float getBugWidth() {
        return BUG_WIDTH;
    }

    @Override
    public float[] getFrameRate() {
        return BUG_FRAME_RATE;
    }


}
