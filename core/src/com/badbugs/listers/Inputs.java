package com.badbugs.listers;

import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ashrinag on 3/6/2016.
 */
public class Inputs implements InputProcessor {

    public static boolean backPressed = false;

    public static boolean leftSwipe = false;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        TouchInfo touchInfo = new TouchInfo();
        touchInfo.touchX = screenX;
        touchInfo.touchY = screenY;
        Util.addToTouchEventsQueue(touchInfo);

        return true;
    }
//    public Inputs()
//    {
//        this(new SwipeListener());
//    }
//
//    /**
//     * Creates a new GestureDetector with default values: halfTapSquareSize=20, tapCountInterval=0.4f,
//     * longPressDuration=1.1f,
//     * maxFlingDelay=0.15f.
//     *
//     * @param listener
//     */
//    public Inputs(GestureListener listener) {
//        super(listener);
//    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            backPressed = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}