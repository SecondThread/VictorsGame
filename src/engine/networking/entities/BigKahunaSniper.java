package engine.networking.entities;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.networking.NetworkManager;

public class BigKahunaSniper extends SniperAI {
	private int player;
	private double xVelocity, yVelocity;
	private String input="";
	private Soldier me=null;
	private double gunAngle;

	public BigKahunaSniper(int player, int team) {
		super(team);
		this.player=player;
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		if (me==null) {
			for (Soldier s : soldiers) {
				if (s.getAI()==this) {
					me=s;
				}
			}
		}
		tryToMove();
		tryToAim();
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
		if (left) {// left
			xVelocity--;
		}
		if (right) {// right
			xVelocity++;
		}
		if (up) {// up
			yVelocity++;
		}
		if (down) {// down
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

	private void tryToAim() {
		if (!input.contains(",")) {
			return;
		}
		double mouseX=Integer.parseInt(input.split(",")[1]), mouseY=Integer.parseInt(input.split(",")[2]);
		double dx=mouseX-me.getPosition().x, dy=mouseY-me.getPosition().y;
		gunAngle=Math.atan2(dy, dx);
	}
	
	public double getGunAngle() {
		return gunAngle;
	}

	public boolean fireIfPossible() {
		if (!input.contains(",")) {
			return false;
		}
		return input.split(",")[3].equals("1");
	}
	
}
