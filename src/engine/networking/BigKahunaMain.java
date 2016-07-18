package engine.networking;

import java.awt.image.BufferedImage;

import engine.game.Window;

public class BigKahunaMain {

	private static BigKahunaGame game;
	private static int updatesPerSecond=60;

	public static void main(String[] args) {
		Window.init();
		wait(.1);
		game=BigKahunaGame.getTwoPlayerGame();
		Window.paint(Window.getBlackImage());
		runGameLoop();
	}

	public static void wait(double seconds) {
		try {
			Thread.sleep((long) (seconds*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void runGameLoop() {
		long startTime=System.currentTimeMillis();
		long startNetworkTime=System.currentTimeMillis();
		final long timeBetweenUpdates=1000/updatesPerSecond;
		final long timeBetweenNetworkUpdates=1000/updatesPerSecond;
		int updates=0, frames=0;
		long lastUpdateFrames=System.currentTimeMillis();

		while (true) {

			while (startTime+timeBetweenUpdates<System.currentTimeMillis()) {
				update();
				updates++;
				startTime+=timeBetweenUpdates;
			}
			
			while (startNetworkTime+timeBetweenNetworkUpdates<System.currentTimeMillis()) {
				networkUpdate();
				startNetworkTime+=timeBetweenNetworkUpdates;
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

	private static void update() {
		game.update();
	}

	private static void render() {
		BufferedImage toDrawOn=new BufferedImage(Window.WIDTH, Window.HEIGHT, BufferedImage.TYPE_INT_RGB);
		game.render(toDrawOn);
		Window.paint(toDrawOn);
	}
	
	private static void networkUpdate() {
		game.networkUpdate();
	}
}
