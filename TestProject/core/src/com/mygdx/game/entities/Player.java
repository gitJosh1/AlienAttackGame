package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.world.GameMap;

public class Player extends Entity {

	private static final int speed = 60;
	//speed on x axis
	private static final int jumpVelocity = 5;
	Texture image;
	
	public Player(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER, map);
		//we can pass the entity type in directly since we know it is going to be a player
		image = new Texture("C:\\Users\\njbat\\OneDrive\\Documents\\TestProject\\core\\assets\\player.png");
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		//jumping controls, (velocity takes them up gravity brings them back)
		if(Gdx.input.isKeyPressed(Keys.SPACE) && grounded)
			this.velocityY += jumpVelocity * getWeight();
		else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0)
			this.velocityY += jumpVelocity * getWeight() * deltaTime;
		//if they are holding the space bar whilst jumping they will jump higher
		//multiply by deltatime so they dont jump too high
		
		super.update(deltaTime, gravity);
		//this will apply the gravity
		
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveX(-speed * deltaTime);
		//moving left (negative speed so we move left)
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveX(speed * deltaTime);
		//moving right (positive speed so we move right)
	}
	
	//control space provides us with a list of all the methods we have access to
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
		//we scale the image so that it is the same size as we specified in entityType
		//current image is
	}

	public static int getSpeed() {
		return speed;
	}

	public static int getJumpvelocity() {
		return jumpVelocity;
	}
	
	

}
