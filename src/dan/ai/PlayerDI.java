package dan.ai;

import java.awt.Color;

import engine.ai.PlayerAI;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.game.Window;

public class PlayerDI extends PlayerAI {
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn = new Soldier[1];
		int x = 50;
		if (onLeft) {
			x = Window.WIDTH - x;
		}
		for (int i = 0; i < toReturn.length; i++) {
			SniperDI temp = new SniperDI();
			toReturn[i] = new Sniper(x, (i + 1) * Window.HEIGHT / (toReturn.length + 1), color, temp);
			temp.setObject(toReturn[i]);
		}
		return toReturn;
	}
}
