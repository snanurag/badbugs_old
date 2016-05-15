//package com.badbugs.payment;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.pay.*;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.GdxRuntimeException;
//
//public class GdxPayExample extends ApplicationAdapter {
//
//	// ----- app stores -------------------------
//	public static final int APPSTORE_UNDEFINED	= 0;
//	public static final int APPSTORE_GOOGLE 	= 1;
//	public static final int APPSTORE_OUYA 		= 2;
//	public static final int APPSTORE_AMAZON 	= 3;
//	public static final int APPSTORE_DESKTOP 	= 4;
//
//	private int isAppStore = APPSTORE_UNDEFINED;
//
//	SpriteBatch batch;
//	Stage stage;
//	Texture img;
//
//	public final static String productID_fullVersion = "fullversion";
//	static PlatformResolver m_platformResolver;
//	public PurchaseManagerConfig purchaseManagerConfig;
//	public PurchaseObserver purchaseObserver = new PurchaseObserver() {
//		@Override
//		public void handleRestore (Transaction[] transactions) {
//			for (int i = 0; i < transactions.length; i++) {
//				if (checkTransaction(transactions[i].getIdentifier(), true) == true) break;
//			}
//		}
//		@Override
//		public void handleRestoreError (Throwable e) {
//			throw new GdxRuntimeException(e);
//		}
//		@Override
//		public void handleInstall () {	}
//
//		@Override
//		public void handleInstallError (Throwable e) {
//			Gdx.app.log("ERROR", "PurchaseObserver: handleInstallError!: " + e.getMessage());
//			throw new GdxRuntimeException(e);
//		}
//		@Override
//		public void handlePurchase (Transaction transaction) {
//			checkTransaction(transaction.getIdentifier(), false);
//		}
//		@Override
//		public void handlePurchaseError (Throwable e) {	//--- Amazon IAP: this will be called for cancelled
//			throw new GdxRuntimeException(e);
//		}
//		@Override
//		public void handlePurchaseCanceled () {	//--- will not be called by amazonIAP
//		}
//	};
//
//	protected boolean checkTransaction (String ID, boolean isRestore) {
//		boolean returnbool = false;
//
//		if (productID_fullVersion.equals(ID)) {
//			Gdx.app.log("checkTransaction", "full version found!");
//
//			//----- put your logic for full version here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//			returnbool = true;
//		}
//		return returnbool;
//	}
//
//	public GdxPayExample () {
//
//		setAppStore(APPSTORE_GOOGLE);	// change this if you deploy to another platform
//
//		// ---- IAP: define products ---------------------
//		purchaseManagerConfig = new PurchaseManagerConfig();
//		purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(productID_fullVersion));
//	}
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//
//		getPlatformResolver().requestPurchaseRestore();	// check for purchases in the past
//
//		//----- here could be a scene2d button with a clicklistener.....
//		// onClick -> getPlatformResolver().requestPurchase(productID_fullVersion);
//		stage = new Stage();
//		Gdx.input.setInputProcessor(stage);
//
//		Image image = new Image(img);
//		image.setX(350);
//		image.setY(200);
//		image.addListener(new ClickListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				getPlatformResolver().requestPurchase(productID_fullVersion);
//				return true;
//			}
//		});
//
//		Information fullVersionInformation = getPlatformResolver().getPurchaseManager().getInformation(productID_fullVersion);
//
//		Gdx.app.log("INFO", "fullVersionInformation -> "+fullVersionInformation);
//
//		stage.addActor(image);
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		stage.act();
//		stage.draw();
//	}
//
//	public PlatformResolver getPlatformResolver() {
//		return m_platformResolver;
//	}
//	public static void setPlatformResolver (PlatformResolver platformResolver) {
//		m_platformResolver = platformResolver;
//	}
//
//	public int getAppStore () {
//		return isAppStore;
//	}
//	public void setAppStore (int isAppStore) {
//		this.isAppStore = isAppStore;
//	}
//}
