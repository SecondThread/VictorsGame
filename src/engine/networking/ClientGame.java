package engine.networking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import engine.background.Background;
import engine.background.FancyBackground;
import engine.background.TwoPlayerBackground;
import engine.game.Window;
import engine.networking.input.InputController;
import engine.particles.Particle;

public class ClientGame {
	private Background background;
	private ArrayList<ClientSoldier> soldiersAlive=new ArrayList<ClientSoldier>();
	private ArrayList<ClientBullet> bullets=new ArrayList<ClientBullet>();
	private InputController inputController;

	private ArrayList<Particle> particles=new ArrayList<Particle>();
	private int particleCreationCounter=0;

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

	public void particleUpdate() {
		Random r=new Random();
		particles.add(new Particle(10, r.nextInt(Window.WIDTH), r.nextInt(Window.HEIGHT), 0, 0, Color.lightGray, Color.white, true, true, .5));
		double velocityMagnitude=2;
		for (ClientBullet b:bullets) {
			for (int i=0; i<2; i++) {
				int radius=r.nextInt(50);
				double angle=r.nextDouble()*Math.PI*2;
				int x=(int) (b.getPosition().x+Math.cos(angle)*radius);
				int y=(int) (b.getPosition().y+Math.sin(angle)*radius);
				double xVelocity=r.nextDouble()*velocityMagnitude-velocityMagnitude/2, yVelocity=r.nextDouble()*velocityMagnitude-velocityMagnitude/2;
				particles.add(new Particle(7, x, y, xVelocity, yVelocity, Color.green, Color.green, false, true, 1));
			}
		}
		if (++particleCreationCounter>2) {
			particleCreationCounter=0;
			for (ClientSoldier s:soldiersAlive) {
				int radius=r.nextInt(20);
				double angle=r.nextDouble()*Math.PI*2;
				int x=(int) (s.getPosition().x+Math.cos(angle)*radius);
				int y=(int) (s.getPosition().y+Math.sin(angle)*radius);
				velocityMagnitude=r.nextDouble()*2;
				particles.add(new Particle(17, x, y, Math.cos(angle)*velocityMagnitude, Math.sin(angle)*velocityMagnitude, s.getTeam()==0 ? Color.red : Color.blue, Color.white, false, true, .8));
			}
		}

		for (Particle p:particles) {
			p.update();
		}
		for (int i=particles.size()-1; i>=0; i--) {
			if (particles.get(i).dead()) {
				particles.remove(i);
			}
		}
	}

	public void update() {
		inputController.update();
		String data=NetworkManager.getData();
		soldiersAlive=NetworkManager.getSoldiers(data);
		bullets=NetworkManager.getBullets(data);
	}

	public void render(BufferedImage toDrawOn) {
		Graphics2D g=(Graphics2D) (toDrawOn.getGraphics());
		for (ClientSoldier s:soldiersAlive) {
			s.render(g);
		}
		for (ClientBullet b:bullets) {
			b.render(g);
		}
		g.dispose();
	}

	public void renderBackground(BufferedImage toDrawOn) {
		Graphics2D g=(Graphics2D) (toDrawOn.getGraphics());
		background.render(g);
		g.dispose();
	}

	public void particleRender(BufferedImage toDrawOn) {
		Graphics2D g=(Graphics2D) (toDrawOn.getGraphics());
		for (Particle p:particles) {
			p.render(g);
		}
		g.dispose();
	}
}
