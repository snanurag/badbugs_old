package com.badbugs.util;

import com.badbugs.Game;
import com.badbugs.objects.BasicObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class Util
{

  private static Queue<TouchInfo> touchEventsQueue = new PriorityQueue<TouchInfo>();

  private static Logger logger = new Logger("com.badbugs", Logger.DEBUG);

  public static void createCameraCoordsFromPixelCords(BasicObject basicObject) throws Exception
  {

    int[][] pixelCords = null;

    try
    {
      pixelCords = basicObject.getPixelCoords();
    } catch (Exception e)
    {
      throw new Exception("Conversion from pixel to camera cordinates are not required for this object.", e);
    }
    if (pixelCords != null)
    {
      float[] finalCords = new float[pixelCords.length * 2];
      for (int i = 0; i < finalCords.length; i = i + 2)
      {

        finalCords[i] = convertXPixelToCameraX(pixelCords[i / 2][0], basicObject);
        finalCords[i + 1] = convertYPixelToCameraY(pixelCords[i / 2][1], basicObject);

      }
      basicObject.setCameraCoords(finalCords);
    }
  }

  private static float convertXPixelToCameraX(float x, BasicObject basicObject)
  {
    return x * ((basicObject.getCameraDimensions()[0]) / (basicObject.getPixelDimensions()[0]));
  }

  private static float convertYPixelToCameraY(float y, BasicObject basicObject)
  {
    return y * ((basicObject.getCameraDimensions()[1]) / (basicObject.getPixelDimensions()[1]));
  }

  public static void addToTouchEventsQueue(TouchInfo info)
  {
    touchEventsQueue.add(info);
  }

  public static TouchInfo getFromTouchEventsQueue()
  {
    return touchEventsQueue.poll();
  }

  @Deprecated
  public static void createScreenCordsFromCameraCords(BasicObject basicObject)
  {

    float[] cameraCords = basicObject.getCameraCoords();
    float[] screenCords = new float[cameraCords.length];
    for (int i = 0; i < cameraCords.length; i = i + 2)
    {
      screenCords[i] = cameraCords[i] * Game.screenWidth / Game.cam_width;
      screenCords[i + 1] = cameraCords[i + 1] * Game.screenHeight / Game.cam_height;
    }
    basicObject.setScreenPixels(screenCords);
  }

  public static Vector2 rotateVectorByGivenAngle(float originX, float originY, float rotation)
  {
    final float cos = MathUtils.cosDeg(rotation);
    final float sin = MathUtils.sinDeg(rotation);

    float x1 = cos * originX - sin * originY;
    float y1 = sin * originX + cos * originY;

    return new Vector2(x1, y1);
  }

  public static Vector2 getKnifeTipInWorld(Polygon knifePolygon)
  {
    Vector2 tip = Util.rotateVectorByGivenAngle(0, knifePolygon.getOriginY(), knifePolygon.getRotation());
    float tipY = knifePolygon.getY() + tip.y;
    float tipX = knifePolygon.getX() + tip.x;

    return new Vector2(tipX, tipY);
  }

  public static Vector2 getLeftBottomWrtTip(float diffX, float diffY, Polygon knifePolygon)
  {
    float x = knifePolygon.getX()  + diffX;
    float y = knifePolygon.getY() + diffY;

    return new Vector2(x, y);
  }

  public static Vector2 getStateOfBugWRTKnife(float x, float y, Polygon knifePolygon)
  {
    Vector2 tip = Util.getKnifeTipInWorld(knifePolygon);
    Vector2 state = new Vector2();
    state.x = (tip.x - x < 0) ? -1 : 1;
    state.y = (tip.y - y < 0) ? -1 : 1;

    return state;
  }

  public static Vector2 getPolygonCenter(Polygon polygon)
  {
    return new Vector2(polygon.getX() + polygon.getOriginX(), polygon.getY() + polygon.getOriginY());
  }

  public static boolean insidePolygon(Polygon polygon, float x, float y)
  {
    return Intersector
        .isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, x, y);
  }

  public static float distanceBetweenPoints(Vector2 polygon1, Vector2 polygon2)
  {
    return (float) Math.sqrt(Math.pow(polygon1.x - polygon2.x, 2) + Math.pow(polygon1.y - polygon2.y, 2));
  }

  public static Logger globalLogger()
  {
    return logger;
  }

  public static boolean checkIfGameOverConditionMet()
  {
    if(ObjectsStore.bugMissed >= 5)
    {
      Gdx.input.setInputProcessor(null);
      return true;
    }
    return false;
  }

}
