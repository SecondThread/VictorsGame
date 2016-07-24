package samurAI;

import engine.ai.PlayerAI;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;

public class SamurAISniper extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[1];
		//toReturn[0]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3, team, new BigKahunaChangable(0, false, team));
		//toReturn[1]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3*2, team, new BigKahunaChangable(1, false, team));
		//toReturn[2]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/4, team, new BigKahunaChangable(2, false, team));
		//toReturn[3]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/4*2, team, new BigKahunaChangable(3, false, team));
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, color, new AAASniper(133));
		toReturn[0]=new Tank(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, team, new MovableTank(team), Math.PI);
		return toReturn;	
	}
}
