package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 5/25/2016.
 */
public class GameOver extends AbstractBasicObject
{
  public GameOver(Texture texture)
  {
    super(texture);
  }

  @Override
  public int[][] getPixelCoords() throws Exception
  {
    throw new Exception("Game over background doesn't require pixel cords");
  }
}
