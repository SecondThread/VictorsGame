package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.entities.Point;

public class ClientSniper extends ClientSoldier {

	private Polygon gun=new Polygon();
	private int gunLength=25, gunHeight=3;
	
	public ClientSniper(double x, double y, int r, int g, int b, double gunAngle) {
		super(x, y, r, g, b);
		updateGun(gunAngle);
		
	}

	private void updateGun(double gunAngle) {
		gun=new Polygon();
		double upperAngle=gunAngle+Math.PI/2, lowerAngle=gunAngle-Math.PI/2;
		Point topLeft=new Point(gunHeight*Math.cos(upperAngle), gunHeight*Math.sin(upperAngle));
		Point bottomLeft=new Point(gunHeight*Math.cos(lowerAngle), gunHeight*Math.sin(lowerAngle));
		Point topRight=new Point(topLeft.x+gunLength*Math.cos(gunAngle), topLeft.y+gunLength*Math.sin(gunAngle));
		Point bottomRight=new Point(bottomLeft.x+gunLength*Math.cos(gunAngle), bottomLeft.y+gunLength*Math.sin(gunAngle));
		gun.addPoint((int)(topLeft.x), (int)(topLeft.y));
		gun.addPoint((int)(bottomLeft.x), (int)(bottomLeft.y));
		gun.addPoint((int)(bottomRight.x), (int)(bottomRight.y));
		gun.addPoint((int)(topRight.x), (int)(topRight.y));
		gun.translate((int)(getPosition().x), (int)(getPosition().y));
	}
	
	protected void subRender(Graphics2D g) {
		g.setColor(Color.white);
		g.fill(gun);
	}
}
