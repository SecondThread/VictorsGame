package samurAI.formations;

import engine.entities.Point;
import engine.entities.Soldier;

public class FormationMovement {
	private Formation toFollow;
	private int formationIndex;
	private double directionToMove=0;
	
	public FormationMovement(Formation toFollow, int formationIndex) {
		this.toFollow=toFollow;
		this.formationIndex=formationIndex;
	} 
	
	
	public void update(Soldier mySoldier) {
		Point futurePosition=getFuturePosition(mySoldier);
		Point targetPosition=toFollow.getIdealPosition(formationIndex);
		directionToMove=futurePosition.directionTo(targetPosition);
	}
	
	private Point getFuturePosition(Soldier s) {
		Point current=s.getPosition();
		return new Point(current.x+s.getVelocity().x*30, current.y+s.getVelocity().y*30);
	}
	
	public double getDirectionToMove() {
		return directionToMove;
	}
	
	public double getMoveSpeed() {
		return 1;
	}
	
}
