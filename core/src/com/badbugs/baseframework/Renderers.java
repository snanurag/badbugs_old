package com.badbugs.baseframework;

import com.badbugs.MainClass;
import com.badbugs.dynamics.BloodSpot;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BloodSprite;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
import com.badbugs.util.ObjectsStore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class Renderers {

  static float elapsedTime = 0;
  static ShapeRenderer shapeRenderer;

  static {
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(MainClass.cam.combined);

  }

  public static void renderKnife(SpriteBatch batch, SilverKnife knife) throws Exception {

    Polygon knifePolygon = knife.getPolygon();

    Texture knifeTexture = knife.getTexture();
    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), 0, 0, knife.getCameraDimensions()[0],
        knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation(), 0, 0, knife.getPixelDimensions()[0],
        knife.getPixelDimensions()[1], false, false);

    //    drawPolygon(knife.getPolygon().getTransformedVertices(), false, true);
  }

  public static void renderBug(SpriteBatch batch, BasicObject bedBug) throws Exception {

    Polygon bugPolygon = bedBug.getPolygon();
    elapsedTime += Gdx.graphics.getDeltaTime();

    bedBug.setTexture(SpritesCreator.bugAnimations.getKeyFrame(elapsedTime, true).getTexture());
    batch.draw(SpritesCreator.bugAnimations.getKeyFrame(elapsedTime, true), bugPolygon.getX(), bugPolygon.getY(),
        bugPolygon.getOriginX(), bugPolygon.getOriginY(), bedBug.getCameraDimensions()[0],
        bedBug.getCameraDimensions()[1], 1, 1, ((BedBug) bedBug).getInitialAngle());

    //    drawPolygon(bedBug.getPolygon().getTransformedVertices(), true, false);
  }

  public static void renderBlood(SpriteBatch batch, Bug bug) throws Exception {

    BloodSpot bloodSpot = ObjectsStore.getBloodSpot(bug);
    BloodSprite blood = ObjectsStore.getBloodSprite(bloodSpot);

    Texture texture = blood.getTexture();
    Polygon polygon = blood.getPolygon();

    bloodSpot.elapsedTime += Gdx.graphics.getDeltaTime();

    if (bloodSpot.elapsedTime > Constants.BLOOD_SPOT_FADE_TIME) {
      ObjectsStore.removeBloodSprite(bloodSpot);
      ObjectsStore.removeBlood(bug);
    }

    float alpha;
    if (bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME < 1) {
      alpha = 1 - bloodSpot.elapsedTime / Constants.BLOOD_SPOT_FADE_TIME;
    } else {
      alpha = 1f;
    }
    //    batch.setColor(1, 1, 1, alpha);
    batch.draw(SpritesCreator.bloodTextureRegion, polygon.getX(), polygon.getY(),
        0, 0, blood.getCameraDimensions()[0],
        blood.getCameraDimensions()[1], 1, 1, polygon.getRotation());

//    batch.draw(texture, polygon.getX(), polygon.getY(), 0, 0, blood.getCameraDimensions()[0],
//        blood.getCameraDimensions()[1], 1, 1, polygon.getRotation(), 0, 0, 0, 0, false, false);

    System.out.println(
        "Position of blood spot x " + polygon.getX() + " and y " + polygon.getY() + " length of blood spot " + blood
            .getCameraDimensions()[0]);
    //    batch.setColor(1, 1, 1, alpha);
  }

  public static void renderFloor(SpriteBatch batch) {
    batch.draw(SpritesCreator.floorTexture, -MainClass.cam_width / 2, -MainClass.cam_height / 2,
        MainClass.cam_width * SpritesCreator.floorTexture.getWidth() / MainClass.screenWidth,
        MainClass.cam_height * SpritesCreator.floorTexture.getHeight() / MainClass.screenHeight);
  }

  public static void drawPolygon(float[] vertices, boolean start, boolean end) {

    try {
      if (start)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      shapeRenderer.setColor(1, 1, 0, 1);
      if (vertices.length >= 6)
        shapeRenderer.polygon(vertices);
      else if (vertices.length == 2)
        shapeRenderer.point(vertices[0], vertices[1], 0);
      if (end)
        shapeRenderer.end();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
