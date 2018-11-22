package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	
	public TiledGameMap() {
		tiledMap = new TmxMapLoader().load("demoMap.tmx");
		//this will be what renders the tiled map
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}

	@Override
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		//Set the camera/view, tells the renderer what camera to use
				tiledMapRenderer.setView(camera);
				tiledMapRenderer.render();
		//this is to render the entities
				//this is so the game renders based on how the cameras want the game to render
				batch.setProjectionMatrix(camera.combined);
				batch.begin();
				super.render(camera, batch);
				batch.end();
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	public void dispose() {
		tiledMap.dispose();

	}
	
	//allows us to get data based on the map
		@Override
		public Tile getTileByCoordinate(int layer, int col, int row) {
			//there is different cells in the tilemap which can have tiles within in
			//this method returns the tile type based on the coordinate of the cell (where in the map) (the tile position)
			Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);
			
			if (cell!= null) {
				//if it equals null then it means we are clicking on a tile outside the map or one that doesn't exist on that layer
			TiledMapTile tile = cell.getTile();
			
				if(tile != null) {
					int id = tile.getId();
					return Tile.getTileByid(id);
				}
			}
			return null;
		}

	@Override
	public int getWidth() {
		//given in width of tiles
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
	}

	@Override
	public int getHeight() {
		//given in height of tiles
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
	}

	@Override
	public int getLayers() {
		//gets the amount of layers
		return tiledMap.getLayers().getCount();
	}

	

}
