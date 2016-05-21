package com.badbugs.dynamics.movement;

import com.badbugs.Game;
import com.badbugs.dynamics.BloodSpot;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.*;

/**
 * Created by ashrinag on 3/21/2016.
 * <p/>
 * Important : In case of knife, polygon is only used for storing cordinates. There is no calculation based on polygon position. And, image of Knife is pivoted at left bottom corner.
 */
public class KnifeMovement
{

  private static Vector2 directionVector;
  private static double elapsedTime;
  private static float angle;
  private static long lastTime;
  private static float XLimit = Constants.XLimit;
  private static float YLimit = Constants.YLimit;

  private static int counter;

  public static void updatePolygon(SilverKnife basicObject) throws Exception
  {
    rotatePolygon(basicObject);
    translatePolygon(basicObject);
  }

  private static void rotatePolygon(SilverKnife silverKnife) throws Exception
  {

    Polygon polygon = silverKnife.getPolygon();
    TouchInfo touchInfoInstance = Util.getFromTouchEventsQueue();

    if (touchInfoInstance != null)
    {

      Util.globalLogger().info("Touch count " + ++counter);
      Vector3 touchPoints;

      touchPoints = Game.cam.unproject(new Vector3(touchInfoInstance.touchX, touchInfoInstance.touchY, 0));

      //TODO this rotation is w.r.t. left bottom. Convert it w.r.t. tip.
      Vector2 tip = Util.getKnifeTipInWorld(polygon);

      float dirX = touchPoints.x - tip.x;
      float dirY = touchPoints.y - tip.y;

      directionVector = new Vector2((float) (dirX / (Math.sqrt(dirX * dirX + dirY * dirY))),
          (float) (dirY / (Math.sqrt(dirX * dirX + dirY * dirY))));
      elapsedTime = 0;
      lastTime = System.currentTimeMillis();
      float angle = (float) (Math.atan2(directionVector.y, directionVector.x) * 180 / Math.PI);

      polygon.setRotation(silverKnife.getInitialAngle() + angle);

      Vector2 leftBottomFromTip = Util.getLeftBottomFromTipInWorld(tip.x, tip.y, polygon);

      if (!checkIfPointInBoundary(tip.x, tip.y))
      {

        Vector2 v = getVectorInBoundary(tip.x, tip.y);

        leftBottomFromTip = Util.getLeftBottomFromTipInWorld(v.x, v.y, polygon);
      }

      polygon.setPosition(leftBottomFromTip.x, leftBottomFromTip.y);

      setAllBugsState(silverKnife);

      Util.globalLogger().debug("Angle of Knife " + (silverKnife.getInitialAngle() + angle));
      Util.globalLogger().debug("Direction vector " + directionVector);
    }
  }

  private static void translatePolygon(Knife knife) throws Exception
  {

    if (directionVector != null)
    {

      Polygon polygon = knife.getPolygon();

      float speed;
      if (GamePurchaseObserver.isPurchased(Constants.double_speed))
      {
        speed = Constants.SILVER_KNIFE_DOUBLE_SPEED;
      } else
      {
        speed = Constants.SILVER_KNIFE_SPEED;

      }
      float xSpeed = directionVector.x * speed;
      float ySpeed = directionVector.y * speed;

      long thisTime = System.currentTimeMillis();
      elapsedTime = (float) (thisTime - lastTime) / 1000f;
      lastTime = thisTime;
      if (elapsedTime == 0)
      {
        elapsedTime = 0.001;
      }

      Util.globalLogger()
          .debug("Speed of knife xSpeed : " + xSpeed + " ySpeed : " + ySpeed + " elapsedTime : " + elapsedTime);

      float x = (float) ((polygon.getX()) + xSpeed * elapsedTime);
      float y = (float) ((polygon.getY()) + ySpeed * elapsedTime);

      if (!checkIfPointInBoundary(x, y))
      {
        Vector2 v = getVectorInBoundary(x, y);
        x = v.x;
        y = v.y;
        directionVector = null;
      }

      polygon.setPosition(x, y);

      attemptToCutAllBugs(knife);

      Util.globalLogger().debug("Position of Knife tip - x " + x + " y " + y);
    }
  }

