package com.spinity.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.mygdxgame.MyGdxGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "juego_soldado";
		cfg.useGL20 = true;
		cfg.width = 320;
		cfg.height = 480;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
