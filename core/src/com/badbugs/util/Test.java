package com.badbugs.util;

/**
 * Created by ashrinag on 2/23/2016.
 */
public class Test {
  private float getValFromZeroToMax(float max) {
    float randomFactor = (float) Math.random();
    return randomFactor * max;
  }

  public static void main(String[] args) {
        System.out.println((int)(2.9));
    Object i = null;
    while(i == null){
      System.out.println("hi");
      break;
    }
  }
}
