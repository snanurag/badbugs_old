package com.badbugs.objects.fonts;

/**
 * Created by ashrinag on 6/17/2016.
 */
public class Font {

    public Font(String text, float x, float y)
    {
        this.text = text;
//        this.scale = scale;
        this.x = x;
        this.y = y;
    }

    private String text;
    private float x;
    private float y;
//    private float scale;

//    public float getScale() {
//        return scale;
//    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public String getText() {
        return text;
    }
}
