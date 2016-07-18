package samurAI.tank;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;
import engine.entities.Tank;

public class SmartTank {
	private double direction=0f;
	private double speed=0;
	private int moveShieldDirection=0;
	
	private EmergencyBlocker emergencyBlocker;
	private ShooterPointer shooterPointer;
	private TankMovement tankMovement;
	
	public SmartTank() {
		emergencyBlocker=new EmergencyBlocker();
		shooterPointer=new ShooterPointer();
		tankMovement=new TankMovement();
	}
	
	public void update(Soldier toAttack, ArrayList<Bullet> bullets, Soldier mySoldier, double radius, Point velocity) {
		emergencyBlocker.update(bullets, (Tank)mySoldier, radius);
		if (toAttack==null) {
			speed=0;
			return;
		}
		tankMovement.update(mySoldier, toAttack);
		if (emergencyBlocker.needToMove()) {
			direction=emergencyBlocker.getDirection();
			speed=emergencyBlocker.getMoveSpeed();
			moveShieldDirection=emergencyBlocker.getDirectionToTurnShield();
			if (moveShieldDirection==0) {
				moveShieldDirection=shooterPointer.getDirectionToTurnShield((Tank)mySoldier, toAttack, false);
			}
		}
		else {
			moveShieldDirection=shooterPointer.getDirectionToTurnShield((Tank)mySoldier, toAttack, false);
			speed=tankMovement.getSpeed();
			direction=tankMovement.getDirection();
		}
	}
	
	public double getDirection() {
		return direction;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getMoveShieldDirection() {
		return moveShieldDirection;
	}
}
