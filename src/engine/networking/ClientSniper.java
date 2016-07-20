package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;

import engine.entities.Point;
import engine.game.Sprite;
import engine.game.Window;

public class ClientSniper extends ClientSoldier {

	private Polygon gun=new Polygon();
	private int team=0;
	private int gunLength=25, gunHeight=3;
	private double gunAngle;
	
	public ClientSniper(double x, double y, int team, double xVelocity, double yVelocity, double gunAngle) {
		super(x, y, team, xVelocity, yVelocity);
		this.gunAngle=gunAngle;
		this.team=team;
		updateGun();
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
			if (team==0) {
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
		g.setColor(Color.white);
		g.fill(gun);
	}
}
