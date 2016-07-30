package samurAI.movement;

import java.util.ArrayList;

import engine.ai.RunnerAI;
import engine.entities.Bullet;
import engine.entities.Soldier;

public class DeathchargeRunner extends RunnerAI {

	private Soldier mySoldier=null;
	private EmergencyRunnerMovement emergencyMovement=new EmergencyRunnerMovement();
	private DeathchargeMovement deathchargeMovement=new DeathchargeMovement();
	private double directionToMove=0, moveSpeed=0;
	private Soldier toTarget=null;

	public DeathchargeRunner(int teamID) {
		super(teamID);
	}

	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		updateMySoldier(soldiers);
		updateToTarget(soldiers);
		emergencyMovement.update(mySoldier, soldiers, bullets);
		deathchargeMovement.update(mySoldier, soldiers, toTarget);
		if (emergencyMovement.getNeedToMove()) {
			directionToMove=emergencyMovement.getDirectionToMove();
			moveSpeed=1;
		}
		else {
			directionToMove=deathchargeMovement.getDirectionToMove();
			moveSpeed=1;
		}
	}

	private void updateMySoldier(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s.getAI()==this) {
				mySoldier=s;
			}
		}
	}

	public boolean blinkIfPossible() {
		return false;
	}

	public double getDirectionToMove() {
		return directionToMove;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	private void updateToTarget(ArrayList<Soldier> soldiers) {
		if (toTarget!=null&&toTarget.isDead()) {
			toTarget=null;
		}
		int team=mySoldier.getTeam();
		if (toTarget==null) {
			for (Soldier s:soldiers) {
				if (s.getTeam()==team) {
					continue;
				}
				if (toTarget==null) {
					toTarget=s;
				}
				if (toTarget.getPosition().distance(mySoldier.getPosition())>s.getPosition().distance(mySoldier.getPosition())) {
					toTarget=s;
				}
			}
		}
		else {
			for (Soldier s:soldiers) {
				if (s.getTeam()==team) {
					continue;
				}
				if (toTarget.getPosition().distance(mySoldier.getPosition())>s.getPosition().distance(mySoldier.getPosition())*1.5) {
					toTarget=s;
				}
			}
		}
	}

}
