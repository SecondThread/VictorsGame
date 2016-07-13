package samurAI;

import java.awt.Color;

import engine.ai.PlayerAI;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.game.Window;

public class SamurAISniper extends PlayerAI {
	private int team=0;
	
	public SamurAISniper(int team) {
		this.team=team;
	}
	
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[2];
		toReturn[0]=new Sniper(onLeft?50:Window.WIDTH-50, 2*Window.HEIGHT/3, color, new AASniper(team));
		toReturn[1]=new Sniper(onLeft?50:Window.WIDTH-50, Window.HEIGHT/3, color, new AASniper(team));
		return toReturn;	
	}
}
