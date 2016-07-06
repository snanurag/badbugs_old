package com.badbugs.util;

import com.badbugs.dynamics.blood.BloodSplash;
import com.badbugs.dynamics.blood.BloodSpot;
import com.badbugs.objects.bugs.Bug;

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

  public static void removeBlood(Bug bug)
  {
    bloodSpotMap.remove(bug);
    bloodSplashMap.remove(bug);
  }



}
