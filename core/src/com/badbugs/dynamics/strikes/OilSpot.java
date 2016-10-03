package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 10/3/2016.
 */
public class OilSpot extends BaseScratch {
    @Override
    public float getScratchLen() {
        return Constants.BLOOD_SPOT_WIDTH;
    }

    public OilSpot(Bug bug, Knife knife, Vector2 hitPoint) throws Exception {
        super(bug, knife, hitPoint);
    }

    @Override
    public BasicObject getScratchSprite(float len) {
        return SpritesCreator.getOilSpot(len);
    }

}