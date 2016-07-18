package samurAI.tank;

import engine.entities.Point;
import engine.entities.Soldier;
import engine.entities.Tank;

public class TankMovement {
	
	private int idealDistance=400, attackDistance=180;
	private double direction=0;
	private double speed=1;
	
	
	public void update(Soldier mySoldier, Soldier toAttack) {
		Point myFuturePosition=getFuturePosition(mySoldier, 30);
		Point enemyFuturePosition=getFuturePosition(toAttack, 30);
		double distance=myFuturePosition.distance(enemyFuturePosition);
		if (distance>attackDistance||!facingEnemy((Tank)mySoldier, toAttack)) {
			if (distance>idealDistance) {
				direction=myFuturePosition.directionTo(enemyFuturePosition);
			}
			else {
				direction=enemyFuturePosition.directionTo(myFuturePosition);
			}
		}
		else {
			direction=myFuturePosition.directionTo(enemyFuturePosition);
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
	
	private boolean facingEnemy(Tank mySoldier, Soldier toAttack) {
		double angleOffset=mySoldier.getShieldAngle()-toAttack.getPosition().directionTo(mySoldier.getPosition());
		angleOffset=(angleOffset%(Math.PI*2)+Math.PI*2)%(Math.PI*2);
		return Math.abs(angleOffset-Math.PI)<Math.PI/4;
	}
	
}
