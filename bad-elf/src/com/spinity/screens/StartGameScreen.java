package com.spinity.screens;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.brashmonkey.spriter.Spriter;
import com.brashmonkey.spriter.ik.SpriterIKResolver;
import com.brashmonkey.spriter.objects.SpriterBone;
import com.brashmonkey.spriter.objects.SpriterIKObject;
import com.brashmonkey.spriter.player.SpriterPlayer;
import com.brashmonkey.spriter.xml.FileHandleSCMLReader;
import com.spinity.gdxspriter.SpriteDrawer;
import com.spinity.gdxspriter.SpriteLoader;
import com.spinity.mygdxgame.Bala;
import com.spinity.mygdxgame.Paracaidista;
import com.spinity.mygdxgame.SpriteAccessor;
import com.spinity.utils.BitmapAccessor;
import com.spinity.utils.BodyAccessor;


/*
 * Aplicacion Torre de Defensa
 * 
 * >> La clase InputAdapter es una clase adaptiva de InputProcessor, usado para recibir eventos de entrada
 *    desde el teclado y el touch screen (mouse en el escritorio).
 *    
 * >> La clase ApplicationListener es llamada cuando la aplicacion genera el ciclo de vida. Todos los metodos
 *    son llamados en un hilo que tiene el contexto OpenGL actual. Asi se puede crear y manipular los recursos de manera segura.
 *    
 * >> La clase ContacListener es llamada para cuando dos Fixtures entran en contacto.
 *    
 *  */

public class StartGameScreen extends AbstractScreen implements ContactListener {
	public StartGameScreen(MainScreen game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	private OrthographicCamera camera; 	// variable de tipo OrthographicCamera, que es una camara con proyeccion ortografica.
	private SpriteBatch batch;	// usado para dibujar rectangulos 2D que referencian a una Texture (region).
	private Texture texture; // variable Texture
	private Sprite base;			// un grafico de escena 2D. mantiene el punto de vista y distribuye los eventos de entrada
	private float porcentX;				// variable de tipo float
	private World world;				// gestiona todas las entidades, simulaciones dinamicas y consultas asincronas
	private final TweenManager tweenManager = new TweenManager(); // actualiza las animaciones y las lineas de tiempo.
	private Vector2 destino = new Vector2(0,1);			// encapsula un vector 2D
	private Vector2 origen = new Vector2(0, -4.8f);
	private Sprite BalaSprite;
	
	private Vector2 pointDown = new Vector2(0,0);
	private float AngleDirection;
	
	private ArrayList<Paracaidista> paracaidistas = new ArrayList<Paracaidista>();
	private ArrayList<Bala> balas = new ArrayList<Bala>();
	private Box2DDebugRenderer debugRender;
	private static float VEL_DISP = 0.5f; //0.5f
	private float rest = 0;
	private boolean shot_ready = true;
	private ArrayList<Body> listaRemoveBody = new ArrayList<Body>(); 
	float w;
	float h;
	private Spriter spriter;
	SpriteLoader loader;
	private SpriterPlayer playerSpriter;
	SpriteDrawer drawer;
	SpriterIKObject obj;
	SpriterIKResolver resolver;
	SpriterBone canonBone;
	
	Spriter manibela;
	private SpriterPlayer maniSpriter;
	SpriteDrawer maniDrawer;
	
	Spriter bala;
	SpriterPlayer balaSpriter;
	SpriteDrawer balaDrawer;
	Sprite bg;
	
	ImageButton btn;
	
	Sound fire;
	Sound boom;
	
	ArrayList<Sprite> explosiones = new ArrayList<Sprite>();
	Texture explosion;
	
	BitmapFont bf;
	SpriteBatch bitmap;
	int score;
	
	ArrayList<BitmapFont> pt_personal = new ArrayList<BitmapFont>();
	BitmapFont ptos;
	
	@Override
	public void show() {		
		w = Gdx.graphics.getWidth(); // obtenemos los valores
		h = Gdx.graphics.getHeight();// del ancho y del alto de la ventana
		
		camera = new OrthographicCamera(10, 10 * h/w); // aqui se construye la OrthographicCamera dando como parametros de manera proporcional el tamaño de ventana
		debugRender = new Box2DDebugRenderer();
		batch = new SpriteBatch(); // construyes el SpriteBatch
		
		world = new World(new Vector2(0, -10), true); // construye el World, dando la "gravedad" de x(0) & y(-10), y true para mejorar rendimiento al no simular cuerpos inactivos
		
		
		texture = new Texture(Gdx.files.internal("data/animaciones/base_canon.png")); // se construye la textura de acuerdo a la imgen dada en el parametro
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); // setear el filtro para las texturas, en este caso lineal
		
		
		InputMultiplexer multiplexer = new InputMultiplexer(); // usado para mantener eventos antes que el stage lo haga	
	    multiplexer.addProcessor(this);
	    multiplexer.addProcessor(inputProcessor);
	    Gdx.input.setInputProcessor(multiplexer); // coloca el inputProcessor que recibira todos los eventos ed entrada de touch y teclado
	      
	    Tween.registerAccessor(Body.class, new BodyAccessor()); // te permite interpolar cualquier atributo desde cualquier objeto
	    Tween.registerAccessor(Sprite.class, new SpriteAccessor());
	    Tween.registerAccessor(BitmapFont.class, new BitmapAccessor());
	    
		BalaSprite = new Sprite(new Texture(Gdx.files.internal("data/animaciones/bala.png")));
		BalaSprite.setSize(0.5f,0.5f);
		
		base = new Sprite(texture); // crea un nuevo sprite con la misma textura de la region.
		base.setSize(2.3f, 2.3f * base.getHeight() / base.getWidth()); // 0.9f, 0.9f * base.getHeight() / base.getWidth()
		base.setOrigin(base.getWidth()/2, base.getHeight()/2);
		base.setPosition((-base.getWidth()/2), (-base.getHeight()/2)-5.4f); // -base.getWidth()/2, -base.getHeight()/2
	    restart(); // fx restart
	    createGround(); // fx createGround
	    
	    fire = Gdx.audio.newSound(Gdx.files.internal("data/sound/fire.wav"));
	    boom = Gdx.audio.newSound(Gdx.files.internal("data/sound/boom.wav"));
	    
	    Texture bgTexture = new Texture(Gdx.files.internal("data/bg.png"));
	    bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    bg = new Sprite(bgTexture);
	    bg.setSize(10, 10*h/w);
	    bg.setPosition(-5, -5*h/w);
	    loader = new SpriteLoader(2048, 2048);
	    drawer = new SpriteDrawer(this.loader, this.batch);
	    spriter =   FileHandleSCMLReader.getSpriter(Gdx.files.internal("data/animaciones/anim_000.scml"), loader);
	    playerSpriter = new SpriterPlayer(spriter, 1, loader);
	    playerSpriter.setAnimationIndex(0);
	    playerSpriter.setFrameSpeed(0);
	    playerSpriter.setScale(0.007f);
	    
	    maniSpriter = new SpriterPlayer(spriter, 0, loader);
	    maniSpriter.setAnimationIndex(0);
	    maniSpriter.setScale(0.01f);
	    maniSpriter.setFrameSpeed(0);
	    
	    explosion = new Texture(Gdx.files.internal("data/boom.png"));
	    explosion.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    
	    bf = new BitmapFont();
	    bitmap = new SpriteBatch();
	    score = 0;
		
	}

