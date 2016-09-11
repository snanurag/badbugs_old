package com.badbugs.baseframework.elements;

import com.badbugs.dynamics.blood.BloodSplash;
import com.badbugs.dynamics.blood.BloodSpot;
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

//  static List<BasicObject> knifeList = new ArrayList<BasicObject>();
  private static List<Bug> bugList = new ArrayList<Bug>();
  private static Map<Bug, BloodSpot> bloodSpotMap = new HashMap<Bug, BloodSpot>();
  private static Map<Bug, BloodSplash> bloodSplashMap = new HashMap<Bug, BloodSplash>();
  private static Map<Constants.KNIFE_TYPE, Knife> knifeMap = new HashMap<Constants.KNIFE_TYPE, Knife>();

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

  public static void add(Bug bug, BloodSpot bloodSpot)
  {
    bloodSpotMap.put(bug, bloodSpot);
  }

  public static BloodSpot getBloodSpot(Bug bug)
  {
    return  bloodSpotMap.get(bug);
  }

  public static void add(Bug bug, BloodSplash bloodSplash)
  {
    bloodSplashMap.put(bug, bloodSplash);
  }

  public static BloodSplash getBloodSplash(Bug bug)
  {
    return  bloodSplashMap.get(bug);
  }

  public static void removeAllBlood(Bug bug)
  {
    bloodSpotMap.remove(bug);
    bloodSplashMap.remove(bug);
  }

  public static void add(Constants.KNIFE_TYPE t, Knife k) throws Exception
  {
    knifeMap.put(t,k);
    k.getPolygon().setPosition(0, 0);
    k.getPolygon().setOrigin(0, k.getCameraDimensions()[1]/2);
  }

  public static Knife getKnife(Constants.KNIFE_TYPE t)
  {
    return knifeMap.get(t);
  }

}
