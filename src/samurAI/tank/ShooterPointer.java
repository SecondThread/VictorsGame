package samurAI.tank;

import java.util.ArrayList;

import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class ShooterPointer {
	
	
	public int getDirectionToTurnShield(Tank mySoldier, Soldier toAttack, boolean onlyAgainstSniper) {
		Soldier nearestSniper=toAttack;
		if (nearestSniper==null) {
			return 0;
		}
		double idealShieldAngle=mySoldier.getPosition().directionTo(nearestSniper.getPosition());//mySoldier.getPosition().directionTo(toDodge.getPosition());
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
