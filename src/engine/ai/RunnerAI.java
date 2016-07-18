package engine.ai;

public class RunnerAI extends SoldierAI {

	public RunnerAI(int teamID) {
		super(teamID);
	}
	
	public boolean blinkIfPossible() {
		return false;
	}

}
