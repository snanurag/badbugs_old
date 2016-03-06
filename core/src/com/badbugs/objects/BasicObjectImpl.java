package com.badbugs.objects;

import com.badbugs.Util;

/**
 * Created by ashrinag on 3/6/2016.
 */
public class BasicObjectImpl implements BasicObject {

    protected static int[] pixelDimensions = {ObjectsCord.BED_BUG_CENTER[0]*2, ObjectsCord.BED_BUG_CENTER[1]*2};

    protected static float[] cameraDimensions = {ObjectsCord.BED_BUG_WIDTH,
            ObjectsCord.BED_BUG_HEIGHT};
    protected static int[] pixelOrigin = {ObjectsCord.BED_BUG_CENTER[0], ObjectsCord.BED_BUG_CENTER[1]};
    protected static float[] finalCords;

    public BasicObjectImpl()
    {
        Util.createCameraCoordsFromPixelCords(this);
    }

    @Override public int[][] getPixelCoords() {
        return ObjectsCord.BED_BUG_CORDS;
    }

    @Override public float[] getCameraCoords() {
        return finalCords;
    }

    @Override public int[] getPixelDimensions() {
        return pixelDimensions;
    }

    @Override public float[] getCameraDimentions() {
        return cameraDimensions;
    }

    @Override public void setCameraCoords(float[] coords) {
        finalCords = coords;
    }

    @Override public int[] getPixelOrigin() {
        return pixelOrigin;
    }

}
