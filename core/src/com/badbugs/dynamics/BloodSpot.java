package com.badbugs.dynamics;

import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.ObjectsCord;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Intersector;
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

  public BloodSpot(Bug bug, Knife knife) throws Exception {
    ObjectsStore.add(bug, this);
    this.bug = (BasicObject) bug;
    this.knife = (BasicObject) knife;
    this.bloodSprite = SpritesCreator.loadBloodSpot();
    ObjectsStore.add(this, bloodSprite);

    //    updateBloodSpotDimensions();
    elapsedTime = 0;
  }

  public void updateBloodSpotDimensions() throws Exception {

    System.out.println("updateBloodSpotDimensions()");
    if (!isBloodSpotMeasured) {

      Polygon knifePolygon = knife.getPolygon();
      Vector2 tip = Util.getVectorAfterRotation(0,knifePolygon.getOriginY(), knifePolygon.getRotation());
      float tipY = knifePolygon.getY() + tip.y;
      float tipX = knifePolygon.getX() + tip.x;
      bloodSprite.getPolygon()
          .setPosition(tipX, tipY);

      float angle = knife.getPolygon().getRotation();

      bloodSprite.getPolygon().setRotation(angle);

      Vector2 vector2 = new Vector2(tipX,
          tipY);

      float bloodSpotLength = 0;

      float[] bugVertices = bug.getPolygon().getTransformedVertices();

      while (Intersector.isPointInPolygon(bugVertices, 0, bugVertices.length - 1, vector2.x, vector2.y)) {
        isBloodSpotMeasured = true;

        System.out.println("Point vector2 " + vector2 + " and angle " + angle + " is inside polygon.");

        vector2.set((float) (vector2.x + 0.01 * Math.cos(Math.toRadians(angle))),
            (float) (vector2.y + 0.01 * Math.sin(Math.toRadians(angle))));

        bloodSpotLength = getBloodLength(bloodSprite, vector2);
        if(bloodSpotLength > 8)
          break;
      }

      bloodSprite.setCameraDimensions(new float[] { bloodSpotLength, ObjectsCord.BLOOD_SPOT_WIDTH });
      bloodSprite.getPolygon()
          .setOrigin(bloodSprite.getCameraDimensions()[0] / 2, bloodSprite.getCameraDimensions()[1] / 2);
    }
  }

  private float getBloodLength(BloodSprite bloodSprite, Vector2 vector2) throws Exception
  {
    float bloodSpotLength = (float) Math.sqrt(Math.pow(bloodSprite.getPolygon().getX() - vector2.x, 2) + Math
        .pow(bloodSprite.getPolygon().getY() - vector2.y, 2));
    return bloodSpotLength;

  }

  public static void main(String[] args) {
    System.out.println(Math.sin(-116));
  }
}
