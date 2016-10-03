package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 9/30/2016.
 */
public abstract class BaseScratch {

    public Vector2 startPoint;
    public Vector2 endPoint;
    private BasicObject bug;
    protected BasicObject knife;
    public float elapsedTime;
    private BasicObject bloodSprite;

    public BaseScratch(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
        this.bug = bug;
        this.knife = knife;
        updateBloodSpotDimensions(hitPoint);
        elapsedTime = 0;

    }

//    public abstract void updateBloodSpotDimensions(Vector2 hitPoint) throws Exception ;

//    public abstract BasicObject getScratchSprite();

    public abstract BasicObject getScratchSprite(float len);
    public abstract float getScratchLen();

    public void updateBloodSpotDimensions(Vector2 hitPoint) throws Exception {

        float bloodSpotLength = getBloodLength(hitPoint);
        float angle = knife.getPolygon().getRotation();

        this.bloodSprite = getScratchSprite(bloodSpotLength);
        bloodSprite.getPolygon().setRotation(angle);
        bloodSprite.setCameraDimensions(new float[]{bloodSpotLength, getScratchLen()});

        // Setting it only for store. Polygon is not used anywhere for calculation.
        bloodSprite.getPolygon()
                .setOrigin(bloodSprite.getCameraDimensions()[0] / 2, bloodSprite.getCameraDimensions()[1] / 2);

        Vector2 centerAfterRotation = Util
                .rotateVectorByGivenAngle(0, bloodSprite.getPolygon().getOriginY(), bloodSprite.getPolygon().getRotation());
        bloodSprite.getPolygon().setPosition(startPoint.x - centerAfterRotation.x, startPoint.y - centerAfterRotation.y);
    }

    protected float getBloodLength(Vector2 hitPoint)throws Exception{
        if (hitPoint == null)
            throw new Exception("Knife did not hit the Bug.");

        float angle = knife.getPolygon().getRotation();

        endPoint = getPointAtPolygonBoundary(bug.getPolygon(), new Vector2(hitPoint.x, hitPoint.y), angle);
        startPoint = getPointAtPolygonBoundary(bug.getPolygon(), new Vector2(hitPoint.x, hitPoint.y), 180 + angle);

        float bloodSpotLength = Util.distanceBetweenPoints(startPoint, endPoint);

        if (bloodSpotLength > Constants.MAX_BLOOD_LENGTH)
            bloodSpotLength = Constants.MAX_BLOOD_LENGTH;

        return bloodSpotLength;
    }

    protected Vector2 getPointAtPolygonBoundary(Polygon polygon, Vector2 startPoint, float angle) {

        if (Util.insidePolygon(polygon, startPoint.x, startPoint.y))
            Util.globalLogger()
                    .debug("Blood spot start point is " + startPoint + " and angle " + angle + " is inside polygon.");

        while (Util.insidePolygon(polygon, startPoint.x, startPoint.y)) {

            startPoint.set((float) (startPoint.x + 0.01 * MathUtils.cosDeg(angle)),
                    (float) (startPoint.y + 0.01 * MathUtils.sinDeg(angle)));
        }
        return startPoint;
    }

    public BasicObject getScratchSprite() {
        return bloodSprite;
    }

}
