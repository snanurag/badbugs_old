package com.badbugs.baseframework.elements;

import com.badbugs.objects.knives.Knife;

/**
 * Created by ashrinag on 9/11/2016.
 */
public class GameStates {
    private static boolean musicOn = true;
    private static boolean soundOn = true;
    public static Knife selectedKnife = null;
    private static boolean demoOver = false;
    private static boolean isBronzeKnifeAvailable = false;
    private static boolean isSteelKnifeAvailable = false;

    public static boolean isMusicOn() {
      return musicOn;
    }

    public static void switchMusic() {
      if(musicOn)
        musicOn = false;
      else
        musicOn = true;
    }

    public static boolean isSoundOn() {
      return soundOn;
    }

    public static void switchSound(){
      if(soundOn)
        soundOn = false;
      else
        soundOn = true;
    }

    public static Knife getSelectedKnife() {
      return selectedKnife;
    }

    public static void setSelectedKnife(Knife selectedKnife) {
      GameStates.selectedKnife = selectedKnife;
    }

    public static void startDemo()
    {
      demoOver = false;
    }

    public static void endDemo()
    {
      demoOver = true;
    }

    public static boolean checkIfDemoOver()
    {
      return demoOver;
    }

    public static boolean isBronzeKnifeAvailable()
    {
        return isBronzeKnifeAvailable;
    }

    public static void setIsBronzeKnifeAvailable(boolean isBronzeKnifeAvailable)
    {
        GameStates.isBronzeKnifeAvailable = isBronzeKnifeAvailable;
    }

    public static boolean isSteelKnifeAvailable()
    {
        return isSteelKnifeAvailable;
    }

    public static void setIsSteelKnifeAvailable(boolean isSteelKnifeAvailable)
    {
        GameStates.isSteelKnifeAvailable = isSteelKnifeAvailable;
    }
}