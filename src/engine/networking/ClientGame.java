package engine.networking;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.background.Background;
import engine.background.TwoPlayerBackground;

public class ClientGame {
	private Background background;
	ArrayList<ClientSoldier> soldiersAlive=new ArrayList<ClientSoldier>();
	ArrayList<ClientBullet> bullets=new ArrayList<ClientBullet>();
	
	public static ClientGame getTwoPlayerGame() {
		return new ClientGame(new TwoPlayerBackground());
	}
	
	private ClientGame(Background background) {
		this.background=background;
		NetworkManager.initAsClient();
		soldiersAlive=NetworkManager.getSoldiers(NetworkManager.getData());
	}
	
	public void update() {
		String data=NetworkManager.getData();
		soldiersAlive=NetworkManager.getSoldiers(data);
		bullets=NetworkManager.getBullets(data);
		//System.out.println(soldiersAlive.size()+"   "+data);
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