  private static Vector2 getVectorInBoundary(float x, float y)
  {

    if (x >= XLimit || x <= -XLimit || y >= YLimit || y <= -YLimit)
    {
      if (x >= XLimit)
      {
        y = y - (x - XLimit) * directionVector.y / directionVector.x;
        x = XLimit;
      } else if (x <= -XLimit)
      {
        y = y - (x + XLimit) * directionVector.y / directionVector.x;
        x = -XLimit;
      }
      if (y >= YLimit)
      {
        x = x - (y - YLimit) * directionVector.x / directionVector.y;
        y = YLimit;
      } else if (y <= -YLimit)
      {
        x = x - (y + YLimit) * directionVector.x / directionVector.y;
        y = -YLimit;
      }
    }

    return new Vector2(x, y);
  }

  private static boolean checkIfPointInBoundary(float x, float y)
  {
    if (x <= XLimit && x >= -XLimit && y <= YLimit && y >= -YLimit)
      return true;
    return false;
  }

  private static void attemptToCreateBlood(Bug bug, Knife knife) throws Exception
  {

    Polygon bugPolygon = bug.getPolygon();
    Polygon knifePolygon = knife.getPolygon();

    Vector2 tip = Util.getKnifeTipInWorld(knifePolygon);

    float a = knifePolygon.getRotation();
    float x1 = tip.x;
    float y1 = tip.y;

    Vector2 hitPoint = getHiPointInXIncreasing(bugPolygon,x1,y1,a);

    //    Renderers.drawLine(x, y, x1, y1);

    if(hitPoint == null)
    {
      hitPoint = getHiPointInXDecreasing(bugPolygon,x1,y1,a);
    }

    //  Renderers.drawLine(x, y, x1, y1);

    if (hitPoint != null)
    {
      if (ObjectsStore.getBloodSpot(bug) == null)
      {
        BloodSpot.createAndStoreBloodSpot(bug, knife, hitPoint);
//        Vector2 bugCenter = Util.getPolygonCenter(bugPolygon);
//        bug.state = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knifePolygon);
      }
    }
  }

  private static Vector2 getHiPointInXIncreasing(Polygon bugPolygon, float x1, float y1, float a)
  {
    return getHitPoint(bugPolygon, x1, y1, a, 0.01f);

  }

  private static Vector2 getHiPointInXDecreasing(Polygon bugPolygon, float x1, float y1, float a)
  {
    return getHitPoint(bugPolygon, x1, y1, a, -0.01f);

  }

  private static Vector2 getHitPoint(Polygon bugPolygon, float x1, float y1, float a, float delta)
  {
    float x = x1 + delta;
    float y = getYOnLine(x, x1, y1, a);

    Vector2 hitPoint = null;

    //These boundaries are on left bottom position of knife. Calibrating them here on tip.
    while (checkIfPointInBoundary(x, y))
    {
      x = x + delta;
      y = getYOnLine(x, x1, y1, a);
      if (Util.insidePolygon(bugPolygon, x, y))
      {
        hitPoint = new Vector2(x, y);
        break;
      }
    }

    Util.globalLogger().debug("Points to draw line : x " + x + " y " + y + " x1 " + x1 + " y1 " + y1);

    return hitPoint;
  }

  private static float getYOnLine(float x, float x1, float y1, float a)
  {
    float y = MathUtils.sinDeg(a) * (x - x1) / MathUtils.cosDeg(a) + y1;
    return y;
  }

  public float getAngle()
  {
    return angle;
  }

  public static void main(String[] args)
  {
    System.out.println(getYOnLine(-0.45287374f, -50.414356f, -21.956242f, 215.85558f));
  }

  private static void setAllBugsState(Knife knife) throws Exception
  {
    synchronized (ObjectsStore.getBugList())
    {
      for (Bug bug : ObjectsStore.getBugList())
      {
        if (bug.hit)
          continue;

        Util.globalLogger()
            .debug("Bug id " + bug.id + " x " + bug.getPolygon().getX() + " y " + bug.getPolygon().getY());

        Vector2 bugCenter = Util.getPolygonCenter(bug.getPolygon());
        bug.state = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

      }
    }

  }

  private static void attemptToCutAllBugs(Knife knife) throws Exception
  {
    synchronized (ObjectsStore.getBugList())
    {
      for (Bug bug : ObjectsStore.getBugList())
      {
        Vector2 bugCenter = Util.getPolygonCenter(bug.getPolygon());

        Vector2 currentState = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

        if(bug.isStateChanged(currentState.x, currentState.y))
        {
          attemptToCreateBlood(bug, knife);
        }
      }
    }

  }
}
