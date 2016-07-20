package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;

import engine.entities.Point;
import engine.game.Sprite;
import engine.game.Window;

public class ClientTank extends ClientSoldier {

	private Polygon shield;
	private int team=0;
	private int shieldWidth=10;
	private double shieldAngle=0;
	
	public ClientTank(double x, double y, int team, double xVelocity, double yVelocity, double shieldAngle) {
		super(x, y, team, xVelocity, yVelocity);
		this.shieldAngle=shieldAngle;
		this.team=team;
		updateShield();
	}
	
	private void updateShield() {
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
	
	public void render(Graphics2D g) {
		if (Window.fancyMode) {			
			g.rotate(shieldAngle+Math.PI/2, getPosition().x, getPosition().y);
			Image toDraw=null;
			if (team==0) {
				toDraw=Sprite.tank.getBufferedImage();
			}
			else {
				toDraw=Sprite.tankBlue.getBufferedImage();
			}
			g.drawImage(toDraw, (int)(getPosition().x-getRadius()), (int)(getPosition().y-getRadius()), (int)(getRadius()*2), (int)(getRadius()*2), null);
			g.rotate(-shieldAngle-Math.PI/2, getPosition().x, getPosition().y);
			subRender(g);
		} 
		else {
			super.render(g);
		}
	}

	protected void subRender(Graphics2D g) {
		g.setColor(Color.blue);
		g.fill(shield);
	}
}
