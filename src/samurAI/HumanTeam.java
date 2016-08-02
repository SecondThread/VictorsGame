package samurAI;

import java.util.ArrayList;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.game.Window;
import engine.networking.entities.BigKahunaChangable;
import samurAI.formations.Formation;
import samurAI.formations.SniperCrossFormation;

public class HumanTeam extends PlayerAI {
	
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[7];
		toReturn=spawnHumans(onLeft, team, 4);
		//toReturn=spawnFormation(onLeft, team);
		//toReturn[0]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT/3, team, new BigKahunaChangable(0, false, team));
		/*toReturn[0]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT*1/8, team, new MovableTank(team), 0);
		toReturn[1]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT*2/8, team, new MovableTank(team), 0);
		toReturn[2]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT*3/8, team, new MovableTank(team), 0);
		toReturn[3]=new Sniper(onLeft?200:Window.WIDTH-200, Window.HEIGHT*4/8, team, new AAASniper(team));
		toReturn[4]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT*5/8, team, new MovableTank(team), 0);
		toReturn[5]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT*6/8, team, new MovableTank(team), 0);
		toReturn[6]=new Tank(onLeft?200:Window.WIDTH-200, Window.HEIGHT*7/8, team, new MovableTank(team), 0);*/
		return toReturn;	
	}
	
	private Soldier[] spawnFormation(boolean onLeft, int team) {
		Formation created=new SniperCrossFormation(onLeft?200:Window.WIDTH-200, Window.HEIGHT/2, team);
		ArrayList<Soldier> asList=created.spawnSoldiers();
		Soldier[] toReturn=new Soldier[asList.size()];
		for (int i=0; i<toReturn.length; i++) {
			toReturn[i]=asList.get(i);
		}
		return toReturn;
	}
	
	private Soldier[] spawnHumans(boolean onLeft, int team, int numberOfHumans) {
		Soldier[] toReturn=new Soldier[numberOfHumans];
		for (int i=0; i<numberOfHumans; i++) {
			toReturn[i]=new Runner(onLeft?200:Window.WIDTH-200, Window.HEIGHT*(i+1)/(numberOfHumans+1), team, new BigKahunaChangable(i, false, team));
		}
		return toReturn;
	}
}