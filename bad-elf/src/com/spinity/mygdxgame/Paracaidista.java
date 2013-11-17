package com.spinity.mygdxgame;

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

public class Paracaidista {
	public String  tipo = "p";
	public Body pBody;
	public Sprite pSprite;
	
	public Paracaidista(World world){
		float posX = (float)Math.random()*10 - 5; // la posX se utiliza para hacer un random del lugar en donde caera el paracaidista
		pSprite = new Sprite(new Texture(Gdx.files.internal("data/paracaida.png"))); // se crea un nuevo sprite, con las mismas caracteristicas de la textura
		pSprite.setSize(1f, 1f*pSprite.getHeight()/pSprite.getWidth()); // se le aplica el tamaño al Sprite (1.6f)
		pSprite.setOrigin(0.2f, 0.2f*pSprite.getHeight()/pSprite.getWidth()); // se actualiza el origen del Sprite (0.8f)
		
		BodyDef ballBodyDef = new BodyDef(); 
		ballBodyDef.type = BodyType.DynamicBody; // se le asigna el tipo de Body como dinamico

		CircleShape shape = new CircleShape();
		shape.setRadius(0.7f); //(0.8f)

		FixtureDef fd = new FixtureDef();
		fd.density = 1f; // se le asigna una densidad, para la mejor interaccion con los demas paracaidistas
		fd.shape = shape;
		
		pBody = world.createBody(ballBodyDef);
		Fixture fix = pBody.createFixture(fd);
		fix.setUserData("par");
		pBody.setGravityScale(0.1f);
		pBody.setUserData(pSprite);
		pBody.setUserData(this);
		pBody.setTransform(posX,9,0); // coloca la posicion del origen y su rotacion
		
		shape.dispose(); // se llama a este  metodo cuando la forma ya no se utiliza mas
	}
	
	public void Draw(SpriteBatch batch){
		pSprite.setPosition(pBody.getPosition().x - 0.8f, pBody.getPosition().y - 0.8f);
		pSprite.draw(batch);
	}
}
