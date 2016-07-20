package engine.background;

import java.awt.Graphics2D;

import engine.game.Sprite;
import engine.game.Window;

public class FancyBackground implements Background{

	private Sprite spaceBackground;
	
	public FancyBackground() {
		spaceBackground=new Sprite("spaceBackground.jpg");
	}
	
	public void render(Graphics2D g) {
		g.drawImage(spaceBackground.getBufferedImage(), 0, 0, Window.WIDTH, Window.HEIGHT, null);
	}

}
