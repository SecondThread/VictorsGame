package dan.ai;

import java.awt.Color;
import java.util.ArrayList;

import engine.ai.PlayerAI;
import engine.entities.Bullet;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.game.Window;

public class PlayerDI extends PlayerAI {
	private ArrayList<Soldier> myTeam;
	private Color myColor = new Color(220, 53, 34);
	private ArrayList<Soldier> otherTeam;

	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn = new Soldier[1];
		int x = 50;
		if (onLeft) {
			x = Window.WIDTH - x;
		}
		for (int i = 0; i < toReturn.length; i++) {
			SniperDI temp = new SniperDI();
			toReturn[i] = new Sniper(x, (i + 1) * Window.HEIGHT / (toReturn.length + 1), color, temp);
			temp.setObject(toReturn[i]);
		}
		return toReturn;
	}

	private void getTeams(ArrayList<Soldier> aliveSoldiers) {
		ArrayList<Soldier> myTeam = new ArrayList<Soldier>();
		ArrayList<Soldier> otherTeam = new ArrayList<Soldier>();
		for (Soldier x : aliveSoldiers) {
			if (x.color.equals(myColor)) {
				myTeam.add(x);
			} else {
				otherTeam.add(x);
			}
		}
		this.myTeam = myTeam;
		this.otherTeam = otherTeam;
	}

	public void update(ArrayList<Soldier> aliveSoldiers, ArrayList<Bullet> bullets) {
		getTeams(aliveSoldiers);
		SniperDI.setTeams(myTeam, otherTeam);
	}
}
