package com.badbugs.util;

import com.badbugs.dynamics.BloodSpot;
import com.badbugs.dynamics.movement.BugMovement;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.BloodSprite;
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
  static List<BasicObject> bugList = new ArrayList<BasicObject>();
  static Map<Bug, BugMovement> bugMovementMap = new HashMap<Bug, BugMovement>();
  static Map<Bug, BloodSpot> bloodSpillMap = new HashMap<Bug, BloodSpot>();
  static Map<BloodSpot, BloodSprite> bloodSpotBloodSpriteMap = new HashMap<BloodSpot, BloodSprite>();

  public static void add(Bug bug, BloodSpot bloodSpot)
  {
    bloodSpillMap.put(bug, bloodSpot);
  }

  public static void removeBlood(Bug bug)
  {
    bloodSpillMap.remove(bug);
  }

  public static BloodSpot getBloodSpot(Bug bug)
  {
    return  bloodSpillMap.get(bug);
  }

  public static void add(BloodSpot bloodSpot, BloodSprite bloodSprite)
  {
    bloodSpotBloodSpriteMap.put(bloodSpot, bloodSprite);
  }

  public static void removeBloodSprite(BloodSpot bloodSpot)
  {
    bloodSpotBloodSpriteMap.remove(bloodSpot);
  }

  public static BloodSprite getBloodSprite(BloodSpot bloodSpot)
  {
    return bloodSpotBloodSpriteMap.get(bloodSpot);
  }

  //  static void addBug(BasicObject bug)
//  {
//    bugList.add(bug);
//  }
//
//  static void addKnife(BasicObject knife)
//  {
//    knifeList.add(knife);
//  }
//
//  static void removeBug(BasicObject bug)
//  {
//    bugList.remove(bug);
//  }
//
//  static void removeKnife(BasicObject knife)
//  {
//    knifeList.remove(knife);
//  }


}