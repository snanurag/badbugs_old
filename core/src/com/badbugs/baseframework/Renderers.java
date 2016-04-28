package com.badbugs.baseframework;

import com.badbugs.MainClass;
import com.badbugs.dynamics.BloodSpot;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class Renderers {

  static float elapsedTime = 0;
  public static ShapeRenderer shapeRenderer;

  static {
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(MainClass.cam.combined);
    shapeRenderer.setAutoShapeType(true);

  }

  public static void renderKnife(SpriteBatch batch, SilverKnife knife) throws Exception {

    Polygon knifePolygon = knife.getPolygon();

    Texture knifeTexture = knife.getTexture();

    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), 0, 0, knife.getCameraDimensions()[0],
        knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation(), 0, 0, knife.getPixelDimensions()[0],
        knife.getPixelDimensions()[1], false, false);

    //    drawPolygon(knife.getPolygon().getTransformedVertices());
  }

  public static void renderBugs(SpriteBatch batch, Knife knife) throws Exception {
    List<Bug> bugList = ObjectsStore.getBugList();

    Util.globalLogger().debug("no of bugs " + bugList.size());

    synchronized (bugList) {
      Iterator<Bug> itr = bugList.iterator();
      while (itr.hasNext()) {
        Bug bedBug = itr.next();
        Util.globalLogger().debug(
            "Bug position of bug : " + bedBug.id + " x " + bedBug.getPolygon().getX() + " and y " + bedBug.getPolygon()
                .getY());

        if (bedBug.dead) {
          itr.remove();
          continue;
        }

        Renderers.renderBug(batch, bedBug);

        Vector2 bugCenter = Util.getPolygonCenter(bedBug.getPolygon());

        Vector2 currentState = Util.getStateOfBugWRTKnife(bugCenter.x, bugCenter.y, knife.getPolygon());

        if (!bedBug.hit && bedBug.state != null && !bedBug.compareState((int) currentState.x, (int) currentState.y)) {

          bedBug.hit = true;
          ObjectsStore.score++;
        }

        if (bedBug.hit) {
          Renderers.renderBlood(batch, bedBug);
          continue;
        }

        if (bedBug.freeze_frame_count < 0 || bedBug.freeze_frame_count > Constants.FREEZE_FRAME_COUNTS) {
          BugMovement.applyMovement(bedBug);
          bedBug.freeze_frame_count = -1;
        } else
          bedBug.freeze_frame_count++;
      }

    }

  }

  public static void renderFloor(SpriteBatch batch) {
    batch.draw(SpritesCreator.floorTexture, -MainClass.cam_width / 2, -MainClass.cam_height / 2,
        MainClass.cam_width * SpritesCreator.floorTexture.getWidth() / MainClass.screenWidth,
        MainClass.cam_height * SpritesCreator.floorTexture.getHeight() / MainClass.screenHeight);
  }

  private static void renderBlood(SpriteBatch batch, Bug bug) throws Exception {

    BloodSpot bloodSpot = ObjectsStore.getBloodSpot(bug);
    if (bloodSpot != null) {
      BloodSprite blood = ObjectsStore.getBloodSprite(bloodSpot);
      if (blood != null) {

        float widthScaleFactor = 2;

        Polygon polygon = blood.getPolygon();

        bloodSpot.elapsedTime += Gdx.graphics.getDeltaTime();

        if (bloodSpot.elapsedTime > Constants.BLOOD_SPOT_FADE_TIME) {
          ObjectsStore.removeBloodSprite(bloodSpot);
          ObjectsStore.removeBlood(bug);
          bug.dead = true;
        }

        Vector2 centerAfterRotation = Util
            .getVectorAfterRotation(0, polygon.getOriginY() * widthScaleFactor, polygon.getRotation());

        float alpha = getAlpha(bloodSpot);

        batch.setColor(1, 1, 1, alpha);

        TextureRegion textureRegion = getProperBloodTexReg(blood.getCameraDimensions()[0]);

        batch.draw(textureRegion, polygon.getX() - centerAfterRotation.x, polygon.getY() - centerAfterRotation.y, 0, 0,
            blood.getCameraDimensions()[0], blood.getCameraDimensions()[1] * widthScaleFactor, 1, 1,
            polygon.getRotation());

        batch.setColor(1, 1, 1, 1);

      }
    }
  }

  public static void renderLives(SpriteBatch batch) throws Exception {

    if (ObjectsStore.bugMissed <= 4)
      renderBug(batch, SpritesCreator.loadLife(Constants.LIFE_1_X_POS));
    if (ObjectsStore.bugMissed <= 3)
      renderBug(batch, SpritesCreator.loadLife(Constants.LIFE_2_X_POS));
    if (ObjectsStore.bugMissed <= 2)
      renderBug(batch, SpritesCreator.loadLife(Constants.LIFE_3_X_POS));
    if (ObjectsStore.bugMissed <= 1)
      renderBug(batch, SpritesCreator.loadLife(Constants.LIFE_4_X_POS));
    if (ObjectsStore.bugMissed <= 0)
      renderBug(batch, SpritesCreator.loadLife(Constants.LIFE_5_X_POS));

  }

  private static void renderBug(SpriteBatch batch, Bug bedBug) throws Exception {

    Polygon bugPolygon = bedBug.getPolygon();
    BloodSpot bloodSpot = ObjectsStore.getBloodSpot(bedBug);
    float alpha;
    if (bloodSpot != null) {
      alpha = getAlpha(bloodSpot);
      batch.setColor(1, 1, 1, alpha);
    }

    if (!bedBug.hit)
      bedBug.elapsedTime += Gdx.graphics.getDeltaTime();

    //    bedBug.setTexture(bedBug.animation.getKeyFrame(bedBug.elapsedTime, true).getTexture());

    if (bedBug.getTexture() == null)
      batch.draw(bedBug.animation.getKeyFrame(bedBug.elapsedTime, true), bugPolygon.getX(), bugPolygon.getY(),
          bugPolygon.getOriginX(), bugPolygon.getOriginY(), bedBug.getCameraDimensions()[0],
          bedBug.getCameraDimensions()[1], 1, 1, bedBug.getPolygon().getRotation());
    else
      batch.draw(bedBug.getTexture(), bugPolygon.getX(), bugPolygon.getY(), bedBug.getCameraDimensions()[0],
          bedBug.getCameraDimensions()[1]);

    batch.setColor(1, 1, 1, 1);

    //    drawCircle(0, 0, 0.2f);
    //    drawPolygon(bedBug.getPolygon().getTransformedVertices());
  }

  private static TextureRegion getProperBloodTexReg(float bloodSpotLen) {
    if (bloodSpotLen < 2)
      return SpritesCreator.bloodTextureRegionSmall;
    else if (bloodSpotLen < 6)
      return SpritesCreator.bloodTextureRegionMedium;
    else
      return SpritesCreator.bloodTextureRegionLong;
  }

  private static float getAlpha(BloodSpot bloodSpot) {
    float alpha;
    if (bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME < 1) {
      alpha = 1 - bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME;
    } else {
      alpha = 0f;
    }
    return alpha;
  }

  @Deprecated public static void drawPolygon(float[] vertices) {

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

  @Deprecated public static void drawLine(float x, float y, float x1, float y1) {
    shapeRenderer.line(x, y, x1, y1);
  }

  @Deprecated public static void drawCircle(float x, float y, float radius) {
    shapeRenderer.circle(x, y, radius);
  }

}
