package com.spinity.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.spinity.utils.NativeFunctions;

/**
 * @author Mats Svensson
 */
public class MainScreen extends Game {

    /**
     * Holds all our assets
     */
	
	public NativeFunctions mNativeFunctions;
	public MainScreen(NativeFunctions nativeFunctions){
		mNativeFunctions = nativeFunctions;
	}
    public AssetManager manager = new AssetManager();

    @Override
    public void create() {
        setScreen(new StartGameScreen(this));
    }
}