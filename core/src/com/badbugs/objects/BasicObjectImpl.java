package com.badbugs.objects;

import com.badbugs.Util;

/**
 * Created by ashrinag on 3/6/2016.
 */
public abstract class BasicObjectImpl implements BasicObject {

  protected int[] pixelDimensions; // = { ObjectsCord.BED_BUG_CENTER[0] * 2, ObjectsCord.BED_BUG_CENTER[1] * 2 };
  protected float[] cameraDimensions; // = { ObjectsCord.BED_BUG_WIDTH, ObjectsCord.BED_BUG_HEIGHT };
  protected float[] cameraCords;
  protected float[] screenPixels;
  protected  float[] screenDimensions;

  protected void init()
  {
    Util.createCameraCoordsFromPixelCords(this);

//    Util.createScreenCordsFromCameraCords(this);

  }

  @Override public float[] getCameraCoords() {
    return cameraCords;
  }

  @Override public int[] getPixelDimensions() {
    return pixelDimensions;
  }

  @Override public float[] getCameraDimensions() {
    return cameraDimensions;
  }

  @Override public void setCameraCoords(float[] coords) {
    cameraCords = coords;
  }


  @Override public void setScreenPixels(float[] coords) {
    this.screenPixels = coords;
  }

  @Override public float[] getScreenPixels() {
    return screenPixels;
  }

  @Override public float[] getScreenDimension() {
    return screenDimensions;
  }

}
