package samurAI;

import java.awt.Color;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.game.Window;

public class SamurAI extends PlayerAI {
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[1];
		toReturn[0]=new Runner(onLeft?50:Window.WIDTH-50, Window.HEIGHT/2, color, new MovableRunner());
		//toReturn[0]=new Sniper(onLeft?200:Window.WIDTH-50, Window.HEIGHT/2, color, new AASniper(1));
		//toReturn[0]=new Tank(onLeft?50:Window.WIDTH-50, Window.HEIGHT/2, color, new TankAI(), 0);
		return toReturn;	
	}
}