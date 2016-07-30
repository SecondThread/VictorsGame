package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;

import engine.entities.Point;
import engine.game.Sprite;
import engine.game.Window;

public class ClientSoldier {
	private double x, y;
	private int team=0;
	private double xVelocity, yVelocity;
	private Color color;
	double radius=15;
	int points=40;
	Polygon shape=new Polygon();
	
	public ClientSoldier(double x, double y, int team, double xVelocity, double yVelocity) {
		this.x=x;
		this.y=y;
		this.team=team;
		this.xVelocity=xVelocity;
		this.yVelocity=yVelocity;
		if (team==0) {
			color=Color.red;
		}
		else {
			color=Color.blue;
		}
		for (int i=0; i<points; i++) {
			double theta=i*Math.PI*2/points;
			shape.addPoint((int)(radius*Math.cos(theta)), (int)(radius*Math.sin(theta)));
		}
	}
	
	public void render(Graphics2D g) {
		if (Window.fancyMode) {
			fancyRender(g);
		}
		else {
			shape.translate((int)x, (int) (y));
			g.setColor(color);
			g.fill(shape);
			shape.translate(-(int)x, -(int)(y));
		}
		subRender(g);
	}
	
	private void fancyRender(Graphics2D g) {
		Image toDraw=null;
		if (team==0) {
			toDraw=Sprite.runner.getBufferedImage();
		}
		else {
			toDraw=Sprite.runnerBlue.getBufferedImage();
		}
		g.rotate(Math.atan2(yVelocity, xVelocity), x, y);
		g.drawImage(toDraw, (int)(getPosition().x-getRadius()), (int)(getPosition().y-getRadius()), (int)(getRadius()*2), (int)(getRadius()*2), null);
		g.rotate(-Math.atan2(yVelocity, xVelocity), x, y);
	}
	
	protected void subRender(Graphics2D g) {
		
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public double getRadius() {
		return radius;
	}
	
	public int getTeam() {
		return team;
	}
}
