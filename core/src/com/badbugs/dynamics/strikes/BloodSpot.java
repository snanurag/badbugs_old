package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/22/2016.
 */
public class BloodSpot extends BaseScratch {

    private BasicObject bloodSprite;

    public BloodSpot(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
        super(bug, knife, hitPoint);
    }

    public void updateBloodSpotDimensions(Vector2 hitPoint) throws Exception {

        float bloodSpotLength = getBloodLength(hitPoint);
        float angle = knife.getPolygon().getRotation();

        this.bloodSprite = SpritesCreator.getBloodSpot(bloodSpotLength);
        bloodSprite.getPolygon().setRotation(angle);
        bloodSprite.setCameraDimensions(new float[]{bloodSpotLength, Constants.BLOOD_SPOT_WIDTH});

        // Setting it only for store. Polygon is not used anywhere for calculation.
        bloodSprite.getPolygon()
                .setOrigin(bloodSprite.getCameraDimensions()[0] / 2, bloodSprite.getCameraDimensions()[1] / 2);

        Vector2 centerAfterRotation = Util
                .rotateVectorByGivenAngle(0, bloodSprite.getPolygon().getOriginY(), bloodSprite.getPolygon().getRotation());
        bloodSprite.getPolygon().setPosition(startPoint.x - centerAfterRotation.x, startPoint.y - centerAfterRotation.y);
    }

    public BasicObject getScratchSprite() {
        return bloodSprite;
    }
}
