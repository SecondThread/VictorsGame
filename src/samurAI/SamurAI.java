package samurAI;

import engine.ai.PlayerAI;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;

public class SamurAI extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[1];
		//toReturn[0]=new Runner(onLeft?50:Window.WIDTH-50, Window.HEIGHT/2, color, new MovableRunner());
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, color, new AAASniper(123));
		toReturn[0]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, team, new MovableTank(123), 0);
		return toReturn;	
	}
}