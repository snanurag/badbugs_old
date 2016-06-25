package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 5/25/2016.
 */
public class Shop extends BasicObjectImpl
{
  public Shop(Texture texture)
  {
    super(texture);
    polygon = new Polygon();
  }

  @Override
  public int[][] getPixelCoords() throws Exception
  {
    throw new Exception("Shop screen background doesn't require pixel cords");
  }
}