	@Override
	public void dispose() { // llamada para destruir la aplicacion.
		batch.dispose(); // se destruye el SpriteBatch
		texture.dispose(); // se destruye el Texture
	}

	@Override
	public void render(float delta) {	// llamada cuando se tiene que renderizar 
		Gdx.gl.glClearColor(1, 1, 1, 1); // color de fondo
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // llamada para limpiar toda la pantalla
		
		rest += delta;
		if(rest >= VEL_DISP){
			shot_ready = true;
		}
		world.step(1/60f, 10, 10); // mejora la colision, integracion y solucion de restricciones. 1° la cantidad de tiempo para simular. 2° para la velocidad de la restrccion. 3° para la posicion de la restriccion
		tweenManager.update(Gdx.graphics.getDeltaTime()); // actualizas todas tus animaciones activas. con parametro deltaTime, que es el lapso de tiempo entre cada frame.
		world.setContactListener(this);
		batch.setProjectionMatrix(camera.combined); // coloca la matriz de proyeccion que sera usada.
		batch.begin(); // configura el SpriteBarch para dibujar
		/*for(int i=0; i< listaBalasS.size();i++){
			listaBalasS.get(i).draw(batch);  // obtiene todas las balas de la lista y las dibuja.
		}*/
		bg.draw(batch);
		playerSpriter.update(0, -4.8f);
		maniSpriter.update(-0.8f, -5.5f);
		if(!shot_ready){
			if(playerSpriter.getFrame() < 480){ //479
			}else{
				playerSpriter.setFrameSpeed(0);
				playerSpriter.setFrame(500);
			}
		}
		drawer.draw(playerSpriter);
		base.draw(batch);
		drawer.draw(maniSpriter);
		for(int l=0;l<listaRemoveBody.size();l++){
			world.destroyBody(listaRemoveBody.get(l));	
			listaRemoveBody.remove(l);
		}
		for(int k=0; k < balas.size();k++){
			if(!balas.get(k).exploto){
				balas.get(k).Draw(BalaSprite, batch);
			}else{
				balas.get(k).tween.kill();
				listaRemoveBody.add(balas.get(k).bBody);
				balas.remove(k);
			}
		}
		for(int j=0;j<paracaidistas.size();j++){ 
			paracaidistas.get(j).Draw(batch);
		}
		
		for(int j=0;j<explosiones.size();j++){ 
			explosiones.get(j).draw(batch);
		}
		
		batch.end();
		
		bitmap.begin();
		bf.draw(bitmap, "Score: " + score, 10, 470);
		bitmap.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			 playerSpriter.setAngle(playerSpriter.getAngle()+1);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			playerSpriter.setAngle(playerSpriter.getAngle()-1);
		}
		if((playerSpriter.getAngle() <= 90 && porcentX < 0) || (playerSpriter.getAngle() >= -90 && porcentX > 0)  ) // condicion para que el cañon se mueva solo 180°
			playerSpriter.setAngle(playerSpriter.getAngle()-porcentX);
		
