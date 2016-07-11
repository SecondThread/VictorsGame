package dan.ai;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Soldier;

public class SniperDI extends SniperAI {
	private boolean canFire = true;
	private double gunAngle = 3.14;
	private int fireType;

	public double getGunAngle() {
		return gunAngle;
	}
	
	public void setGunAngle(double xAngle){
		gunAngle = xAngle;
	}
	
	public void setFireType(int type){
		fireType = type;
	}
	
	

	public boolean fireIfPossible() {
		return canFire;
	}
	
	public void setFire(boolean xFire){
		canFire = xFire;
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets){
		if(canFire){
		}
	}
}
