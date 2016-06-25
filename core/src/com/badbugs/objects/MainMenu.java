package com.badbugs.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 6/25/2016.
 */
public class MainMenu extends BasicObjectImpl{
    public MainMenu(Texture texture)
    {
        super(texture);
        polygon = new Polygon();
    }

    @Override
    public int[][] getPixelCoords() throws Exception
    {
        throw new Exception("Main menu screen background doesn't require pixel cords");
    }

}
