package samurAI;

import java.util.ArrayList;

import engine.ai.TankAI;
import engine.entities.Bullet;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;
import samurAI.tank.EmergencyBlocker;
import samurAI.tank.SmartTank;

public class MovableTank extends TankAI {
	public MovableTank(int teamID) {
		super(teamID);
	}

	private SmartTank movement=new SmartTank();
	private Soldier mySoldier;
	private int directionToTurn=0;
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		updateMySoldier(soldiers);
		movement.update(getNearestSniper(soldiers, false), bullets, (Tank)mySoldier, mySoldier.getRadius(), mySoldier.getVelocity());
		moveSpeed=movement.getSpeed();
		direction=movement.getDirection();
		directionToTurn=movement.getMoveShieldDirection();

	}
	
	private void updateMySoldier(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s.getAI()==this) {
				mySoldier=s;
			}
		}
	}
	
	public int getShieldTurnDirection() {
		return directionToTurn;
	}
	
	public Soldier getNearestSniper(ArrayList<Soldier> soldiers, boolean onlyAgainstSniper) {
		Soldier toReturn=null;
		for (Soldier s:soldiers) {
			if (onlyAgainstSniper&&!(s instanceof Sniper)) {
				continue;
			}
			if (s.getAI().getTeamID()==mySoldier.getAI().getTeamID()) {
				continue;
			}
			if (toReturn==null) {
				toReturn=s;
				continue;
			}
			if (toReturn.getPosition().distance(mySoldier.getPosition())>s.getPosition().distance(mySoldier.getPosition())) {
				toReturn=s;
			}
		}
		return toReturn;
	}
}
