package com.badbugs.objects.knives;

import com.badbugs.Game;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Constants;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/11/2016.
 */
public class StoneKnife extends Knife {

    public StoneKnife(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { ObjectsCord.STONE_KNIFE_CENTER[0] * 2, ObjectsCord.STONE_KNIFE_CENTER[1] * 2 };
        cameraDimensions = new float[] { Constants.STONE_KNIFE_WIDTH, Constants.STONE_KNIFE_HEIGHT};
        screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
                cameraDimensions[1] * Game.screenHeight / Game.cam_height };
        init();
    }

    private float SPEED = 400;
    @Override public int[][] getPixelCoords() {
        return ObjectsCord.STONE_KNIFE_CORDS;
    }

    public float getInitialAngle() {
        return 180;
    }

    @Override
    public float getSpeed() {
        return 400;
    }

}
