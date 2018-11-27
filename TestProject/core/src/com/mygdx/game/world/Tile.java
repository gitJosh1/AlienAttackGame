package com.mygdx.game.world;

import java.util.HashMap;

//enum is a set of constant objects
//TileType in tutorial video
public enum Tile {
	
	GRASS(1, true, "Floor"),
	DIRT(2, true, "Floor2"),
	SKY(3, false, "Sky"),
	LAVA(4, true, "Lava"),
	CLOUD(5, true, "Cloud"),
	STONE(6, true, "Stone");
	
	public static final int TILE_SIZE = 16;
	
	
	private int id;
	private boolean collidable;
	private String name;
	private float damage;
	
	//private since all tiles will be intiated within this class
	private Tile (int id, boolean collidable, String name) {
		this(id, collidable, name, 0);
	}
	private Tile (int id, boolean collidable, String name, float damage) {
		this.id = id;
		this.collidable = collidable;
		this.name= name;
		this.damage= damage;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	public String getName() {
		return name;
	}
	
	public float getDamage() {
		return damage;
	}
	//use a hash map to get the enum id for the tile that is going to be used
	private static HashMap<Integer, Tile> tileMap;
	//static method so when the game starts, this code will run (will fill the hashmap)
	
	static {
		tileMap = new HashMap<Integer, Tile>();
		for (Tile tileType : Tile.values()) {
			tileMap.put(tileType.getId(), tileType);
				}
			}
	//static method to get the tile by using their id from the hashmap
	public static Tile getTileByid(int id) {
		return tileMap.get(id);
	}
	
}
