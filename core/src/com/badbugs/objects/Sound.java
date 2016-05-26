package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 5/26/2016.
 */
public class Sound extends BasicObjectImpl
{
  public Sound(Texture texture)
  {
    super(texture);
    polygon = new Polygon();
  }

  @Override
  public int[][] getPixelCoords() throws Exception
  {
    return new int[0][];
  }
}
