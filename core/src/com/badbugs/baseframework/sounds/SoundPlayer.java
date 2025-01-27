package com.badbugs.baseframework.sounds;

import com.badbugs.baseframework.elements.GameStates;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by ashrinag on 6/25/2016.
 */
public class SoundPlayer {

    private static Sound gameOver;
    private static Sound knifeWoodImpact;
    private static Sound knifeBugImpact;
    private static Sound knifeSlash;
    private static Sound buttonClick;

    public static void loadAllSounds() {
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sounds/game_over.ogg"));
        knifeWoodImpact = Gdx.audio.newSound(Gdx.files.internal("sounds/knife_wood_impact.ogg"));
        knifeSlash = Gdx.audio.newSound(Gdx.files.internal("sounds/knife_slash.ogg"));
        knifeBugImpact = Gdx.audio.newSound(Gdx.files.internal("sounds/knife_bug_impact.ogg"));
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/button_click.ogg"));

    }

    public static void playGameOver() {
        if (GameStates.isSoundOn())
            gameOver.play(1.0f);
    }

    public static void playKnifeWoodImpact() {
        if (GameStates.isSoundOn())
            knifeWoodImpact.play(0.5f);
    }

    public static void playKnifeSlash() {
        if (GameStates.isSoundOn())
            knifeSlash.play(1.5f);
    }

    public static void playKnifeBugImpact() {
        if (GameStates.isSoundOn())
            knifeBugImpact.play(2.0f);
    }

    public static void playButtonClick() {
        if (GameStates.isSoundOn())
            buttonClick.play(1.0f);
    }

    public static void disposeAll() {
        gameOver.dispose();
        knifeWoodImpact.dispose();
        knifeSlash.dispose();
        knifeBugImpact.dispose();
        buttonClick.dispose();

    }
}
