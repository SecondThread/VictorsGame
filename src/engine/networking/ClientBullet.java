package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.entities.Point;

public class ClientBullet {
	private double x, y;
	
	public ClientBullet(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect((int)x-1, (int)y-1, 3, 3);
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
}
