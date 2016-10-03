package com.badbugs.dynamics.strikes;

import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.knives.Knife;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 10/3/2016.
 */
public class OilSplash extends Splash {


    public OilSplash(Vector2 bloodSpotCenter, float bloodLength, Knife knife) throws Exception {
        super(bloodSpotCenter, bloodLength, knife);
    }

    @Override
    protected BasicObject getSplashDot() {
        return SpritesCreator.getOilDot();
    }
}