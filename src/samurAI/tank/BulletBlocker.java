package samurAI.tank;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Tank;
import samurAI.movement.BulletDodger;

public class BulletBlocker {

	private BulletDodger dodger;
	private double idealShieldAngle=0;
	
	public BulletBlocker() {
		dodger=new BulletDodger();
	}

	public double distanceFromBullet(Point location, Bullet bullet) {
		return dodger.distanceFromBullet(location, bullet);
	}

	public boolean needToMove(Point location, Bullet bullet, double radius) {
		return dodger.needToDodge(location, bullet, radius, 4);
	}

	public double getDirectionToMove(Tank mySoldier, Point location, Bullet toDodge) {
		if (isShieldLinedUp(mySoldier, toDodge)) {
			// block
			return dodger.getDirectionToMove(location, toDodge)+Math.PI;
		}
		// dodge
		return dodger.getDirectionToMove(location, toDodge);
	}

	private boolean isShieldLinedUp(Tank mySoldier, Bullet toDodge) {
		double bulletAngle=toDodge.getAngle();
		double shieldAngle=mySoldier.getShieldAngle();
		if (Math.abs(((bulletAngle-shieldAngle)%(2*Math.PI)+2*Math.PI)%(2*Math.PI)-Math.PI)<Math.PI/4) {
			return true;
		}
		return false;
	}

	public int getDirectionToTurnShield(Tank mySoldier, Bullet toDodge) {
		idealShieldAngle=mySoldier.getPosition().directionTo(toDodge.getPosition());
		double currentAngle=mySoldier.getShieldAngle();
		currentAngle=(currentAngle%(Math.PI*2)+Math.PI*2)%(Math.PI*2);
		idealShieldAngle=(idealShieldAngle%(Math.PI*2)+Math.PI*2)%(Math.PI*2);
		if (currentAngle>idealShieldAngle) {
			if (currentAngle-idealShieldAngle<Math.PI*2-currentAngle+idealShieldAngle) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			if (idealShieldAngle-currentAngle<Math.PI*2-idealShieldAngle+currentAngle) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}

}
