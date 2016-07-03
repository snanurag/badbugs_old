package com.badbugs.payment;

import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ashrinag on 5/16/2016.
 */


public class GamePurchaseObserver implements PurchaseObserver {


    public static boolean isPurchased(String productIdentifier) {
        return Constants.availabaleIdentifiers.contains(productIdentifier);
    }

    @Override
    public void handleRestore(Transaction[] transactions) {
        for (int i = 0; i < transactions.length; i++) {
            Constants.availabaleIdentifiers.add(transactions[i].getIdentifier());
        }
    }

    @Override
    public void handleRestoreError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void handleInstall() {
    }

    @Override
    public void handleInstallError(Throwable e) {
        Gdx.app.log("ERROR", "PurchaseObserver: handleInstallError!: " + e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void handlePurchase(Transaction transaction) {
        Constants.availabaleIdentifiers.add(transaction.getIdentifier());
//    checkTransaction(transaction.getIdentifier(), false);
    }

    @Override
    public void handlePurchaseError(Throwable e) {    //--- Amazon IAP: this will be called for cancelled
        e.printStackTrace();
    }

    @Override
    public void handlePurchaseCanceled() {    //--- will not be called by amazonIAP
    }

}
