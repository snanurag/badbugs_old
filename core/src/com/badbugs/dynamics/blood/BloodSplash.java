package com.badbugs.dynamics.blood;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 7/6/2016.
 */
public class BloodSplash {

    private static final float MAX_RADIUS = 4;
    private static final float MIN_RADIUS = 2;
    private static final int MAX_BLOOD_STRIPES = 5;
    private static final int MIN_BLOOD_STRIPES = 2;
    private static final int BLOOD_DOTS_PER_STRIPE = 20;

    private int bloodStrips;
    private float splashRadius;
    private Vector2 bloodSpotCenter;
    private float bloodLength;
    private float angle;
    private List<List<BasicObject>> listOfBloodSprites = new ArrayList<List<BasicObject>>();

    public BloodSplash(Vector2 bloodSpotCenter, float bloodLength, Knife knife) throws Exception {
        this.bloodSpotCenter = bloodSpotCenter;
        this.bloodLength = bloodLength;
        this.angle = knife.getPolygon().getRotation();
        init();
    }

    public List<List<BasicObject>> getListOfBloodSprites() {
        return listOfBloodSprites;
    }

    private void init() throws Exception {

        Vector2 centerAfterRotation = Util
                .rotateVectorByGivenAngle(bloodLength / 2, 0, angle);
        bloodSpotCenter.set(bloodSpotCenter.x , bloodSpotCenter.y );

        bloodStrips = (int) (bloodLength * MAX_BLOOD_STRIPES / Constants.MAX_BLOOD_LENGTH);
        if (bloodStrips < MIN_BLOOD_STRIPES)
            bloodStrips = MIN_BLOOD_STRIPES;

        splashRadius = (int) (bloodLength * MAX_RADIUS / Constants.MAX_BLOOD_LENGTH);
        if (splashRadius < MIN_RADIUS)
            splashRadius = MIN_RADIUS;

        for (int i = 0; i < bloodStrips; i++) {
            List<BasicObject> bloodSpriteList = new ArrayList<BasicObject>();
            listOfBloodSprites.add(bloodSpriteList);
            createBloodSprites(bloodSpriteList);
        }
    }

    private void createBloodSprites(List<BasicObject> bloodSpriteList) throws Exception {
        float angle = (float) Math.random() * 360;
        for (int i = 0; i < BLOOD_DOTS_PER_STRIPE; i++) {
            Vector2 v = Util.rotateVectorByGivenAngle(splashRadius * logDistributionFactor(), 0, angle);
            BasicObject bloodDot = SpritesCreator.loadBloodDot();
            bloodDot.getPolygon().setPosition(bloodSpotCenter.x + v.x - Constants.BLOOD_DOT_RADIUS, bloodSpotCenter.y + v.y - Constants.BLOOD_DOT_RADIUS);
            bloodDot.setCameraDimensions(new float[]{2 * Constants.BLOOD_DOT_RADIUS, 2 * Constants.BLOOD_DOT_RADIUS});
            bloodSpriteList.add(bloodDot);
        }
    }

    private float logDistributionFactor() {
        double x = Math.random();
        double randomN = -x * Math.log(1 - x) / 2;
        if (randomN > 1)
            return 1;
        return (float) randomN;
    }

}
