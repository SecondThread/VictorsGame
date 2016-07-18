package engine.entities;

import java.awt.Color;
import java.util.ArrayList;

import engine.ai.RunnerAI;

public class Runner extends Soldier {
	
	private int blinkCounter=60, blinkCooldown=90;
	private RunnerAI ai;
	
	public Runner(double x, double y, Color color, RunnerAI ai) {
		super(x, y, color, ai);
		friction=.95;
		acceleration=0.25;
		isRunner=true;
		this.ai=ai;
	}
	
	protected void subUpdate(ArrayList<Soldier> others, ArrayList<Bullet> bullets) {
		
		if (blinkCounter>0) {
			blinkCounter--;
		}
		else {
			if (ai.blinkIfPossible()) {
				xVelocity*=-1.5;
				yVelocity*=-1.5;
				blinkCounter=blinkCooldown;
			}
		}
	}
	
	public int getFramesUntilCanBlink() {
		return blinkCounter;
	}
	
	public Color getColor() {
		Color normal=super.getColor();
		if (blinkCounter==0) {
			return normal;
		}
		return new Color((int)(normal.getRed()*.7), (int)(normal.getGreen()*.7), (int)(normal.getBlue()*0.7));
	}
}
