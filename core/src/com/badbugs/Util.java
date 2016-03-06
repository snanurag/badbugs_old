package com.badbugs;

import com.badbugs.objects.BasicObject;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class Util {

    private static Queue<TouchInfo> touchEventsQueue = new PriorityQueue<TouchInfo>();

    public static void createCameraCoordsFromPixelCords(BasicObject basicObject) {
        int[][] pixelCords = basicObject.getPixelCoords();

        float[] finalCords = new float[pixelCords.length * 2];
        for (int i = 0; i < finalCords.length; i = i + 2) {

            finalCords[i] = convertXPixelToCameraX(finalCords[i], basicObject);
            finalCords[i + 1] = convertYPixelToCameraY(finalCords[i + 1], basicObject);

        }
        basicObject.setCameraCoords(finalCords);
    }

    private static float convertXPixelToCameraX(float x, BasicObject basicObject) {
        return x * ((basicObject.getCameraDimentions()[0]) / (basicObject.getPixelDimensions()[0]));
    }

    private static float convertYPixelToCameraY(float y, BasicObject basicObject) {
        return y * ((basicObject.getCameraDimentions()[1]) / (basicObject.getPixelDimensions()[1]));
    }


    public static void addToTouchEventsQueue(TouchInfo info) {
        touchEventsQueue.add(info);
    }

    public static TouchInfo getFromTouchEventsQueue() {
        return touchEventsQueue.poll();
    }

    public static float changeXPixeltoCamera(int pixel) {
        return pixel * MainClass.cam_width / MainClass.screenWidth  ;
    }
}