		//debugRender.render(world, camera.combined);
	}

	@Override
	public void resize(int width, int height) { // llamada cuando la aplicacion necesita ser redimensionada
	}

	@Override
	public void pause() { // llamada cuando la aplicacion es pausada
	}

	@Override
	public void resume() { // llamada cuando la aplicacion es reanudada luego de una pausa
	}
	
	private void restart() { 
		
		tweenManager.killAll();

		Tween.call(new TweenCallback() {
			@Override public void onEvent(int type, BaseTween<?> source) {
				
				Paracaidista p = new Paracaidista(world);
				paracaidistas.add(p);
			}
		}).repeat(-1, 1.5f).start(tweenManager); // se inicializa el tweenManager
	}
	
	private void crearBala(Vector2 destino){ // se crea las balas del juego
		Bala bala = new Bala(world);
	    bala.bBody.setTransform(origen.x, origen.y,0);
		balas.add(bala);
		
		bala.tween = Tween.to(bala.bBody, BodyAccessor.POS_XY, 1f) // indica a la bala en que posicion acaba su recorrido (bala.bSprite, SpriteAccessor.POS_XY, 1f)
		.ease(Linear.INOUT)
		.setUserData(bala)
		.target(destino.x, destino.y)
		.setCallback(finBalaCallback)
		.setCallbackTriggers(TweenCallback.START | TweenCallback.COMPLETE)
		.start(tweenManager);
	}
	
	
	private void createGround(){
		BodyDef bd = new BodyDef();
		bd.position.set(0, -7); // coloca la posicion del Body dentro del World
		bd.type = BodyType.StaticBody;

		PolygonShape shape = new PolygonShape(); //se crea una forma de poligono 
		shape.setAsBox(10, 1); // construye los vertices para alinear la caja con los ejes. la mitad del ancho, la mitad del alto

		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f; // frccion entre los Body
		fd.restitution = 0f; // que tanto rebotan luego de haber chocado con otro elemento
		fd.shape = shape;

		Fixture fix = world.createBody(bd).createFixture(fd); // se crea el Body y el Fixture en el World
		fix.setUserData("piso");
		shape.dispose();
	}
	
	TweenCallback finBalaCallback = new TweenCallback() { // Se llama cuando la bala ha sido disparada, hasta que llega a su destino
		@Override public void onEvent(int type, BaseTween source) { // de ahi se quita a la bala de la lista de balas
			Bala bala = (Bala)source.getUserData();
			switch (type) {
				case START: System.out.println("Start Bullet");break;
				case COMPLETE:
					System.out.println("Final Bullet");
					listaRemoveBody.add(bala.bBody);
					balas.remove(bala);
					break;
			}
		}
	};
	
	@Override
	public boolean keyDown(int keycode) { // llamada cuando una tecla es presionada
		if(keycode == Keys.SPACE){
			if(shot_ready){
				crearBala(destino);
				shot_ready = false;
				rest = 0;
				fire.play();
				playerSpriter.setFrame(0);
				playerSpriter.setFrameSpeed(20);
			}
		}
		if(keycode == Keys.A)
			maniSpriter.setFrameSpeed(10);
		if(keycode == Keys.D)
			maniSpriter.setFrameSpeed(-10);
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) { // llamada cuando una tecla es presionada
		if(keycode == Keys.SPACE){
			if(shot_ready){
				crearBala(destino);
				shot_ready = false;
				rest = 0;
				System.out.println();
				playerSpriter.setFrame(0);
				playerSpriter.setFrameSpeed(20);
			}
		}
		if(keycode == Keys.A || keycode == Keys.D)
			maniSpriter.setFrameSpeed(0);
			
		return false;
	}

	@Override
	public void beginContact(Contact contact) { // llamado cuando dos Fixtures entran en contacto
		// TODO Auto-generated method stub
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		String tipoA = (String)fixtureA.getUserData();
		String tipoB = (String)fixtureB.getUserData();
		Bala bala;
		Paracaidista paracaidista;
		//System.out.println(tipoA);
		if(tipoA.equals("bal")&& tipoB.equals("par")){
			bala = (Bala)fixtureA.getBody().getUserData();
			paracaidista = (Paracaidista)fixtureB.getBody().getUserData();
			bala.exploto = true;
			score++;
			boom.play();
			Sprite choco = new Sprite(explosion);
			choco.setSize(1, 1);
			choco.setColor(1, 1, 1, 0);
			choco.setPosition(bala.bBody.getPosition().x - 0.5f, bala.bBody.getPosition().y- 0.5f);
			Timeline.createSequence()
				.push(Tween.to(choco, SpriteAccessor.OPACITY, 0.2f)
					.target(1).ease(Linear.INOUT))
				.push(Tween.to(choco, SpriteAccessor.OPACITY, 0.5f)
					.target(0).ease(Linear.INOUT))
					.start(tweenManager);
			
			explosiones.add(choco);
			//bala.tween.kill();
			//listaRemoveBody.add(bala.bBody);
			listaRemoveBody.add(paracaidista.pBody);
			paracaidistas.remove(paracaidista);
			//balas.remove(bala);
		}else if(tipoB.equals("bal")&& tipoA.equals("par")){
			bala = (Bala)fixtureB.getBody().getUserData();
			//bala.tween.kill();
			paracaidista = (Paracaidista)fixtureA.getBody().getUserData();
			bala.exploto = true;
			score++;
			boom.play();
			Sprite choco = new Sprite(explosion);
			choco.setSize(1, 1);
			choco.setColor(1, 1, 1, 0);
			choco.setPosition(bala.bBody.getPosition().x - 0.5f, bala.bBody.getPosition().y- 0.5f);
			Timeline.createSequence()
				.push(Tween.to(choco, SpriteAccessor.OPACITY, 0.2f)
					.target(1).ease(Linear.INOUT))
				.push(Tween.to(choco, SpriteAccessor.OPACITY, 0.5f)
					.target(0).ease(Linear.INOUT))
					.setUserData(choco).setCallback(explosionCB)
					.setCallbackTriggers(TweenCallback.START | TweenCallback.COMPLETE)
					.start(tweenManager);
			
			explosiones.add(choco);
			//listaRemoveBody.add(bala.bBody);
			listaRemoveBody.add(paracaidista.pBody);
			paracaidistas.remove(paracaidista);
			//balas.remove(bala);
		}
	}
	
	TweenCallback explosionCB = new TweenCallback() { // Se llama cuando la bala ha sido disparada, hasta que llega a su destino
		@Override public void onEvent(int type, BaseTween source) { // de ahi se quita a la bala de la lista de balas
			Sprite explosion = (Sprite)source.getUserData();
			switch (type) {
				case START: ;break;
				case COMPLETE:
					explosiones.remove(explosion);
					break;
			}
		}
	};
	
	private final InputProcessor inputProcessor = new InputAdapter() {
        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
        	pointDown = new Vector2(x,y);
        	return true;
        }
        
        
        public boolean touchDragged(int x, int y, int pointer){
        	Vector2 direction = new Vector2(x - pointDown.x, y - pointDown.y);
        	float resultandte  = (float) Math.sqrt(Math.pow(pointDown.y-y,2)+Math.sqrt(Math.pow(pointDown.x-x,2)));
        	if(pointDown.y < y && resultandte>50){
	        	AngleDirection = direction.angle()*-1;
	        	AngleDirection +=90;
	    		playerSpriter.setAngle(AngleDirection);
        	}
        	return true;
        }
        public boolean touchUp(int x, int y, int pointer, int button) {
        	float resultandte  = (float) Math.sqrt(Math.pow(pointDown.y-y,2)+Math.sqrt(Math.pow(pointDown.x-x,2)));
        	if(pointDown.y < y && resultandte>50){
	        	destino = new Vector2(0,1);
	        	destino.setAngle(AngleDirection+90);
	        	destino = new Vector2(destino.x*15,destino.y*15 -5f ); 
	        	crearBala(destino);
        	}
        	return true;
        }
	};
	

	@Override
	public void endContact(Contact contact) { // llamado cuando termina el contacto entre dos Fixtures
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { // llamado para actuar antes de contacto
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) { // llamado para actuar despues del contacto
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
