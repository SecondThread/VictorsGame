package dan.ai;

import java.awt.Color;

import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;

public class Formations {
	private static boolean onLeft;
	private static Color color;
	
	public static void init(boolean onLeft, Color color){
		Formations.onLeft = onLeft;
		Formations.color = color;
	}
	
	public static Soldier[] allSniper(int num){
		Soldier[] toReturn = new Soldier[num];
		int x = 50;
		if (!onLeft) {
			x = Window.WIDTH - x;
		}
		for (int i = 0; i < toReturn.length; i++) {
			SniperDI temp = new SniperDI();
			toReturn[i] = new Sniper(x, (i + 1) * Window.HEIGHT / (toReturn.length + 1), color, temp);
			temp.setObject(toReturn[i]);
		}
		return toReturn;
	}
	
	public static Soldier[] allTank(int num){
		Soldier[] toReturn = new Soldier[num];
		int x = 50;
		double sAngle = 0;
		if (!onLeft) {
			x = Window.WIDTH - x;
			sAngle = 3.14;
		}
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i] = new Tank(x, (i + 1) * Window.HEIGHT / (toReturn.length + 1), color, new TankDI(),sAngle);
		}
		return toReturn;
	}

	public static Soldier[] halfNHalf(int num){
		Soldier[] toReturn = new Soldier[num];
		int x = 50;
		double sAngle = 0;
		if (!onLeft) {
			x = Window.WIDTH - x;
			sAngle = 3.14;
		}
		for(int i = 0; i < toReturn.length; i++){
			if(i % 2 != 0){
				SniperDI temp = new SniperDI();
				toReturn[i] = new Sniper(x, (i + 1) * Window.HEIGHT / (toReturn.length + 1), color, temp);
				temp.setObject(toReturn[i]);
			}else{
				toReturn[i] = new Tank(x, (i + 1) * Window.HEIGHT / (toReturn.length + 1), color, new TankDI(),sAngle);
			}
		}
		return toReturn;
	}
}
