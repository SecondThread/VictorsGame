package dan.ai;

import java.util.ArrayList;

import engine.entities.Point;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class CalcClass {
	public static double calcAngle(Soldier x, ArrayList<Soldier> aliveSoldiers, int fireType) {
		// 0-close 1-straight 2-sniper 3-tank
		Soldier temp;
		if (fireType == 1) {
		} else {
			temp = CalcClass.findClosest(x, aliveSoldiers, fireType);
			Point tempPoint = temp.getPosition();
			System.out.println(tempPoint.x + " " + tempPoint.y + " " + x.getPosition().x + " " + x.getPosition().y);
			double toReturn = Math.atan((tempPoint.y - x.getPosition().y) / (tempPoint.x - x.getPosition().x));
			if(tempPoint.x - x.getPosition().x < 0)
				toReturn += 3.14;
			return toReturn;
		}
		return 0;
	}

	// type 0-none 1-sniper 2-tank
	public static Soldier findClosest(Soldier x, ArrayList<Soldier> aliveSoldiers, int type) {
		double shortest;
		int shortI;
		if (!x.equals(aliveSoldiers.get(0))) {
			shortest = aliveSoldiers.get(0).getPosition().distance(x.getPosition());
			shortI = 0;
		}else{
			shortest = aliveSoldiers.get(1).getPosition().distance(x.getPosition());
			shortI = 1;
		}
		for (int i = shortI + 1; i < aliveSoldiers.size(); i++) {
			double temp = aliveSoldiers.get(i).getPosition().distance(x.getPosition());
			if (type == 0 || (type == 1 && aliveSoldiers.get(i) instanceof Sniper)
					|| (type == 2 && aliveSoldiers.get(i) instanceof Tank) && !x.equals(temp)) {
				if (temp < shortest) {
					shortest = temp;
					shortI = i;
				}
			}
		}
		return aliveSoldiers.get(shortI);
	}
}
