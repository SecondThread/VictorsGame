package samurAI;

import java.util.ArrayList;

import engine.ai.PlayerAI;
import engine.entities.Soldier;
import engine.game.Window;
import samurAI.formations.Formation;
import samurAI.formations.SniperDuoFormation;

public class SamurAI extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[2];
		toReturn=spawnFormation(onLeft, team);
		//toReturn[0]=new Tank(onLeft?50:Window.WIDTH-50, Window.HEIGHT/3, team, new MovableTank(team), 0);
		//toReturn[1]=new Tank(onLeft?50:Window.WIDTH-50, Window.HEIGHT/3*2, team, new MovableTank(team), 0);
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, color, new AAASniper(123));
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3, team, new AAASniper(team));
		//toReturn[1]=new Sniper(onLeft?200:Window.WIDTH-200, 2*Window.HEIGHT/3, team, new AAASniper(team));
		return toReturn;	
	}
	
	private Soldier[] spawnFormation(boolean onLeft, int team) {
		Formation created=new SniperDuoFormation(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, team);
		ArrayList<Soldier> asList=created.spawnSoldiers();
		Soldier[] toReturn=new Soldier[asList.size()];
		for (int i=0; i<toReturn.length; i++) {
			toReturn[i]=asList.get(i);
		}
		return toReturn;
	}
}