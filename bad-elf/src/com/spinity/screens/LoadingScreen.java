package com.spinity.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen extends AbstractScreen{
	
	private Stage stage;
	private Image background;
	private Image fillbackground;
	private Image fill_left;
	private Image fill_mid;
	private Image fill_right;
	private Image effect;
	private Image border;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	
	float factorw = 0;
	float factorh = 0;
	/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
	
	public LoadingScreen(MainScreen game) {
        super(game);
    }

	public void render(float delta) {
        // Draws a red background
    	Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
        fill_mid.invalidate();
        fill_right.setPosition(223*factorw+20+fill_mid.getWidth(),20*factorh);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    	
    }

    @Override
    public void show() {
    	float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(w,h);
		game.manager.load("data/loading.pack", TextureAtlas.class);
		game.manager.finishLoading();
		TextureAtlas atlas = game.manager.get("data/loading.pack", TextureAtlas.class);
		stage = new Stage();
		background = new Image(atlas.findRegion("loading_background"));
		fillbackground = new Image(atlas.findRegion("fill_background"));
		fill_left = new Image(atlas.findRegion("fill_left"));
		fill_mid = new Image(atlas.findRegion("fill_mid"));
		fill_right = new Image(atlas.findRegion("fill_right"));
		effect = new Image(atlas.findRegion("effect"));
		border = new Image(atlas.findRegion("border"));
		
		factorw = w/1280;
		factorh = w/800;
    	background.setSize(w, h);
    	fill_left.setPosition(223*factorw,20*factorh);
    	fill_mid.setPosition(223*factorw+20,20*factorh);
    	fill_right.setPosition(223*factorw+20+fill_mid.getWidth(),20*factorh);
    	fillbackground.setSize(796*factorw+40,38);
    	fillbackground.setPosition(223*factorw,20*factorh);
    	effect.setSize(796*factorw+40,38);
    	effect.setPosition(223*factorw,20*factorh);
    	border.setSize(796*factorw+40,38);
    	border.setPosition(223*factorw,20*factorh);
		stage.addActor(background);
		stage.addActor(fillbackground);
		stage.addActor(fill_left);
		stage.addActor(fill_mid);
		stage.addActor(fill_right);
		stage.addActor(effect);
		stage.addActor(border);
    }

    @Override
    public void hide() {
    }
	
	public int getsize() {
	      String input = "";
	      int size= 0;

	      try {
	         URL url = new URL(
	               "http://190.118.213.102/mb/login.php?name=carlos&password=esmeralda");
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
	      size = Integer.parseInt(input);
	      return size;
	   }

}
