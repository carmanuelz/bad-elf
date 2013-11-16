package com.me.mygdxgame;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.brashmonkey.spriter.player.SpriterAbstractPlayer;
import com.brashmonkey.spriter.player.SpriterPlayer;
import com.me.gdxspriter.SpriterDrawer;

public class Bala {
	public String tipo = "b";
	public Body bBody;
	public Sprite bSprite;
	public Tween tween;
	public boolean exploto = false;
	
	public Bala(World world){
		bSprite = new Sprite (new Texture(Gdx.files.internal("data/bala.png")));
		bSprite.setSize(0.5f, 0.5f); // se le da un tamaño
		bSprite.setOrigin(0.25f, 0.25f); // se le cambia el origen de la bala (+ 0.25f, 0.25f)
		bSprite.setColor(1,0,0,1);
		
		CircleShape circulo = new CircleShape(); // se crea una variable CircleShape
		circulo.setRadius(0.25f); // se le indica un radio
	      
	    FixtureDef fixture = new FixtureDef(); // se crea un FixtureDef que define una fixture
	    fixture.isSensor = true; // informa si existe algun contacto
	    fixture.shape = circulo; // se le da la forma de circulo al FixtureDef creado
	    
		BodyDef bala = new BodyDef(); // se crea una variable BodyDef
		bala.type = BodyType.StaticBody; // se le indica que el tipo de Body es estatico
		
		bBody = world.createBody(bala); // se crea un body en el World utilizando el BodyDef creado anteriormente
		Fixture fix = bBody.createFixture(fixture); // se crea la Fixture del Body
		fix.setUserData("bal");
		bBody.setUserData(this); // se coloca el UserData del Sprite
	}
	
	public void Draw(SpriterPlayer player, SpriterDrawer drawer){
		player.update(bBody.getPosition().x, bBody.getPosition().y); // + 0.25f
		drawer.draw(player);
	}
}
