package samurAI.movement;

import engine.entities.Point;
import engine.entities.Soldier;
import engine.game.Window;

public class AntiTankMovement {
	private double direction=0f;
	private double speed=1;
	private double directionModifier=1;
	private int flipCounter=0;
	
	private double extrapolationValue=30;
	
	public AntiTankMovement() {
		this(30);
	}
	
	public AntiTankMovement(double extrapolationValue) {
		this.extrapolationValue=extrapolationValue;
	}
	
	public void update(Soldier toAttack, Soldier mySoldier, double radius, Point velocity) {
		flipCounter++;
		if (flipCounter>1200) {
			directionModifier*=-1;
		}
		Point position=mySoldier.getPosition();
		Point pointToAttack=getPointToAttack(toAttack, mySoldier);
		double angle=pointToAttack.directionTo(position);
		tryToFlipAngle(angle, radius, toAttack, pointToAttack);
		Point target=getTargetPoint(angle, radius, toAttack, pointToAttack);
		direction=position.directionTo(target);
	}
	
	private Point getPointToAttack(Soldier toAttack, Soldier mySoldier) {
		Point position=toAttack.getPosition();
		position.x+=(toAttack.getVelocity().x-mySoldier.getVelocity().x)*extrapolationValue;
		position.y+=(toAttack.getVelocity().y-mySoldier.getVelocity().y)*extrapolationValue;
		return position;
	}
	
	private double getAttackRadius(double radius, Soldier toAttack) {
		return 100+radius+toAttack.getVelocity().magnitude()*3*extrapolationValue-30;
	}
	
	public void tryToFlipAngle(double angle, double radius, Soldier toAttack, Point pointToAttack) {
		Point toTarget=getTargetPoint(angle, radius, toAttack, pointToAttack);
		directionModifier*=-1;
		Point flipped=getTargetPoint(angle, radius, toAttack, pointToAttack);
		directionModifier*=-1;
		boolean canFlip=isValidTarget(flipped);
		boolean needToFlip=canFlip&&!isValidTarget(toTarget);
		if (needToFlip) {
			directionModifier*=-1;
			return;
		}
		if (!canFlip) {
			return;
		}
		double direction=toAttack.getVelocity().direction();
		double worstDirection=toAttack.getPosition().directionTo(pointToAttack);
		Point unpolarDirection=new Point(Math.cos(direction), Math.sin(direction));
		Point unpolarWorstDirection=new Point(Math.cos(worstDirection), Math.sin(worstDirection));
		if (unpolarDirection.distance(unpolarWorstDirection)<0.2) {
			//System.out.println("Flipping because really bad...");
			//directionModifier*=-1;
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
