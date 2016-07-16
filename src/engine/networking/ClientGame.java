package engine.networking;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.background.Background;
import engine.background.TwoPlayerBackground;
import engine.networking.input.InputController;
import engine.networking.input.MouseController;

public class ClientGame {
	private Background background;
	private ArrayList<ClientSoldier> soldiersAlive=new ArrayList<ClientSoldier>();
	private ArrayList<ClientBullet> bullets=new ArrayList<ClientBullet>();
	private InputController inputController;
	
	public static ClientGame getTwoPlayerGame() {
		return new ClientGame(new TwoPlayerBackground());
	}
	
	private ClientGame(Background background) {
		this.background=background;
		NetworkManager.initAsClient();
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
