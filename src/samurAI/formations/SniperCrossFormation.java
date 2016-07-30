package samurAI.formations;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Runner;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;
import engine.game.Window;
import samurAI.AAASniper;

public class SniperCrossFormation extends Formation {
	private int team;
	private double[] directionSuggustion=new double[2];
	private Point[] positions=new Point[2];
	private Sniper player1=null;
	private Sniper player2=null;
	private Soldier target=null;
	private double angle=0, idealDistance=500, angleOffset=Math.PI/2;

	public SniperCrossFormation(int x, int y, int team) {
		super(x, y, 2);
		player1=new Sniper(getIdealPosition(0).x, getIdealPosition(0).y, team, new FormationCrossShooter(team, this, 0, new AAASniper(team, true)));
		player2=new Sniper(getIdealPosition(1).x, getIdealPosition(1).y, team, new FormationCrossShooter(team, this, 1, new AAASniper(team, true)));
		super.setRadius(50);
		this.team=team;
	}

	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		if (target==null) {
			getTarget(soldiers);
		}
		if (((FormationCrossShooter)player1.getAI()).needToMove()||((FormationCrossShooter)player2.getAI()).needToMove()) {
			setFormationBroken(true);
		}
		else {
			setFormationBroken(false);
		}
		updateIdealDistance();
		rotateAngle();
	}
	
	private void updateIdealDistance() {
		if (target instanceof Tank) {
			idealDistance=500;
			angleOffset=Math.PI/2;
		}
		if (target instanceof Sniper) {
			int framesLeft=((Sniper) target).getFramesUntilCanShoot();
			if (framesLeft<40) {
				idealDistance=(int) (800+target.getVelocity().magnitude()*30);
			}
			else {
				idealDistance=400;
			}
			angleOffset=Math.PI/3;
		}
		if (target instanceof Runner) {
			idealDistance=500;
			angleOffset=Math.PI/3;
		}
	}
	
	private void getTarget(ArrayList<Soldier> soldiers) {
		Point position=player1.getPosition().midpoint(player2.getPosition());
		for (Soldier s:soldiers) {
			if (s.getTeam()==team) {
				continue;
			}
			if (target==null) {
				target=s;
			}
			if (s.getPosition().distance(position)<target.getPosition().distance(position)) {
				target=s;
			}
		}
		angle=position.directionTo(target.getPosition());
	}
	
	public void setDirectionSuggustion(double direction, int formationIndex, Point position) {
		directionSuggustion[formationIndex]=direction;
		positions[formationIndex]=position;
	}

	public ArrayList<Soldier> spawnSoldiers() {
		ArrayList<Soldier> toReturn=new ArrayList<Soldier>();
		toReturn.add(player1);
		toReturn.add(player2);
		return toReturn;
	}
	
	public Point getIdealPosition(int formationIndex) {
		double angle=this.angle-angleOffset/2+angleOffset*formationIndex;
		if (target==null) {
			return super.getIdealPosition(formationIndex);
		}
		Point enemyPosition=target.getPosition();
		enemyPosition.x-=Math.cos(angle)*idealDistance;
		enemyPosition.y-=Math.sin(angle)*idealDistance;
		return enemyPosition;
	}
	
	private void rotateAngle() {
		if (target==null) {
			return;
		}
		Point corner1=new Point(0, 0), corner2=new Point(0, Window.HEIGHT), corner3=new Point(Window.WIDTH, 0), corner4=new Point(Window.WIDTH, Window.HEIGHT);
		Point farthest=corner1;
		if (farthest.distance(target.getPosition())<corner2.distance(target.getPosition())) {
			farthest=corner2;
		}
		if (farthest.distance(target.getPosition())<corner3.distance(target.getPosition())) {
			farthest=corner3;
		}
		if (farthest.distance(target.getPosition())<corner4.distance(target.getPosition())) {
			farthest=corner4;
		}
		
		double finalAngle=(farthest.directionTo(target.getPosition())%(Math.PI*2)+Math.PI*2)%(Math.PI*2);
		double currentAngle=(angle%(Math.PI*2)+Math.PI*2)%(Math.PI*2);
		if (currentAngle>finalAngle) {
			if (currentAngle-finalAngle>Math.PI) {
				currentAngle+=Math.PI/100;
			}
			else {
				currentAngle-=Math.PI/100;
			}
		}
		else {
			if (finalAngle-currentAngle>Math.PI) {
				currentAngle-=Math.PI/100;
			}
			else {
				currentAngle+=Math.PI/100;
			}
		}
		angle=currentAngle;
	}
	
}
