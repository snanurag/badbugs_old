package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 2/28/2016.
 */
public interface BasicObject {

  public int[][] getPixelCoords();

  public float[] getCameraCoords();

  public int[] getPixelDimensions();

  public float[] getCameraDimensions();

  public void setCameraCoords(float[] coords);

  public Texture getTexture();

  public void setTexture(Texture texture);

  public Polygon getPolygon() throws Exception;



  @Deprecated
  public float[] getScreenDimension();

  @Deprecated
  public void setScreenPixels(float[] coords);

  @Deprecated
  public float[] getScreenPixels();
}
