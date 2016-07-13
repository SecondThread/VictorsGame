package samurAI;

import java.util.ArrayList;

import engine.ai.SoldierAI;
import engine.entities.Bullet;
import engine.entities.Soldier;
import samurAI.input.Keyboard;

public class MoveableRunner extends SoldierAI {
	private Keyboard keyboard=new Keyboard();
	private double xVelocity=0, yVelocity=0;
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		
		keyboard.update();
		//keyboard.setDisplayKeycodeMessages(true);
		if (keyboard.getKeyDown(68)) {//left
			xVelocity++;
		}
		if (keyboard.getKeyDown(65)) {//right
			xVelocity--;
		}
		if (keyboard.getKeyDown(87)) {//up
			yVelocity++;
		}
		if (keyboard.getKeyDown(83)) {//down
			yVelocity--;
		}
		
		direction=Math.atan2(yVelocity, xVelocity);
		moveSpeed=Math.max(Math.abs(xVelocity), Math.abs(yVelocity))<0.1?0:1;
	}
}
