package com.badbugs.objects.bugs;

import com.badbugs.MainClass;
import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.ObjectsCord;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class BedBug extends BasicObjectImpl {

  public BedBug() {

    pixelDimensions = new int[] { ObjectsCord.BED_BUG_CENTER[0] * 2, ObjectsCord.BED_BUG_CENTER[1] * 2 };
    cameraDimensions = new float[] { ObjectsCord.BED_BUG_WIDTH, ObjectsCord.BED_BUG_HEIGHT };
    screenDimensions = new float[] { cameraDimensions[0] * MainClass.screenWidth / MainClass.cam_width,
    cameraDimensions[1] * MainClass.screenHeight / MainClass.cam_height };

    init();
  }

  @Override public int[][] getPixelCoords(){
    return ObjectsCord.BED_BUG_CORDS;
  }

}
