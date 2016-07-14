package engine.ai;

import java.util.ArrayList;
import java.util.Random;

import engine.entities.Bullet;
import engine.entities.Soldier;

public class SoldierAI {
	
	public double direction=new Random().nextDouble()*6.28;
	public double moveSpeed=0;//new Random().nextDouble();
	protected int teamID=0;
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
	}
	
	public double getDirectionToMove() {
		return direction;
	}
	
	/**
	 * Gets the speed your ball should move (between -1 and 1)
	 * @return
	 * A speed value between -1 (backwards full) and 1 (forwards full)
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	
	public int getTeamID() {
		return teamID;
	}
	
}
