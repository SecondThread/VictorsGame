package engine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import engine.ai.TankAI;

public class Tank extends Soldier {

	private double shieldAngle=0;
	private int shieldWidth=10;
	private TankAI ai;
	private Polygon shield;
	private Point topLeft, topRight, bottomLeft, bottomRight;
	
	public Tank(double x, double y, Color color, TankAI ai, double startShieldAngle) {
		super(x, y, color, ai);
		shieldAngle=startShieldAngle;
		this.ai=ai;
	}
	
	protected void subUpdate(ArrayList<Soldier> others) {
		tryToRotateShield();
		updateShield();//yuck...
		translateShield(1);
		Point sideRight=topRight.midpoint(bottomRight);
		Point upperSideRight=sideRight.midpoint(topRight);
		Point lowerSideRight=sideRight.midpoint(bottomRight);
		for (Soldier s:others) {
			if (s.getShape().contains(topLeft.x, topLeft.y)||s.getShape().contains(topRight.x, topRight.y)||s.getShape().contains(bottomLeft.x, bottomLeft.y)
					||s.getShape().contains(bottomRight.x, bottomRight.y)||s.getShape().contains(sideRight.x, sideRight.y)||s.getShape().contains(upperSideRight.x, upperSideRight.y)
					||s.getShape().contains(lowerSideRight.x, lowerSideRight.y)){	
				System.out.println("!!!");
				s.OnCollision(true);
			}
		}
		translateShield(-1);
	}
	
	private void translateShield(double multiplier) {
		topLeft.x+=getPosition().x*multiplier;
		topLeft.y+=getPosition().y*multiplier;
		topRight.x+=getPosition().x*multiplier;
		topRight.y+=getPosition().y*multiplier;
		bottomLeft.x+=getPosition().x*multiplier;
		bottomLeft.y+=getPosition().y*multiplier;
		bottomRight.x+=getPosition().x*multiplier;
		bottomRight.y+=getPosition().y*multiplier;
	}

	private void tryToRotateShield() {
		if (ai.getShieldTurnDirection()>0) {
			shieldAngle+=Math.PI/250;
		}
		else {
			shieldAngle-=Math.PI/250;
		}
	}
	
	private void updateShield() {
		shield=new Polygon();
		double upperAngle=shieldAngle+Math.PI/4, lowerAngle=shieldAngle-Math.PI/4;
		topLeft=new Point(getRadius()*1.9*Math.cos(upperAngle), getRadius()*1.9*Math.sin(upperAngle));
		bottomLeft=new Point(getRadius()*1.9*Math.cos(lowerAngle), getRadius()*1.9*Math.sin(lowerAngle));
		topRight=new Point(topLeft.x+shieldWidth*Math.cos(shieldAngle), topLeft.y+shieldWidth*Math.sin(shieldAngle));
		bottomRight=new Point(bottomLeft.x+shieldWidth*Math.cos(shieldAngle), bottomLeft.y+shieldWidth*Math.sin(shieldAngle));
		shield.addPoint((int)(topLeft.x), (int)(topLeft.y));
		shield.addPoint((int)(bottomLeft.x), (int)(bottomLeft.y));
		shield.addPoint((int)(bottomRight.x), (int)(bottomRight.y));
		shield.addPoint((int)(topRight.x), (int)(topRight.y));
		shield.translate((int)(getPosition().x), (int)(getPosition().y));
	}
	
	protected void subRender(Graphics2D g) {
		if (shield==null) {
			updateShield();
		}
		g.setColor(Color.blue);
		g.fill(shield);
	}
	
}
