package com.badbugs.dynamics.movement;

import com.badbugs.Game;
import com.badbugs.baseframework.SoundPlayer;
import com.badbugs.dynamics.blood.BloodSplash;
import com.badbugs.dynamics.blood.BloodSpot;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 3/21/2016.
 * <p/>
 * Important : In case of knife, polygon is only used for storing cordinates. There is no calculation based on polygon position. And, image of Knife is pivoted at left bottom corner.
 */
public class KnifeMovement {

    private static Vector2 directionVector;
    private static double elapsedTime;
    private static float angle;
    private static long lastTime;
    private static float XLimit = Constants.XLimit;
    private static float YLimit = Constants.YLimit;

    private static int counter;

    public static void updatePolygon(Knife basicObject, TouchInfo touchInfo) throws Exception {
        if (!Util.checkIfGameOverConditionMet()) {
            rotatePolygon(basicObject, touchInfo);
            translatePolygon(basicObject);
        }
    }

    private static void rotatePolygon(Knife knife, TouchInfo touchInfo) throws Exception {

        if (directionVector != null) {
            return;
        }

        Polygon polygon = knife.getPolygon();

        if (touchInfo != null) {

            SoundPlayer.playKnifeSlash();
            Util.globalLogger().info("Touch count " + ++counter);
            Vector3 touchPoints;

            touchPoints = Game.cam.unproject(new Vector3(touchInfo.touchX, touchInfo.touchY, 0));
            Util.moveTouchPtUpByKnifeYOrig(knife.getPolygon(), touchPoints);
            float dirX = touchPoints.x - polygon.getX();
            float dirY = touchPoints.y - polygon.getY();

            directionVector = new Vector2((float) (dirX / (Math.sqrt(dirX * dirX + dirY * dirY))),
                    (float) (dirY / (Math.sqrt(dirX * dirX + dirY * dirY))));
            elapsedTime = 0;
            lastTime = System.currentTimeMillis();
            float angle = (float) (Math.atan2(directionVector.y, directionVector.x) * 180 / Math.PI);

            setAllBugsState(knife);

            polygon.setRotation(knife.getInitialAngle() + angle);

            Util.globalLogger().debug("Angle of Knife " + angle);
            Util.globalLogger().debug("Direction vector " + directionVector);
        }
    }

    private static void translatePolygon(Knife knife) throws Exception {

        if (directionVector != null) {

            Polygon polygon = knife.getPolygon();

            float speed;
            if (Constants.DEMO || GamePurchaseObserver.isPurchased(Constants.double_speed)) {
                speed = Constants.SILVER_KNIFE_DOUBLE_SPEED;
            } else {
                speed = Constants.SILVER_KNIFE_SPEED;

            }
            float xSpeed = directionVector.x * speed;
            float ySpeed = directionVector.y * speed;

            long thisTime = System.currentTimeMillis();
            elapsedTime = (float) (thisTime - lastTime) / 1000f;
            lastTime = thisTime;
            if (elapsedTime == 0) {
                elapsedTime = 0.001;
            }

            Util.globalLogger()
                    .debug("Speed of knife xSpeed : " + xSpeed + " ySpeed : " + ySpeed + " elapsedTime : " + elapsedTime);

            float x = (float) ((polygon.getX()) + xSpeed * elapsedTime);
            float y = (float) ((polygon.getY()) + ySpeed * elapsedTime);

            polygon.setPosition(x, y);

            if (!checkIfPointInBoundary(polygon.getX(), polygon.getY())) {

                Vector2 v = moveVectorInBoundary(polygon.getX(), polygon.getY());
                polygon.setPosition(v.x, v.y);
                SoundPlayer.playKnifeWoodImpact();
                directionVector = null;
            }

            attemptToCutAllBugs(knife);

            Util.globalLogger().debug("Position of Knife tip - x " + x + " y " + y);
        }
    }

    private static Vector2 moveVectorInBoundary(float x, float y) {

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

    private static void attemptToCreateBlood(Bug bug, Knife knife) throws Exception {

        Polygon bugPolygon = bug.getPolygon();
        Polygon knifePolygon = knife.getPolygon();

        Vector2 tip = Util.getKnifeTipInWorld(knifePolygon);

        float a = knifePolygon.getRotation();
        float x1 = tip.x;
        float y1 = tip.y;

        Vector2 hitPoint = getHiPointInXIncreasing(bugPolygon, x1, y1, a);

        if (hitPoint == null) {
            hitPoint = getHiPointInXDecreasing(bugPolygon, x1, y1, a);
        }

        if (hitPoint != null) {
            if (ObjectsStore.getBloodSpot(bug) == null) {
                BloodSpot.createAndStoreBloodSpot(bug, knife, hitPoint);
                BloodSpot bloodSpot = ObjectsStore.getBloodSpot(bug);
                ObjectsStore.add(bug, new BloodSplash(hitPoint,bloodSpot.getBloodSprite().getCameraDimensions()[0]));
                SoundPlayer.playKnifeBugImpact();
            }
        }
    }

    private static Vector2 getHiPointInXIncreasing(Polygon bugPolygon, float x1, float y1, float a) {
        return getHitPoint(bugPolygon, x1, y1, a, 0.01f);

    }

    private static Vector2 getHiPointInXDecreasing(Polygon bugPolygon, float x1, float y1, float a) {
        return getHitPoint(bugPolygon, x1, y1, a, -0.01f);

    }

    private static Vector2 getHitPoint(Polygon bugPolygon, float x1, float y1, float a, float delta) {
        float x = x1 + delta;
        float y = getYOnLine(x, x1, y1, a);

        Vector2 hitPoint = null;

        //These boundaries are on left bottom position of knife. Calibrating them here on tip.
        while (checkIfPointInBoundary(x, y)) {
            x = x + delta;
            y = getYOnLine(x, x1, y1, a);
            if (Util.insidePolygon(bugPolygon, x, y)) {
                hitPoint = new Vector2(x, y);
                break;
            }
        }

        Util.globalLogger().debug("Points to draw line : x " + x + " y " + y + " x1 " + x1 + " y1 " + y1);
        //  ImageRenderers.drawLine(x, y, x1, y1);

        return hitPoint;
    }

    private static float getYOnLine(float x, float x1, float y1, float a) {
        float y = MathUtils.sinDeg(a) * (x - x1) / MathUtils.cosDeg(a) + y1;
        return y;
    }

    private static void setAllBugsState(Knife knife) throws Exception {
        synchronized (ObjectsStore.getBugList()) {
            for (Bug bug : ObjectsStore.getBugList()) {
                if (bug.hit)
                    continue;

                Util.globalLogger()
                        .debug("Bug id " + bug.id + " x " + bug.getPolygon().getX() + " y " + bug.getPolygon().getY());

                Vector2 bugCenter = Util.getPolygonCenter(bug.getPolygon());
                bug.state = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

            }
        }
    }

    private static void attemptToCutAllBugs(Knife knife) throws Exception {
        synchronized (ObjectsStore.getBugList()) {
            for (Bug bug : ObjectsStore.getBugList()) {
                Vector2 bugCenter = Util.getPolygonCenter(bug.getPolygon());

                Vector2 currentState = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

                if (bug.isStateChanged(currentState.x, currentState.y)) {
                    attemptToCreateBlood(bug, knife);
                }
            }
        }

    }

    public float getAngle() {
        return angle;
    }
}
