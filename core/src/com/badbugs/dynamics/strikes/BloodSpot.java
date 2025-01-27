package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/22/2016.
 */
public class BloodSpot extends BaseScratch {

    public BloodSpot(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
        super(bug, knife, hitPoint);
    }

    @Override
    public BasicObject getScratchSprite(float len) {
        return SpritesCreator.getBloodSpot(len);
    }

    @Override
    public float getScratchLen() {
        return Constants.BLOOD_SPOT_WIDTH;
    }
}
