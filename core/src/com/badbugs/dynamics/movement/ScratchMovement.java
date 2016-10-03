package com.badbugs.dynamics.movement;

import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.dynamics.strikes.BaseScratch;
import com.badbugs.dynamics.strikes.BronzeScratch;
import com.badbugs.dynamics.strikes.SteelScratch;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ashrinag on 10/2/2016.
 */
public class ScratchMovement {

    public static void updateScratchPositions() throws Exception {
        synchronized (ObjectsStore.getBugList()) {
            for (Bug bug : ObjectsStore.getBugList()) {
                if (ObjectsStore.getScratches(bug) != null) {
                    BaseScratch[] scratches = ObjectsStore.getScratches(bug);
                    for (BaseScratch scratch : scratches) {
                        if (scratch instanceof BronzeScratch || scratch instanceof SteelScratch) {

                            float elapsedTime = Gdx.graphics.getDeltaTime();
                            BasicObject basicObject = scratch.getScratchSprite();

                            if(bug.freeze_frame_count == -1){
                                float x = basicObject.getPolygon().getX() + bug.speed * MathUtils.cosDeg(bug.getPolygon()
                                        .getRotation() - bug.getInitialAngle()) * elapsedTime;
                                float y = basicObject.getPolygon().getY() + bug.speed * MathUtils.sinDeg(bug.getPolygon()
                                        .getRotation() - bug.getInitialAngle()) * elapsedTime;
                                basicObject.getPolygon().setPosition(x, y);
                            }
                        }
                    }
                }
            }
        }
    }
}
