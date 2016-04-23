package com.badbugs.dynamics.movement;

import com.badbugs.MainClass;
import com.badbugs.objects.ObjectsCord;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
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

    for (Bug bug2 : ObjectsStore.getBugList()) {
      if (bug != bug2) {
        avoidCollisionBetweenBugs(bug, bug2);
      }
    }
  }

  private static void avoidCollisionBetweenBugs(Bug bug1, Bug bug2) throws Exception {
    Vector2 bug1Center = Util.getPolygonCenter(bug1.getPolygon());
    Vector2 bug2Center = Util.getPolygonCenter(bug2.getPolygon());

    if(bug1.hit || bug2.hit || bug1.freeze_frame_count != -1)
      return;


    float distance = Util.distanceBetweenPoints(bug1Center, bug2Center);

    if (bug1Center.x < Constants.COLLISION_AVOIDING_X && bug1Center.x > -Constants.COLLISION_AVOIDING_X
        && bug2Center.x < Constants.COLLISION_AVOIDING_X && bug2Center.x > -Constants.COLLISION_AVOIDING_X
        && bug1Center.y < Constants.COLLISION_AVOIDING_Y && bug1Center.y > -Constants.COLLISION_AVOIDING_Y
        && bug2Center.y < Constants.COLLISION_AVOIDING_Y && bug2Center.y > -Constants.COLLISION_AVOIDING_Y) {
      if (distance < ObjectsCord.BED_BUG_HEIGHT) {
        float bug1SpeedX = bug1.speed * MathUtils.cosDeg(bug1.getPolygon().getRotation() - bug1.getInitialAngle());
        float bug1SpeedY = bug1.speed * MathUtils.sinDeg(bug1.getPolygon().getRotation() - bug1.getInitialAngle());
        float bug2SpeedX = bug2.speed * MathUtils.cosDeg(bug2.getPolygon().getRotation() - bug2.getInitialAngle());
        float bug2SpeedY = bug2.speed * MathUtils.sinDeg(bug2.getPolygon().getRotation() - bug2.getInitialAngle());

        boolean comingCloseAlongX = areComingClose(bug1Center.x, bug2Center.x, bug1SpeedX, bug2SpeedX);
        boolean comingCloseAlongY = areComingClose(bug1Center.y, bug2Center.y, bug1SpeedY, bug2SpeedY);

        if (bug2.freeze_frame_count != -1) {
          bug1.getPolygon().setRotation(bug1.getPolygon().getRotation() + 180);

        } else if (bug1SpeedX * bug2SpeedX < 0 && bug1SpeedY * bug2SpeedY < 0 && comingCloseAlongX
            && comingCloseAlongY) {
          bug1.getPolygon().setRotation(bug1.getPolygon().getRotation() + 180);
          bug2.getPolygon().setRotation(bug2.getPolygon().getRotation() + 180);

        } else if (bug1SpeedX * bug2SpeedX < 0 && comingCloseAlongX) {
          bug1.getPolygon().setRotation(-(bug1.getPolygon().getRotation()));
          bug2.getPolygon().setRotation(-(bug2.getPolygon().getRotation()));

        } else if (bug1SpeedY * bug2SpeedY < 0 && comingCloseAlongY) {
          bug1.getPolygon()
              .setRotation(-(bug1.getPolygon().getRotation() - bug1.getInitialAngle()) + bug1.getInitialAngle());
          bug2.getPolygon()
              .setRotation(-(bug2.getPolygon().getRotation() - bug2.getInitialAngle()) + bug2.getInitialAngle());

        } else if (comingCloseAlongX || comingCloseAlongY) {
          float temp = bug1.speed;
          bug1.speed = bug2.speed;
          bug2.speed = temp;

          temp = bug1.getPolygon().getRotation();
          bug1.getPolygon().setRotation(bug2.getPolygon().getRotation());
          bug2.getPolygon().setRotation(temp);
        }

        bug1.freeze_frame_count++;
        bug2.freeze_frame_count++;
      }

    }

  }

  private static boolean areComingClose(float cord1, float cord2, float speed1, float speed2) {
    float origDist = Math.abs(cord1 - cord2);
    float finalDist = Math.abs(cord1 + speed1 * 0.01f - cord2 - speed2 * 0.01f);

    if (finalDist < origDist) {
      return true;
    }
    return false;
  }

}
