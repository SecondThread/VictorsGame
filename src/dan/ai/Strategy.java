package dan.ai;

import java.util.ArrayList;

import engine.entities.Soldier;

public class Strategy {
	private static ArrayList<Soldier> myTeam;
	private static boolean onLeft;

	public static void init(boolean onLeft) {
		Strategy.onLeft = onLeft;
	}

	public static void update(ArrayList<Soldier> myTeam) {
		Strategy.myTeam = myTeam;
	}

	public static void fullCharge() {
		for (Soldier x : myTeam) {
			if (onLeft)
				x.getAI().direction = 3.14;
			else
				x.getAI().direction = 0;
			x.getAI().moveSpeed = 1;
		}
	}

}
