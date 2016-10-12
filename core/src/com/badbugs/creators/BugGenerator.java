package com.badbugs.creators;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/24/2016.
 */

public class BugGenerator extends Thread {

    private int bugId = 0;
    private boolean running = true;
    private final int SINGLE_BUG_RALLY = 3;
    private final int DOUBLE_BUG_RALLY = 5;
    private final int ONLYNORMALBUG_RALLY_LIMIT = 15;
    private final int ONLYBRONZEBUG_RALLY_LIMIT = 3;
    private int BUG_COUNT_PER_RALLY_LIMIT = 3;
    private static int allBugRallyCount = 0;
    private static int bronzeBugRallyCount = 0;
    private boolean killsUnder2Sec = false;
    private boolean wasLastBugMetal = false;
    private int consecutiveKillCountUnder2Sec = 0;
    private int lastRegisteredMissedBugs = ObjectsStore.bugMissed;

    private int[] eightEntryPoints = new int[8];//    4  5
                                                //  3      6
                                                //  2      7
                                                //    1 0
    private static int ENTRY_SIDES = 8;

    public void terminateBugGenerator() {
        running = false;
    }

    public void run() {
        int sleepCount = 0;
        try {
            while (running) {
                if (Constants.DEMO && bugId == Constants.DEMO_BUGS) {
                    GameStates.endDemo();
                    running = false;
                }

                Thread.sleep(500);
                sleepCount++;
                Util.globalLogger().info("Bug list size -> " + ObjectsStore.getBugList().size());
                if (sleepCount % 5 == 0) {
                    if (allBugRallyCount > ONLYNORMALBUG_RALLY_LIMIT + ONLYBRONZEBUG_RALLY_LIMIT && ObjectsStore
                            .getBugList().isEmpty()) {
                        killsUnder2Sec = true;
                        consecutiveKillCountUnder2Sec++;
                        Util.globalLogger().info("ConsecutiveKillCountUnder2Sec -> "+consecutiveKillCountUnder2Sec);
                        if (consecutiveKillCountUnder2Sec == 3)
                            BUG_COUNT_PER_RALLY_LIMIT++;
                    } else consecutiveKillCountUnder2Sec = 0;
                }
                if (sleepCount % 6 == 0) {
                    sleepCount = 0;
                    if (IsABugMissed()) BUG_COUNT_PER_RALLY_LIMIT = 3;
                    if (!MainGameScreen.isPaused) {
                        if (allBugRallyCount < SINGLE_BUG_RALLY) createBug(1, getANormalBug());
                        else if (allBugRallyCount < SINGLE_BUG_RALLY + DOUBLE_BUG_RALLY) createBug(2, getANormalBug());
                        else if (!wasLastBugMetal && allBugRallyCount >= ONLYNORMALBUG_RALLY_LIMIT && killsUnder2Sec) {
                            if (bronzeBugRallyCount < ONLYBRONZEBUG_RALLY_LIMIT) {
                                createBug(BUG_COUNT_PER_RALLY_LIMIT, Constants.BUG_TYPE.BRONZE);
                                bronzeBugRallyCount++;
                            } else createBug(BUG_COUNT_PER_RALLY_LIMIT, getAMetalBug());
                            wasLastBugMetal = true;
                        } else createBug(BUG_COUNT_PER_RALLY_LIMIT, getANormalBug());
                        allBugRallyCount++;
                    }
                    killsUnder2Sec = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBug(int bugCount, Constants.BUG_TYPE t) throws Exception{
        initializeEntriesToZero();
        for(int i=0; i<bugCount; i++) createBug(t);
    }

    private void createBug(Constants.BUG_TYPE t) throws Exception {
        int level = getLevel();
        Bug bug = SpritesCreator.createBug(t, level);
//        BedBug bug = SpritesCreator.loadBugNoLegMovement(level);
        bug.id = ++bugId;
        initialize(bug);
        ObjectsStore.add(bug);

        bug.speed = Constants.BUG_SPEED[level];
    }

    private int getLevel() {
        double randomFactor = Math.random();
        int level = (int) (randomFactor * Constants.MAX_BUG_LEVEL);
        return level;
    }

    private void initialize(Bug bug) throws Exception {

        Polygon polygon = bug.getPolygon();

        int entryPos = (int) (Math.random() * ENTRY_SIDES);
        while (eightEntryPoints[entryPos] == 1) {
            entryPos = (int) (Math.random() * ENTRY_SIDES);
        }

        float x;
        float y;
        float angle;

        switch (entryPos){
            case 0:{
                y = -Game.cam_height / 2 - bug.getPolygon().getOriginY();
                x = getValFromZeroToMax(Game.cam_width / 2);
                angle = 90 + getValFromZeroToMax(60);
                eightEntryPoints[0] =1;
                break;
            }
            case 1:{
                y = -Game.cam_height / 2 - bug.getPolygon().getOriginY();
                x = -getValFromZeroToMax(Game.cam_width / 2);
                angle = 30 + getValFromZeroToMax(60);
                eightEntryPoints[1] =1;
                break;
            }
            case 2:{
                x = -Game.cam_width / 2 - bug.getPolygon().getOriginX();
                y = -getValFromZeroToMax(Game.cam_height / 2);
                angle = getValFromZeroToMax(60);
                eightEntryPoints[2] =1;
                break;
            }
            case 3:{
                x = -Game.cam_width / 2 - bug.getPolygon().getOriginX();
                y = getValFromZeroToMax(Game.cam_height / 2);
                angle = -getValFromZeroToMax(60);
                eightEntryPoints[3] =1;
                break;
            }
            case 4:{
                y = Game.cam_height / 2 + bug.getPolygon().getOriginY();
                x = -getValFromZeroToMax(Game.cam_width / 2);
                angle = -30 - getValFromZeroToMax(60);
                eightEntryPoints[4] =1;
                break;
            }
            case 5:{
                y = Game.cam_height / 2 + bug.getPolygon().getOriginY();
                x = getValFromZeroToMax(Game.cam_width / 2);
                angle = -90 - getValFromZeroToMax(60);
                eightEntryPoints[5] =1;
                break;
            }
            case 6:{
                x = Game.cam_width / 2 + bug.getPolygon().getOriginX();
                y = getValFromZeroToMax(Game.cam_height / 2);
                angle = 180 + getValFromZeroToMax(60);
                eightEntryPoints[6] =1;
                break;
            }
            default:{
                x = Game.cam_width / 2 + bug.getPolygon().getOriginX();
                y = -getValFromZeroToMax(Game.cam_height / 2);
                angle = 120 + getValFromZeroToMax(60);
                eightEntryPoints[7] =1;
                break;
            }
        }

        polygon.setPosition(x - polygon.getOriginX(), y - polygon.getOriginY());
        polygon.setRotation(angle + bug.getInitialAngle());

    }

    private float getValFromZeroToMax(float max) {
        float randomFactor = (float) Math.random();
        return randomFactor * max;
    }

    private Constants.BUG_TYPE getANormalBug(){
        wasLastBugMetal = false;
        int i =(int) getValFromZeroToMax(3);
        switch (i){
            case 0 : return Constants.BUG_TYPE.BED;
            case 1 : return Constants.BUG_TYPE.LADY;
            case 2 : return Constants.BUG_TYPE.BLACK;
            default : return Constants.BUG_TYPE.BED;
        }
//        if(allBugRallyCount%5==0)
//            return Constants.BUG_TYPE.BED;
//        else if(allBugRallyCount%5==1)
//            return Constants.BUG_TYPE.BLACK;
//        else if(allBugRallyCount%5==2)
//            return Constants.BUG_TYPE.LADY;
//        else if(allBugRallyCount%5==3)
//            return Constants.BUG_TYPE.BRONZE;
//            else
//            return Constants.BUG_TYPE.STEEL;
//        return Constants.BUG_TYPE.BRONZE;
    }

    private Constants.BUG_TYPE getAMetalBug(){
        int i =(int) getValFromZeroToMax(2);
        switch (i){
            case 0 : return Constants.BUG_TYPE.BRONZE;
            case 1 : return Constants.BUG_TYPE.STEEL;
            default : return Constants.BUG_TYPE.BRONZE;
        }
    }

    private boolean IsABugMissed(){
        if(lastRegisteredMissedBugs != ObjectsStore.bugMissed){
            lastRegisteredMissedBugs = ObjectsStore.bugMissed;
            return true;
        }
        else return false;
    }

    private void initializeEntriesToZero(){
        for(int i=0; i< eightEntryPoints.length; i++){
            eightEntryPoints[i] = 0;
        }
    }
}
