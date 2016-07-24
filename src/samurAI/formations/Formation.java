package samurAI.formations;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;

public class Formation {
	protected double x, y;
	private double radius=30;
	private double angleOffset=0;
	private int soldiersInFormation;
	private boolean formationBroken=false;
	
	public Formation(int x, int y, int soldiersInFormation) {
		this.x=x;
		this.y=y;
		this.soldiersInFormation=soldiersInFormation;
	}
	
	public ArrayList<Soldier> spawnSoldiers() {
		return new ArrayList<Soldier>();
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		
	}
	
	public Point getIdealPosition(int formationIndex) {
		double angle=Math.PI*2*formationIndex/soldiersInFormation+angleOffset;
		double newX=x+radius*Math.cos(angle);
		double newY=y+radius*Math.sin(angle);
		return new Point(newX, newY);
	}
	
	public boolean getFormationBroken() {
		return formationBroken;
	}
	
	public boolean isTimeToShoot(int formationIndex) {
		return true;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	protected void setRadius(double radius) {
		this.radius=radius;
	}
	
	protected void setAngleOffset(double angleOffset) {
		this.angleOffset=angleOffset;
	}
	
	public double getRadius() {
		return radius;
	}
	
	protected void setFormationBroken(boolean broken) {
		formationBroken=broken;
	}
	
}
