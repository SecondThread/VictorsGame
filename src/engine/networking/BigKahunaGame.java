package engine.networking;

import java.util.ArrayList;

import engine.background.Background;
import engine.background.TwoPlayerBackground;
import engine.entities.Soldier;
import engine.game.Game;

public class BigKahunaGame extends Game {
	public static ArrayList<Soldier> soldiersToAdd=new ArrayList<Soldier>();
	public static ArrayList<Soldier> soldiersToRemove=new ArrayList<Soldier>();
	
	public static BigKahunaGame getTwoPlayerGame() {
		return new BigKahunaGame(new TwoPlayerBackground());
	}
	
	protected BigKahunaGame(Background background) {
		super(background);
		NetworkManager.initAsClient();
		NetworkManager.resetPlayers();
	}
	
	public void networkUpdate() {
		NetworkManager.sendSoldierPositions(soldiersAlive, bullets);
		//System.out.println(NetworkManager.getKeyboardInputs(0));
	}

	public static void addPlayer(Soldier toAdd, Soldier toRemove) {
		soldiersToAdd.add(toAdd);
		soldiersToRemove.add(toRemove);
	}
	
}
