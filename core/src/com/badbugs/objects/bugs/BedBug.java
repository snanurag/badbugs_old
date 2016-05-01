package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.objects.ObjectsCord;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class BedBug extends Bug {

  public BedBug(Texture texture) {
    super(texture);
    pixelDimensions = new int[] { ObjectsCord.BED_BUG_CENTER[0] * 2, ObjectsCord.BED_BUG_CENTER[1] * 2 };
    cameraDimensions = new float[] { ObjectsCord.BED_BUG_WIDTH, ObjectsCord.BED_BUG_HEIGHT };
    screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
    cameraDimensions[1] * Game.screenHeight / Game.cam_height };

    init();
  }

  @Override public int[][] getPixelCoords(){
    return ObjectsCord.BED_BUG_CORDS;
  }

  public float getInitialAngle()
  {
    return -90;
  }

}
