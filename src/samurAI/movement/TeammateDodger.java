package samurAI.movement;

import java.util.ArrayList;

import engine.entities.Point;
import engine.entities.Soldier;

public class TeammateDodger {
	private boolean needToMove=false;
	private double xVelocity=0, yVelocity=0;
	
	public TeammateDodger() {
	}
	
	public void update(ArrayList<Soldier> soldiers, Soldier mySoldier) {
		soldiers=getSoldiersOnTeam(soldiers, mySoldier);
		Point myFuturePosition=getFuturePosition(mySoldier);
		needToMove=false;
		xVelocity=0;
		yVelocity=0;
		for (Soldier s:soldiers) {
			Point otherFuturePosition=getFuturePosition(s);
			if (myFuturePosition.distance(otherFuturePosition)<mySoldier.getRadius()*4) {
				double direction=otherFuturePosition.directionTo(myFuturePosition);
				xVelocity+=Math.cos(direction);
				yVelocity+=Math.sin(direction);
				needToMove=true;
			}
		}
	}
	
	public Point getDodgeVelocity() {
		return new Point(xVelocity, yVelocity);
	}
	
	public boolean getNeedToMove() {
		if (needToMove) {
			System.out.println("Need to move...");
		}
		return needToMove;
	}
	
	private ArrayList<Soldier> getSoldiersOnTeam(ArrayList<Soldier> soldiers, Soldier mySoldier) {
		ArrayList<Soldier> toReturn=new ArrayList<Soldier>();
		for (Soldier s:soldiers) {
			if (s.getTeam()==mySoldier.getTeam()&&s!=mySoldier) {
				toReturn.add(s);
			}
		}
		return toReturn;
	}
	
	private Point getFuturePosition(Soldier s) {
		Point current=s.getPosition();
		return new Point(current.x+s.getVelocity().x*30, current.y+s.getVelocity().y*30);
	}
}
