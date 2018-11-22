package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.entities.Player;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.Tile;
import com.mygdx.game.world.TiledGameMap;
//platformergame in tutorial
public class AlienGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	OrthographicCamera cam;
	
	GameMap gameMap;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera();
	
		//false so it doesn't load from top left
		
		cam.setToOrtho(false, w, y);
		cam.update();
		//loads the game map
		gameMap = new TiledGameMap();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(cam, batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
		
	}
}
