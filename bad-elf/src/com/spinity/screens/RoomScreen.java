package com.spinity.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class RoomScreen extends AbstractScreen{
	
	Stage stage;
	float factorw = 1;
	float factorh = 1;
	
	public RoomScreen(MainScreen game) {
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
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		factorw = 1280; // 1280 - 1024 - 800
		factorh = 800; // 800 - 600 - 480
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		Table room = new Table();
		room.setSize(factorw, factorh);
		room.setPosition(0,0);//h*factorh
		stage.addActor(room);
		
		Table playerA = new Table();
		//playerA.setSize((float) (factorw/2.7), factorh); //1280: 2.7 , 1024: 3.41 , 800: 4
		Label userA = new Label("Player A",skin);
		Texture texture1 = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		TextureRegion image1 = new TextureRegion(texture1);
		Image imagePlayerA = new Image(image1);
		Label levelA= new Label("LVL: 00",skin);
		Label victoriesA= new Label("Victories: 00",skin);
		Label defeatA= new Label("Defeats: 00",skin);
		TextButton readyA = new TextButton("Ready", skin);
		
		playerA.add(userA);
		playerA.row();
		playerA.add(imagePlayerA);
		playerA.row();
		playerA.add(levelA);
		playerA.row();
		playerA.add(victoriesA);
		playerA.row();
		playerA.add(defeatA);
		playerA.row();
		playerA.add(readyA);
		playerA.debug();
		
		room.add(playerA).width((float) (factorw/2.7));
		
		Table vs = new Table();
		vs.setSize((float) (factorw/2.21), factorh); // 1280: 2.21 , 1024: 2.42 , 800: 2
		Label timel = new Label("Tiempo: ",skin);
		SelectBox time = new SelectBox(new String[] {"1:00", "2:00", "3:00", "ilimitado"}, skin);
		Label goldl = new Label("Oro: ",skin);
		SelectBox gold = new SelectBox(new String[] {"100", "200", "300", "400"}, skin);
		Label versus = new Label("VS",skin);
		Label MapName = new Label("Nombre del Mapa",skin);
		Label prev = new Label("Anterior",skin);
		Texture texture2 = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		TextureRegion image2 = new TextureRegion(texture2);
		Image imageMap = new Image(image2);
		Label next = new Label("Siguiente",skin);
		TextButton start = new TextButton("Start Game", skin);
		
		Table features =new Table();
		features.add(timel);
		features.add(time);
		features.row();
		features.add(goldl);
		features.add(gold);
		features.debug();
		
		Table map = new Table();
		map.add(prev);
		map.add(imageMap);
		map.add(next);
		map.debug();
		
		vs.add(features);
		vs.row();
		vs.add(versus);
		vs.row();
		vs.add(MapName);
		vs.row();
		vs.add(map);
		vs.row();
		vs.add(start);
		vs.debug();
		
		room.add(vs);
		
		Table playerB = new Table();
		playerB.setSize((float) (factorw/2.7), factorh);
		Label userB = new Label("Player B",skin);
		Texture texture3 = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		TextureRegion image3 = new TextureRegion(texture3);
		Image imagePlayerB = new Image(image3);
		Label levelB= new Label("LVL: 00",skin);
		Label victoriesB= new Label("Victories: 00",skin);
		Label defeatB= new Label("Defeats: 00",skin);
		TextButton readyB = new TextButton("Ready", skin);
		
		playerB.add(userB);
		playerB.row();
		playerB.add(imagePlayerB);
		playerB.row();
		playerB.add(levelB);
		playerB.row();
		playerB.add(victoriesB);
		playerB.row();
		playerB.add(defeatB);
		playerB.row();
		playerB.add(readyB);
		playerB.debug();
		
		room.add(playerB);
		room.debug();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		stage.dispose();
		
	}
	
	public boolean needsGL20 () {
		return true;
	}

}
