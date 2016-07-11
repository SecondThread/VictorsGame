package dan.ai;

import engine.ai.TankAI;

public class TankDI extends TankAI {
	private int shieldDir = 0;
	
	public int getShieldTurnDirection() {
		return shieldDir;
	}
	//counterclockwise(1), clockwise(-1), or not at all(0)
	public void setShieldTurnDirection(int xDir){
		shieldDir = xDir;
	}
}
