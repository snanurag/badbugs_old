package com.badbugs.objects.knives;

import com.badbugs.Game;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Constants;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/11/2016.
 */
public class BronzeKnifeTilted extends Knife{

    private float SPEED = 340;
    public BronzeKnifeTilted(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { 0,0};
        cameraDimensions = new float[] { Constants.BRONZE_KNIFE_WIDTH/1.414f, Constants.BRONZE_KNIFE_HEIGHT};
        screenDimensions = new float[] {0,0};
        init();
    }

    @Override public int[][] getPixelCoords() {
        return ObjectsCord.BRONZE_KNIFE_CORDS;
    }

    public float getInitialAngle() {
        return 180;
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }
}
