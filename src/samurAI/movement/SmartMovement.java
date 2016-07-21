package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class SmartMovement {
	private double direction=0f;
	private double speed=0;

	private AntiTankMovement tankMovement;
	private AntiRunnerMovement runnerMovement;
	private AntiSniperMovement sniperMovement;
	private EmergencyMovement emergencyMovement;
	private TeammateDodger teammateDodger;

	public SmartMovement() {
		tankMovement=new AntiTankMovement();
		runnerMovement=new AntiRunnerMovement();
		sniperMovement=new AntiSniperMovement();
		emergencyMovement=new EmergencyMovement();
		teammateDodger=new TeammateDodger();
	}

	public void update(Soldier toAttack, ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets, Soldier mySoldier, double radius, Point velocity) {
		emergencyMovement.update(bullets, mySoldier.getPosition(), radius, velocity);
		tankMovement.update(toAttack, bullets, mySoldier, radius, velocity);
		sniperMovement.update(toAttack, bullets, mySoldier, radius, velocity);
		runnerMovement.update(toAttack, bullets, mySoldier, radius, velocity);
		teammateDodger.update(soldiers, mySoldier);
		if (emergencyMovement.needToMove()) {
			direction=emergencyMovement.getDirection();
			speed=emergencyMovement.getSpeed();
		}
		else {
			if (teammateDodger.getNeedToMove()) {
				speed=1;
				direction=teammateDodger.getDodgeVelocity().direction();
			}
			else {
				if (toAttack instanceof Tank) {
					direction=tankMovement.getDirection();
					speed=tankMovement.getSpeed();
				}
				else if (toAttack instanceof Sniper) {
						direction=sniperMovement.getDirection();
						speed=sniperMovement.getSpeed();
				}
				else {
						direction=runnerMovement.getDirection();
						speed=runnerMovement.getSpeed();
				}
			}
		}
	}

	public double getDirection() {
		return direction;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean goodTimeToShoot() {
		return sniperMovement.goodTimeToShoot();
	}
}
