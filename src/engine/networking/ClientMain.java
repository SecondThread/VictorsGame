package engine.networking;

import java.awt.image.BufferedImage;

import engine.game.Main;
import engine.game.Window;
import engine.networking.input.SoldierChooser;

public class ClientMain {
	private static ClientGame game;
	private static int updatesPerSecond=15;
	private static int particleUpdatesPerSecond=50;
	private static int soldierType;
	
	public static void main(String[] args) {
		soldierType=SoldierChooser.chooseType();
		Window.init();
		Main.wait(.1);
		game=ClientGame.getTwoPlayerGame();
		Window.paint(Window.getBlackImage());
		runGameLoop();
	}

	private static void runGameLoop() {
		long startTime=System.currentTimeMillis();
		long startParticleTime=System.currentTimeMillis();
		final long timeBetweenUpdates=1000/updatesPerSecond;
		final long timeBetweenParticleUpdates=1000/particleUpdatesPerSecond;
		int updates=0, frames=0;
		long lastUpdateFrames=System.currentTimeMillis();
		
		while (true) {
			if (startTime+timeBetweenUpdates<System.currentTimeMillis()) {
				update();
				updates++;
				startTime+=timeBetweenUpdates;
			}
			if (startParticleTime+timeBetweenParticleUpdates<System.currentTimeMillis()) {
				particleUpdate();
				startParticleTime+=timeBetweenParticleUpdates;
			}
			render();
			frames++;
			if (lastUpdateFrames+1000<System.currentTimeMillis()) {
				lastUpdateFrames+=1000;
				Window.setTitle("Match      |      Updates: "+updates+"    FPS: "+frames);
				updates=0;
				frames=0;
			}
		}
	}
	
	private static void particleUpdate() {
		game.particleUpdate();
	}
	
	private static void update() {
		game.update();
	}
	
	private static void render() {
		BufferedImage toDrawOn=new BufferedImage(Window.WIDTH, Window.HEIGHT, BufferedImage.TYPE_INT_RGB);
		game.renderBackground(toDrawOn);
		game.particleRender(toDrawOn);
		game.render(toDrawOn);
		Window.paint(toDrawOn);
	}
	
	public static int getSoldierType() {
		return soldierType;
	}
	
}
