package samurAI.formations;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Soldier;
import samurAI.AAASniper;

public class FormationShooter extends SniperAI {
	private SniperDuoFormation formation;
	private int formationIndex;
	private boolean formationBroken=false;
	private AAASniper backup;
	private FormationMovement movement;
	private Soldier mySoldier=null;
	
	public FormationShooter(int teamID, SniperDuoFormation formation, int formationIndex, AAASniper backup) {
		super(teamID);
		this.formation=formation;
		this.formationIndex=formationIndex;
		this.backup=backup;
		if (formationIndex==0) {			
			backup.setTargetOffsetRadiusMultiplier(1.2);
		}
		else {
			backup.setTargetOffsetRadiusMultiplier(-1.2);
		}
		movement=new FormationMovement(formation, formationIndex);
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		formationBroken=formation.getFormationBroken();
		updateMySoldier(soldiers);
		movement.update(mySoldier);
		backup.setMySoldier(mySoldier);
		backup.update(soldiers, bullets);
		updateDirection();
		formation.setDirectionSuggustion(backup.direction, formationIndex, mySoldier.getPosition());
		if (formationIndex==1) {
			formation.update(soldiers, bullets);
		}
	}
	
	private void updateDirection() {
		if (formationBroken) {
			direction=backup.direction;
		}
		else {
			direction=movement.getDirectionToMove();
		}
	}
	
	public double getGunAngle() {
		return backup.getGunAngle();
	}
	
	public double getMoveSpeed() {
		if (formationBroken) {
			System.out.println("broken");
			return 1;//return backup.getMoveSpeed();
		}
		return movement.getMoveSpeed();
	}
	
	public boolean fireIfPossible() {
		if (formationBroken) {
			return backup.fireIfPossible();			
		}
		return backup.fireIfPossible()&&formation.isTimeToShoot(formationIndex);
	}
	
	public void breakFormation() {
		formationBroken=true;
	}
	
	public void establishFormation() {
		formationBroken=false;
	}
	
	private void updateMySoldier(ArrayList<Soldier> soldiers) {
		for (Soldier s:soldiers) {
			if (s.getAI()==this) {
				mySoldier=s;
			}
		}
	}
	
}
