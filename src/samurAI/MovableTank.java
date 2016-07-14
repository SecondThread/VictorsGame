package samurAI;

import java.util.ArrayList;

import engine.ai.TankAI;
import engine.entities.Bullet;
import engine.entities.Soldier;
import samurAI.input.Keyboard;

public class MovableTank extends TankAI{
	private Keyboard keyboard=new Keyboard();
	private double xVelocity=0, yVelocity=0;
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		
		xVelocity=0;
		yVelocity=0;
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
		if (Math.abs(xVelocity)<0.1&&Math.abs(yVelocity)<0.1) {
			moveSpeed=0;
		}
		else {			
			moveSpeed=1;
		}
	}
}
