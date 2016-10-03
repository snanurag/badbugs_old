package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 7/6/2016.
 */
public class BloodSplash extends Splash{


    public BloodSplash(Vector2 bloodSpotCenter, float bloodLength, Knife knife) throws Exception {
        super(bloodSpotCenter, bloodLength, knife);
    }

    @Override
    protected BasicObject getSplashDot() {
        return SpritesCreator.getBloodDot();
    }

}
