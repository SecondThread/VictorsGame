package samurAI;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.game.Window;
import engine.networking.entities.BigKahunaChangable;

public class SamurAISniper extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[1];
		toReturn[0]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, team, new BigKahunaChangable(0, false, team));
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, color, new AAASniper(133));
		//toReturn[0]=new Runner(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, color, new MovableRunner());
		return toReturn;	
	}
}
