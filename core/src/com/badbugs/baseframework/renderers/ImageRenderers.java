package com.badbugs.baseframework.renderers;

import com.badbugs.Game;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.dynamics.strikes.BaseScratch;
import com.badbugs.dynamics.strikes.BloodSpot;
import com.badbugs.dynamics.strikes.OilSpot;
import com.badbugs.dynamics.strikes.Splash;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.bugs.BronzeBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.bugs.SteelBug;
import com.badbugs.objects.knives.*;
import com.badbugs.util.Constants;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class ImageRenderers {

    public static ShapeRenderer shapeRenderer;

    static {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(Game.cam.combined);
        shapeRenderer.setAutoShapeType(true);
    }

    public static void renderKnife(SpriteBatch batch, Knife knife) throws Exception {

        Polygon knifePolygon = knife.getPolygon();
        Texture knifeTexture = knife.getTexture();

        batch.draw(new TextureRegion(knifeTexture), knifePolygon.getX(), knifePolygon.getY(), 0, 0, knife.getCameraDimensions()[0],
                knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation());

//        batch.draw(new TextureRegion(knifeTexture), 0, 0, 0, 0, knife.getCameraDimensions()[0]/1.414f,
//                knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation());
        //        drawPolygon(knife.getPolygon().getTransformedVertices());
    }

    public static void renderKnifeShadow(SpriteBatch batch) throws Exception {


        Knife knife = GameStates.getSelectedKnife();
        Knife shadow;

        if(knife instanceof StoneKnife || knife instanceof StoneKnifeTilted)
            shadow = ObjectsStore.getKnifeShadow(Constants.KNIFE_SHADOW.STONE);
        else if(knife instanceof BronzeKnife || knife instanceof BronzeKnifeTilted)
            shadow = ObjectsStore.getKnifeShadow(Constants.KNIFE_SHADOW.BRONZE);
        else
            shadow = ObjectsStore.getKnifeShadow(Constants.KNIFE_SHADOW.STEEL);

        if(knife instanceof StoneKnife || knife instanceof BronzeKnife || knife instanceof SteelKnife){
            Polygon knifePolygon = knife.getPolygon();
            batch.draw(new TextureRegion(shadow.getTexture()), knifePolygon.getX()+Constants.SHADOW_LAG, knifePolygon.getY()+Constants.SHADOW_LAG, 0, 0, knife.getCameraDimensions()[0],
                    knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation());
        }
        else {
            Vector2 shadowHandle = getHandle(knife);
            float handleLen = Util.distanceBetweenPoints(shadowHandle, new Vector2(0,0));
            float a = (float) (Math.atan2(shadowHandle.y, shadowHandle.x) * 180 / Math.PI);
            Vector2 shadowPos = getShadowPosition(knife, a);
            batch.draw(new TextureRegion(shadow.getTexture()), shadowPos.x, shadowPos.y, 0, 0, handleLen, knife
                    .getCameraDimensions()[1], 1, 1, a);
        }

        //        drawPolygon(knife.getPolygon().getTransformedVertices());
    }

    private static Vector2 getHandle(Knife knife) throws Exception{
        Vector2 knifeHandle = Util.rotateVectorByGivenAngle(knife.getCameraDimensions()[0]  , 0, knife.getPolygon().getRotation());
        Vector2 shadowHandle = new Vector2(knifeHandle.x - knife.getCameraDimensions()[0]/1.414f, knifeHandle.y - knife.getCameraDimensions()[0]/1.414f);
        return shadowHandle;
    }

    private static Vector2 getShadowPosition(Knife knife, float shadowAngle) throws Exception{
        Vector2 v = Util.rotateVectorByGivenAngle(0, 1.5f, shadowAngle);
        Vector2 knifeCenterPos = Util.rotateVectorByGivenAngle(0, 1.5f, knife.getPolygon().getRotation());
//        Vector2 knifeTip = Util.getKnifeTipInWorld(knife.getPolygon());
        return  new Vector2(knife.getPolygon().getX() + knifeCenterPos.x - v.x, knife.getPolygon().getY() + knifeCenterPos.y - v.y);
    }

    private static void renderBlood(SpriteBatch batch, Bug bug) throws Exception {

        BaseScratch[] scratches = ObjectsStore.getScratches(bug);

        if (scratches == null) return;

        float elapsedTime = getElapsedTimeForScratches(scratches);

        if (elapsedTime > Constants.BLOOD_SPOT_FADE_TIME) {
            ObjectsStore.clearAllScratches(bug);
            bug.dead = true;
        }

        float alpha = getAlpha(elapsedTime);

        for (BaseScratch scratch : scratches) {

            if (scratch == null) continue;

            BasicObject scratchSprite = scratch.getScratchSprite();

            if (scratchSprite != null) {
                Polygon polygon = scratchSprite.getPolygon();

                batch.setColor(1, 1, 1, alpha);
                batch.draw(scratchSprite.getTexture(), polygon.getX(), polygon.getY(), 0, 0,
                        scratchSprite.getCameraDimensions()[0], scratchSprite.getCameraDimensions()[1], 1, 1,
                        polygon.getRotation(), 0, 0, scratchSprite.getTexture().getWidth(),
                        scratchSprite.getTexture().getHeight(), false, false);
                batch.setColor(1, 1, 1, 1);
            }
        }
    }

    private static void renderSplash(SpriteBatch batch, Bug bug) throws Exception{

        BaseScratch[] scratches = ObjectsStore.getScratches(bug);

        if (scratches == null) return;

        float elapsedTime = getElapsedTimeForScratches(scratches);

        float alpha = getAlpha(elapsedTime);

        for (BaseScratch scratch : scratches) {

            if (scratch == null) continue;

            if (scratch instanceof BloodSpot || scratch instanceof OilSpot) {

                Splash splash = ObjectsStore.getSplash(bug);

                if (splash != null) {
                    List<List<BasicObject>> listList = splash.getListOfBloodSprites();
                    for (List<BasicObject> list : listList) {
                        for (BasicObject bloodSprite : list) {
                            batch.setColor(1, 1, 1, alpha);
                            batch.draw(bloodSprite.getTexture(), bloodSprite.getPolygon().getX(), bloodSprite
                                            .getPolygon().getY(),
                                    bloodSprite.getCameraDimensions()[0], bloodSprite.getCameraDimensions()[1]);
                            batch.setColor(1, 1, 1, 1);
                        }
                    }
                }
            }
        }
    }

    private static float getElapsedTimeForScratches(BaseScratch[] scratches){

        for(BaseScratch scratch:scratches){
            if(scratch instanceof BloodSpot || scratch instanceof OilSpot){
                scratch.elapsedTime += Gdx.graphics.getDeltaTime();
                return scratch.elapsedTime;
            }
        }
        return 0;
    }

    public static void renderLives(SpriteBatch batch, Bug[] lives) throws Exception {
        if (ObjectsStore.bugMissed <= 4)
            renderBug(batch, lives[0]);
        if (ObjectsStore.bugMissed <= 3)
            renderBug(batch, lives[1]);
        if (ObjectsStore.bugMissed <= 2)
            renderBug(batch, lives[2]);
        if (ObjectsStore.bugMissed <= 1)
            renderBug(batch, lives[3]);
        if (ObjectsStore.bugMissed <= 0)
            renderBug(batch, lives[4]);
    }

    public static void renderBugs(SpriteBatch batch) throws Exception {
        List<Bug> bugList = ObjectsStore.getBugList();
        synchronized (bugList) {
            for (Bug bug : bugList) {
                ImageRenderers.renderBug(batch, bug);
            }
        }
        for(Bug bug: ObjectsStore.getHitBugList()){
            ImageRenderers.renderBug(batch, bug);
        }
    }

    public static void renderScratches(SpriteBatch batch) throws Exception {
        List<Bug> bugList = ObjectsStore.getHitBugList();
        synchronized (bugList) {
            for (Bug bedBug : bugList) ImageRenderers.renderBlood(batch, bedBug);
        }

        bugList = ObjectsStore.getBugList();
        synchronized (bugList){
            for(Bug bug: bugList){
                if(bug instanceof BronzeBug || bug instanceof SteelBug) ImageRenderers.renderBlood(batch, bug);
            }
        }
    }

    public static void renderSplashes(SpriteBatch batch) throws Exception{
        List<Bug> bugList = ObjectsStore.getHitBugList();
        synchronized (bugList) {
            for (Bug bedBug : bugList) ImageRenderers.renderSplash(batch, bedBug);
        }
    }

    private static void renderBug(SpriteBatch batch, Bug bedBug) throws Exception {

        Polygon bugPolygon = bedBug.getPolygon();

        BaseScratch[] scratches = ObjectsStore.getScratches(bedBug);

        float elapsedTime;
        if (scratches == null) elapsedTime = 0;
        else elapsedTime = getElapsedTimeForScratches(scratches);

        if (elapsedTime > 0) {

//            BloodSpot bloodSpot = (BloodSpot) scratches[0];
            float alpha = getAlpha(elapsedTime);

            batch.setColor(1, 1, 1, alpha);
            bedBug.elapsedTime = 0;
        } else {
            bedBug.elapsedTime += Gdx.graphics.getDeltaTime();
        }

        if (bedBug.getTexture() == null)
            batch.draw(bedBug.animation.getKeyFrame(bedBug.elapsedTime, true), bugPolygon.getX(), bugPolygon.getY(),
                    bugPolygon.getOriginX(), bugPolygon.getOriginY(), bedBug.getCameraDimensions()[0],
                    bedBug.getCameraDimensions()[1], 1, 1, bedBug.getPolygon().getRotation());
        else
            batch.draw(bedBug.getTexture(), bugPolygon.getX(), bugPolygon.getY(), bedBug.getCameraDimensions()[0],
                    bedBug.getCameraDimensions()[1]);

        batch.setColor(1, 1, 1, 1);

        //      drawCircle(0, 0, 0.2f);
        //      drawPolygon(bedBug.getPolygon().getTransformedVertices());
    }

    public static void renderGameOverBackground(SpriteBatch batch, GameOver gameOver) throws Exception {
        gameOver.elapsedTime += Gdx.graphics.getDeltaTime();
        float alpha = gameOver.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
        if (alpha < 1) {
            batch.setColor(1, 1, 1, alpha);
        }
        batch.draw(gameOver.getTexture(), -gameOver.getCameraDimensions()[0] / 2, -gameOver.getCameraDimensions()[1] / 2,
                gameOver.getCameraDimensions()[0], gameOver.getCameraDimensions()[1]);
        batch.setColor(1, 1, 1, 1);
    }

    public static void renderBasicObject(SpriteBatch batch, BasicObject basicObject) throws Exception {
        batch.draw(basicObject.getTexture(), basicObject.getPolygon().getX(), basicObject.getPolygon().getY(),
                basicObject.getCameraDimensions()[0], basicObject.getCameraDimensions()[1]);

    }

    private static float getAlpha(float elapsedTime) {
        float alpha;
        if (elapsedTime / Constants.BLOOD_SPOT_FADE_TIME < 1) {
            alpha = 1 - elapsedTime / Constants.BLOOD_SPOT_FADE_TIME;
        } else {
            alpha = 0f;
        }
        return alpha;
    }

    @Deprecated
    public static void drawPolygon(float[] vertices) {
        try {
            //      if (start)
            shapeRenderer.setColor(1, 1, 0, 1);
            if (vertices.length >= 6)
                shapeRenderer.polygon(vertices);
            else if (vertices.length == 2)
                shapeRenderer.point(vertices[0], vertices[1], 0);
            //shapeRenderer.line();
            // if (end)
            //   shapeRenderer.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void drawLine(float x, float y, float x1, float y1) {
        shapeRenderer.line(x, y, x1, y1);
    }

    @Deprecated
    public static void drawCircle(float x, float y, float radius) {
        shapeRenderer.circle(x, y, radius);
    }

}
