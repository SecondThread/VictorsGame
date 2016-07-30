package samurAI.tank;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Tank;
import engine.game.Window;
import samurAI.movement.TeammateDodger;

public class EmergencyBlocker {
	private double direction;
	private int directionToTurnShield;
	private boolean needToMove;
	private double moveSpeed;

	private BulletBlocker bulletBlocker;
	private TeammateDodger teammateDodger;
	
	public EmergencyBlocker() {
		bulletBlocker=new BulletBlocker();
	}
	
	public void update(ArrayList<Bullet> bullets, Tank mySoldier, double radius) {
		if (tryToDodgeWall(mySoldier.getPosition(), mySoldier.getVelocity(), radius)||tryToDodgeBullet(bullets, mySoldier, mySoldier.getPosition(), radius)) {
			needToMove=true;
			moveSpeed=1;
		}
		else {
			needToMove=false;
			direction=Math.atan2(mySoldier.getVelocity().y, mySoldier.getVelocity().x)+Math.PI;
			moveSpeed=0.6;
		}
	}
	
	public boolean needToMove() {
		return needToMove;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public int getDirectionToTurnShield() {
		return directionToTurnShield;
	}
	
	private boolean tryToDodgeBullet(ArrayList<Bullet> bullets, Tank mySoldier, Point position, double radius) {
		if (bullets.isEmpty()) {
			return false;
		}
		
		for (Bullet b:bullets) {
			if (bulletBlocker.needToMove(position, b, radius)) {
				direction=bulletBlocker.getDirectionToMove(mySoldier, position, b);
				directionToTurnShield=bulletBlocker.getDirectionToTurnShield(mySoldier, b);
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
		directionToTurnShield=0;
		direction=Math.atan2(yVelocity, xVelocity);
		return true;
	}
}
