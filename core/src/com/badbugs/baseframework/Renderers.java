package com.badbugs.baseframework;

import com.badbugs.MainClass;
import com.badbugs.dynamics.movement.KnifeMovement;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.bugs.BedBug;
import com.badbugs.objects.knives.SilverKnife;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class Renderers {

  // Polygon knifePolygon;
  static float elapsedTime = 0;
  static double angle;

  public static void renderKnife(SpriteBatch batch, SilverKnife knife) throws Exception {
    //    elapsedTime += Gdx.graphics.getDeltaTime();
    //
    //    float pos_x = cam_width / 2 - BUG_SPEED * elapsedTime % (cam_width);

    Polygon knifePolygon = knife.getPolygon();

    KnifeMovement.updatePolygon(knife);
    Texture knifeTexture = knife.getTexture();
//    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), knifePolygon.getOriginX(),
//        knifePolygon.getOriginY(), knife.getCameraDimensions()[0], knife.getCameraDimensions()[1], 1, 1,
//        knifePolygon.getRotation(), 0, 0, knife.getPixelDimensions()[0], knife.getPixelDimensions()[1], false, false);
    batch.draw(knifeTexture, knifePolygon.getX(), knifePolygon.getY(), 0,
        0, knife.getCameraDimensions()[0], knife.getCameraDimensions()[1], 1, 1,
        knifePolygon.getRotation(), 0, 0, knife.getPixelDimensions()[0], knife.getPixelDimensions()[1], false, false);

  }

  public static void renderBug(SpriteBatch batch, BasicObject bedBug) throws Exception {
    elapsedTime += Gdx.graphics.getDeltaTime();

    float pos_y = MainClass.cam_height / 2 - MainClass.BUG_SPEED * elapsedTime % (MainClass.cam_height);

    Polygon bugPolygon = bedBug.getPolygon();
    bedBug.getPolygon().setPosition(-MainClass.cam_width / 2, -MainClass.cam_height / 2);

    bedBug.setTexture(ObjectBuilders.bugAnimations.getKeyFrame(elapsedTime, true).getTexture());
    batch.draw(ObjectBuilders.bugAnimations.getKeyFrame(elapsedTime, true), bugPolygon.getX(), bugPolygon.getY(),
        bugPolygon.getOriginX(), bugPolygon.getOriginY(), bedBug.getCameraDimensions()[0],
        bedBug.getCameraDimensions()[1], 1, 1, ((BedBug) bedBug).getInitialAngle());
  }

  private static void drawPolygon(BasicObject basicObject) {

    try {
      ShapeRenderer shapeRenderer = new ShapeRenderer();
      shapeRenderer.setProjectionMatrix(MainClass.cam.combined);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      shapeRenderer.setColor(1, 1, 0, 1);
      shapeRenderer.polygon(basicObject.getPolygon().getTransformedVertices());
      shapeRenderer.end();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
