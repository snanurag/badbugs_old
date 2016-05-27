package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 5/28/2016.
 */
public class Button extends BasicObjectImpl
{
  public Button(Texture texture)
  {
    super(texture);
    polygon = new Polygon();

  }

  @Override
  public int[][] getPixelCoords() throws Exception
  {
    throw new Exception("Buttons don't need pixel coordinates");
  }
}
