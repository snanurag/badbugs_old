package com.badbugs.dynamics.movement;

import com.badbugs.Game;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.baseframework.sounds.SoundPlayer;
import com.badbugs.dynamics.strikes.*;
import com.badbugs.objects.bugs.*;
import com.badbugs.objects.knives.BronzeKnife;
import com.badbugs.objects.knives.Knife;
import com.badbugs.objects.knives.SteelKnife;
import com.badbugs.objects.knives.StoneKnife;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.util.Constants;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Iterator;

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

    public static void updatePolygon( TouchInfo touchInfo) throws Exception {
        Knife basicObject = GameStates.getSelectedKnife();
        if (!Util.checkIfGameOverConditionMet()) {
            rotatePolygon(basicObject, touchInfo);
            translatePolygon(basicObject);
        }
    }

    private static void rotatePolygon(Knife knife, TouchInfo touchInfo) throws Exception {

        if (directionVector != null) {
            return;
        }

        synchronized (ObjectsStore.getBugList()){
            for(Bug bug: ObjectsStore.getBugList()){
                bug.hitInThisThrow = false;
            }
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

            updateBugsState(knife);

            polygon.setRotation(knife.getInitialAngle() + angle);

            Util.globalLogger().debug("Angle of Knife " + angle);
            Util.globalLogger().debug("Direction vector " + directionVector);

//            attemptToCutAllBugs(knife);
        }
    }

    private static void translatePolygon(Knife knife) throws Exception {

        if (directionVector != null) {

            Polygon polygon = knife.getPolygon();

            float speed;
            if (GamePurchaseObserver.isPurchased(Constants.double_speed) || Constants.DEMO) {
                speed = Constants.BOOSTER * knife.getSpeed();
            } else {
                speed = knife.getSpeed();
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

                Vector2 v = moveKnifeBackInBoundary(polygon.getX(), polygon.getY());
                polygon.setPosition(v.x, v.y);
                SoundPlayer.playKnifeWoodImpact();
                directionVector = null;
            }

            attemptToCutAllBugs(knife);

            Util.globalLogger().debug("Position of Knife tip - x " + x + " y " + y);
        }
    }

    private static void attemptToCutAllBugs(Knife knife) throws Exception {
        synchronized (ObjectsStore.getBugList()) {
            Iterator<Bug> itr = ObjectsStore.getBugList().iterator();
            while(itr.hasNext()){
                Bug bug = itr.next();
                Vector2 bugCenter = Util.getPolygonCenter(bug.getPolygon());

                Vector2 currentState = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

                if (!bug.hitInThisThrow && bug.isStateChanged(currentState.x, currentState.y)) {
                    bug.hitInThisThrow = true;
                    Vector2 hitCoords = getKnifeAndBugHitCoords(bug, knife);
                    if(hitCoords != null){
                        if (knife instanceof BronzeKnife) {
                            bug.hitCount++;
                            bug.hitCount++;
                        } else if (knife instanceof SteelKnife) {
                            bug.hitCount++;
                            bug.hitCount++;
                            bug.hitCount++;
                        } else bug.hitCount++;

                        createScratch(bug, knife, hitCoords);

                        if (bug instanceof BronzeBug && bug.hitCount >= 2 || bug instanceof SteelBug && bug.hitCount
                                >= 3 || bug instanceof BlackBug || bug instanceof BedBug || bug instanceof LadyBug) {
                            bug.hit = true;
                            itr.remove();
                            ObjectsStore.addHitBug(bug);
                            ObjectsStore.score++;
//                            if(bug instanceof BronzeBug || bug instanceof SteelBug)
//                                ObjectsStore.clearAllScratches(bug);
                        }
                    }
                }
            }
        }
    }

    private static void createScratch(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
        if (hitPoint != null) {
            if (bug instanceof BronzeBug) {
                if (ObjectsStore.getScratches(bug) == null) ObjectsStore.add(bug, new BaseScratch[2]);
                if (bug.hitCount >= 2){
                    OilSpot spot = new OilSpot(bug, knife, hitPoint);
                    ObjectsStore.add(bug, 1, spot);
                    createSplash(bug, spot, knife);
                }
                else ObjectsStore.add(bug, 0, new BronzeScratch(bug, knife, hitPoint));
            } else if (bug instanceof SteelBug) {
                if (ObjectsStore.getScratches(bug) == null) ObjectsStore.add(bug, new BaseScratch[3]);
                if (bug.hitCount == 2) ObjectsStore.add(bug, 1, new SteelScratch(bug, knife, hitPoint));
                else if (bug.hitCount >= 3){
                    OilSpot spot = new OilSpot(bug, knife, hitPoint);
                    ObjectsStore.add(bug, 2, spot);
                    createSplash(bug, spot, knife);
                }
                else ObjectsStore.add(bug, 0, new SteelScratch(bug, knife, hitPoint));
            } else {
                if (ObjectsStore.getScratches(bug) == null) {

                    ObjectsStore.add(bug, new BloodSpot[1]);
                    ObjectsStore.add(bug, 0, new BloodSpot(bug, knife, hitPoint));
                    BloodSpot bloodSpot = (BloodSpot) ObjectsStore.getScratches(bug)[0];
                   createSplash(bug, bloodSpot, knife);
                }
            }
            SoundPlayer.playKnifeBugImpact();
        }
    }

    private static void createSplash(Bug bug, BaseScratch spot, Knife knife) throws Exception {
        if(spot instanceof BloodSpot)
            ObjectsStore.add(bug, new BloodSplash(new Vector2((spot.startPoint.x + spot.endPoint.x) / 2, (spot.startPoint
                .y + spot.endPoint.y) / 2), spot.getScratchSprite().getCameraDimensions()[0], knife));
        else
            ObjectsStore.add(bug, new OilSplash(new Vector2((spot.startPoint.x + spot.endPoint.x) / 2, (spot.startPoint
                    .y + spot.endPoint.y) / 2), spot.getScratchSprite().getCameraDimensions()[0], knife));
    }

    private static Vector2 moveKnifeBackInBoundary(float x, float y) {

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

    private static Vector2 getKnifeAndBugHitCoords(Bug bug, Knife knife) throws Exception {

        Polygon bugPolygon = bug.getPolygon();
        Polygon knifePolygon = knife.getPolygon();

        Vector2 tip = Util.getKnifeTipInWorld(knifePolygon);

        float a = knifePolygon.getRotation();
        float x1 = tip.x;
        float y1 = tip.y;

        Vector2 hitCoords = getHitCoordsInXIncreasing(bugPolygon, x1, y1, a);

        if (hitCoords == null) {
            hitCoords = getHitCoordsInXDecreasing(bugPolygon, x1, y1, a);
        }

        return hitCoords;
    }

    private static boolean checkIfPointInBoundary(float x, float y) {
        if (x <= XLimit && x >= -XLimit && y <= YLimit && y >= -YLimit)
            return true;
        return false;
    }

    private static Vector2 getHitCoordsInXIncreasing(Polygon bugPolygon, float x1, float y1, float a) {
        return getHitPoint(bugPolygon, x1, y1, a, 0.01f);

    }

    private static Vector2 getHitCoordsInXDecreasing(Polygon bugPolygon, float x1, float y1, float a) {
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

    private static void updateBugsState(Knife knife) throws Exception {
        synchronized (ObjectsStore.getBugList()) {
            for (Bug bug : ObjectsStore.getBugList()) {
//                if (bug.hit)
//                    continue;

                Util.globalLogger()
                        .debug("Bug id " + bug.id + " x " + bug.getPolygon().getX() + " y " + bug.getPolygon().getY());

                Vector2 bugCenter = Util.getPolygonCenter(bug.getPolygon());
                bug.state = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

            }
        }
    }

    public float getAngle() {
        return angle;
    }
}
