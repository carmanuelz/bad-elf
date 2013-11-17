package com.spinity.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spinity.screens.MainScreen;
import com.spinity.utils.NativeFunctions;

public class Main implements NativeFunctions{
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "juego_soldado";
		cfg.useGL20 = true;
		cfg.width = 320;
		cfg.height = 480;
		
		Main game = new Main();		
		new LwjglApplication(new MainScreen(game), cfg);
	}

	@Override
	public void cliente() {
		// TODO Auto-generated method stub
		
	}
}
