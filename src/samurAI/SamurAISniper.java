package samurAI;

import java.util.ArrayList;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.game.Window;
import samurAI.formations.Formation;
import samurAI.formations.SniperCrossFormation;
import samurAI.movement.DeathchargeRunner;

public class SamurAISniper extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[1];
		toReturn=spawnFormation(onLeft, team, 2);
		//toReturn[0]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3, team, new BigKahunaChangable(0, false, team));
		//toReturn[1]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3*2, team, new BigKahunaChangable(1, false, team));
		//toReturn[2]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/4, team, new BigKahunaChangable(2, false, team));
		toReturn[0]=new Runner(onLeft?50:Window.WIDTH-50, Window.HEIGHT*5/6, team, new DeathchargeRunner(team));
		toReturn[1]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/6, team, new DeathchargeRunner(team));
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3, team, new AAASniper(team));
		//toReturn[0]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3*2, team, new MovableTank(team), Math.PI);
		return toReturn;	
	}
	
	private Soldier[] spawnFormation(boolean onLeft, int team, int extra) {
		Formation created=new SniperCrossFormation(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, team);
		ArrayList<Soldier> asList=created.spawnSoldiers();
		Soldier[] toReturn=new Soldier[asList.size()+extra];
		for (int i=0; i<asList.size(); i++) {
			toReturn[i+extra]=asList.get(i);
		}
		return toReturn;
	}
}
