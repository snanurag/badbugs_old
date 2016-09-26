package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/27/2016.
 */
public class LadyBug extends Bug{

    //Pixels
    private static int[][] BED_BUG_CORDS = { { 142, -120 + 256 }, { 129, -108 + 256 }, { 128, -83 + 256 },
            { 116, -68 + 256 }, { 107, -60 + 256 }, { 100, -58 + 256 }, { 93, -60 + 256 }, { 84, -68 + 256 },
            { 72, -83 + 256 }, { 71, -108 + 256 }, { 58, -120 + 256 }, { 48, -140 + 256 }, { 41, -167 + 256 },
            { 50, -206 + 256 }, { 100, -240 + 256 }, { 150, -206 + 256 }, { 159, -167 + 256 }, { 152, -140 + 256 } };

    //Pixels
    private static int[] BED_BUG_CENTER = { 100, 128 };

    private static float BED_BUG_HEIGHT = 12;
    private static float BED_BUG_WIDTH = 12 * 11 / 14;

    private static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};

    public LadyBug(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { BED_BUG_CENTER[0] * 2, BED_BUG_CENTER[1] * 2 };
        cameraDimensions = new float[] { BED_BUG_WIDTH, BED_BUG_HEIGHT };
        screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
                cameraDimensions[1] * Game.screenHeight / Game.cam_height };

        init();
    }

    @Override public int[][] getPixelCoords(){
        return BED_BUG_CORDS;
    }

    public float getInitialAngle()
    {
        return -90;
    }

    @Override
    public float getBugHeight() {
        return BED_BUG_HEIGHT;
    }

    @Override
    public float getBugWidth() {
        return BED_BUG_WIDTH;
    }

    @Override
    public float[] getFrameRate() {
        return BUG_FRAME_RATE;
    }


}
