package com.badbugs.baseframework;

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
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 4/28/2016.
 */
public class FontRenderers {

    static BitmapFont font_40;// = new BitmapFont(Gdx.files.internal("fonts/tahoma.fnt"), false);
    static BitmapFont font_30;// = new BitmapFont(Gdx.files.internal("fonts/tahoma.fnt"), false);

    static OrthographicCamera fontsCam;

    public static void loadAllFonts() {

        fontsCam = new OrthographicCamera(Game.screenWidth, Game.screenHeight);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/coiny-regular.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 48;
        font_40 = generator.generateFont(parameter); // font size 12 pixels
//        parameter.size = 10;
        font_30 = font_40; //tgenerator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

    }

    public static void renderScore(Batch batch, int score) {
        font_40.getData().setScale(Constants.GLOBAL_SCALE);
        batch.setProjectionMatrix(fontsCam.combined);
        font_40.draw(batch, "Bugs " + score, Constants.SCORE_X_POS * Game.screenWidth / Constants.HOME_SCREEN_W, Constants.SCORE_Y_POS * Game.screenHeight / Constants.HOME_SCREEN_H);//, 10, -1, false);
        batch.setProjectionMatrix(Game.cam.combined);

    }

    public static void rendGameOverText(Batch batch, GameOver gameOver, int score) {
        float alpha = gameOver.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
        if (alpha <= 1) {
            font_30.setColor(0, 0, 0, alpha);
        }
        font_30.getData().setScale(Constants.GLOBAL_SCALE);
        font_30.draw(batch, "Game Over", Constants.GAME_OVER_TEXT_X_POS, Constants.GAME_OVER_TEXT_Y_POS, 13, -1, false);
        font_30.draw(batch, "Bugs " + score, Constants.SCORE_TEXT_X_POS, Constants.SCORE_TEXT_Y_POS, 13, -1, false);
    }

    public static void renderBuyOptionText(Batch batch, GameOver buyOption) {
        float alpha = buyOption.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
        if (alpha <= 1) {
            font_30.setColor(0, 0, 0, alpha);
        }
        font_30.getData().setScale(Constants.GLOBAL_SCALE);

        font_30.draw(batch, Constants.BUY_FULL_VERSION, Constants.FULL_VER_TEXT_X_POS, Constants.FULL_VER_TEXT_Y_POS, 13, -1, false);

    }

    public static void renderText(Batch batch, Font f) {
        font_30.getData().setScale(f.getScale());
        Vector3 v = Game.cam.unproject(new Vector3(f.getX(), f.getY(), 0));
        batch.setProjectionMatrix(fontsCam.combined);
        font_30.draw(batch, f.getText(), v.x, v.y);
        batch.setProjectionMatrix(Game.cam.combined);

    }

}
