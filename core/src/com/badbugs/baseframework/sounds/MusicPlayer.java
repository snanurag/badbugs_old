package com.badbugs.baseframework.sounds;

import com.badbugs.baseframework.elements.GameStates;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by ashrinag on 6/25/2016.
 */
public class MusicPlayer {

    static Music nature_music;
    static Music intro_music;

    static {
        nature_music = Gdx.audio.newMusic(Gdx.files.internal("musics/nature_music.ogg"));
        nature_music.setLooping(true);                // will repeat playback until music.stop() is called
        intro_music = Gdx.audio.newMusic(Gdx.files.internal("musics/intro_music.ogg"));
        intro_music.setLooping(true);                // will repeat playback until music.stop() is called
    }

    public static void playNatureMusic() {
        if (GameStates.isMusicOn())
            nature_music.play();
    }

    public static void stopNatureMusic() {
        nature_music.stop();

    }

    public static void playIntroMusic()
    {
        if (GameStates.isMusicOn())
            intro_music.play();
    }

    public static void stopIntroMusic()
    {
        intro_music.stop();
    }

    public static void pauseIntroMusic()
    {
        intro_music.pause();
    }

    public static void dispose() {
        nature_music.dispose();
        intro_music.dispose();
    }


}
