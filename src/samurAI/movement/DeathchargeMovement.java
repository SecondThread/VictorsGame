package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Point;
import engine.entities.Runner;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class DeathchargeMovement {
	
	private double directionToMove=0, moveSpeed=0;
	private boolean shouldDeathcharge=false;
	private AntiTankMovement tankMovement=new AntiTankMovement(5);
	
	public void update(Soldier mySoldier, ArrayList<Soldier> soldiers, Soldier toTarget) {
		if (toTarget==null) {
			moveSpeed=0;
			return;
		}
		if (shouldDeathcharge(mySoldier, toTarget)) {
			shouldDeathcharge=true;
			deathcharge(mySoldier, toTarget);
		}
		else {
			moveNormally(mySoldier, toTarget);
			shouldDeathcharge=false;
		}
		
	}
	
	public boolean shouldDeathcharge(Soldier mySoldier, Soldier toTarget) {
		double distance=mySoldier.getPosition().distance(getFuturePosition(toTarget));
		if (toTarget instanceof Runner) {
			return distance<150;
		}
		if (toTarget instanceof Tank) {
			double angle=toTarget.getPosition().directionTo(mySoldier.getPosition());
			double shieldAngle=((Tank)toTarget).getShieldAngle();
			boolean offsetEnough=new Point(Math.cos(angle), Math.sin(angle)).distance(new Point(Math.cos(shieldAngle), Math.sin(shieldAngle)))>1.5;
			return distance<300&&offsetEnough;
		}
		if (toTarget instanceof Sniper) {
			return distance<150||(distance<400&&((Sniper)toTarget).getFramesUntilCanShoot()>60);
		}
		return true;
	}
	
	public void moveNormally(Soldier mySoldier, Soldier toTarget) {
		double distance=mySoldier.getPosition().distance(toTarget.getPosition());
		if (toTarget instanceof Sniper) {
			int idealDistance=350;
			if (distance>idealDistance) {
				directionToMove=mySoldier.getPosition().directionTo(getFuturePosition(toTarget));
			}
			else {
				directionToMove=getFuturePosition(toTarget).directionTo(mySoldier.getPosition());
			}
			return;
		}
		if (toTarget instanceof Runner) {
			int idealDistance=200;
			if (distance>idealDistance) {
				directionToMove=mySoldier.getPosition().directionTo(getFuturePosition(toTarget));
			}
			else {
				directionToMove=getFuturePosition(toTarget).directionTo(mySoldier.getPosition());
			}
			return;
		}
		if (toTarget instanceof Tank) {
			tankMovement.update(toTarget, mySoldier, mySoldier.getRadius(), mySoldier.getVelocity());
			directionToMove=tankMovement.getDirection();
			return;
		}
	}
	
	public Point getFuturePosition(Soldier other) {
		return getFuturePosition(other, 20);
	}
	
	public Point getFuturePosition(Soldier other, double extrapolationValue) {
		return new Point(other.getPosition().x+other.getVelocity().x*extrapolationValue, other.getPosition().y+other.getVelocity().y*extrapolationValue);
	}
	
	public double getDirectionToMove() {
		return directionToMove;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	public boolean getShouldDeathcharge() {
		return shouldDeathcharge;
		
	}

	private void deathcharge(Soldier mySoldier, Soldier toTarget) {
		if (!(toTarget instanceof Tank)) {			
			directionToMove=mySoldier.getPosition().directionTo(toTarget.getPosition());
		}
		else {
			Point toAttack=toTarget.getPosition();
			toAttack.x+=1.5*toTarget.getRadius()*Math.cos(((Tank)toTarget).getShieldAngle());
			toAttack.y+=1.5*toTarget.getRadius()*Math.sin(((Tank)toTarget).getShieldAngle());
			directionToMove=mySoldier.getPosition().directionTo(toAttack);
		}
		moveSpeed=1;
	}
	
}
