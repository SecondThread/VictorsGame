package dan.ai;

import java.util.ArrayList;

import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class CalcClass {
	public static double calcAngle(int fireType) {
		// 0-close 1-straight 2-sniper 3-tank
		return 0;
	}

	// type 0-none 1-sniper 2-tank
	public static Soldier findClosest(Soldier x, ArrayList<Soldier> aliveSoldiers, int type) {
		double shortest = aliveSoldiers.get(0).getPosition().distance(x.getPosition());
		int shortI = 0;
		for (int i = 1; i < aliveSoldiers.size(); i++) {
			double temp = aliveSoldiers.get(i).getPosition().distance(x.getPosition());
			if (type == 0 || (type == 1 && aliveSoldiers.get(i) instanceof Sniper)
					|| (type == 2 && aliveSoldiers.get(i) instanceof Tank)){
				if (temp < shortest) {
					shortest = temp;
					shortI = i;
				}
			}
		}
		return aliveSoldiers.get(shortI);
	}
}
