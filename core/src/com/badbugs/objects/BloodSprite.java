package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/22/2016.
 */
public class BloodSprite extends BasicObjectImpl{

  public BloodSprite(Texture texture) {
    super(texture);
    polygon = new Polygon();
  }

  @Override public int[][] getPixelCoords() throws Exception {
    throw new Exception("No need of pixesl cords for this type of Image.");
  }

}
