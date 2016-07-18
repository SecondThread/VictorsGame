package samurAI.movement;

import java.util.ArrayList;

import javax.swing.JFrame;

import engine.entities.Bullet;
import engine.entities.Soldier;
import engine.game.Window;

//I don't really want to do this right now...
public class RealityMap {
	
	private float[][] map=new float[Window.WIDTH/4][Window.HEIGHT/4];
	
	public RealityMap(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		for (int x=0; x<map.length; x++) {
			for (int y=0; y<map[x].length; y++) {
				map[x][y]=0;
			}
		}
		
	}
	
	private static void printMap(RealityMap map) {
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
