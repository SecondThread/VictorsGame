package engine.networking.entities;

import java.util.ArrayList;

import engine.ai.RunnerAI;
import engine.entities.Bullet;
import engine.entities.Runner;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.networking.BigKahunaGame;
import engine.networking.NetworkManager;

public class BigKahunaChangable extends RunnerAI {
	private int player;
	private boolean onLeft;
	private Soldier me=null;
	private int team=0;
	
	public BigKahunaChangable(int player, boolean onLeft, int team) {
		super(player);
		this.player=player;
		this.onLeft=onLeft;
		this.team=team;
		System.out.println(team);
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		int playerType=NetworkManager.getSoldierType(player);
		if (playerType==-1) {
			return;
		}
		
		if (me==null) {
			for (Soldier s:soldiers) {
				if (s.getAI()==this) {
					me=s;
				}
			}
		}
		
		if (playerType==0) {
			BigKahunaGame.addPlayer(new Sniper(me.getPosition().x, me.getPosition().y, team, new BigKahunaSniper(player)), me);
		}
		else if (playerType==1) {
			BigKahunaGame.addPlayer(new Tank(me.getPosition().x, me.getPosition().y, team, new BigKahunaTank(player), onLeft?0:Math.PI), me);
		}
		else if (playerType==2) {
			BigKahunaGame.addPlayer(new Runner(me.getPosition().x, me.getPosition().y, team, new BigKahunaRunner(player)), me);
		}
	}
}
