package samurAI.movement;

import engine.entities.Bullet;
import engine.entities.Point;

public class BulletDodger {
	
	public double distanceFromBullet(Point location, Bullet bullet) {
		double slope=Math.tan(bullet.getAngle());
		double yIntercept=-slope*bullet.getPosition().x+bullet.getPosition().y;
		//converted to ax+by+c=0
		double a=-slope;
		double b=1;
		double c=-yIntercept;
		double distance=Math.abs(a*location.x+b*location.y+c)/Math.sqrt(a*a+b*b);
		return distance;
	}
	
	public boolean needToDodge(Point location, Bullet bullet, double radius) {
		return needToDodge(location, bullet, radius, 2);
	}
	
	public boolean needToDodge(Point location, Bullet bullet, double radius, double radiusMultiplier) {
		double distanceFromCenter=distanceFromBullet(location, bullet);
		return distanceFromCenter<radius*radiusMultiplier&&!bulletPastMe(location, bullet);		
	}
	
	public double getDirectionToMove(Point location, Bullet toDodge) {
		double bulletSlope=Math.tan(toDodge.getAngle());
		double yIntercept=-bulletSlope*toDodge.getPosition().x+toDodge.getPosition().y;
		boolean aboveBullet=location.y>bulletSlope*location.x+yIntercept;
		double moveSlope=-1/bulletSlope;
		double positiveAngle=(Math.atan(moveSlope)+Math.PI)%Math.PI;
		if (aboveBullet) {
			return positiveAngle;
		}
		else {
			return positiveAngle+Math.PI;
		}
	}
	
	private boolean bulletPastMe(Point location, Bullet bullet) {
		double bulletSlope=Math.tan(bullet.getAngle());
		double dodgeSlope=-1/bulletSlope;
		double yIntercept=-dodgeSlope*bullet.getPosition().x+bullet.getPosition().y;
		boolean aboveLine=location.y>dodgeSlope*location.x+yIntercept;
		double futureBulletY=bullet.getPosition().y+Math.sin(bullet.getAngle());
		double futureBulletX=bullet.getPosition().x+Math.cos(bullet.getAngle());
		boolean bulletWillBeAboveLine=futureBulletY>dodgeSlope*futureBulletX+yIntercept;
		return aboveLine^bulletWillBeAboveLine;
	}
}
