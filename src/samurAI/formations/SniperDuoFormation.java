package samurAI.formations;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.game.Window;
import samurAI.AAASniper;
import samurAI.movement.BulletDodger;

public class SniperDuoFormation extends Formation {

	private int team;
	private double angleOffset=0;
	private double[] directionSuggustion=new double[2];
	private Point[] positions=new Point[2];
	private BulletDodger dodger;
	
	private double xVelocity=0, yVelocity=0;
	private double friction=0.99;

	public SniperDuoFormation(int x, int y, int team) {
		super(x, y, 2);
		super.setRadius(50);
		this.team=team;
		dodger=new BulletDodger();
	}

	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		if (needToBreakFormation(bullets)||positions[0]==null||positions[1]==null) {
			setFormationBroken(true);
		}
		else {
			setFormationBroken(false);
			angleOffset+=Math.PI*2/700;
			setAngleOffset(angleOffset);
			updatePhysics();
		}
	}
	
	private void updatePhysics() {
		x=(positions[0].x+positions[1].x)/2;
		y=(positions[0].y+positions[1].y)/2;
		positions[0]=positions[1]=null;
		double x1=Math.cos(directionSuggustion[0]);
		double x2=Math.cos(directionSuggustion[1]);
		double y1=Math.sin(directionSuggustion[0]);
		double y2=Math.sin(directionSuggustion[1]);
		double finalDirection=Math.atan2((y1+y2)/2, (x1+x2)/2);
		double speed=.93;
		
		xVelocity+=speed*Math.cos(finalDirection);
		yVelocity+=speed*Math.sin(finalDirection);
		
		xVelocity*=friction;
		yVelocity*=friction;
		
		x+=xVelocity;
		y+=yVelocity;
		x=Math.min(Window.WIDTH-getRadius()*2, Math.max(x, getRadius()*2));
		y=Math.min(Window.HEIGHT-getRadius()*2, Math.max(y, getRadius()*2));
	}
	
	public void setDirectionSuggustion(double direction, int formationIndex, Point position) {
		directionSuggustion[formationIndex]=direction;
		positions[formationIndex]=position;
	}

	public ArrayList<Soldier> spawnSoldiers() {
		ArrayList<Soldier> toReturn=new ArrayList<Soldier>();
		toReturn.add(new Sniper(getIdealPosition(0).x, getIdealPosition(0).y, team, new FormationShooter(team, this, 0, new AAASniper(team))));
		toReturn.add(new Sniper(getIdealPosition(1).x, getIdealPosition(1).y, team, new FormationShooter(team, this, 1, new AAASniper(team))));
		return toReturn;
	}

	private boolean needToBreakFormation(ArrayList<Bullet> bullets) {
		return needToDodgeBullet(bullets);
	}

	private boolean needToDodgeBullet(ArrayList<Bullet> bullets) {
		for (Bullet bill:bullets) {
			if (dodger.needToDodge(getPosition(), bill, getRadius(), 2)) {
				return true;
			}
		}
		return false;
	}

}
