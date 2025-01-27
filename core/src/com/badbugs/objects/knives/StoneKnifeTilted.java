package com.badbugs.objects.knives;

import com.badbugs.Game;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Constants;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/11/2016.
 */
public class StoneKnifeTilted extends Knife {

    public StoneKnifeTilted(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { 0, 0};
        cameraDimensions = new float[] { Constants.STONE_KNIFE_WIDTH/1.414f, Constants.STONE_KNIFE_HEIGHT};
        screenDimensions = new float[] { 0, 0};
        init();
    }

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
