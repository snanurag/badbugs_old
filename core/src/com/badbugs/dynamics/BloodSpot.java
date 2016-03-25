package com.badbugs.dynamics;

import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/22/2016.
 */
public class BloodSpot {

  private BasicObject bug;
  private float elapsedTime;

  public BloodSpot(Bug bug, float angle) throws Exception {
    ObjectsStore.add(bug, this);
    this.bug = (BasicObject) bug;
    updateBloodSpotDimensions(angle);
    elapsedTime = 0;
  }

  public void spill(SpriteBatch batch) throws Exception {
    elapsedTime += Gdx.graphics.getDeltaTime();

    Renderers.renderBlood(batch, ObjectsStore.getBloodSprite(this), elapsedTime);

    if (elapsedTime > Constants.BLOOD_SPOT_FADE_TIME) {
      ObjectsStore.removeBloodSprite(this);
      ObjectsStore.removeBlood((Bug) this.bug);
    }
  }

  private void updateBloodSpotDimensions(float angle) throws Exception {
    BloodSprite bloodSprite = SpritesCreator.loadBloodSpot();
    bloodSprite.getPolygon().setPosition(this.bug.getPolygon().getX() + bug.getCameraDimensions()[0] / 2,
        this.bug.getPolygon().getY() + bug.getCameraDimensions()[1] / 2);
    bloodSprite.getPolygon().setRotation(angle);
    ObjectsStore.add(this, bloodSprite);

    Vector2 vector2 = new Vector2(bloodSprite.getPolygon().getX(), bloodSprite.getPolygon().getY());
    float bloodSpotLength = 0;
    float[] bugVertices = bug.getPolygon().getTransformedVertices();
    while (Intersector.isPointInPolygon(bugVertices, 0, bugVertices.length - 1, vector2.x, vector2.y)) {
      vector2.set((float) (vector2.x + 0.2 * Math.cos(angle)), (float) (vector2.y + 0.2 * Math.sin(angle)));
    }
    bloodSpotLength = 2 * (float) Math.sqrt(Math.pow(bloodSprite.getPolygon().getX() - vector2.x, 2) + Math
        .pow(bloodSprite.getPolygon().getY() - vector2.y, 2));
    bloodSprite.setCameraDimensions(new float[] { bloodSpotLength, 2 });
  }

}
