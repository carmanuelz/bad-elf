package com.spinity.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class LobyScreen extends AbstractScreen {

	String[] listEntries = {"USer1","User2","User3","User4","User5","User6","User7","User8","User9","User10","User11","User12"};
	
	Stage stage;
	float factorw = 1;
	float factorh = 1;
	
	public LobyScreen(MainScreen game) {
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
		
		
		
		factorw = w/1280;
		factorh = h/800;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		//Skin skin2 = new Skin(Gdx.files.internal("data/480/skin480.json"));
		
		TextButton buttona = new TextButton("Loby", skin, "toggle");
		TextButton buttonb = new TextButton("Armeria", skin, "toggle");
		TextButton buttonc = new TextButton("Perfil", skin, "toggle");
		TextButton buttond = new TextButton("Ayuda", skin, "toggle");
		
		buttonb.setChecked(true);
		buttonb.padBottom(15*factorh);
		buttonc.padBottom(15*factorh);
		buttond.padBottom(15*factorh);
		buttona.padBottom(15*factorh);
		
		Table head = new Table();
		stage.addActor(head);
		head.setSize(1280*factorw, 41*factorh);
		head.setPosition(0,h-41*factorh);
		head.add(buttona).fill().expandX().expandY();
		head.add(buttonb).fill().expandX().expandY();
		head.add(buttonc).fill().expandX().expandY();
		head.add(buttond).fill().expandX().expandY();
		
		head.debug();
		
		TextButton refresh = new TextButton("R", skin);
		CheckBox checkBox1 = new CheckBox(" Filtro1", skin);
		CheckBox checkBox2 = new CheckBox(" Filtro2", skin);
		CheckBox checkBox3 = new CheckBox(" Filtro3", skin);
		
		Button room1 = new Button(skin,"toggle");
		TextButton room2 = new TextButton("Room2", skin);
		TextButton room3 = new TextButton("Room3", skin);
		TextButton room4 = new TextButton("Room4", skin);
		
		Texture texture2 = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		TextureRegion image2 = new TextureRegion(texture2);
		Image imageActora = new Image(image2);
		Image imageActorb = new Image(image2);
		Label usera = new Label("userA ",skin);
		Label userb = new Label("userB ",skin);
		Label vs = new Label("VS ",skin);
		
		Table rooma = new Table();
		rooma.setSize(140*factorw ,450*factorh);
		rooma.add(imageActora);
		rooma.add(vs).width(100);
		rooma.add(imageActorb);
		rooma.row();
		rooma.add(usera);
		rooma.add().width(100);
		rooma.add(userb);
		rooma.debug();
		
		room1.add(rooma);
		
		
		
		TextButton prev = new TextButton("Prev", skin);
		TextButton next = new TextButton("Next", skin);
		
		TextButton unirse = new TextButton("Unirse", skin);
		TextButton crear = new TextButton("Crear", skin);
		
		
		Table rooms = new Table();
		stage.addActor(rooms);
		rooms.setSize(976*factorw, 476*factorh);
		rooms.setPosition(5*factorw,274*factorh);
		rooms.add(refresh).height(30);
		rooms.add(checkBox1);
		rooms.add(checkBox2);
		rooms.add(checkBox3);
		rooms.row();
		rooms.add(room1).height(140*factorh).width(400*factorw).expand().colspan(2);
		rooms.add(room2).height(140*factorh).width(400*factorw).expand().colspan(2);
		rooms.row();
		rooms.add(room3).height(140*factorh).width(400*factorw).expand().colspan(2);
		rooms.add(room4).height(140*factorh).width(400*factorw).expand().colspan(2);
		rooms.row();
		rooms.add(prev);
		rooms.add(crear);
		rooms.add(unirse);
		rooms.add(next);
		
		rooms.debug();
		
		Table mensajes = new Table();
		Label user1 = new Label("user1: ",skin);
		Label mnjuser1 = new Label("mensaje 1",skin);
		Label user2 = new Label("user2: ",skin);
		Label mnjuser2 = new Label("mensaje 2",skin);
		Label user3 = new Label("user3: ",skin);
		Label mnjuser3 = new Label("mensaje 3",skin);
		Label user4 = new Label("user4: ",skin);
		Label mnjuser4 = new Label("mensaje 4",skin);
		Label user5 = new Label("user5: ",skin);
		Label mnjuser5 = new Label("mensaje 5",skin);
		Label user6 = new Label("user6: ",skin);
		Label mnjuser6 = new Label("mensaje 6",skin);
		mensajes.add(user1).height(13);
		mensajes.add(mnjuser1);
		mensajes.row();
		mensajes.add(user2).height(13);
		mensajes.add(mnjuser2);
		mensajes.row();
		mensajes.add(user3).height(13);
		mensajes.add(mnjuser3);
		mensajes.row();
		mensajes.add(user4).height(13);
		mensajes.add(mnjuser4);
		mensajes.row();
		mensajes.add(user5).height(13);
		mensajes.add(mnjuser5);
		mensajes.row();
		mensajes.add(user6).height(13);
		mensajes.add(mnjuser6);
		mensajes.left().bottom();
		
		TextButton enviar = new TextButton("Enviar", skin);
		TextField textfield = new TextField("", skin);
		
		ScrollPane historial = new ScrollPane(mensajes,skin);
		//.setScrollingDisabled(true, true);
		historial.setFadeScrollBars(true);
		historial.setScrollPercentY(0.1f);
		//historial.setOverscroll(true, false);
		Table chat = new Table();
		stage.addActor(chat);
		chat.setSize(976*factorw, 258*factorh);
		chat.setPosition(5*factorw,5);
		chat.add(historial).expand().colspan(2).fill().expandX().expandY();
		chat.row();
		chat.add(textfield).height(40*factorh).fill().expandX().expandY();
		chat.add(enviar);
		chat.debug();
		
		List list = new List(listEntries, skin);
		ScrollPane scrollPane2 = new ScrollPane(list, skin);
		scrollPane2.setScrollingDisabled(true, true);
		//scrollPane2.setFadeScrollBars(false);
		TextButton buscar = new TextButton("B", skin);
		TextField buscarfield = new TextField("", skin);
		TextButton contactos = new TextButton("Contactos", skin);
		
		Texture texture1 = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		TextureRegion image = new TextureRegion(texture1);
		Image imageActor = new Image(image);
		ScrollPane perfil = new ScrollPane(imageActor,skin);
		
		Table users = new Table();
		stage.addActor(users);
		users.setSize(248*factorw, 745*factorh);
		users.setPosition(1008*factorw, 5*factorh);
		users.add(buscarfield).height(40*factorh);
		users.add(buscar);
		users.row();
		users.add(scrollPane2).height(374*factorh).expand().colspan(2).fill().expandX().expandY();
		users.row();
		users.add(contactos).expand().colspan(2).fill().expandX().expandY();;
		users.row();
		users.add(perfil).height(225*factorh).expand().colspan(2).fill().expandX().expandY();
		
		users.debug();
		
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
