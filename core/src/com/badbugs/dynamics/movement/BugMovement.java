package com.badbugs.dynamics.movement;

import com.badbugs.objects.bugs.Bug;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ashrinag on 3/22/2016.
 */
public class BugMovement {

  public static void applyMovement(Bug bug) throws Exception {
    float elapsedTime = Gdx.graphics.getDeltaTime();
    float x =
        bug.getPolygon().getX() + bug.speed * MathUtils.cosDeg(bug.getPolygon().getRotation() - bug.getInitialAngle())
            * elapsedTime;
    float y =
        bug.getPolygon().getY() + bug.speed * MathUtils.sinDeg(bug.getPolygon().getRotation() - bug.getInitialAngle())
            * elapsedTime;

    bug.getPolygon().setPosition(x, y);
  }
}
