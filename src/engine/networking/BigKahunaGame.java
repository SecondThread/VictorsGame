package engine.networking;

import engine.background.Background;
import engine.background.TwoPlayerBackground;
import engine.game.Game;

public class BigKahunaGame extends Game {
	
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
		System.out.println(NetworkManager.getKeyboardInputs(0));
	}

}
