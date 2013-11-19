package com.spinity.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SignInScreen extends AbstractScreen {
	
	Stage stage;
	TextButton btnStart;
	String resp, name, pass;
	
	public SignInScreen(MainScreen game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.setViewport(width, height, true);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		btnStart = new TextButton("Start Game", skin);
		btnStart.setSize(100,50);
		stage.addActor(btnStart);
		
		AgregarListener();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		stage.dispose();
	}
	
	public boolean needsGL20 () {
		return true;
	}
	
	
	private void AgregarListener(){
		btnStart.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new StartGameScreen(game));
	            return true;
	    	}
		});
	}

}
