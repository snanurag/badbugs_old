package com.badbugs.baseframework.renderers;

import com.badbugs.Game;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.fonts.Font;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by ashrinag on 4/28/2016.
 */
public class FontRenderers {

    static BitmapFont font_48;// = new BitmapFont(Gdx.files.internal("fonts/tahoma.fnt"), false);
    static BitmapFont font_60;// = new BitmapFont(Gdx.files.internal("fonts/tahoma.fnt"), false);
    static BitmapFont font_72;
    static OrthographicCamera fontsCam;

    public static void loadAllFonts() {

        fontsCam = new OrthographicCamera(Game.screenWidth, Game.screenHeight);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/GoodDog.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 48;
        font_48 = generator.generateFont(parameter); // font size 12 pixels
        parameter.size = 60;
        font_60 = generator.generateFont(parameter); // font size 12 pixels
        parameter.size = 72;
        font_72 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

    }

    public static void renderScore(Batch batch, int score) {
        batch.setProjectionMatrix(fontsCam.combined);
        font_48.draw(batch, "Bugs " + score, Constants.SCORE_X_POS * Game.screenWidth / Constants.HOME_SCREEN_W, Constants.SCORE_Y_POS * Game.screenHeight / Constants.HOME_SCREEN_H);//, 10, -1, false);
        batch.setProjectionMatrix(Game.cam.combined);

    }

    public static void rendGameOverText(Batch batch, GameOver gameOver, int score) {
        float alpha = gameOver.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
        if (alpha <= 1) {
            font_72.setColor(0, 0, 0, alpha);
        }
        batch.setProjectionMatrix(fontsCam.combined);
        font_72.draw(batch, "Game Over", Constants.GAME_OVER_TEXT_X_POS * Game.screenWidth / Constants.HOME_SCREEN_W, Constants.GAME_OVER_TEXT_Y_POS * Game.screenHeight / Constants.HOME_SCREEN_H);//, 13, -1, false);
        font_72.draw(batch, "Bugs " + score, Constants.SCORE_TEXT_X_POS * Game.screenWidth / Constants.HOME_SCREEN_W, Constants.SCORE_TEXT_Y_POS * Game.screenHeight / Constants.HOME_SCREEN_H);//, 13, -1, false);
        batch.setProjectionMatrix(Game.cam.combined);
    }

    public static void renderBuyOptionText(Batch batch, GameOver buyOption) {
        float alpha = buyOption.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;

        if (alpha <= 1) {
            font_60.setColor(0, 0, 0, alpha);
        }
        font_60.draw(batch, Constants.BUY_FULL_VERSION, Constants.FULL_VER_TEXT_X_POS, Constants.FULL_VER_TEXT_Y_POS);

    }

    public static void renderText(Batch batch, Font f) {
        batch.setProjectionMatrix(fontsCam.combined);
        font_60.draw(batch, f.getText(), f.getX() * Game.screenWidth / Constants.HOME_SCREEN_W, f.getY() * Game.screenHeight / Constants.HOME_SCREEN_H);
        batch.setProjectionMatrix(Game.cam.combined);

    }

}
