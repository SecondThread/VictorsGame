package engine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import engine.ai.RunnerAI;
import engine.game.Sprite;
import engine.game.Window;

public class Runner extends Soldier {
	
	private int blinkCounter=60, blinkCooldown=90;
	private RunnerAI ai;
	
	public Runner(double x, double y, int team, RunnerAI ai) {
		super(x, y, team, ai);
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
	
	public void render(Graphics2D g) {
		if (Window.fancyMode) {
			Image toDraw=null;
			if (getTeam()==0) {
				toDraw=Sprite.runner.getBufferedImage();
			}
			else {
				toDraw=Sprite.runnerBlue.getBufferedImage();
			}
			g.rotate(Math.atan2(yVelocity, xVelocity), getPosition().x, getPosition().y);
			g.drawImage(toDraw, (int)(getPosition().x-getRadius()), (int)(getPosition().y-getRadius()), (int)(getRadius()*2), (int)(getRadius()*2), null);
			g.rotate(-Math.atan2(yVelocity, xVelocity), getPosition().x, getPosition().y);
		} 
		else {
			super.render(g);
		}
	}
	
	public Color getColor() {
		Color normal=super.getColor();
		if (blinkCounter==0) {
			return normal;
		}
		return new Color((int)(normal.getRed()*.7), (int)(normal.getGreen()*.7), (int)(normal.getBlue()*0.7));
	}
}
