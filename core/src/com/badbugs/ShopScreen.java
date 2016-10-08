package com.badbugs;

import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.baseframework.renderers.FontRenderers;
import com.badbugs.baseframework.renderers.ImageRenderers;
import com.badbugs.baseframework.sounds.MusicPlayer;
import com.badbugs.baseframework.sounds.SoundPlayer;
import com.badbugs.listers.Inputs;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.fonts.Font;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.payment.PlatformBuilder;
import com.badbugs.util.Constants;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;

/**
 * Created by ashrinag on 5/14/2016.
 */
public class ShopScreen extends ScreenAdapter {

    private float[] KNIFE_BOOSTER_BUTTON = new float[]{Constants.KNIFE_BOOSTER_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.KNIFE_BOOSTER_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.KNIFE_BOOSTER_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.KNIFE_BOOSTER_H * Game.screenHeight / Constants.HOME_SCREEN_H};

    private float[] BRONZE_KNIFE_BUTTON = new float[]{Constants.BRONZE_KNIFE_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.BRONZE_KNIFE_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.BRONZE_KNIFE_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.BRONZE_KNIFE_H * Game.screenHeight / Constants.HOME_SCREEN_H};

    private float[] STEEL_KNIFE_BUTTON = new float[]{Constants.STEEL_KNIFE_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.STEEL_KNIFE_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
            Constants.STEEL_KNIFE_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.STEEL_KNIFE_H * Game.screenHeight / Constants.HOME_SCREEN_H};

    private float[] BACK_BUTTON = new float[]{Constants.BACK_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.BACK_TOP * Game.screenHeight / Constants.HOME_SCREEN_H, Constants.BACK_W * Game.screenWidth / Constants.HOME_SCREEN_W,
            Constants.BACK_H * Game.screenHeight / Constants.HOME_SCREEN_H};

    private Rectangle knifeBoosterBounds = new Rectangle(KNIFE_BOOSTER_BUTTON[0], KNIFE_BOOSTER_BUTTON[1], KNIFE_BOOSTER_BUTTON[2],
            KNIFE_BOOSTER_BUTTON[3]);
    private Rectangle bronzeKnifeBounds = new Rectangle(BRONZE_KNIFE_BUTTON[0], BRONZE_KNIFE_BUTTON[1], BRONZE_KNIFE_BUTTON[2],
            BRONZE_KNIFE_BUTTON[3]);
    private Rectangle steelKnifeBounds = new Rectangle(STEEL_KNIFE_BUTTON[0], STEEL_KNIFE_BUTTON[1], STEEL_KNIFE_BUTTON[2],
            STEEL_KNIFE_BUTTON[3]);
    private Rectangle backButtonBounds = new Rectangle(BACK_BUTTON[0], BACK_BUTTON[1], BACK_BUTTON[2], BACK_BUTTON[3]);

    private BasicObject shop;
    private BasicObject knifeBooster;
    private BasicObject bronzeKnife;
    private BasicObject steelKnife;
    private BasicObject back;
    private Font knifeBoosterFont;
    private Font bronzeKnifeFont;
    private Font steelKnifeFont;
    private Game game;
    private TouchInfo touchInfo;

    public ShopScreen(Game game) {
        this.game = game;
        MusicPlayer.playNatureMusic();
        IAPinit();
        shop = ObjectsStore.getShop();
        back = ObjectsStore.getBack();
        knifeBooster = ObjectsStore.getKnifeBooster();
        bronzeKnife = ObjectsStore.getBronzeKnifeForShop();
        steelKnife = ObjectsStore.getSteelKnifeForShop();
        knifeBoosterFont = new Font("Knife booster", Constants.KNIFE_BOOSTER_TEXT_X * Game.screenWidth / Constants
                .HOME_SCREEN_W, Constants.KNIFE_BOOSTER_TEXT_Y * Game.screenHeight / Constants.HOME_SCREEN_H);
        bronzeKnifeFont = new Font("Bronze Knife", Constants.BRONZE_KNIFE_TEXT_X * Game.screenWidth / Constants
                .HOME_SCREEN_W, Constants.BRONZE_KNIFE_TEXT_Y * Game.screenHeight / Constants.HOME_SCREEN_H);
        steelKnifeFont = new Font("Steel Knife", Constants.STEEL_KNIFE_TEXT_X * Game.screenWidth / Constants
                .HOME_SCREEN_W, Constants.STEEL_KNIFE_TEXT_Y * Game.screenHeight / Constants.HOME_SCREEN_H);
    }

    private static void IAPinit() {
        // ---- IAP: define products ---------------------
        PurchaseManagerConfig purchaseManagerConfig = new PurchaseManagerConfig();
        purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(Constants.double_speed));
        purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(Constants.bronze_knife));
        purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(Constants.steel_knife));
        GamePurchaseObserver purchaseObserver = new GamePurchaseObserver();
        PlatformBuilder.setComponents(null, purchaseObserver, purchaseManagerConfig);
        try {
            PlatformBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float delta) {

        if (Inputs.backPressed) {
            Inputs.backPressed = false;
            SoundPlayer.playButtonClick();
            MusicPlayer.stopNatureMusic();
            game.setScreen(new MainMenuScreen(game));
        }

        try {
            ImageRenderers.renderBasicObject(Game.batch, shop);
            ImageRenderers.renderBasicObject(Game.batch, back);
            ImageRenderers.renderBasicObject(Game.batch, knifeBooster);
            ImageRenderers.renderBasicObject(Game.batch, bronzeKnife);
            ImageRenderers.renderBasicObject(Game.batch, steelKnife);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FontRenderers.renderText(Game.batch, knifeBoosterFont);
        FontRenderers.renderText(Game.batch, bronzeKnifeFont);
        FontRenderers.renderText(Game.batch, steelKnifeFont);
        touchInfo = Util.doTouchEventsQueueEmpty();

        if (touchInfo != null) {
            if (knifeBoosterBounds.contains(touchInfo.touchX, touchInfo.touchY) && !Constants.availabaleIdentifiers
                    .contains(Constants.double_speed)) {
                SoundPlayer.playButtonClick();
                PlatformBuilder.getPlatformResolver().requestPurchase(Constants.double_speed);
                return;
            } else if (bronzeKnifeBounds.contains(touchInfo.touchX, touchInfo.touchY) && !Constants
                    .availabaleIdentifiers.contains(Constants.bronze_knife)) {
                SoundPlayer.playButtonClick();
                PlatformBuilder.getPlatformResolver().requestPurchase(Constants.bronze_knife);
                return;
            } else if (steelKnifeBounds.contains(touchInfo.touchX, touchInfo.touchY) && !Constants
                    .availabaleIdentifiers.contains(Constants.steel_knife)) {
                SoundPlayer.playButtonClick();
                PlatformBuilder.getPlatformResolver().requestPurchase(Constants.steel_knife);
                return;
            } else if (backButtonBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                MusicPlayer.stopNatureMusic();
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

}

