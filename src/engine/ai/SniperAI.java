package engine.ai;

public class SniperAI extends SoldierAI {
	
	public double getGunAngle() {
		return 1;
	}
	
	public boolean fireIfPossible() {
		return false;
	}
}
