package samurAI;

import java.util.ArrayList;

import engine.ai.SoldierAI;
import engine.entities.Bullet;
import engine.entities.Soldier;
import samurAI.movement.EmergencyMovement;

public class MovableRunner extends SoldierAI {
	private EmergencyMovement movement=new EmergencyMovement();
	private Soldier mySoldier;
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		updateMySoldier(soldiers);
		movement.update(bullets, mySoldier.getPosition(), mySoldier.getRadius(), mySoldier.getVelocity());
		moveSpeed=movement.getSpeed();
		direction=movement.getDirection();
	}
	
	private void updateMySoldier(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s.getAI()==this) {
				mySoldier=s;
			}
		}
	}
}
