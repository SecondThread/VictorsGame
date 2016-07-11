package engine.ai;

import java.awt.Color;
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
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[1];
		int x=50;
		if (onLeft) {
			x=Window.WIDTH-x;
		}
		for (int i=0; i<toReturn.length; i++) {
			if (i%2==0) {				
				toReturn[i]=new Tank(x, (i+1)*Window.HEIGHT/(toReturn.length+1), color, new TankAI(), 1);
			}
			else {
				toReturn[i]=new Sniper(x, (i+1)*Window.HEIGHT/(toReturn.length+1), color, new SniperAI());
			}
		}
		return toReturn;
	}
	
	public void update(ArrayList<Soldier> players, ArrayList<Bullet> bullets) {
		for(Soldier x : players){
			if(x instanceof Sniper){
				
			}
		}
	}
}
