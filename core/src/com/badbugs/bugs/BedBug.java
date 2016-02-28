package com.badbugs.bugs;

import com.badbugs.util.BugsCord;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class BedBug implements Bug {

//  @Override public void setRotation() {
//
//  }

  public BedBug(){
    Util.createBugCoords(this);
  }
  private static float[] pixelDimensions = {200, 256};
  private static float[] cameraDimensions = {20 * 11 / 14,
      20};
  private static float[] pixelOrigin = {100,128};
  private static float[] finalCords;

  @Override public float[][] getBugPixelCoords() {
    return BugsCord.BED_BUG_CORDS;
  }

  @Override public float[] getBugCameraCoords() {
    return finalCords;
  }

  @Override public float[] getPixelDimensions() {
    return pixelDimensions;
  }

  @Override public float[] getCameraDimentions() {
    return cameraDimensions;
  }

  @Override public void setBugCameraCoords(float[] coords) {
    finalCords = coords;
  }

  @Override public float[] getBugPixelOrigin() {
    return pixelOrigin;
  }
}
