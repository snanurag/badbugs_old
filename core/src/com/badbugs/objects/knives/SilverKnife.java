package com.badbugs.objects.knives;

import com.badbugs.objects.BasicObjectImpl;
import com.badbugs.objects.ObjectsCord;

/**
 * Created by ashrinag on 3/6/2016.
 */
public class SilverKnife extends BasicObjectImpl {

    public SilverKnife() {
        pixelDimensions = new int[]{ObjectsCord.SILVER_KNIFE_CENTER[0] * 2, ObjectsCord.SILVER_KNIFE_CENTER[1] * 2};

        cameraDimensions = new float[]{ObjectsCord.SILVER_KNIFE_WIDTH,
                ObjectsCord.SILVER_KNIFE_HEIGHT};
        pixelOrigin = new int[]{ObjectsCord.SILVER_KNIFE_CENTER[0], ObjectsCord.SILVER_KNIFE_CENTER[1]};
    }
}
