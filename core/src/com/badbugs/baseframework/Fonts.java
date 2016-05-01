package com.badbugs.baseframework;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by ashrinag on 4/28/2016.
 */
public class Fonts {

  static BitmapFont font;

  public static void loadAllFonts()
  {
    loadScore();
  }

  private static void loadScore()
  {
    font = new BitmapFont(Gdx.files.internal("fonts/narkisim.fnt"),false);
    font.setColor(Color.YELLOW);
    font.getData().setScale(0.15f);

  }

  public static void renderScore(Batch batch, int score)
  {
    font.draw(batch, "Score "+score, Constants.SCORE_X_POS, Game.cam_height/2,  10, -1, false);

  }
}
