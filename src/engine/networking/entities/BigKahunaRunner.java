package engine.networking.entities;

import java.util.ArrayList;

import engine.ai.RunnerAI;
import engine.entities.Bullet;
import engine.entities.Soldier;
import engine.networking.NetworkManager;

public class BigKahunaRunner extends RunnerAI {

	private double xVelocity=0, yVelocity=0;
	private int player=0;
	private String input="";
	
	public BigKahunaRunner(int player, int team) {
		super(team);
		this.player=player;
	}

	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		tryToMove();
	}
	
	private void tryToMove() {
		xVelocity=0;
		yVelocity=0;
		input=NetworkManager.getKeyboardInputs(player);
		boolean right=false, left=false, up=false, down=false;
		if (input.length()>1) {
			right=input.charAt(0)=='1';
			left=input.charAt(1)=='1';
			up=input.charAt(2)=='1';
			down=input.charAt(3)=='1';
		}
		if (left) {//left
			xVelocity--;
		}
		if (right) {//right
			xVelocity++;
		}
		if (up) {//up
			yVelocity++;
		}
		if (down) {//down
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
	
	public boolean blinkIfPossible() {
		if (!input.contains(",")) {
			return false;
		}
		return input.split(",")[3].equals("1");
	}
	
}
