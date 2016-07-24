package samurAI;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;
import samurAI.movement.SmartMovement;
import samurAI.movement.TeammateDodger;

public class AAASniper extends SniperAI {
	private Soldier soldierToTarget=null;
	private Soldier mySoldier;
	private double gunAngle=0;
	
	private SmartMovement movement=new SmartMovement();
	private boolean fireIfPossible=true;
	
	public AAASniper(int team) {
		super(team);
		teamID=team;
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		updateMySoldier(soldiers);
		getSoldierToTarget(soldiers);
		if (soldierToTarget!=null) {
			targetSoldier();
			movement.update(soldierToTarget, soldiers, bullets, mySoldier, mySoldier.getRadius(), mySoldier.getVelocity());
		}
		fireIfPossible=movement.goodTimeToShoot(soldiers, mySoldier, gunAngle);
		direction=movement.getDirection();
		moveSpeed=movement.getSpeed();
	}
	
	private void getSoldierToTarget(ArrayList<Soldier> soldiers) {
		ArrayList<Soldier> targetable=new ArrayList<Soldier>();//all soldiers not on my team
		for (Soldier s:soldiers) {
			if (s.getTeam()!=mySoldier.getTeam()) {
				targetable.add(s);
			}
		}
		
		if (targetable.isEmpty()) {
			soldierToTarget=null;
			return;
		}
		
		soldierToTarget=targetable.get(0);//aim at closest other
		for (Soldier s:targetable) {
			if (s.getPosition().distance(mySoldier.getPosition())<soldierToTarget.getPosition().distance(mySoldier.getPosition())) {
				soldierToTarget=s;
			}
		}
	}

	private void targetSoldier() {
		Point myLocation=mySoldier.getPosition();
		Point otherLocation=soldierToTarget.getPosition();
		double distance=myLocation.distance(otherLocation);
		double bulletTravelTime=distance/Bullet.velocity; //speed=distance/time, so time=distance/speed
		otherLocation.x+=soldierToTarget.getVelocity().x*bulletTravelTime*.75;
		otherLocation.y+=soldierToTarget.getVelocity().y*bulletTravelTime*.75;
		gunAngle=Math.atan2(otherLocation.y-myLocation.y, otherLocation.x-myLocation.x);
	}

	private void updateMySoldier(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s.getAI()==this) {
				mySoldier=s;
			}
		}
	}
	
	public double getGunAngle() {
		return gunAngle;
	}

	public boolean fireIfPossible() {
		return fireIfPossible;
	}
	
	public Soldier getClosestSoldier(ArrayList<Soldier> soldiers) {
		Soldier closest=null;
		for (Soldier s:soldiers) {
			if (s==mySoldier) {
				continue;
			}
			if (closest==null) {
				closest=s;
			}
			if (mySoldier.getPosition().distance(closest.getPosition())>mySoldier.getPosition().distance(s.getPosition())) {
				closest=s;
			}
		}
		return closest;
	}
	
	public void setMySoldier(Soldier mySoldier) {
		this.mySoldier=mySoldier;
	}
	
}
