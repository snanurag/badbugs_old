package com.badbugs;

import android.os.Bundle;

import com.badbugs.payment.GooglePlayResolver;
import com.badbugs.payment.PlatformBuilder;
import com.badbugs.payment.PlatformResolver;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

	Game game;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new Game();
		initialize(game, config);

		PlatformBuilder.setPlatformResolver(new GooglePlayResolver());

	}
}
