package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import engine.entities.Point;

public class ClientSoldier {
	private double x, y;
	private Color color;
	double radius=15;
	int points=40;
	Polygon shape=new Polygon();
	
	public ClientSoldier(double x, double y, int r, int g, int b) {
		this.x=x;
		this.y=y;
		color=new Color(r, g, b);
		for (int i=0; i<points; i++) {
			double theta=i*Math.PI*2/points;
			shape.addPoint((int)(radius*Math.cos(theta)), (int)(radius*Math.sin(theta)));
		}
	}
	
	public void render(Graphics2D g) {
		shape.translate((int)x, (int) (y));
		g.setColor(color);
		g.fill(shape);
		subRender(g);
		shape.translate(-(int)x, -(int)(y));
	}
	
	protected void subRender(Graphics2D g) {
		
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public double getRadius() {
		return radius;
	}
}
