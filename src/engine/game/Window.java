package engine.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	private static JFrame frame;
	private static JPanel outerPanel;
	public static final int WIDTH=800, HEIGHT=450;
	
	private static int scaleFactor=1;
	
	public static void init() {
		outerPanel=new JPanel();
		outerPanel.setPreferredSize(new Dimension(WIDTH/scaleFactor, HEIGHT/scaleFactor));
		
		frame=new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(outerPanel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void setTitle(String title) {
		frame.setTitle(title);
	}
	
	public static void paint(BufferedImage image) {
		Graphics g=outerPanel.getGraphics();
		
		g.drawImage(image, 0, HEIGHT, WIDTH/scaleFactor, -HEIGHT/scaleFactor, null);
		g.dispose();
	}
	
	public static BufferedImage getBlackImage() {
		BufferedImage toReturn=new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		for (int x=0; x<toReturn.getWidth(); x++) {
			for (int y=0; y<toReturn.getHeight(); y++) {
				toReturn.setRGB(x, y, Color.black.getRGB());
			}
		}
		return toReturn;
	}

	public static void addKeyListener(KeyListener listener) {
		frame.addKeyListener(listener);
	}
	
	public static void addMouseListener(MouseListener listener) {
		frame.addMouseListener(listener);
	}
	
	public static void addMouseMotionListener(MouseMotionListener listener) {
		frame.addMouseMotionListener(listener);
	}
	
}
