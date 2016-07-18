package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.entities.Point;

public class ClientTank extends ClientSoldier {

	private Polygon shield;
	private int shieldWidth=10;
	
	public ClientTank(double x, double y, int r, int g, int b, double shieldAngle) {
		super(x, y, r, g, b);
		updateShield(shieldAngle);
	}
	
	private void updateShield(double shieldAngle) {
		shield=new Polygon();
		double upperAngle=shieldAngle+Math.PI/4, lowerAngle=shieldAngle-Math.PI/4;
		Point topLeft=new Point(getRadius()*1.9*Math.cos(upperAngle), getRadius()*1.9*Math.sin(upperAngle));
		Point bottomLeft=new Point(getRadius()*1.9*Math.cos(lowerAngle), getRadius()*1.9*Math.sin(lowerAngle));
		Point topRight=new Point(topLeft.x+shieldWidth*Math.cos(shieldAngle), topLeft.y+shieldWidth*Math.sin(shieldAngle));
		Point bottomRight=new Point(bottomLeft.x+shieldWidth*Math.cos(shieldAngle), bottomLeft.y+shieldWidth*Math.sin(shieldAngle));
		shield.addPoint((int)(topLeft.x), (int)(topLeft.y));
		shield.addPoint((int)(bottomLeft.x), (int)(bottomLeft.y));
		shield.addPoint((int)(bottomRight.x), (int)(bottomRight.y));
		shield.addPoint((int)(topRight.x), (int)(topRight.y));
		shield.translate((int)(getPosition().x), (int)(getPosition().y));
	}

	protected void subRender(Graphics2D g) {
		g.setColor(Color.blue);
		g.fill(shield);
	}
}
