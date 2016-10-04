package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/27/2016.
 */
public class BronzeBug extends Bug{

    //Pixels
//    private static int[][] BUG_CORDS = { { 142, -120 + 256 }, { 129, -108 + 256 }, { 128, -83 + 256 },
//            { 116, -68 + 256 }, { 107, -60 + 256 }, { 100, -58 + 256 }, { 93, -60 + 256 }, { 84, -68 + 256 },
//            { 72, -83 + 256 }, { 71, -108 + 256 }, { 58, -120 + 256 }, { 48, -140 + 256 }, { 41, -167 + 256 },
//            { 50, -206 + 256 }, { 100, -240 + 256 }, { 150, -206 + 256 }, { 159, -167 + 256 }, { 152, -140 + 256 } };
    private static int[][] BUG_CORDS = {{157, -481 + 485}, {177, -481 + 485}, {205, -466 + 485}, {222, -445 + 485},
            {229, -421 + 485}, {225, -371 + 485}, {223, -323 + 485}, {218, -301 + 485}, {216, -257 + 485}, {210, -231
            + 485}, {173, -205 + 485}, {155, -199 + 485}, {131, -226 + 485}, {107, -233 + 485}, {93, -254 + 485},
            {93, -297 + 485}, {93, -320 + 485}, {89, -379 + 485}, {85, -420 + 485}, {94, -445 + 485}, {128, -479 + 485}};

    //Pixels
    private static int[] BUG_CENTER = { 157, 242 };

    private static float BUG_HEIGHT = 14;
    private static float BUG_WIDTH = 14 * 11 / 14;

    private static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};

    public BronzeBug(Texture texture) {
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
