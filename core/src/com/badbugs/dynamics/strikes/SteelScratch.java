package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 10/2/2016.
 */
public class SteelScratch extends BaseScratch{
    private BasicObject bronzeScratch;

    public SteelScratch(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
        super(bug, knife, hitPoint);
    }
//
//    public void updateBloodSpotDimensions(Vector2 hitPoint) throws Exception {
//
//        float bloodSpotLength = getBloodLength(hitPoint);
//        float angle = knife.getPolygon().getRotation();
//
//        this.bronzeScratch = SpritesCreator.getBronzeScratch();
//        bronzeScratch.getPolygon().setRotation(angle);
//        bronzeScratch.setCameraDimensions(new float[]{bloodSpotLength, Constants.BRONZE_SCRATCH_WIDTH});
//
//        // Setting it only for store. Polygon is not used anywhere for calculation.
//        bronzeScratch.getPolygon()
//                .setOrigin(bronzeScratch.getCameraDimensions()[0] / 2, bronzeScratch.getCameraDimensions()[1] / 2);
//
//        Vector2 centerAfterRotation = Util
//                .rotateVectorByGivenAngle(0, bronzeScratch.getPolygon().getOriginY(), bronzeScratch.getPolygon().getRotation());
//        bronzeScratch.getPolygon().setPosition(startPoint.x - centerAfterRotation.x, startPoint.y - centerAfterRotation.y);
//    }
//
//    public BasicObject getScratchSprite() {
//        return bronzeScratch;
//    }

    @Override
    public BasicObject getScratchSprite(float len) {
        return SpritesCreator.getBronzeScratch();
    }

    @Override
    public float getScratchLen() {
        return Constants.BRONZE_SCRATCH_WIDTH;
    }

}
