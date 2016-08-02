package engine.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage image=null;
	public static final Sprite runner=new Sprite("/runner.png");
	public static final Sprite tank=new Sprite("/tank.png");
	public static final Sprite sniper=new Sprite("/sniper.png");
	
	public static final Sprite runnerBlue=new Sprite("/runnerBlue.png");
	public static final Sprite tankBlue=new Sprite("/tankBlue.png");
	public static final Sprite sniperBlue=new Sprite("/sniperBlue.png");
	
	public static final Sprite fancyBackground=new Sprite("/spaceBackground.png");
	public static final Sprite logo=new Sprite("/logo.png");
	
	public BufferedImage getBufferedImage() {
		return image;
	}
	
	public Sprite(String location) {
		System.out.println("Loading from: "+location+"...");
		try { 
			image=ImageIO.read(Sprite.class.getResourceAsStream(location));
			//image=ImageIO.read(new File(location));	
			System.out.println("\t isNull: "+(image==null));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
