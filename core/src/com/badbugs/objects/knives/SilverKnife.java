package com.badbugs.objects.knives;

import com.badbugs.MainClass;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.ObjectsCord;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/6/2016.
 */
public class SilverKnife extends BasicObjectImpl implements Knife{

  public SilverKnife(Texture texture) {
    super(texture);
    pixelDimensions = new int[] { ObjectsCord.SILVER_KNIFE_CENTER[0] * 2, ObjectsCord.SILVER_KNIFE_CENTER[1] * 2 };
    cameraDimensions = new float[] { ObjectsCord.SILVER_KNIFE_WIDTH, ObjectsCord.SILVER_KNIFE_HEIGHT };
    screenDimensions = new float[] { cameraDimensions[0] * MainClass.screenWidth / MainClass.cam_width,
    cameraDimensions[1] * MainClass.screenHeight / MainClass.cam_height };
    init();
  }

  @Override public int[][] getPixelCoords(){
    return ObjectsCord.SILVER_KNIFE_CORDS;
  }

  public int getInitialAngle()
  {
    return 180;
  }

}
