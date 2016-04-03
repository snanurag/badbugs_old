package com.badbugs.util;

import com.badbugs.MainClass;
import com.badbugs.objects.BasicObject;
import com.badlogic.gdx.math.Polygon;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class Util {

  private static Queue<TouchInfo> touchEventsQueue = new PriorityQueue<TouchInfo>();

  public static void createCameraCoordsFromPixelCords(BasicObject basicObject) throws Exception {

    int[][] pixelCords = null;

    try {
      pixelCords = basicObject.getPixelCoords();
    } catch (Exception e) {
      throw new Exception("Conversion from pixel to camera cordinates are not required for this object.", e);
    }
    if (pixelCords != null) {
      float[] finalCords = new float[pixelCords.length * 2];
      for (int i = 0; i < finalCords.length; i = i + 2) {

        finalCords[i] = convertXPixelToCameraX(pixelCords[i / 2][0], basicObject);
        finalCords[i + 1] = convertYPixelToCameraY(pixelCords[i / 2][1], basicObject);

      }
      basicObject.setCameraCoords(finalCords);
    }
  }

  private static float convertXPixelToCameraX(float x, BasicObject basicObject) {
    return x * ((basicObject.getCameraDimensions()[0]) / (basicObject.getPixelDimensions()[0]));
  }

  private static float convertYPixelToCameraY(float y, BasicObject basicObject) {
    return y * ((basicObject.getCameraDimensions()[1]) / (basicObject.getPixelDimensions()[1]));
  }

  public static void addToTouchEventsQueue(TouchInfo info) {
    touchEventsQueue.add(info);
  }

  public static TouchInfo getFromTouchEventsQueue() {
    return touchEventsQueue.poll();
  }

  @Deprecated public static void createScreenCordsFromCameraCords(BasicObject basicObject) {

    float[] cameraCords = basicObject.getCameraCoords();
    float[] screenCords = new float[cameraCords.length];
    for (int i = 0; i < cameraCords.length; i = i + 2) {
      screenCords[i] = cameraCords[i] * MainClass.screenWidth / MainClass.cam_width;
      screenCords[i + 1] = cameraCords[i + 1] * MainClass.screenHeight / MainClass.cam_height;
    }
    basicObject.setScreenPixels(screenCords);
  }

  public static float getTipY(float tipY, Polygon polygon)
  {
    return tipY + polygon.getOriginY() *(float) Math.cos(Math.toRadians(polygon.getRotation()));
  }

//  public static float getTipX(float tipX, Polygon polygon)
//  {
//    return tipX + 2*polygon.getOriginX() *(float) Math.cos(Math.toRadians(polygon.getRotation()));
//  }



}
