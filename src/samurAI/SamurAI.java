package samurAI;

import engine.ai.PlayerAI;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;

public class SamurAI extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[2];
		toReturn[0]=new Tank(onLeft?50:Window.WIDTH-50, Window.HEIGHT/3, team, new MovableTank(team), 0);
		toReturn[1]=new Tank(onLeft?50:Window.WIDTH-50, Window.HEIGHT/3*2, team, new MovableTank(team), 0);
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, color, new AAASniper(123));
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3, team, new AAASniper(team));
		//toReturn[1]=new Sniper(onLeft?200:Window.WIDTH-200, 2*Window.HEIGHT/3, team, new AAASniper(team));
		return toReturn;	
	}
}