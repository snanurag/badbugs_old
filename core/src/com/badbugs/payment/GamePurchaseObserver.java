package com.badbugs.payment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ashrinag on 5/16/2016.
 */


public class GamePurchaseObserver implements PurchaseObserver {

  private static Set<String> availabaleIdentifiers = new HashSet<String>();

  @Override
  public void handleRestore (Transaction[] transactions) {
    for (int i = 0; i < transactions.length; i++) {
      availabaleIdentifiers.add(transactions[i].getIdentifier());
    }
  }
  @Override
  public void handleRestoreError (Throwable e) {
    throw new GdxRuntimeException(e);
  }
  @Override
  public void handleInstall () {	}

  @Override
  public void handleInstallError (Throwable e) {
    Gdx.app.log("ERROR", "PurchaseObserver: handleInstallError!: " + e.getMessage());
    throw new GdxRuntimeException(e);
  }
  @Override
  public void handlePurchase (Transaction transaction) {
    availabaleIdentifiers.add(transaction.getIdentifier());
//    checkTransaction(transaction.getIdentifier(), false);
  }
  @Override
  public void handlePurchaseError (Throwable e) {	//--- Amazon IAP: this will be called for cancelled
    throw new GdxRuntimeException(e);
  }
  @Override
  public void handlePurchaseCanceled () {	//--- will not be called by amazonIAP
  }

  public static boolean isPurchased(String productIdentifier)
  {
    return availabaleIdentifiers.contains(productIdentifier);
  }

}
