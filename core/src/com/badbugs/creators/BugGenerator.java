package com.badbugs.creators;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Constants;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/24/2016.
 */

public class BugGenerator extends Thread {

    final int WALLS_COUNT = 4;
    int bugId = 0;
    int lastWall;
    boolean running = true;

    public void terminateBugGenerator() {
        running = false;
    }

    public void run() {
        try {

            while (running) {
                if (Constants.DEMO && bugId == Constants.DEMO_BUGS) {
                    GameStates.endDemo();
                    running = false;
                }
                Thread.sleep(1000);
                if (!MainGameScreen.isPaused)
                    createBug();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBug() throws Exception {
        int level = getLevel();
//        BedBug bug = SpritesCreator.loadBedBug(level);
        BedBug bug = SpritesCreator.loadBugNoLegMovement(level);
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

        int wall = 0;
        while (lastWall == wall) {
            wall = (int) (Math.random() * WALLS_COUNT);
        }

        lastWall = wall;

        float x;
        float y;
        float angle;

        if (wall == 0) // bottom wall
        {
            y = -Game.cam_height / 2 - bug.getPolygon().getOriginY();
            x = getValFromNegToPosMax(Game.cam_width / 2);
            if (x < 0)
                angle = 30 + getValFromZeroToMax(60);
            else
                angle = 90 + getValFromZeroToMax(60);
        } else if (wall == 1) // left wall
        {
            x = -Game.cam_width / 2 - bug.getPolygon().getOriginX();
            y = getValFromNegToPosMax(Game.cam_height / 2);
            if (y < 0)
                angle = getValFromZeroToMax(60);
            else
                angle = -getValFromZeroToMax(60);
        } else if (wall == 2) // top wall
        {
            y = Game.cam_height / 2 + bug.getPolygon().getOriginY();
            x = getValFromNegToPosMax(Game.cam_width / 2);
            if (x < 0)
                angle = -30 - getValFromZeroToMax(60);
            else
                angle = -90 - getValFromZeroToMax(60);
        } else {
            x = Game.cam_width / 2 + bug.getPolygon().getOriginX();
            y = getValFromNegToPosMax(Game.cam_height / 2);
            if (y < 0)
                angle = 120 + getValFromZeroToMax(60);
            else
                angle = 180 + getValFromZeroToMax(60);
        }

        polygon.setPosition(x - polygon.getOriginX(), y - polygon.getOriginY());
        polygon.setRotation(angle + bug.getInitialAngle());

    }

    private float getValFromZeroToMax(float max) {
        float randomFactor = (float) Math.random();
        return randomFactor * max;
    }

    private float getValFromNegToPosMax(float max) {
        float randomFactor = (float) Math.random();
        return (randomFactor * 2 - 1) * max;
    }
}
