package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

	private Random r = new Random();
	int counter = 0;
	private static float speed;
	private Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		speed = (float) 4.0;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x,  0,  Game.WIDTH-37);
		y = Game.clamp((int)y,  0,  Game.HEIGHT-60);	
		
		collision();
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Enemy){
				if(getBounds().intersects(tempObject.getBounds())){
					//collisioncode
					HUD.HEALTH -=2;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
	} 
	
	public static void setSpeed(float speed){
		Player.speed = speed;
	}
	public static float getSpeed(){
		return speed;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
