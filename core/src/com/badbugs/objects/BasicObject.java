package com.badbugs.objects;

/**
 * Created by ashrinag on 2/28/2016.
 */
public interface BasicObject {

  public int[][] getPixelCoords();

  public float[] getCameraCoords();

  public int[] getPixelDimensions();

  public float[] getCameraDimentions();

  public void setCameraCoords(float[] coords);

  public int[] getPixelOrigin();

}
