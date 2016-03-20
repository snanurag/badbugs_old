package com.badbugs.objects;

import com.badbugs.util.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by ashrinag on 3/6/2016.
 */
public abstract class BasicObjectImpl implements BasicObject {

  protected int[] pixelDimensions; // = { ObjectsCord.BED_BUG_CENTER[0] * 2, ObjectsCord.BED_BUG_CENTER[1] * 2 };
  protected float[] cameraDimensions; // = { ObjectsCord.BED_BUG_WIDTH, ObjectsCord.BED_BUG_HEIGHT };
  protected float[] cameraCords;
  protected float[] screenPixels;
  protected  float[] screenDimensions;
  private Texture texture;
  protected Polygon polygon;

  public BasicObjectImpl(Texture texture)
  {
    this.texture = texture;
  }

  protected void init()
  {
    Util.createCameraCoordsFromPixelCords(this);
    if(this.getCameraCoords() != null)
    {
      polygon = new Polygon(this.getCameraCoords());
      polygon.setOrigin(this.getCameraDimensions()[0] / 2, this.getCameraDimensions()[1] / 2);
    }
//    Util.createScreenCordsFromCameraCords(this);

  }

  @Override public float[] getCameraCoords() {
    return cameraCords;
  }

  @Override public int[] getPixelDimensions() {
    return pixelDimensions;
  }

  @Override public float[] getCameraDimensions() {
    return cameraDimensions;
  }

  @Override public void setCameraCoords(float[] coords) {
    cameraCords = coords;
  }


  @Override public void setScreenPixels(float[] coords) {
    this.screenPixels = coords;
  }

  @Override public float[] getScreenPixels() {
    return screenPixels;
  }

  @Override public float[] getScreenDimension() {
    return screenDimensions;
  }

  @Override public Polygon getPolygon() throws Exception{
    if(polygon == null)
      throw new Exception("Polygon is created for this object.");
    else
      return polygon;
  }

  @Override public Texture getTexture() {
    return texture;
  }

  @Override public void setTexture(Texture texture) {
    this.texture = texture;
  }
}
