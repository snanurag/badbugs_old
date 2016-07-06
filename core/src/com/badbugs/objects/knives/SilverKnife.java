package com.badbugs.objects.knives;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.objects.ObjectsCord;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 3/6/2016.
 * <p>
 * Important : In case of knife, polygon is only used for storing cordinates. There is no calculation based on polygon position.
 */
public class SilverKnife extends Knife {

  public SilverKnife(Texture texture) {
    super(texture);
    pixelDimensions = new int[] { ObjectsCord.SILVER_KNIFE_CENTER[0] * 2, ObjectsCord.SILVER_KNIFE_CENTER[1] * 2 };
    cameraDimensions = new float[] { ObjectsCord.SILVER_KNIFE_WIDTH, ObjectsCord.SILVER_KNIFE_HEIGHT };
    screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
        cameraDimensions[1] * Game.screenHeight / Game.cam_height };
    init();
  }

  @Override public int[][] getPixelCoords() {
    return ObjectsCord.SILVER_KNIFE_CORDS;
  }

}
