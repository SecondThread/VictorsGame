package samurAI.tank;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;
import engine.entities.Tank;
import samurAI.movement.TeammateDodger;

public class SmartTank {
	private double direction=0f;
	private double speed=0;
	private int moveShieldDirection=0;
	
	private EmergencyBlocker emergencyBlocker;
	private ShooterPointer shooterPointer;
	private TankMovement tankMovement;
	private TeammateDodger teammateDodger;
	
	public SmartTank() {
		emergencyBlocker=new EmergencyBlocker();
		shooterPointer=new ShooterPointer();
		tankMovement=new TankMovement();
		teammateDodger=new TeammateDodger();
	}
	
	public void update(Soldier toAttack, ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets, Soldier mySoldier, double radius, Point velocity) {
		emergencyBlocker.update(bullets, (Tank)mySoldier, radius);
		teammateDodger.update(soldiers, mySoldier);
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
		else if (teammateDodger.getNeedToMove()) {
			direction=teammateDodger.getDirectionToMove();
			speed=1;
			moveShieldDirection=shooterPointer.getDirectionToTurnShield((Tank)mySoldier, toAttack, false);
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
