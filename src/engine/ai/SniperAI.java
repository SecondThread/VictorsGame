package engine.ai;

public class SniperAI extends SoldierAI {
	
	public double getGunAngle() {
		return System.currentTimeMillis()/1000.0;
	}
	
	public boolean fireIfPossible() {
		return true;
	}
}
