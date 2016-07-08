package engine.ai;

public class TankAI extends SoldierAI {
	/**
	 * gets 1, 0, or -1 for turn the shield counterclockwise(1), clockwise(-1), or not at all(0)
	 * @return
	 * 1, 0, or -1
	 */
	public int getShieldTurnDirection() {
		return 1;
	}
}
