package com.badbugs.creators;

import com.badbugs.MainClass;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/24/2016.
 */

public class BugGenerator extends Thread {

  int walls = 4;
  int bugId = 0;

  public void run() {
    try {
      while (true) {
        Thread.sleep(1000);
        createBug();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createBug() throws Exception {
    int level = 0; //TODO replace by getLevel
    BedBug bug = SpritesCreator.loadBedBug(level);
    bug.id = ++bugId;
    setBugsInitializationParams(bug);
    ObjectsStore.add(bug);

    bug.speed = Constants.BUG_SPEED[level];
  }

  private int getLevel() {
    double randomFactor = Math.random();
    int level = (int) (randomFactor * Constants.MAX_BUG_LEVEL);
    return level;
  }

  private void setBugsInitializationParams(Bug bug) throws Exception {
    Polygon polygon = bug.getPolygon();

    int wall = (int) (Math.random() * walls);
    float x;
    float y;
    float angle;
    if (wall == 0) // bottom wall
    {
      y = -MainClass.cam_height / 2 - bug.getPolygon().getOriginY();
      x = getValueOnRandomizationFactor(true, MainClass.cam_width / 2, (float) Math.random());
      if (x < 0)
        angle = 30 + getValueOnRandomizationFactor(false, 60, (float) Math.random());
      else
        angle = 90 + getValueOnRandomizationFactor(false, 60, (float) Math.random());
    } else if (wall == 1) // left wall
    {
      x = -MainClass.cam_width / 2 - bug.getPolygon().getOriginX();
      y = getValueOnRandomizationFactor(true, MainClass.cam_height / 2, (float) Math.random());
      if (y < 0)
        angle = getValueOnRandomizationFactor(false, 60, (float) Math.random());
      else
        angle = -getValueOnRandomizationFactor(false, 60, (float) Math.random());
    }
    else if (wall == 2) // top wall
    {
      y = MainClass.cam_height / 2 + bug.getPolygon().getOriginY();
      x = getValueOnRandomizationFactor(true, MainClass.cam_width / 2, (float) Math.random());
      if (x < 0)
        angle = -30 - getValueOnRandomizationFactor(false, 60, (float) Math.random());
      else
        angle = -90 - getValueOnRandomizationFactor(false, 60, (float) Math.random());
    }
    else {
      x = MainClass.cam_width / 2 + bug.getPolygon().getOriginX();
      y = getValueOnRandomizationFactor(true, MainClass.cam_height / 2, (float) Math.random());
      if (y < 0)
        angle = 120 + getValueOnRandomizationFactor(false, 60, (float) Math.random());
      else
        angle = 180 + getValueOnRandomizationFactor(false, 60, (float) Math.random());

    }

    Util.rotateAroundCenter(polygon, x, y, angle + bug.getInitialAngle());
//    polygon.setPosition(x, y);
    polygon.setRotation(angle + bug.getInitialAngle());

  }

  private float getValueOnRandomizationFactor(boolean negativeAllowed, float max, float randomFactor) {
    if (negativeAllowed) {
      return (randomFactor * 2 - 1) * max;
    } else {
      return randomFactor * max;
    }
  }
}
