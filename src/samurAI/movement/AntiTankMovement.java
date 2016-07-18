package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;
import engine.game.Window;

public class AntiTankMovement {
	private double direction=0f;
	private double speed=1;
	private double directionModifier=1;
	
	public void update(Soldier toAttack, ArrayList<Bullet> bullets, Soldier mySoldier, double radius, Point velocity) {
		Point position=mySoldier.getPosition();
		Point pointToAttack=getPointToAttack(toAttack, mySoldier);
		double angle=pointToAttack.directionTo(position);
		tryToFlipAngle(angle, radius, toAttack, pointToAttack);
		Point target=getTargetPoint(angle, radius, toAttack, pointToAttack);
		direction=position.directionTo(target);
	}
	
	private Point getPointToAttack(Soldier toAttack, Soldier mySoldier) {
		Point position=toAttack.getPosition();
		position.x+=(toAttack.getVelocity().x-mySoldier.getVelocity().x)*30;
		position.y+=(toAttack.getVelocity().y-mySoldier.getVelocity().y)*30;
		return position;
	}
	
	private double getAttackRadius(double radius, Soldier toAttack) {
		return 100+radius+toAttack.getVelocity().magnitude()*60;
	}
	
	public void tryToFlipAngle(double angle, double radius, Soldier toAttack, Point pointToAttack) {
		Point toTarget=getTargetPoint(angle, radius, toAttack, pointToAttack);
		directionModifier*=-1;
		Point flipped=getTargetPoint(angle, radius, toAttack, pointToAttack);
		directionModifier*=-1;
		if (isValidTarget(flipped)&&!isValidTarget(toTarget)) {
			directionModifier*=-1;
		}
	}
	
	private boolean isValidTarget(Point toTarget) {
		int margin=30;
		return toTarget.x>margin&&toTarget.y>margin&&toTarget.x<Window.WIDTH-margin&&toTarget.y<Window.HEIGHT-margin;
	}
	
	private Point getTargetPoint(double angle, double radius, Soldier toAttack, Point pointToAttack) {
		angle+=Math.PI/5*directionModifier;
		double distanceFromEnemy=getAttackRadius(radius, toAttack);
		Point target=new Point(distanceFromEnemy*Math.cos(angle)+pointToAttack.x, distanceFromEnemy*Math.sin(angle)+pointToAttack.y);
		return target;
	}
	

	public double getDirection() {
		return direction;
	}
	
	public double getSpeed() {
		return speed;
	}
	
}
