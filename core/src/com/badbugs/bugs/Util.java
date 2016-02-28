package com.badbugs.bugs;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class Util {

  public static void createBugCoords(Bug bug) {
    float[][] pixelCords = bug.getBugPixelCoords();

    float[] finalCords = new float[pixelCords.length * 2];
    for (int i = 0; i < finalCords.length; i = i + 2) {

      finalCords[i] = convertXPixelToCameraX(finalCords[i], bug);
      finalCords[i + 1] = convertYPixelToCameraY(finalCords[i + 1], bug);

    }
    bug.setBugCameraCoords(finalCords);
  }

  //  private static float changeX(float x, float y, float angle) {
  //    return (float) (x * Math.cos(angle * Math.PI / 180) - y * Math.sin(angle * Math.PI / 180));
  //  }
  //
  //  private static float changeY(float x, float y, float angle) {
  //    return (float) (x * Math.sin(angle * Math.PI / 180) + y * Math.cos(angle * Math.PI / 180));
  //  }

  private static float convertXPixelToCameraX(float x, Bug bug) {
    return x * ((bug.getCameraDimentions()[0]) / (bug.getPixelDimensions()[0]));
  }

  private static float convertYPixelToCameraY(float y, Bug bug) {
    return y * ((bug.getCameraDimentions()[1]) / (bug.getPixelDimensions()[1]));
  }
}
