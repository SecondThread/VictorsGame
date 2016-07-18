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
	private ArrayList<Soldier> otherTeam;
	private int teamID = 5;

	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Formations.init(onLeft, color);
		Soldier[] toReturn = Formations.allTank(1);
		return toReturn;
	}

	private void getTeams(ArrayList<Soldier> aliveSoldiers) {
		ArrayList<Soldier> myTeam = new ArrayList<Soldier>();
		ArrayList<Soldier> otherTeam = new ArrayList<Soldier>();
		for (Soldier x : aliveSoldiers) {
			if (x.getAI().getTeamID() == (teamID)) {
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
