package engine.networking;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.background.Background;
import engine.background.FancyBackground;
import engine.background.TwoPlayerBackground;
import engine.game.Window;
import engine.networking.input.InputController;

public class ClientGame {
	private Background background;
	private ArrayList<ClientSoldier> soldiersAlive=new ArrayList<ClientSoldier>();
	private ArrayList<ClientBullet> bullets=new ArrayList<ClientBullet>();
	private InputController inputController;
	
	public static ClientGame getTwoPlayerGame() {
		Background background=new TwoPlayerBackground();
		if (Window.fancyMode) {
			background=new FancyBackground();
		}
		return new ClientGame(background);
	}
	
	private ClientGame(Background background) {
		this.background=background;
		NetworkManager.initAsClient();
		NetworkManager.sendSoldierType(Client.getPlayerID(), ClientMain.getSoldierType());
		soldiersAlive=NetworkManager.getSoldiers(NetworkManager.getData());
		inputController=new InputController();
	}
	
	public void update() {
		inputController.update();
		String data=NetworkManager.getData();
		soldiersAlive=NetworkManager.getSoldiers(data);
		bullets=NetworkManager.getBullets(data);
	}
	
	public void render(BufferedImage toDrawOn) {
		Graphics2D g=(Graphics2D)(toDrawOn.getGraphics());
		background.render(g);
		for (ClientSoldier s:soldiersAlive) {
			s.render(g);
		}
		for (ClientBullet b:bullets) {
			b.render(g);
		}
	}
}
