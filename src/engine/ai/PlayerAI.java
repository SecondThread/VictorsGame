package engine.ai;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Sniper;
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
	public Soldier[] getStartFormation(boolean onLeft, int team) {
		Soldier[] toReturn=new Soldier[9];
		int x=50;
		if (!onLeft) {
			x=Window.WIDTH-x;
		}
		for (int i=0; i<toReturn.length; i++) {
			if (i%2==0) {				
				toReturn[i]=new Tank(x, (i+1)*Window.HEIGHT/(toReturn.length+1), team, new TankAI(12), 1);
			}
			else {
				toReturn[i]=new Sniper(x, (i+1)*Window.HEIGHT/(toReturn.length+1), team, new SniperAI(12));
			}
		}
		return toReturn;
	}
	
	public void update(ArrayList<Soldier> players, ArrayList<Bullet> bullets) {
		
	}
}
