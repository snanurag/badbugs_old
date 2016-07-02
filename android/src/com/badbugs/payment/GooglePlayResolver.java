package com.badbugs.payment;

import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;

public class GooglePlayResolver extends PlatformResolver {

  private final static String GOOGLEKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApjmuxFG+BIi2+1vSHExK0V4VqFCG+eB6Pq06WhWZXB7kBMJIZbezPDdS2Rj2OfKMc7WKvaooKNJFdZWf+cElxl6HJzychwWfOdrDCJ2HvzFDyGGg7I5ty5yzZCuHLU1a3Z9Mk3/b1RGCtQX8ioDAoeDyiU7czbv7iEX0juFD+OPlU7YoEIk3crmc6JP5B0tqkDQUSuoZxV+ymIZmdGkATd8GauBNJ3NLGFxSFwHqVY+JOpXAKx+U3wmNESy8Hn+uxyx3ImentD3jxyc1MwOdl54IHAbNfW8Lx6We1u0yhc6di3mhs/011ydtWKU5gXQuOoeKaPU1xbh/UvgPCmP2OQIDAQAB";
  static final int RC_REQUEST = 10001;    // (arbitrary) request code for the purchase flow

  public GooglePlayResolver() {

  }

  public void installIAP(PurchaseManager mgr, PurchaseObserver purchaseObserver, PurchaseManagerConfig config) {
    super.installIAP(mgr, purchaseObserver, config);
    config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLEKEY);
  }
}
