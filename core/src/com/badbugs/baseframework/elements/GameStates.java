package com.badbugs.baseframework.elements;

import com.badbugs.objects.BasicObject;
import com.badbugs.objects.knives.*;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.util.Constants;

/**
 * Created by ashrinag on 9/11/2016.
 */
public class GameStates {
    private static boolean musicOn = true;
    private static boolean soundOn = true;
    private static Knife selectedKnife = null;
    private static boolean demoOver = false;

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

    public static void setSelectedKnife(Constants.KNIFE_TYPE k) {

      GameStates.selectedKnife = ObjectsStore.getKnifeShadow(k);
    }

    public static BasicObject getPanel(){
        if(isBronzeKnifeAvailable() && isSteelKnifeAvailable()){
            if(selectedKnife instanceof StoneKnife || selectedKnife instanceof StoneKnifeTilted) return  ObjectsStore.getPanel(Constants.PANEL.BRONZE_STEEL);
            else if(selectedKnife instanceof BronzeKnife || selectedKnife instanceof BronzeKnifeTilted) return  ObjectsStore.getPanel(Constants.PANEL.STONE_STEEL);
            else if(selectedKnife instanceof SteelKnife || selectedKnife instanceof SteelKnifeTilted) return  ObjectsStore.getPanel(Constants.PANEL.STONE_BRONZE);
        }
        else if(isBronzeKnifeAvailable()){
            if(selectedKnife instanceof StoneKnife || selectedKnife instanceof StoneKnifeTilted)  return  ObjectsStore.getPanel(Constants.PANEL.BRONZE);
            else if(selectedKnife instanceof BronzeKnife || selectedKnife instanceof BronzeKnifeTilted)  return  ObjectsStore.getPanel(Constants.PANEL.STONE);
        }
        else if(isSteelKnifeAvailable()){
            if(selectedKnife instanceof StoneKnife || selectedKnife instanceof StoneKnifeTilted)  return  ObjectsStore.getPanel(Constants.PANEL.STEEL);
            else if(selectedKnife instanceof SteelKnife || selectedKnife instanceof SteelKnifeTilted)  return  ObjectsStore.getPanel(Constants.PANEL.STONE);
        }
        return ObjectsStore.getPanel(Constants.PANEL.EMPTY);
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
        return GamePurchaseObserver.isPurchased(Constants.bronze_knife);
    }

    public static boolean isSteelKnifeAvailable()
    {
        return GamePurchaseObserver.isPurchased(Constants.steel_knife);
    }

    public static boolean isKnifeBoosterAvailable(){
        return GamePurchaseObserver.isPurchased(Constants.double_speed);
    }
}
