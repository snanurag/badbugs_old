package com.badbugs.bugs;

/**
 * Created by ashrinag on 2/28/2016.
 */
public interface Bug {

  public float[][] getBugPixelCoords();

  public float[] getBugCameraCoords();

  public float[] getPixelDimensions();

  public float[] getCameraDimentions();

  public void setBugCameraCoords(float[] coords);

  public float[] getBugPixelOrigin();

//  public void setRotation();
}
