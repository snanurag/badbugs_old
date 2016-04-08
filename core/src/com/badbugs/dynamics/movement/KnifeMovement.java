package com.badbugs.dynamics.movement;

import com.badbugs.MainClass;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.ObjectsCord;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 3/21/2016.
 */
public class KnifeMovement {

  static Vector2 directionVector;
  static double elapsedTime;
  static float angle;
  static long lastTime;
  private static float XLimit = MainClass.cam_width / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;
  private static float YLimit = MainClass.cam_height / 2 + Constants.KNIFE_BOUNDARY_PENETRATION;

  public static void updatePolygon(SilverKnife basicObject) throws Exception {
    rotatePolygon(basicObject);
    translatePolygon(basicObject);
  }

  private static void rotatePolygon(SilverKnife silverKnife) throws Exception {

    Polygon polygon = silverKnife.getPolygon();
    TouchInfo touchInfoInstance = Util.getFromTouchEventsQueue();

    if (touchInfoInstance != null) {

      Vector3 touchPoints;

      touchPoints = MainClass.cam.unproject(new Vector3(touchInfoInstance.touchX, touchInfoInstance.touchY, 0));

      Vector2 tip = Util.getVectorAfterRotation(0, polygon.getOriginY(), polygon.getRotation());

      float tipX = polygon.getX() + tip.x;
      float tipY = polygon.getY() + tip.y;

      float dirX = touchPoints.x - tipX;
      float dirY = touchPoints.y - tipY;

      directionVector = new Vector2((float) (dirX / (Math.sqrt(dirX * dirX + dirY * dirY))),
          (float) (dirY / (Math.sqrt(dirX * dirX + dirY * dirY))));
      elapsedTime = 0;
      lastTime = System.currentTimeMillis();
      float angle = (float) (Math.atan2(directionVector.y, directionVector.x) * 180 / Math.PI);

      polygon.setRotation(silverKnife.getInitialAngle() + angle);

      Vector2 leftBottonInRefToTip = Util.getVectorAfterRotation(0, -polygon.getOriginY(), polygon.getRotation());

      float newX = tipX + leftBottonInRefToTip.x;
      float newY = tipY + leftBottonInRefToTip.y;

      polygon.setPosition(newX, newY);

      if (!checkIfPointInBoundary(newX, newY)) {
        Vector2 v = getVectorInBoundary(newX, newY);
        polygon.setPosition(v.x, v.y);
      }

      System.out.println("Angle of Knife " + (silverKnife.getInitialAngle() + angle));
      System.out.println("Direction vector " + directionVector);
    }
  }

  private static void translatePolygon(BasicObject basicObject) throws Exception {

    if (directionVector != null) {

      Polygon polygon = basicObject.getPolygon();

      float xSpeed = directionVector.x * ObjectsCord.SILVER_KNIFE_DOUBLE_SPEED;
      float ySpeed = directionVector.y * ObjectsCord.SILVER_KNIFE_DOUBLE_SPEED;

      System.out.println("xSpeed : " + xSpeed + " ySpeed : " + ySpeed);

      long thisTime = System.currentTimeMillis();
      elapsedTime = (float) (thisTime - lastTime) / 1000f;
      lastTime = thisTime;
      if (elapsedTime == 0) {
        elapsedTime = 0.001;
      }

      System.out.println("elapsedTime is -> " + elapsedTime);

      float x = (float) ((polygon.getX()) + xSpeed * elapsedTime);
      float y = (float) ((polygon.getY()) + ySpeed * elapsedTime);

      if (!checkIfPointInBoundary(x, y)) {
        Vector2 v = getVectorInBoundary(x, y);
        x = v.x;
        y = v.y;
        directionVector = null;
      }

      polygon.setPosition(x, y);

      System.out.println("Position of Knife tip - x " + x + " y " + y);
      Vector3 pixelTip = MainClass.cam.project(new Vector3(x, y, 0));
      System.out.println("Position of Knife tip in pixels - x " + pixelTip.x + " y " + pixelTip.y);

    }
  }

  private static Vector2 getVectorInBoundary(float x, float y) {

    if (x >= XLimit || x <= -XLimit || y >= YLimit || y <= -YLimit) {
      if (x >= XLimit) {
        y = y - (x - XLimit) * directionVector.y / directionVector.x;
        x = XLimit;
      } else if (x <= -XLimit) {
        y = y - (x + XLimit) * directionVector.y / directionVector.x;
        x = -XLimit;
      }
      if (y >= YLimit) {
        x = x - (y - YLimit) * directionVector.x / directionVector.y;
        y = YLimit;
      } else if (y <= -YLimit) {
        x = x - (y + YLimit) * directionVector.x / directionVector.y;
        y = -YLimit;
      }
    }

    return new Vector2(x, y);
  }

  private static boolean checkIfPointInBoundary(float x, float y) {
    if (x <= XLimit && x >= -XLimit && y <= YLimit && y >= -YLimit)
      return true;
    return false;
  }

  public float getAngle() {
    return angle;
  }

}
