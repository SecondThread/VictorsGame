package samurAI;

import java.awt.Color;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.game.Window;
import engine.networking.entities.BigKahunaChangable;

public class SamurAISniper extends PlayerAI {
	private int team=0;
	
	public SamurAISniper(int team) {
		this.team=team;
	}
	
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[1];
		toReturn[0]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, color, new BigKahunaChangable(0, false));
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, color, new AAASniper(133));
		//toReturn[0]=new Runner(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, color, new MovableRunner());
		return toReturn;	
	}
}
