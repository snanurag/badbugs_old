package com.badbugs.util;

import com.badbugs.objects.BasicObject;
import com.badlogic.gdx.math.*;
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

  private static boolean musicOn = true;
  private static boolean soundOn = true;

  private static boolean demoOver = false;

  public static boolean isMusicOn() {
    return musicOn;
  }

  public static void switchMusic() {
    if(musicOn)
      musicOn = false;
    else
      musicOn = true;
  }

  public static boolean isSoundOn() {
    return soundOn;
  }

  public static void switchSound(){
    if(soundOn)
      soundOn = false;
    else
      soundOn = true;
  }

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

  /**
   * Use of this method helped in avoid synchronous lock.
   * @return
     */
  public static TouchInfo doTouchEventsQueueEmpty()
  {
    TouchInfo info = null;

    while(touchEventsQueue.peek() != null)
    {
      info = touchEventsQueue.poll();
    }
    return info;
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

  //Moving the touch point up because knife's initial angle is 180 degree.
  public static void moveTouchPtUpByKnifeYOrig(Polygon knifePolygon, Vector3 touchPoint)
  {
    Vector2 shift = Util.rotateVectorByGivenAngle(0, knifePolygon.getOriginY(), knifePolygon.getRotation());
    touchPoint.x = touchPoint.x + shift.x;
    touchPoint.y = touchPoint.y + shift.y;

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
    if(Constants.DEMO)
      return false;

    if(ObjectsStore.bugMissed >= 5)
    {
//      Gdx.input.setInputProcessor(null);
      return true;
    }
    return false;
  }

  public static void startDemo()
  {
    demoOver = false;
  }

  public static void endDemo()
  {
    demoOver = true;
  }

  public static boolean checkIfDemoOver()
  {
    return demoOver;
  }

}
