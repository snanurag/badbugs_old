package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 6/25/2016.
 */
public class MainGame extends BasicObjectImpl {
    public MainGame(Texture texture)
    {
        super(texture);
        polygon = new Polygon();
    }

    @Override
    public int[][] getPixelCoords() throws Exception
    {
        throw new Exception("Main Game screen background doesn't require pixel cords");
    }
}
