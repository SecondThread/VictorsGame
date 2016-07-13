package samurAI;

import java.awt.Color;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;

public class SamurAI extends PlayerAI {
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[2];
		toReturn[0]=new Runner(onLeft?50:Window.WIDTH-50, Window.HEIGHT/3, color, new MovableRunner());
		toReturn[1]=new Tank(onLeft?50:Window.WIDTH-50, 2*Window.HEIGHT/3, color, new MovableTank(), 0);
		return toReturn;	
	}
}
