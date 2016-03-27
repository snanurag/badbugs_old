package com.badbugs.util;

/**
 * Created by ashrinag on 2/23/2016.
 */
public class Test {
  public static void main(String[] args) {
    double z = 1;
    System.out.println(z);
    System.out.println(System.currentTimeMillis());
    for(int i=0; i<1000000; i++)
    {
      z = z* 2.4;
    }
    System.out.println(z);
    System.out.println(System.currentTimeMillis());

  }
}
