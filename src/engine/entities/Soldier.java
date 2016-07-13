package engine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;

import engine.ai.SoldierAI;
import engine.game.Window;

public class Soldier {
	
	private double x, y, radius=15, xVelocity=0, yVelocity=0;
	private Color color;
	private Polygon shape=new Polygon(), shapeToReturn;
	private int points=40;
	
	private SoldierAI ai;
	protected double acceleration=.04, friction=0.99;
	
	protected boolean isRunner=false;
	
	private boolean dead=false;
	
	public Soldier(double x, double y, Color color, SoldierAI ai) {
		this.x=x; 
		this.y=y;
		this.color=color;
		for (int i=0; i<points; i++) {
			double theta=i*Math.PI*2/points;
			shape.addPoint((int)(radius*Math.cos(theta)), (int)(radius*Math.sin(theta)));
		}
		this.ai=ai;
	}
	
	public void update(ArrayList<Soldier> otherSoldiers, ArrayList<Bullet> bullets) {
		updateShapeToReturn();
		ai.update(otherSoldiers, bullets);
		subUpdate(otherSoldiers, bullets);
		double direction=ai.getDirectionToMove();
		double speed=ai.getMoveSpeed()*acceleration;
		
		xVelocity+=speed*Math.cos(direction);
		yVelocity+=speed*Math.sin(direction);
		
		xVelocity*=friction;
		yVelocity*=friction;
		
		x+=xVelocity;
		y+=yVelocity;
		
		checkForCollisions(otherSoldiers);
		
		if (x<0||y<0||x>=Window.WIDTH||y>=Window.HEIGHT) {
			dead=true;
		}
		System.out.println(xVelocity+" "+yVelocity);
		
	}
	
	protected void subUpdate(ArrayList<Soldier> others, ArrayList<Bullet> bullets) {
		
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
	
	public boolean isDead() {
		return dead;
	}
	
	public void OnCollision(boolean otherIsRunner) {
		if (isRunner) {
			if (otherIsRunner) {
				dead=true;
			}
			else {
				dead=false;
			}
		}
		else {
			dead=true;
		}
	}
	
	public boolean isRunner() {
		return isRunner;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public Point getVelocity() {
		return new Point(xVelocity, yVelocity);
	}
	
	private void checkForCollisions(ArrayList<Soldier> others) {
		for (Soldier s:others) {
			if (this==s)
				continue;
			Point otherLocation=s.getPosition();
			double dx=otherLocation.x-x;
			double dy=otherLocation.y-y;
			double distance=Math.sqrt(dx*dx+dy*dy);
			if (distance<radius*2) {
				if (isRunner()) {
					if (s.isRunner()) {
						dead=true;
					}
					else {
						dead=false;
					}
				}
				else {
					dead=true;
				}
				s.OnCollision(isRunner());
			}
		}
	}
	
	public double getRadius() {
		return radius;
	}
	
	private void updateShapeToReturn() {
		shapeToReturn=new Polygon();
		for (int i=0; i<points; i++) {
			double theta=i*Math.PI*2/points;
			shapeToReturn.addPoint((int)(radius*Math.cos(theta)), (int)(radius*Math.sin(theta)));
		}
		shapeToReturn.translate((int)x, (int)y);
	}
	
	public Polygon getShape() {	
		if (shapeToReturn==null) {
			updateShapeToReturn();
		}
		return shapeToReturn;
	}
	
}
