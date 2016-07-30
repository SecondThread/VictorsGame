package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Soldier;
import engine.game.Window;

public class EmergencyRunnerMovement {
	
	private boolean needToMove=false;
	private EmergencyMovement movement=new EmergencyMovement();
	private TeammateDodger teammateDodger=new TeammateDodger(); 
	private double directionToMove=0;
	
	public EmergencyRunnerMovement() {
		
	}
	
	public void update(Soldier mySoldier, ArrayList<Soldier> otherSoldiers, ArrayList<Bullet> bullets) {
		movement.update(bullets, mySoldier.getPosition(), mySoldier.getRadius(), mySoldier.getVelocity());
		if (tryToDodgeBullets(mySoldier, bullets)||tryToDodgeTeammate(mySoldier, otherSoldiers)) {
			needToMove=true;
		}
		else {
			needToMove=false;
		}
	}
	
	private boolean tryToDodgeBullets(Soldier mySoldier, ArrayList<Bullet> bullets) {
		needToMove=movement.needToMove();
		directionToMove=movement.getDirection();
		return needToMove;
	}
	
	private boolean tryToDodgeTeammate(Soldier mySoldier, ArrayList<Soldier> soldiers) {
		teammateDodger.update(soldiers, mySoldier);
		if (teammateDodger.getNeedToMove()) {
			directionToMove=teammateDodger.getDirectionToMove();
			return true;
		}
		return false;
	}
	
	public boolean getNeedToMove() {
		return needToMove;
	}
	
	public double getDirectionToMove() {
		return directionToMove;
	}
	
}
