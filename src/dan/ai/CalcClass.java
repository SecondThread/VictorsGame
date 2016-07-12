package dan.ai;

import java.util.ArrayList;

import engine.entities.Point;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class CalcClass {
	public static double calcAngle(Soldier x, ArrayList<Soldier> otherTeam, int fireType) {
		// 0-close 1-straight 2-sniper 3-tank
		Soldier temp;
		if (fireType == 1 || otherTeam.size() == 0) {
		} else {
			temp = CalcClass.findClosest(x, otherTeam, fireType);
			Point tempPoint = temp.getPosition();
			System.out.println(tempPoint.x + " " + tempPoint.y + " " + x.getPosition().x + " " + x.getPosition().y);
			double toReturn = Math.atan((tempPoint.y - x.getPosition().y) / (tempPoint.x - x.getPosition().x));
			if (tempPoint.x - x.getPosition().x < 0)
				toReturn += 3.14;
			return toReturn;
		}
		return 0;
	}

	// type 0-none 1-sniper 2-tank
	public static Soldier findClosest(Soldier x, ArrayList<Soldier> otherTeam, int type) {
		double shortest;
		int shortI;
		if (!x.equals(otherTeam.get(0)) || otherTeam.size() <= 1) {
			shortest = otherTeam.get(0).getPosition().distance(x.getPosition());
			shortI = 0;
		} else {
			shortest = otherTeam.get(1).getPosition().distance(x.getPosition());
			shortI = 1;
		}
		for (int i = shortI + 1; i < otherTeam.size(); i++) {
			double temp = otherTeam.get(i).getPosition().distance(x.getPosition());
			if (type == 0 || (type == 1 && otherTeam.get(i) instanceof Sniper)
					|| (type == 2 && otherTeam.get(i) instanceof Tank) && !x.equals(temp)) {
				if (temp < shortest) {
					shortest = temp;
					shortI = i;
				}
			}
		}
		return otherTeam.get(shortI);
	}
}
