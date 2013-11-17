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
	TextButton btnSignIn;
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
		
		Label nameLabel = new Label("Username:", skin);
		TextField nameText = new TextField("",skin);
		Label passLabel = new Label("Password:", skin);
		TextField passText = new TextField("",skin);
		passText.setPasswordCharacter('*');
		passText.setPasswordMode(true);
		btnSignIn = new TextButton("Sign In", skin, "toggle");

		Table table = new Table();
		table.setPosition(200, 200);
		table.setSize(300, 200);
		table.add(nameLabel);
		table.add(nameText).width(100);
		table.row();
		table.add(passLabel);
		table.add(passText).width(100);
		table.row();
		table.add(btnSignIn).colspan(2);
		stage.addActor(table);
		table.debug();
		
		name = nameText.getText();
		pass = passText.getText();
		
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
	
	public String getId() {
	      String input = "";

	      try {
	         URL url = new URL(
	               "http://localhost/androidGame/entrar.php?usuario=" + name + "&contrasena=" + pass);
	         URLConnection connection = url.openConnection();
	         connection.setConnectTimeout(3000);
	         connection.connect();
	         BufferedReader br = new BufferedReader(new InputStreamReader(
	               connection.getInputStream()));
	         String line;
	         while ((line = br.readLine()) != null) {
	            input += line;
	         }
	         br.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	         input = "";
	      } 
	      return input;
	}
	
	private void AgregarListener(){
		btnSignIn.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    		resp = getId();
	    		System.out.println(resp);
	    		if(Integer.parseInt(resp) == 1){
	    			System.out.println("wawawawawawaaaaaaaaaaa");
	    			game.setScreen(new RoomScreen(game));
	    			//System.out.println("wawawawawawaaaaaaaaaaa");
	    		}
	    		else
	    			game.setScreen(new SignInScreen(game));
	            return true;
	    	}
		});
	}

}
