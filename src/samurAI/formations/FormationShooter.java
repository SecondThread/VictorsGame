package samurAI.formations;

import java.util.ArrayList;

import engine.ai.SniperAI;
import engine.entities.Bullet;
import engine.entities.Soldier;

public class FormationShooter extends SniperAI {
	private Formation formation;
	private int formationIndex;
	private boolean formationBroken=false;
	private SniperAI backup;
	
	public FormationShooter(int teamID, Formation formation, int formationIndex, SniperAI backup) {
		super(teamID);
		this.formation=formation;
		this.formationIndex=formationIndex;
		this.backup=backup;
	}
	
	public void update(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		backup.update(soldiers, bullets);
	}
	
	public double getGunAngle() {
		return backup.getGunAngle();
	}
	
	public double getMoveSpeed() {
		return backup.getMoveSpeed();
	}
	
	public boolean fireIfPossible() {
		return backup.fireIfPossible();
	}
	
	public void breakFormation() {
		formationBroken=true;
	}
	
	public void establishFormation() {
		formationBroken=false;
	}
	
}
