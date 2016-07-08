package engine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import engine.ai.SniperAI;

public class Sniper extends Soldier {
	private SniperAI ai;
	private double gunAngle=0;
	private int gunLength=15;
	private Polygon gun;
	
	public Sniper(double x, double y, Color color, SniperAI ai) {
		super(x, y, color, ai);
		this.ai=ai;
	}
	
	protected void subUpdate(ArrayList<Soldier> others) {
		gunAngle=ai.getGunAngle();
		updateGun();
	}
	
	private void updateGun() {
		gun=new Polygon();
		double upperAngle=gunAngle+Math.PI/10, lowerAngle=gunAngle-Math.PI/10;
		Point topLeft=new Point(Math.cos(upperAngle), Math.sin(upperAngle));
		Point bottomLeft=new Point(Math.cos(lowerAngle), Math.sin(lowerAngle));
		Point topRight=new Point(topLeft.x+gunLength*Math.cos(gunLength), topLeft.y+gunLength*Math.sin(gunLength));
		Point bottomRight=new Point(bottomLeft.x+gunLength*Math.cos(gunLength), bottomLeft.y+gunLength*Math.sin(gunLength));
		gun.addPoint((int)(topLeft.x), (int)(topLeft.y));
		gun.addPoint((int)(bottomLeft.x), (int)(bottomLeft.y));
		gun.addPoint((int)(bottomRight.x), (int)(bottomRight.y));
		gun.addPoint((int)(topRight.x), (int)(topRight.y));
		gun.translate((int)(getPosition().x), (int)(getPosition().y));
	}
	
	protected void subRender(Graphics2D g) {
		if (gun==null) {
			updateGun();
		}
		g.setColor(Color.black);
		g.fill(gun);
	}

}
