package engine.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage image=null;
	public static final Sprite runner=new Sprite("runner.png");
	public static final Sprite tank=new Sprite("tank.png");
	public static final Sprite sniper=new Sprite("sniper.png");
	
	public static final Sprite runnerBlue=new Sprite("runnerBlue.png");
	public static final Sprite tankBlue=new Sprite("tankBlue.png");
	public static final Sprite sniperBlue=new Sprite("sniperBlue.png");
	
	public BufferedImage getBufferedImage() {
		return image;
	}
	
	public Sprite(String location) {
		try {
			image=ImageIO.read(new File(location));	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
