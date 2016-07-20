package engine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.game.Sprite;
import engine.game.Window;

public class Sniper extends Soldier {
	private SniperAI ai;
	private double gunAngle=0;
	private int gunLength=25, gunHeight=3;
	private Polygon gun;
	private int shootCounter=0, maxShootCounter=120;
	
	public Sniper(double x, double y, int team, SniperAI ai) {
		super(x, y, team, ai);
		this.ai=ai;
	}
	
	protected void subUpdate(ArrayList<Soldier> others, ArrayList<Bullet> bullets) {
		gunAngle=ai.getGunAngle();
		updateGun();
		tryToShoot(bullets);
	}
	
	private void updateGun() {
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
	
	public void render(Graphics2D g) {
		if (Window.fancyMode) {			
			g.rotate(gunAngle, getPosition().x, getPosition().y);
			double newRadius=getRadius()*44/40;
			Image toDraw=null;
			if (getTeam()==0) {
				toDraw=Sprite.sniper.getBufferedImage();
			}
			else {
				toDraw=Sprite.sniperBlue.getBufferedImage();
			}
			g.drawImage(toDraw, (int)(getPosition().x-newRadius), (int)(getPosition().y-newRadius), (int)(getRadius()*2*58/44), (int)(newRadius*2), null);
			g.rotate(-gunAngle, getPosition().x, getPosition().y);
			//subRender(g);
		} 
		else {
			super.render(g);
		}
	}
	
	protected void subRender(Graphics2D g) {
		if (gun==null) {
			updateGun();
		}
		g.setColor(Color.white);
		g.fill(gun);
	}

	private void tryToShoot(ArrayList<Bullet> bullets) {
		if (shootCounter<=0) {
			if (ai.fireIfPossible()) {
				shootCounter=maxShootCounter;
				fire(bullets);
			}
		}
		else {
			shootCounter--;
		}
	}
	
	private void fire(ArrayList<Bullet> bullets) {
		double xOffset=getRadius()*1.5*Math.cos(gunAngle);
		double yOffset=getRadius()*1.5*Math.sin(gunAngle);
		Bullet toCreate=new Bullet(getPosition().x+xOffset, getPosition().y+yOffset, gunAngle);
		bullets.add(toCreate);
	}
	
	public int getFramesUntilCanShoot() {
		return shootCounter; 
	}
	
	public double getGunAngle() {
		return gunAngle;
	}
	
}
