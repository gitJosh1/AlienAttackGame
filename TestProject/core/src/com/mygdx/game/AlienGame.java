package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.entities.Player;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.Tile;
import com.mygdx.game.world.TiledGameMap;

import java.awt.*;
import java.awt.image.BufferStrategy;


//platformergame in tutorial
public class AlienGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	
	GameMap gameMap;
	ShapeRenderer sr;
	Texture weapon;

	@Override
	public void create () {
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		weapon = new Texture("C:\\Users\\njbat\\OneDrive\\Documents\\TestProject\\core\\assets\\Pistol.png");

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

		//interface attempt
		batch.begin();
		//draw the bars here
		sr.begin(ShapeRenderer.ShapeType.Filled);
		//health
		sr.setColor(Color.RED); //uses gdx.color
		sr.rect(15, 450, 100, 10);

		sr.setColor(Color.GREEN);
		sr.rect(15, 450, 90, 10);
		//humanity
		sr.setColor(Color.GRAY);
		sr.rect(275, 450, 100, 10);

		sr.setColor(Color.ORANGE);
		sr.rect(275, 450, 90, 10);
		//experience
		sr.setColor(Color.GRAY);
		sr.rect(15, 440, 100, 5);

		sr.setColor(Color.BLUE);
		sr.rect(15, 440, 90, 5);
		sr.end();

		sr.begin(ShapeRenderer.ShapeType.Line);
		//outlines (weapon and bars)
		//weapon
		sr.setColor(Color.GREEN);
		sr.rect(575, 425, 50, 50);
		batch.draw(weapon, 575, 425);
		//health
		sr.setColor(Color.BLACK);
		sr.rect(15, 450, 100, 10);
		//humanity
		sr.rect(275, 450, 100, 10);
		//experience
		sr.rect(15, 440, 100, 5);
		sr.end();
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
		
	}
}
