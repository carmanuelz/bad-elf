package com.gushikustudios.rube.loader.serializers;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.gushikustudios.rube.RubeDefaults;
import com.gushikustudios.rube.RubeScene;

public class RubeWorldSerializer extends ReadOnlySerializer<RubeScene>
{
	private WorldSerializer mWorldSerializer;
	private RubeScene scene;
   
	public RubeWorldSerializer(Json json)
	{
		scene = new RubeScene();
		mWorldSerializer = new WorldSerializer(scene,json);
		json.setSerializer(World.class, mWorldSerializer);
		json.setIgnoreUnknownFields(true);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public RubeScene read(Json json, JsonValue jsonData, Class type) 
	{
		scene.stepsPerSecond 		= json.readValue("stepsPerSecond", 		int.class, RubeDefaults.World.stepsPerSecond, 		jsonData);
		scene.positionIterations 	= json.readValue("positionIterations", 	int.class, RubeDefaults.World.positionIterations, 	jsonData);
		scene.velocityIterations 	= json.readValue("velocityIterations", 	int.class, RubeDefaults.World.velocityIterations, 	jsonData);
		scene.setWorld(json.readValue(World.class,	jsonData));
		return scene;
	}

}
