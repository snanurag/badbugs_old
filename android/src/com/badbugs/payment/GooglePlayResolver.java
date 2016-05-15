//package com.badbugs.payment;
//
//import com.badlogic.gdx.pay.PurchaseManagerConfig;
//
//public class GooglePlayResolver extends PlatformResolver {
//
//    private final static String GOOGLEKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiT5IscqHkWfa5Jss5n77OlNdkSxm3jpAA+WyZOI58/gEqNVkJcF5JJd3wWzSu5SwrFmjKWnwXLgTtaDJkVRzalN/i0HHAvYQJlpsPLGt4PmWRbVgPjbJA/KOGLW0o1Avavb/Doq/dEjbKO7zg839g4xYdiHnWHuYbAG88NQ+kd9sekUVHySL3vRPCnK/lGgbt5Y8SSWLiCUTOTwnR2iIxODpXraRbT/+lAm1t1Ac/WOhskAfYqerPetG2FKHcKBq5xNTd+C1A4Khzd75fkCvd2wXY+qWDtv21uHicmA95OCAPKdnIvM3/5naFnd3Nb5O6n+pUS0OK2z4HQ3rWzEbIQIDAQAB";
//
//
//    static final int RC_REQUEST = 10001;    // (arbitrary) request code for the purchase flow
//
//    public GooglePlayResolver(GdxPayExample game) {
//        super(game);
//
//        PurchaseManagerConfig config = game.purchaseManagerConfig;
//        config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLEKEY);
//        initializeIAP(null, game.purchaseObserver, config);
//    }
//}
