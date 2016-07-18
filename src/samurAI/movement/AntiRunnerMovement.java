package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;

public class AntiRunnerMovement {

	private double direction=0f;
	private double speed=1;
	
	private final int idealDistance=250;
	
	public void update(Soldier toAttack, ArrayList<Bullet> bullets, Soldier mySoldier, double radius, Point velocity) {
		Point myFuturePosition=getFuturePosition(mySoldier, 30);
		Point enemyFuturePosition=getFuturePosition(toAttack, 30);
		if (myFuturePosition.distance(enemyFuturePosition)<idealDistance) {
			direction=enemyFuturePosition.directionTo(myFuturePosition);//run
		}
		else {
			direction=myFuturePosition.directionTo(enemyFuturePosition);//go toward him
		}
	}
	
	public Point getFuturePosition(Soldier soldier, int scaleFactor) {
		return new Point(soldier.getPosition().x+soldier.getVelocity().x*scaleFactor, soldier.getPosition().y+soldier.getVelocity().y*scaleFactor);
	}
	

	public double getDirection() {
		return direction;
	}
	
	public double getSpeed() {
		return speed;
	}
}
