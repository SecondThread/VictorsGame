package engine.networking.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import engine.game.Window;

public class MouseController implements MouseMotionListener{

	private volatile int x=0, y=0;
	
	public MouseController() {
		Window.addMouseMotionListener(this);
	}
	
	public void mouseDragged(MouseEvent e) {
		x=e.getX()*Window.scaleFactor;
		y=Window.HEIGHT-e.getY()*Window.scaleFactor;
	}

	public void mouseMoved(MouseEvent e) {
		x=e.getX()*Window.scaleFactor;
		y=Window.HEIGHT-e.getY()*Window.scaleFactor;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
