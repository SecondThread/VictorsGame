package samurAI;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Soldier;

public class AAASniper extends SniperAI {
	private Soldier soldierToTarget=null;
	private Soldier mySoldier;
	private double gunAngle=0;
	
	private boolean dodge=true;
	private int dodgeCounter=50;
	
	public AAASniper(int team) {
		teamID=team;
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		updateMySoldier(soldiers);
		getSoldierToTarget(soldiers);
		if (soldierToTarget!=null) {
			targetSoldier();
		}		
		
		if (dodge) {
			tryToDodge();
		}
	}
	
	private void getSoldierToTarget(ArrayList<Soldier> soldiers) {
		ArrayList<Soldier> targetable=new ArrayList<Soldier>();//all soldiers not on my team
		for (Soldier s:soldiers) {
			if (s.getAI().getTeamID()!=teamID) {
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
	
	private void tryToDodge() {
		dodgeCounter-=1;
		if (dodgeCounter<=-200) {
			dodgeCounter=200;
		}
		System.out.println(dodgeCounter);
		
		moveSpeed=1;
		if (dodgeCounter>0) {
			direction=Math.PI/2;
		}
		else {
			direction=3*Math.PI/2;
		}
	}

}