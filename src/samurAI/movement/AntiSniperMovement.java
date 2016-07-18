package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Sniper;
import engine.entities.Soldier;

public class AntiSniperMovement {
	private double direction=0f;
	private double speed=1;
	private boolean goodTimeToShoot=false;
	
	private int idealDistance=250;
	
	public void update(Soldier toAttack, ArrayList<Bullet> bullets, Soldier mySoldier, double radius, Point velocity) {
		Point myFuturePosition=getFuturePosition(mySoldier, 30);
		Point enemyFuturePosition=getFuturePosition(toAttack, 30);
		if (toAttack instanceof Sniper) {
			int framesLeft=((Sniper) toAttack).getFramesUntilCanShoot();
			if (framesLeft<40) {
				idealDistance=(int) (700+toAttack.getVelocity().magnitude()*30);
			}
			else {
				idealDistance=400;
			}
			goodTimeToShoot=framesLeft>45&&framesLeft<60;
		}
		else {
			goodTimeToShoot=myFuturePosition.distance(enemyFuturePosition)<350;
		}
		if (myFuturePosition.distance(enemyFuturePosition)<idealDistance) {
			direction=enemyFuturePosition.directionTo(myFuturePosition);//run
		}
		else {
			direction=myFuturePosition.directionTo(enemyFuturePosition);//go toward him
		}
	}
	
	public Point getFuturePosition(Soldier soldier, int scaleFactor) {
		return new Point(soldier.getPosition().x+soldier.getVelocity().x*scaleFactor, soldier.getPosition().y+soldier.getVelocity().y*scaleFactor);
	}
	

	public double getDirection() {
		return direction;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public boolean goodTimeToShoot() {
		return goodTimeToShoot;
	}
	
}
