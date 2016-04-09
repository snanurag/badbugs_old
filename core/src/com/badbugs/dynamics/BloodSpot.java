package com.badbugs.dynamics;

import com.badbugs.MainClass;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.ObjectsCord;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Intersector;
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
  private boolean isBloodSpotMeasured;
  private BloodSprite bloodSprite;
  private static float XLimit = MainClass.cam_width / 2;
  private static float YLimit = MainClass.cam_height / 2;

  public BloodSpot(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
    ObjectsStore.add(bug, this);
    this.bug = (BasicObject) bug;
    this.knife = (BasicObject) knife;
    this.bloodSprite = SpritesCreator.loadBloodSpot();
    ObjectsStore.add(this, bloodSprite);

    updateBloodSpotDimensions(hitPoint);
    elapsedTime = 0;
  }

  public void updateBloodSpotDimensions(Vector2 hitPoint) throws Exception {

    System.out.println("updateBloodSpotDimensions()");

    if (!isBloodSpotMeasured) {

      if (hitPoint == null)
        throw new Exception("Knife did not hit the Bug.");

      float angle = knife.getPolygon().getRotation();

      bloodSprite.getPolygon().setRotation(angle);

      Vector2 endPoint = getPointAtPolygonExtreme(bug.getPolygon(), new Vector2(hitPoint.x, hitPoint.y), angle);
      Vector2 startPoint = getPointAtPolygonExtreme(bug.getPolygon(), new Vector2(hitPoint.x, hitPoint.y), 180 + angle);

      bloodSprite.getPolygon().setPosition(startPoint.x, startPoint.y);

      float bloodSpotLength = getBloodLength(startPoint, endPoint);

      if (bloodSpotLength > 8)
        bloodSpotLength = 8;

      bloodSprite.setCameraDimensions(new float[] { bloodSpotLength, ObjectsCord.BLOOD_SPOT_WIDTH });
      bloodSprite.getPolygon()
          .setOrigin(bloodSprite.getCameraDimensions()[0] / 2, bloodSprite.getCameraDimensions()[1] / 2);
    }
  }

  private Vector2 getPointAtPolygonExtreme(Polygon polygon, Vector2 startPoint, float angle) {

    while (Util.insidePolygon(polygon, startPoint.x, startPoint.y)) {

      isBloodSpotMeasured = true;

      System.out.println("Point vector2 " + startPoint + " and angle " + angle + " is inside polygon.");

      startPoint.set((float) (startPoint.x + 0.01 * MathUtils.cosDeg(angle)),
          (float) (startPoint.y + 0.01 * MathUtils.sinDeg(angle)));
    }
    return startPoint;
  }

  private float getBloodLength(Vector2 startPoint, Vector2 endPoint) throws Exception {
    float bloodSpotLength = (float) Math
        .sqrt(Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2));
    return bloodSpotLength;
  }

  public static void main(String[] args) {
    System.out.println(Math.sin(-116));
  }
}
