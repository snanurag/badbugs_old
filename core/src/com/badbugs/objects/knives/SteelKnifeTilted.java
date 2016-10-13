package com.badbugs.objects.knives;

import com.badbugs.Game;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Constants;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 3/6/2016.
 * <p>
 * Important : In case of knife, polygon is only used for storing cordinates. There is no calculation based on polygon position.
 */
public class SteelKnifeTilted extends Knife {

    private float SPEED = 280;
    public SteelKnifeTilted(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { 0,0};
        cameraDimensions = new float[] { Constants.STEEL_KNIFE_WIDTH/1.414f, Constants.STEEL_KNIFE_HEIGHT};
        screenDimensions = new float[] { 0,0 };
        init();
    }

    @Override public int[][] getPixelCoords() {
        return ObjectsCord.STEEL_KNIFE_CORDS;
    }

    public float getInitialAngle() {
        return 180;
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

}
