package com.badbugs.baseframework;

import com.badbugs.MainClass;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badbugs.util.Constants;
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

  public static void renderKnife(SpriteBatch batch, SilverKnife knife) throws Exception {

    Polygon knifePolygon = knife.getPolygon();

    KnifeMovement.updatePolygon(knife);
    Texture knifeTexture = knife.getTexture();
    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), 0, 0, knife.getCameraDimensions()[0],
        knife.getCameraDimensions()[1], 1, 1, knifePolygon.getRotation(), 0, 0, knife.getPixelDimensions()[0],
        knife.getPixelDimensions()[1], false, false);

    //    drawPolygon(knife);
  }

  public static void renderBug(SpriteBatch batch, BasicObject bedBug) throws Exception {
    elapsedTime += Gdx.graphics.getDeltaTime();

    float pos_y = MainClass.cam_height / 2 - MainClass.BUG_SPEED * elapsedTime % (MainClass.cam_height);

    Polygon bugPolygon = bedBug.getPolygon();
    bedBug.getPolygon().setPosition(-MainClass.cam_width / 2, -MainClass.cam_height / 2);

    bedBug.setTexture(SpritesCreator.bugAnimations.getKeyFrame(elapsedTime, true).getTexture());
    batch.draw(SpritesCreator.bugAnimations.getKeyFrame(elapsedTime, true), bugPolygon.getX(), bugPolygon.getY(),
        bugPolygon.getOriginX(), bugPolygon.getOriginY(), bedBug.getCameraDimensions()[0],
        bedBug.getCameraDimensions()[1], 1, 1, ((BedBug) bedBug).getInitialAngle());

    //    drawPolygon(bedBug.getPolygon().getTransformedVertices());
  }

  public static void renderBlood(SpriteBatch batch, BasicObject blood, float elapsedTime) throws Exception {
    Texture texture = blood.getTexture();
    Polygon polygon = blood.getPolygon();

    float alpha;
    if (elapsedTime / Constants.BLOOD_SPOT_FADE_TIME < 1) {
      alpha = 1 - elapsedTime / Constants.BLOOD_SPOT_FADE_TIME;
    } else {
      alpha = 1f;
    }
    //    batch.setColor(1, 1, 1, alpha);
    batch.draw(texture, polygon.getX(), polygon.getY(), 0, 0, blood.getCameraDimensions()[0],
        blood.getCameraDimensions()[1], 1, 1, polygon.getRotation(), 0, 0, 200, 20, false, false);

    System.out.println("Position of blood spot x " + polygon.getX() + " and y " + polygon.getY());
    //    batch.setColor(1, 1, 1, alpha);
  }

  public static void drawPolygon(float[] vertices) {

    try {
      ShapeRenderer shapeRenderer = new ShapeRenderer();
      shapeRenderer.setProjectionMatrix(MainClass.cam.combined);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      shapeRenderer.setColor(1, 1, 0, 1);
      if (vertices.length > 6)
        shapeRenderer.polygon(vertices);
      else if (vertices.length == 2)
        shapeRenderer.point(vertices[0], vertices[1], 0);
      shapeRenderer.end();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
