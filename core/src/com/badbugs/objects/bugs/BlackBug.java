package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/27/2016.
 */
public class BlackBug extends Bug {
    //Pixels
    private static int[][] BUG_CORDS = {{151, -326 + 354}, {171, -323 + 354}, {190, -314 + 354}, {210, -295 + 354},
            {223, -273 + 354}, {230, -244 + 354}, {233, -191 + 354}, {232, -162 + 354}, {227, -140 + 354}, {213, -117
            + 354}, {196, -104 + 354}, {154, -83 + 354}, {112, -103 + 354}, {90, -121 + 354}, {76, -149 + 354}, {71,
            -175 + 354}, {70, -213 + 354}, {78, -257 + 354}, {89, -283 + 354}, {111, -308 + 354}, {134, -321 + 354}};

    //Pixels
    private static int[] BUG_CENTER = { 152, 177 };

    private static float BUG_HEIGHT = 11;
    private static float BUG_WIDTH = 9.5f;

    private static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};

    public BlackBug(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { BUG_CENTER[0] * 2, BUG_CENTER[1] * 2 };
        cameraDimensions = new float[] { BUG_WIDTH, BUG_HEIGHT};
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
