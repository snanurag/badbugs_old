package com.badbugs.dynamics;

import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.ObjectsCord;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/22/2016.
 */
public class BloodSpot {

  private BasicObject bug;
  public float elapsedTime;
  private BasicObject knife;
  private BloodSprite bloodSprite;

  private BloodSpot(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
    this.bug = bug;
    this.knife = knife;
    this.bloodSprite = SpritesCreator.loadBloodSpot();
    ObjectsStore.add(this, bloodSprite);

    updateBloodSpotDimensions(hitPoint);
    elapsedTime = 0;

  }

  public static void createAndStoreBloodSpot(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
    ObjectsStore.add(bug, new BloodSpot(bug, knife, hitPoint));
  }

  public void updateBloodSpotDimensions(Vector2 hitPoint) throws Exception {

      if (hitPoint == null)
        throw new Exception("Knife did not hit the Bug.");

      float angle = knife.getPolygon().getRotation();

      bloodSprite.getPolygon().setRotation(angle);

      Vector2 endPoint = getPointAtPolygonBoundary(bug.getPolygon(), new Vector2(hitPoint.x, hitPoint.y), angle);
      Vector2 startPoint = getPointAtPolygonBoundary(bug.getPolygon(), new Vector2(hitPoint.x, hitPoint.y), 180 + angle);

      bloodSprite.getPolygon().setPosition(startPoint.x, startPoint.y);

      float bloodSpotLength = Util.distanceBetweenPoints(startPoint, endPoint);

      if (bloodSpotLength > 8)
        bloodSpotLength = 8;

      bloodSprite.setCameraDimensions(new float[] { bloodSpotLength, ObjectsCord.BLOOD_SPOT_WIDTH });
      bloodSprite.getPolygon()
          .setOrigin(bloodSprite.getCameraDimensions()[0] / 2, bloodSprite.getCameraDimensions()[1] / 2);
  }

  private Vector2 getPointAtPolygonBoundary(Polygon polygon, Vector2 startPoint, float angle) {

    if (Util.insidePolygon(polygon, startPoint.x, startPoint.y))
      Util.globalLogger()
          .debug("Blood spot start point is " + startPoint + " and angle " + angle + " is inside polygon.");

    while (Util.insidePolygon(polygon, startPoint.x, startPoint.y)) {

      startPoint.set((float) (startPoint.x + 0.01 * MathUtils.cosDeg(angle)),
          (float) (startPoint.y + 0.01 * MathUtils.sinDeg(angle)));
    }
    return startPoint;
  }

}
