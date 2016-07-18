package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;
import engine.game.Window;

public class EmergencyMovement {
	private boolean needToMove=false;
	private double direction=0f;
	private double speed=0;
	
	private BulletDodger bulletDodger;
	
	public EmergencyMovement() {
		bulletDodger=new BulletDodger();
	}
	
	public void update(ArrayList<Bullet> bullets, Point position, double radius, Point velocity) {
		if (tryToDodgeWall(position, velocity, radius)||tryToDodgeBullet(bullets, position, radius)) {
			speed=1;
			needToMove=true;
		}
		else {
			direction=Math.atan2(velocity.y, velocity.x)+Math.PI;
			speed=0.6;
			needToMove=false;
		}
	}
	
	private boolean tryToDodgeBullet(ArrayList<Bullet> bullets, Point position, double radius) {
		if (bullets.isEmpty()) {
			return false;
		}
		
		for (Bullet b:bullets) {
			if (bulletDodger.needToDodge(position, b, radius)) {
				direction=bulletDodger.getDirectionToMove(position, b);
				return true;
			}
		}
		return false;
	}
	
	private boolean tryToDodgeWall(Point position, Point velocity, double radius) {
		Point futurePosition=new Point(position.x+velocity.x*30, position.y+velocity.y*30);
		double xVelocity=0, yVelocity=0;
		double radiusMultiplier=3;
		if (futurePosition.x<radius*radiusMultiplier) {
			xVelocity+=1;
		}
		if (futurePosition.y<radius*radiusMultiplier) {
			yVelocity+=1;
		}
		if (Window.WIDTH-futurePosition.x<radius*radiusMultiplier) {
			xVelocity-=1;
		}
		if (Window.HEIGHT-futurePosition.y<radius*radiusMultiplier) {
			yVelocity-=1;
		}
		if (xVelocity==0&&yVelocity==0) {
			return false;
		}
		direction=Math.atan2(yVelocity, xVelocity);
		return true;
	}
	
	
	
	public boolean needToMove() {
		return needToMove;
	}
	
	public double getDirection() {
		return direction;
	}
	
	//move if I need to, otherwise don't.
	public double getSpeed() {
		return speed;
	}
	
}
