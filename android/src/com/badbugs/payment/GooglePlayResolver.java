package com.badbugs.payment;

import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;

public class GooglePlayResolver extends PlatformResolver {

  private final static String GOOGLEKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlQtZgFb25FgKMET9jBKeT4ltJA92PDorFzSpRM4Dd/c+V/orhetCD+QF2xvvhgTSokAp269bDB5K8Cr4ASEJplDAbGJPJRc3bep352ZWRiXUHJ9tp/JE8TJUZAxshjxminJ6lyJajHT6XbltHGXO0lQQtZtQ8I+BP1+S9dr8qFDt2q5l47XOpSgRoXvRLGXrNJmfqRFeT2GKXLWgA2FznMYZFo5MVEM1b33r9rNxvd1C59k+G+6Xvr5OQSUmfzSJxuAxPNIY6eNaUloN2hVFrdMKGpfeAHrcGCvfqUvU1QXSiEXgyVA2SMUkLnghdUr2CCBoJqYWs4D8oO1dVdcGbQIDAQAB";
  static final int RC_REQUEST = 10001;    // (arbitrary) request code for the purchase flow

  public GooglePlayResolver() {

  }

  public void installIAP(PurchaseManager mgr, PurchaseObserver purchaseObserver, PurchaseManagerConfig config) {
    super.installIAP(mgr, purchaseObserver, config);
    config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLEKEY);
  }
}
