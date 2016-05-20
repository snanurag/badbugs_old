package com.badbugs.payment;

import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;

/**
 * Created by ashrinag on 5/20/2016.
 */
public class PlatformBuilder {
  private static PlatformResolver platformResolver;
  public static PurchaseManager purchaseManager;
  public static PurchaseObserver purchaseObserver;
  public static PurchaseManagerConfig purchaseManagerConfig;

  public static void setPlatformResolver(PlatformResolver platformResolver) {
    PlatformBuilder.platformResolver = platformResolver;
  }

  public static void setComponents(PurchaseManager mgr, PurchaseObserver observer,
      PurchaseManagerConfig config) {
    purchaseManager = mgr;
    purchaseObserver = observer;
    purchaseManagerConfig = config;
  }

  public static void build() throws Exception
  {
    if(platformResolver != null)
    {
      platformResolver.installIAP(purchaseManager, purchaseObserver, purchaseManagerConfig);
      platformResolver.installIAP();
    }
    else
    {
      throw new Exception("Error in building Platform Resolver");
    }
  }

  public static PlatformResolver getPlatformResolver() {
    return platformResolver;
  }
}
