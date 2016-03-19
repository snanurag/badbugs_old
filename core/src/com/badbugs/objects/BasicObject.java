package com.badbugs.objects;

/**
 * Created by ashrinag on 2/28/2016.
 */
public interface BasicObject {

  public int[][] getPixelCoords();

  public float[] getCameraCoords();

  public int[] getPixelDimensions();

  public float[] getCameraDimensions();

  public float[] getScreenPixels();

  public float[] getScreenDimension();

  public void setCameraCoords(float[] coords);

  public void setScreenPixels(float[] coords);

}
