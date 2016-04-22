package com.badbugs.dynamics.movement;

import com.badbugs.MainClass;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

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

    Vector2 bugCenter = new Vector2(bug.getPolygon().getX() + bug.getPolygon().getOriginX(),
        bug.getPolygon().getY() + bug.getPolygon().getOriginY());

    if (bugCenter.x > MainClass.cam_width / 2 + bug.getCameraDimensions()[0]
        || bugCenter.x < -MainClass.cam_width / 2 - bug.getCameraDimensions()[0]
        || bugCenter.y > MainClass.cam_height / 2 + bug.getCameraDimensions()[1]
        || bugCenter.y < -MainClass.cam_height / 2 - bug.getCameraDimensions()[1]) {

      bug.dead = true;
    }
  }
}
