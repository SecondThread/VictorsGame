package samurAI;

import java.awt.Color;

import engine.ai.PlayerAI;
import engine.entities.Runner;
import engine.entities.Soldier;
import engine.game.Window;

public class SamurAI extends PlayerAI {
	public Soldier[] getStartFormation(boolean onLeft, Color color) {
		Soldier[] toReturn=new Soldier[1];
		toReturn[0]=new Runner(onLeft?50:Window.WIDTH-50, Window.HEIGHT/2, color, new MoveableSprinter());
		return toReturn;
		
	}
}
