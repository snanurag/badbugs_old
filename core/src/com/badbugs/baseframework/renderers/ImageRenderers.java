package com.badbugs.baseframework.renderers;

import com.badbugs.Game;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.dynamics.strikes.BaseScratch;
import com.badbugs.dynamics.strikes.BloodSplash;
import com.badbugs.dynamics.strikes.BloodSpot;
import com.badbugs.dynamics.strikes.OilSpot;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.bugs.BronzeBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.bugs.SteelBug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

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

        //        drawPolygon(knife.getPolygon().getTransformedVertices());
    }

    private static void renderBlood(SpriteBatch batch, Bug bug) throws Exception {

        BaseScratch[] scratches = ObjectsStore.getScratches(bug);

        if(scratches == null) return;

        float alpha = 1f;

        for(BaseScratch scratch: scratches){

            if(scratch == null) continue;

            if(scratch instanceof BloodSpot || scratch instanceof OilSpot){

                BloodSplash bloodSplash = ObjectsStore.getBloodSplash(bug);
                alpha = getAlpha(scratch);
                scratch.elapsedTime += Gdx.graphics.getDeltaTime();

                if (scratch.elapsedTime > Constants.BLOOD_SPOT_FADE_TIME) {
                    ObjectsStore.clearAllScratches(bug);
                    bug.dead = true;
                }

                if (bloodSplash != null) {
                    List<List<BasicObject>> listList = bloodSplash.getListOfBloodSprites();
                    for (List<BasicObject> list : listList) {
                        for (BasicObject bloodSprite : list) {
                            batch.setColor(1, 1, 1, alpha);
                            batch.draw(bloodSprite.getTexture(), bloodSprite.getPolygon().getX(), bloodSprite.getPolygon().getY(),
                                    bloodSprite.getCameraDimensions()[0], bloodSprite.getCameraDimensions()[1]);
                            batch.setColor(1, 1, 1, 1);
                        }
                    }
                }
            }

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

    private static void renderBug(SpriteBatch batch, Bug bedBug) throws Exception {

        Polygon bugPolygon = bedBug.getPolygon();

        BaseScratch[] scratches = ObjectsStore.getScratches(bedBug);
        if (scratches != null && scratches[0] instanceof BloodSpot) {
            BloodSpot bloodSpot = (BloodSpot) scratches[0];
            float alpha = getAlpha(bloodSpot);
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

    private static float getAlpha(BaseScratch bloodSpot) {
        float alpha;
        if (bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME < 1) {
            alpha = 1 - bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME;
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
