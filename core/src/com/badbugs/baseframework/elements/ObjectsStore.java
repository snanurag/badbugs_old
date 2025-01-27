package com.badbugs.baseframework.elements;

import com.badbugs.Game;
import com.badbugs.dynamics.strikes.BaseScratch;
import com.badbugs.dynamics.strikes.Splash;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.bugs.Bug;
import com.badbugs.objects.knives.Knife;
import com.badbugs.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class ObjectsStore {

  private static List<Bug> bugList = new ArrayList<Bug>();
  private static List<Bug> hitBugList = new ArrayList<Bug>();
  private static Map<Bug, BaseScratch[]> scratchMap = new HashMap<Bug, BaseScratch[]>();
  private static Map<Bug, Splash> splashMap = new HashMap<Bug, Splash>();
  private static Map<Constants.KNIFE_TYPE, Knife> knifeMap = new HashMap<Constants.KNIFE_TYPE, Knife>();
  private static Map<Constants.KNIFE_SHADOW, Knife> knifeShadowMap = new HashMap<Constants.KNIFE_SHADOW, Knife>();
  private static Map<Constants.PANEL, BasicObject> panelMap = new HashMap<Constants.PANEL, BasicObject>();
  private static GameOver gameoverBackground;
  private static Bug[] lives;
  private static BasicObject floor;
  private static BasicObject googlePlay;
  private static BasicObject shop;
  private static BasicObject knifeBooster;
  private static BasicObject knifeBoosterDisabled;
  private static BasicObject back;
  private static BasicObject soundButton;
  private static BasicObject musicButton;
  private static BasicObject playButton;
  private static BasicObject shopButton;
  private static BasicObject quitButton;
  private static BasicObject mainMenuBackGround;
  private static BasicObject bronzeKnifeForShop;
  private static BasicObject bronzeKnifeForShopDisabled;
  private static BasicObject steelKnifeForShop;
  private static BasicObject steelKnifeForShopDisabled;

  public static int score = 0;
  public static int bugMissed = 0;

  public static void add(Bug bug)
  {
    synchronized (bugList)
    {
      bugList.add(bug);
    }
  }

  public static List<Bug> getBugList()
  {
    return  bugList;
  }

  public static void add(Bug bug, BaseScratch[] scratches){
    scratchMap.put(bug, scratches);
  }
  public static void add(Bug bug, int index, BaseScratch scratch)
  {
    if(scratchMap.get(bug)!=null) scratchMap.get(bug)[index] = scratch;
//    scratchMap.put(bug, scratch);
  }

  public static BaseScratch[] getScratches(Bug bug)
  {
    return  scratchMap.get(bug);
  }

  public static void add(Bug bug, Splash splash)
  {
    splashMap.put(bug, splash);
  }

  public static Splash getSplash(Bug bug)
  {
    return  splashMap.get(bug);
  }

  public static void clearAllScratches(Bug bug) {
    scratchMap.remove(bug);
    splashMap.remove(bug);
  }

  public static void add(Constants.KNIFE_TYPE t, Knife k) throws Exception {
    knifeMap.put(t, k);
    k.getPolygon().setPosition(0, 0);
    k.getPolygon().setOrigin(0, k.getCameraDimensions()[1] / 2);
  }

  public static Knife getKnifeShadow(Constants.KNIFE_TYPE t)
  {
    return knifeMap.get(t);
  }

  public static void add(Constants.KNIFE_SHADOW t, Knife k) throws Exception {
    knifeShadowMap.put(t, k);
    k.getPolygon().setPosition(Constants.SHADOW_LAG, Constants.SHADOW_LAG);
    k.getPolygon().setOrigin(0, k.getCameraDimensions()[1] / 2);
  }

  public static Knife getKnifeShadow(Constants.KNIFE_SHADOW t)
  {
    return knifeShadowMap.get(t);
  }

  public static void add(Constants.PANEL t, BasicObject k) throws Exception {
    panelMap.put(t, k);
    k.setCameraDimensions(new float[]{Constants.PANEL_WIDTH, Game.cam_height});
    k.getPolygon().setPosition(Game.cam_width / 2 - Constants.PANEL_ARROW_WIDTH, -Game.cam_height / 2);
  }

  public static BasicObject getPanel(Constants.PANEL t)
  {
    return panelMap.get(t);
  }

  public static void dispose() {
    for (BasicObject t : knifeMap.values()) t.getTexture().dispose();
    for (BasicObject t : panelMap.values()) t.getTexture().dispose();
    for (BasicObject t : knifeShadowMap.values()) t.getTexture().dispose();
  }

  public static GameOver getGameoverBackground() {
    return gameoverBackground;
  }

  public static void setGameoverBackground(GameOver gameoverBackground) {
    ObjectsStore.gameoverBackground = gameoverBackground;
  }

  public static Bug[] getLives() {
    return lives;
  }

  public static void setLives(Bug[] lives) {
    ObjectsStore.lives = lives;
  }

  public static BasicObject getFloor() {
    return floor;
  }

  public static void setFloor(BasicObject floor) {
    ObjectsStore.floor = floor;
  }

  public static BasicObject getGooglePlay() {
    return googlePlay;
  }

  public static void setGooglePlay(BasicObject googlePlay) {
    ObjectsStore.googlePlay = googlePlay;
  }

  public static BasicObject getShop() {
    return shop;
  }

  public static void setShop(BasicObject shop) {
    ObjectsStore.shop = shop;
  }

  public static BasicObject getKnifeBooster() {
    return knifeBooster;
  }

  public static void setKnifeBooster(BasicObject knifeBooster) {
    ObjectsStore.knifeBooster = knifeBooster;
  }

  public static BasicObject getBack() {
    return back;
  }

  public static void setBack(BasicObject back) {
    ObjectsStore.back = back;
  }

  public static BasicObject getSoundButton() {
    return soundButton;
  }

  public static void setSoundButton(BasicObject soundButton) {
    ObjectsStore.soundButton = soundButton;
  }

  public static BasicObject getMusicButton() {
    return musicButton;
  }

  public static void setMusicButton(BasicObject musicButton) {
    ObjectsStore.musicButton = musicButton;
  }

  public static BasicObject getPlayButton() {
    return playButton;
  }

  public static void setPlayButton(BasicObject playButton) {
    ObjectsStore.playButton = playButton;
  }

  public static BasicObject getShopButton() {
    return shopButton;
  }

  public static void setShopButton(BasicObject shopButton) {
    ObjectsStore.shopButton = shopButton;
  }

  public static BasicObject getQuitButton() {
    return quitButton;
  }

  public static void setQuitButton(BasicObject quitButton) {
    ObjectsStore.quitButton = quitButton;
  }

  public static BasicObject getMainMenuBackGround() {
    return mainMenuBackGround;
  }

  public static void setMainMenuBackGround(BasicObject mainMenuBackGround) {
    ObjectsStore.mainMenuBackGround = mainMenuBackGround;
  }

  public static List<Bug> getHitBugList() {
    return hitBugList;
  }

  public static void addHitBug(Bug bug){
      hitBugList.add(bug);
  }

  public static BasicObject getSteelKnifeForShop() {
    return steelKnifeForShop;
  }

  public static void setSteelKnifeForShop(BasicObject steelKnifeForShop) {
    ObjectsStore.steelKnifeForShop = steelKnifeForShop;
  }

  public static BasicObject getBronzeKnifeForShop() {
    return bronzeKnifeForShop;
  }

  public static void setBronzeKnifeForShop(BasicObject bronzeKnifeForShop) {
    ObjectsStore.bronzeKnifeForShop = bronzeKnifeForShop;
  }

  public static BasicObject getKnifeBoosterDisabled() {
    return knifeBoosterDisabled;
  }

  public static void setKnifeBoosterDisabled(BasicObject knifeBoosterDisabled) {
    ObjectsStore.knifeBoosterDisabled = knifeBoosterDisabled;
  }

  public static BasicObject getBronzeKnifeForShopDisabled() {
    return bronzeKnifeForShopDisabled;
  }

  public static void setBronzeKnifeForShopDisabled(BasicObject bronzeKnifeForShopDisabled) {
    ObjectsStore.bronzeKnifeForShopDisabled = bronzeKnifeForShopDisabled;
  }

  public static BasicObject getSteelKnifeForShopDisabled() {
    return steelKnifeForShopDisabled;
  }

  public static void setSteelKnifeForShopDisabled(BasicObject steelKnifeForShopDisabled) {
    ObjectsStore.steelKnifeForShopDisabled = steelKnifeForShopDisabled;
  }
}
