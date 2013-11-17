package com.spinity.main;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.spinity.screens.MainScreen;
import com.spinity.utils.NativeFunctions;

public class MainActivity extends AndroidApplication implements NativeFunctions{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        initialize(new MainScreen(this), cfg);
    }

	@Override
	public void cliente() {
		// TODO Auto-generated method stub
		
	}
}