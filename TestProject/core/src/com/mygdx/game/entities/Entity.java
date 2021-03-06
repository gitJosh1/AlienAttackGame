package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.world.GameMap;

public abstract class Entity {
	//keeps track of x and y
	protected Vector2 pos;
	protected EntityType type;
	//speed along the y axis
	protected float velocityY = 0;
	protected GameMap map;
	protected boolean grounded = false;
	
	
	public Entity(float x, float y, EntityType type, GameMap map) {
		this.pos = new Vector2(x,y);
		this.type = type;
		this.map = map;
	}
	
	public void update (float deltaTime, float gravity) {
		float newY = pos.y;
		//need to make the velocity get affected by gravity (shown below)
		this.velocityY += gravity * deltaTime * getWeight();
		newY += this.velocityY * deltaTime;
		//the new y value will be affected by velocityY which is basically the amount we will be moving per second in the y axis
		//positive moving up, negative moving down
		
		if(map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())) {
			if(velocityY < 0) {
				//we are checking if the entity is moving down when colliding with the map
				this.pos.y = (float) Math.floor(pos.y); //math.floor is opposite to ceiling and rounds down
				grounded = true; //they have hit the ground
			}
			this.velocityY = 0;
		} else {
			this.pos.y = newY; //if there is no collision then move the player
			grounded = false;
			}
		//need an else since we will be handling two situations
	}
	
	public abstract void render(SpriteBatch batch);
	
	protected void moveX(float amount) {
		//good way to check collision so all entities have access
		float newX = this.pos.x + amount;
		if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight()))
			this.pos.x = newX;
		//we are getting the theoretical new x of the player if they get to move
		//but will not move if there is a collision, otherwise we will set the new x
	}
	
	public Vector2 getPos() {
		return pos;
	}

	public float getx () {
		return pos.x;
	}
	
	public float gety() {
		return pos.y;
	}
	
	public EntityType getType() {
		return type;
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	public int getWidth() {
		return type.getWidth();
	}	
	
	public int getHeight() {
		return type.getHeight();
	}	
	
	public float getWeight() {
		return type.getWeight();
	}	
	
}
