package com.badbugs.objects.bugs;

import com.badbugs.Game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 2/28/2016.
 */
public class BedBug extends Bug {

  //Pixels
//  private static int[][] BUG_CORDS = { { 142, -120 + 256 }, { 129, -108 + 256 }, { 128, -83 + 256 },
//          { 116, -68 + 256 }, { 107, -60 + 256 }, { 100, -58 + 256 }, { 93, -60 + 256 }, { 84, -68 + 256 },
//          { 72, -83 + 256 }, { 71, -108 + 256 }, { 58, -120 + 256 }, { 48, -140 + 256 }, { 41, -167 + 256 },
//          { 50, -206 + 256 }, { 100, -240 + 256 }, { 150, -206 + 256 }, { 159, -167 + 256 }, { 152, -140 + 256 } };
  private static int[][] BUG_CORDS = {{206, -205 + 450}, {200, -170 + 450}, {182, -147 + 450},
          {158, -133 + 450}, {133, -151 + 450}, {118, -173 + 450}, {116, -207 + 450},
          {104, -218 + 450}, {93, -234 + 450}, {81, -261 + 450}, {75, -284 + 450}, {73, -321 + 450},
          {85, -348 + 450}, {159, -398 + 450}, {184, -389 + 450}, {211, -371 + 450}, {231, -347 + 450},
          {242, -318 + 450}, {243, -298 + 450}, {239, -274 + 450}, {231, -248 + 450}, {221, -227 + 450}};

  //Pixels
  private static int[] BUG_CENTER = { 161, 225 };

  private static float BUG_HEIGHT = 12;
  private static float BUG_WIDTH = 12 * 11 / 14;

  private static float BUG_FRAME_RATE[] = {1 / 60f, 1 / 85f, 1 / 100f, 1 / 120f, 1 / 150f};

  public BedBug(Texture texture) {
    super(texture);
    pixelDimensions = new int[] { BUG_CENTER[0] * 2, BUG_CENTER[1] * 2 };
    cameraDimensions = new float[] {BUG_WIDTH, BUG_HEIGHT};
    screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
    cameraDimensions[1] * Game.screenHeight / Game.cam_height };

    init();
  }

  @Override public int[][] getPixelCoords(){
    return BUG_CORDS;
  }

  public float getInitialAngle()
  {
    return -90;
  }

  @Override
  public float getBugHeight() {
    return BUG_HEIGHT;
  }

  @Override
  public float getBugWidth() {
    return BUG_WIDTH;
  }

  @Override
  public float[] getFrameRate() {
    return BUG_FRAME_RATE;
  }


}
