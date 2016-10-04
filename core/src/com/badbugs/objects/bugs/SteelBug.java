package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/27/2016.
 */
public class SteelBug extends Bug {

    //Pixels

    private static int[][] BUG_CORDS = {{136, -316 + 319}, {170, -287 + 319}, {189, -260 + 319}, {198, -240 + 319},
            {202, -224 + 319}, {198, -201 + 319}, {186, -176 + 319}, {186, -123 + 319}, {181, -94 + 319}, {165, -80 +
            319}, {158, -76 + 319}, {158, -59 + 319}, {156, -48 + 319}, {146, -37 + 319}, {133, -35 + 319}, {119, -38
            + 319}, {111, -44 + 319}, {108, -52 + 319}, {107, -60 + 319}, {107, -63 + 319}, {107, -79 + 319}, {97,
            -86 + 319}, {88, -97 + 319}, {84, -106 + 319}, {84, -119 + 319}, {84, -173 + 319}, {71, -197 + 319}, {67,
            -215 + 319}, {68, -231 + 319}, {74, -249 + 319}, {89, -274 + 319}, {118, -305 + 319}};
    //Pixels
    private static int[] BUG_CENTER = { 135, 160 };

    private static float BUG_HEIGHT = 14;
    private static float BUG_WIDTH = 14 * 11 / 14;

    private static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};

    public SteelBug(Texture texture) {
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
