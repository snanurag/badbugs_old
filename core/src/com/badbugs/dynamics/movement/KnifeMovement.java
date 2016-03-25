package com.badbugs.dynamics.movement;

import com.badbugs.MainClass;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.ObjectsCord;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 3/21/2016.
 */
public class KnifeMovement {

  static Vector2 directionVector;
  static float elapsedTime;
  static float angle;


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
      //      System.out.println(vector3.x + " " + vector3.y);
      //      System.out.println(touchInfoInstance.touchX + " " + touchInfoInstance.touchY);
      float dirX = touchPoints.x - (polygon.getX() + polygon.getOriginX());
      float dirY = touchPoints.y - (polygon.getY() + polygon.getOriginY());

      directionVector = new Vector2((float) (dirX / (Math.sqrt(dirX * dirX + dirY * dirY))),
          (float) (dirY / (Math.sqrt(dirX * dirX + dirY * dirY))));
      elapsedTime = 0;
      float angle = (float) (Math.atan2(directionVector.y, directionVector.x) * 180 / Math.PI);
      polygon.setRotation(silverKnife.getInitialAngle() + angle);
      System.out.println("Angle of Knife " + (silverKnife.getInitialAngle() + angle));
    }
  }

  private static void translatePolygon(BasicObject basicObject) throws Exception {

    if (directionVector != null) {
      Polygon polygon = basicObject.getPolygon();
      float xSpeed = directionVector.x * ObjectsCord.SILVER_KNIFE_SPEED;
      float ySpeed = directionVector.y * ObjectsCord.SILVER_KNIFE_SPEED;

      elapsedTime += Gdx.graphics.getDeltaTime();
      float tipX = polygon.getX() + xSpeed * elapsedTime;
      float tipY = polygon.getY() + ySpeed * elapsedTime;

      if (tipX < MainClass.cam_width / 2 && tipX > -MainClass.cam_width / 2
          && tipY + polygon.getOriginY() < MainClass.cam_height / 2
          && tipY + polygon.getOriginY() > -MainClass.cam_height / 2) {
        polygon.setPosition(tipX, tipY);

      } else {
        if (tipX >= MainClass.cam_width / 2) {
          tipX = MainClass.cam_width / 2;
          polygon.setPosition(tipX, tipY);
          directionVector = null;
        } else if (tipX <= -MainClass.cam_width / 2) {
          tipX = -MainClass.cam_width / 2;
          polygon.setPosition(tipX, tipY);
          directionVector = null;
        }
        if (tipY + polygon.getOriginY() >= MainClass.cam_height / 2) {
          tipY = MainClass.cam_height / 2;
          polygon.setPosition(tipX, tipY);
          directionVector = null;
        } else if (tipY + polygon.getOriginY() <= -MainClass.cam_height / 2) {
          tipY = -MainClass.cam_height / 2;
          polygon.setPosition(tipX, tipY);
          directionVector = null;
        }
      }

      System.out.println("Position of Knife tip - x " + tipX + " y " + tipY);
      Vector3 pixelTip = MainClass.cam.project(new Vector3(tipX, tipY, 0));
      System.out.println("Position of Knife tip in pixels - x " + pixelTip.x + " y " + pixelTip.y);

    }
  }

  public float getAngle()
  {
    return angle;
  }
//  public static Vector3 getKnifeLastDirection() throws Exception
//  {
//    if (directionVector != null)
//      return directionVector;
//    else
//      throw new Exception("Knife is not moving.");
//  }

}
