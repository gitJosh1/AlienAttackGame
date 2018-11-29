package com.mygdx.game.world;

import java.awt.*;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Player;

public abstract class GameMap {
	
	protected ArrayList<Entity> entities;
	protected Camera cam;

	
		public GameMap() {
			entities = new ArrayList<Entity>();
			Player p = new Player(40, 400, this);
			entities.add(p);
		}
	//made so we can call the methods in alien game
	//orthogonal camera since it is side on
		public void render (OrthographicCamera camera, SpriteBatch batch) {
			//loop through the different entities and render them
			for(Entity entity: entities) {
				entity.render(batch);
			}
			camera.position.x = entities.get(0).getx();
			camera.position.y = entities.get(0).gety();
			camera.update();
		}
		public void update (float delta) {
			//need to apply gravity (used earths gravity in this case)
			for(Entity entity: entities) {
				entity.update(delta, -9.8f);
			}

		}
		public abstract void dispose ();
		
		public abstract int getWidth();
		public abstract int getHeight();
		public abstract int getLayers();
		//gets the width, height and number of layers
		
		/**
		 * gets a tile by pixel position within the game world at a specified layer.
		 * @param layer 
		 * @param x
		 * @param y
		 * @return
		 */
		//called getTileByLocation on video tutorial
		public Tile getTileByid(int layer, float x, float y) {
			return this.getTileByCoordinate(layer, (int) (x/Tile.TILE_SIZE), (int) (y/Tile.TILE_SIZE));
		}
		//gets the location on the screen (game world) and divide it by the tile size
		//converts it to an integer, it gets the location within the array that the tile exists in
		//then calls the next method to get the tile from where it is.
		
		/**
		 *Gets a tile at its coordinate wihtin the map at a specified layer 
		 * @param layer
		 * @param col
		 * @param row
		 * @return
		 */
		public abstract Tile getTileByCoordinate(int layer, int col, int row);
		
		public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
			//checking whether the player is outside the map (also checking the width of the player is inside the map)
			if (x < 0 || y < 0 || x + width > getPixelWidth()|| y + height > getPixelHeight()) {
				return true;
			}
			//triple embedded loop to check for each tile
			//math.ceil rounds numbers up to the next value, if you dont do this will have weird results if entities are same size as tiles
			//only check the squares around the entity
			for(int row = (int) (y / Tile.TILE_SIZE); row < Math.ceil((y + height) / Tile.TILE_SIZE); row++) {
				for(int col = (int) (x / Tile.TILE_SIZE); col < Math.ceil((x + width) / Tile.TILE_SIZE); col++) {
					for(int layer = 0; layer < getLayers(); layer++) {
						Tile type = getTileByCoordinate(layer, col, row);
						if(type != null && type.isCollidable()) 
							return true;
						//tells the game there is a collision		
					}
				}
			}
			return false;
		}
		
		//gets pixel width and height
		public int getPixelWidth() {
			return this.getWidth() * Tile.TILE_SIZE;
		}
		public int getPixelHeight() {
			return this.getHeight() * Tile.TILE_SIZE;
		}
}
