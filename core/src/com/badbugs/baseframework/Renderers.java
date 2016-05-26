package com.badbugs.baseframework;

import com.badbugs.Game;
import com.badbugs.dynamics.BloodSpot;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.Shop;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class Renderers
{

  public static ShapeRenderer shapeRenderer;

  static
  {
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(Game.cam.combined);
    shapeRenderer.setAutoShapeType(true);
  }

  public static void renderKnife(SpriteBatch batch, Knife knife) throws Exception
  {
    Polygon knifePolygon = knife.getPolygon();

    Texture knifeTexture = knife.getTexture();

    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), 0, 0, knife.getCameraDimensions()[0],
        knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation(), 0, 0, knife.getPixelDimensions()[0],
        knife.getPixelDimensions()[1], false, false);

    //        drawPolygon(knife.getPolygon().getTransformedVertices());
  }

  private static void renderBlood(SpriteBatch batch, Bug bug) throws Exception
  {
    BloodSpot bloodSpot = ObjectsStore.getBloodSpot(bug);
    if (bloodSpot != null)
    {
      BloodSprite blood = ObjectsStore.getBloodSprite(bloodSpot);

      if (blood != null)
      {
        float widthScaleFactor = 2;
        Polygon polygon = blood.getPolygon();
        bloodSpot.elapsedTime += Gdx.graphics.getDeltaTime();
        if (bloodSpot.elapsedTime > Constants.BLOOD_SPOT_FADE_TIME)
        {
          ObjectsStore.removeBloodSprite(bloodSpot);
          ObjectsStore.removeBlood(bug);
          bug.dead = true;
        }

        Vector2 centerAfterRotation = Util
            .rotateVectorByGivenAngle(0, polygon.getOriginY() * widthScaleFactor, polygon.getRotation());

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

  public static void renderLives(SpriteBatch batch, Bug[] lives) throws Exception
  {
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

  public static void renderBugs(SpriteBatch batch) throws Exception
  {
    List<Bug> bugList = ObjectsStore.getBugList();
    synchronized (bugList)
    {
      for (Bug bedBug : bugList)
      {
        Renderers.renderBug(batch, bedBug);
      }
    }
  }

  public static void renderBloods(SpriteBatch batch) throws Exception
  {
    List<Bug> bugList = ObjectsStore.getBugList();
    synchronized (bugList)
    {
      for (Bug bedBug : bugList)
      {
        if (bedBug.hit)
        {
          Renderers.renderBlood(batch, bedBug);
        }
      }
    }
  }

  private static void renderBug(SpriteBatch batch, Bug bedBug) throws Exception
  {
    Polygon bugPolygon = bedBug.getPolygon();

    BloodSpot bloodSpot = ObjectsStore.getBloodSpot(bedBug);
    if (bloodSpot != null)
    {
      float alpha = getAlpha(bloodSpot);
      batch.setColor(1, 1, 1, alpha);
      bedBug.elapsedTime = 0;
    } else
    {
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

  public static void renderFloor(SpriteBatch batch)
  {
    batch.draw(SpritesCreator.floorTexture, -Game.cam_width / 2, -Game.cam_height / 2,
        Game.cam_width * SpritesCreator.floorTexture.getWidth() / Game.screenWidth,
        Game.cam_height * SpritesCreator.floorTexture.getHeight() / Game.screenHeight);
  }

  public static void renderHomePage(SpriteBatch batch)
  {
    batch.draw(SpritesCreator.mainMenuTexture, -Game.cam_width / 2, -Game.cam_height / 2, Game.cam_width,
        Game.cam_height);
  }

  public static void renderGameOverBackground(SpriteBatch batch, GameOver gameOver) throws Exception
  {
    gameOver.elapsedTime += Gdx.graphics.getDeltaTime();
    float alpha = gameOver.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
    if (alpha < 1)
    {
      batch.setColor(1, 1, 1, alpha);
    }
    batch.draw(gameOver.getTexture(), -gameOver.getCameraDimensions()[0] / 2, -gameOver.getCameraDimensions()[1] / 2,
        gameOver.getCameraDimensions()[0], gameOver.getCameraDimensions()[1]);
    batch.setColor(1, 1, 1, 1);
  }

  public static void renderShopScreen(SpriteBatch batch, Shop shop)
  {
    batch.draw(shop.getTexture(), -shop.getCameraDimensions()[0] / 2, -shop.getCameraDimensions()[1] / 2,
        shop.getCameraDimensions()[0], shop.getCameraDimensions()[1]);
  }

  private static TextureRegion getProperBloodTexReg(float bloodSpotLen)
  {
    if (bloodSpotLen < 2)
      return SpritesCreator.bloodTextureRegionSmall;
    else if (bloodSpotLen < 6)
      return SpritesCreator.bloodTextureRegionMedium;
    else
      return SpritesCreator.bloodTextureRegionLong;
  }

  private static float getAlpha(BloodSpot bloodSpot)
  {
    float alpha;
    if (bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME < 1)
    {
      alpha = 1 - bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME;
    } else
    {
      alpha = 0f;
    }
    return alpha;
  }

  @Deprecated
  public static void drawPolygon(float[] vertices)
  {
    try
    {
      //      if (start)
      shapeRenderer.setColor(1, 1, 0, 1);
      if (vertices.length >= 6)
        shapeRenderer.polygon(vertices);
      else if (vertices.length == 2)
        shapeRenderer.point(vertices[0], vertices[1], 0);
      //shapeRenderer.line();
      // if (end)
      //   shapeRenderer.end();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Deprecated
  public static void drawLine(float x, float y, float x1, float y1)
  {
    shapeRenderer.line(x, y, x1, y1);
  }

  @Deprecated
  public static void drawCircle(float x, float y, float radius)
  {
    shapeRenderer.circle(x, y, radius);
  }

}
