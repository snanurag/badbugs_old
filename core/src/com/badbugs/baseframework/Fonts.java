package com.badbugs.baseframework;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.objects.GameOver;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by ashrinag on 4/28/2016.
 */
public class Fonts
{

  static BitmapFont font;

  public static void loadAllFonts()
  {
    loadScore();
  }

  private static void loadScore()
  {
    font = new BitmapFont(Gdx.files.internal("fonts/narkisim.fnt"), false);
    font.setColor(Color.YELLOW);
    font.getData().setScale(0.15f);

  }

  public static void renderScore(Batch batch, int score)
  {
    font.draw(batch, "Score " + score, Constants.SCORE_X_POS, Game.cam_height / 2, 10, -1, false);
  }

  public static void rendGameOverText(Batch batch, GameOver gameOver, int score)
  {
    float alpha = gameOver.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
    if (alpha <= 1)
    {
      font.setColor(1, 1, 0, alpha);
    }
    font.getData().setScale(0.2f);
    font.draw(batch, "Game Over", Constants.GAME_OVER_TEXT_X_POS, Constants.GAME_OVER_TEXT_Y_POS, 13, -1, false);
    font.draw(batch, "Score " + score, Constants.SCORE_TEXT_X_POS, Constants.SCORE_TEXT_Y_POS, 13, -1, false);
  }
}
