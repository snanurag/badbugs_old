package com.badbugs.baseframework;

import com.badbugs.objects.BasicObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashrinag on 3/20/2016.
 */
public class ObjectsStore {

  static List<BasicObject> knifeList = new ArrayList<BasicObject>();
  static List<BasicObject> bugList = new ArrayList<BasicObject>();

  static void addBug(BasicObject bug)
  {
    bugList.add(bug);
  }

  static void addKnife(BasicObject knife)
  {
    knifeList.add(knife);
  }

  static void removeBug(BasicObject bug)
  {
    bugList.remove(bug);
  }

  static void removeKnife(BasicObject knife)
  {
    knifeList.remove(knife);
  }
}
