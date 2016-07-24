package engine.networking;

import java.awt.image.BufferedImage;

import engine.game.Main;
import engine.game.Window;
import engine.networking.input.SoldierChooser;

public class ClientMain {
	private static ClientGame game;
	private static int updatesPerSecond=15;
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
		final long timeBetweenUpdates=1000/updatesPerSecond;
		int updates=0, frames=0;
		long lastUpdateFrames=System.currentTimeMillis();
		
		while (true) {
			while (startTime+timeBetweenUpdates<System.currentTimeMillis()) {
				update();
				updates++;
				startTime+=timeBetweenUpdates;
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
	
	public static int getSoldierType() {
		return soldierType;
	}
	
}
