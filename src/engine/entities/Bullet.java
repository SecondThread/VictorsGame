package engine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.game.Window;

public class Bullet {
	private double x, y;
	private double angle;
	private double velocity=15;
	
	private boolean dead=false; 
	
	public Bullet(double x, double y, double angle) {
		this.x=x;
		this.y=y;
		this.angle=angle;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void update(ArrayList<Soldier> soldiers) {
		for (int i=0; i<15; i++) {
			move();
			tryToHitShield(soldiers);
			if (!dead) checkForCollisions(soldiers);
		}
		if (x<0||y<0||x>=Window.WIDTH||y>=Window.HEIGHT) {
			kill();
		}
	}
	
	private void move() {
		double xVelocity=Math.cos(angle)*velocity;
		double yVelocity=Math.sin(angle)*velocity;
		x+=xVelocity/15;
		y+=yVelocity/15;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect((int)x-1, (int)y-1, 3, 3);
	}
	
	private void checkForCollisions(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s.getShape().contains(x, y)) {
				if (!dead) {
					s.OnCollision(true);
					dead=true;
				}
			}
		}
	}

	public void kill() {
		dead=true;
	}
	
	public void tryToHitShield(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s instanceof Tank) {
				if (((Tank)s).getShield().contains(x, y)) {
					kill();
				}
			}
		}
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public double getVelocityMagnitude() {
		return velocity;
	}
	
	
}
