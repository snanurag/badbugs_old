package com.badbugs.objects.bugs;

import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.ObjectsCord;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class BedBug extends BasicObjectImpl {


    public BedBug() {

        pixelDimensions = new int[]{ObjectsCord.BED_BUG_CENTER[0] * 2, ObjectsCord.BED_BUG_CENTER[1] * 2};

        cameraDimensions = new float[]{ObjectsCord.BED_BUG_WIDTH,
                ObjectsCord.BED_BUG_HEIGHT};
        pixelOrigin = new int[]{ObjectsCord.BED_BUG_CENTER[0], ObjectsCord.BED_BUG_CENTER[1]};
    }


}
