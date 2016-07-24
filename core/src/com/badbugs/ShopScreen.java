package com.badbugs;

import com.badbugs.baseframework.FontRenderers;
import com.badbugs.baseframework.ImageRenderers;
import com.badbugs.baseframework.MusicPlayer;
import com.badbugs.baseframework.SoundPlayer;
import com.badbugs.creators.SpritesCreator;
import com.badbugs.objects.Button;
import com.badbugs.objects.Shop;
import com.badbugs.objects.fonts.Font;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.payment.PlatformBuilder;
import com.badbugs.util.Constants;
import com.badbugs.util.Inputs;
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
    private static float[] KNIFE_BOOSTER_BUTTON;
    private static float[] BACK_BUTTON;
    private static Shop shop;
    private static Button knifeBooster;
    private static Button back;
    private static Font knifeBoosterFont;
    private static Rectangle knifeBoosterBounds;
    private static Rectangle backButtonBounds;
    Game game;
    TouchInfo touchInfo;

    public ShopScreen(Game game) {
        this.game = game;
        MusicPlayer.playNatureMusic();
    }

    public static void load() {
        IAPinit();
        try {
            defineBounds();
            shop = SpritesCreator.loadShop();
            back = SpritesCreator.loadBackButton();
            knifeBooster = SpritesCreator.loadKnifeBooster();
            knifeBoosterFont = new Font("Knife booster", Constants.KNIFE_BOOSTER_TEXT_X, Constants.KNIFE_BOOSTER_TEXT_Y);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void defineBounds() {
        // Top left coordinates are the pivots
        KNIFE_BOOSTER_BUTTON = new float[]{Constants.KNIFE_BOOSTER_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.KNIFE_BOOSTER_TOP * Game.screenHeight / Constants.HOME_SCREEN_H,
                Constants.KNIFE_BOOSTER_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.KNIFE_BOOSTER_H * Game.screenHeight / Constants.HOME_SCREEN_H};
        BACK_BUTTON = new float[]{Constants.BACK_LEFT * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.BACK_TOP * Game.screenHeight / Constants.HOME_SCREEN_H, Constants.BACK_W * Game.screenWidth / Constants.HOME_SCREEN_W,
                Constants.BACK_H * Game.screenHeight / Constants.HOME_SCREEN_H};

        knifeBoosterBounds = new Rectangle(KNIFE_BOOSTER_BUTTON[0], KNIFE_BOOSTER_BUTTON[1], KNIFE_BOOSTER_BUTTON[2],
                KNIFE_BOOSTER_BUTTON[3]);
        backButtonBounds = new Rectangle(BACK_BUTTON[0], BACK_BUTTON[1], BACK_BUTTON[2], BACK_BUTTON[3]);
    }

    private static void IAPinit() {
        // ---- IAP: define products ---------------------
        PurchaseManagerConfig purchaseManagerConfig = new PurchaseManagerConfig();
        purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(Constants.double_speed));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        FontRenderers.renderText(Game.batch, knifeBoosterFont);
        touchInfo = Util.doTouchEventsQueueEmpty();

        if (touchInfo != null) {
            if (knifeBoosterBounds.contains(touchInfo.touchX, touchInfo.touchY) && !Constants.availabaleIdentifiers.contains(Constants.double_speed)) {
                SoundPlayer.playButtonClick();
                PlatformBuilder.getPlatformResolver().requestPurchase(Constants.double_speed);
                return;
            } else if (backButtonBounds.contains(touchInfo.touchX, touchInfo.touchY)) {
                SoundPlayer.playButtonClick();
                MusicPlayer.stopNatureMusic();
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

}

