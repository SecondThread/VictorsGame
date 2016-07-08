package engine.ai;

import java.awt.Color;

import engine.entities.Runner;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;

public class PlayerAI {
	
	/**
	 * This method is called at the beginning of the match. Return an array of length 10 of your ten soldiers
	 * @param onLeft
	 * @param color
	 * @return
	 */
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[9];
		int x=50;
		if (onLeft) {
			x=Window.WIDTH-x;
		}
		for (int i=0; i<toReturn.length; i++) {
			if (i%2==0) {				
				toReturn[i]=new Tank(x, (i+1)*Window.HEIGHT/(toReturn.length+1), color, new TankAI(), 1);
			}
			else {
				toReturn[i]=new Runner(x, (i+1)*Window.HEIGHT/(toReturn.length+1), color, new SoldierAI());
			}
		}
		return toReturn;
	}
	
	public void update() {
		
	}
}
