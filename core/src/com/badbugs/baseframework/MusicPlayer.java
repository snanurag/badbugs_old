package com.badbugs.baseframework;

import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by ashrinag on 6/25/2016.
 */
public class MusicPlayer {

    static Music nature_music;

    static {
        nature_music = Gdx.audio.newMusic(Gdx.files.internal("musics/nature_music.ogg"));
        nature_music.setLooping(true);                // will repeat playback until music.stop() is called
        nature_music.setVolume(2.0f);
    }

    public static void playNatureMusic() {
        if (Util.isMusicOn())
            nature_music.play();
    }

    public static void stopNatureMusic() {
        nature_music.stop();

    }

    public static void dispose() {
        nature_music.dispose();
    }


}
