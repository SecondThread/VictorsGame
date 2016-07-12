package dan.ai;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;

public class SniperDI extends SniperAI {
	private boolean canFire = true;
	private double gunAngle = 0;
	private int fireType = 0;
	private Soldier myObject;
	private static ArrayList<Soldier> myTeam;
	private static ArrayList<Soldier> otherTeam;

	public void setObject(Soldier newObject) {
		myObject = newObject;
	}

	public static void setTeams(ArrayList<Soldier> myTeam, ArrayList<Soldier> otherTeam) {
		SniperDI.myTeam = myTeam;
		SniperDI.otherTeam = otherTeam;
	}

	public double getGunAngle() {
		return gunAngle;
	}

	public void setGunAngle(double xAngle) {
		gunAngle = xAngle;
	}

	// type 0-none 1-sniper 2-tank
	public void setFireType(int type) {
		fireType = type;
	}

	public boolean fireIfPossible() {
		return canFire;
	}

	public void setFire(boolean xFire) {
		canFire = xFire;
	}

	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		if (canFire) {
			// System.out.println(CalcClass.calcAngle(myObject, soldiers,
			// fireType));
			setGunAngle(CalcClass.calcAngle(myObject, otherTeam, fireType));
		}
	}
}
