package com.badbugs.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by ashrinag on 2/22/2016.
 */
public class SpritePackerUtility {
  public static void main(String[] args) throws Exception {
//    String[] arg = {"D:\\Workspace\\badbugs2\\resources\\images\\bed_bug2", "D:\\Workspace\\badbugs2\\resources\\images\\bed_bug2", "sprite"};
    TexturePacker.Settings s = new TexturePacker.Settings();

    s.maxHeight = 2048;
    s.maxWidth = 2048;
    s.paddingY = 0;
    s.paddingX = 0;
    TexturePacker.process(s, "D:\\Workspace\\badbugs2\\resources\\images\\anim_lady_bug", "D:\\Workspace\\badbugs2" +
            "\\resources\\images\\anim_lady_bug", "lady_bug");
  }
}
