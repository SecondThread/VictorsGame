package samurAI.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.game.Window;

public class Keyboard implements KeyListener{

	private boolean[] downLastUpdate=new boolean[1000];
	private boolean[] downThisUpdate=new boolean[1000];
	private boolean[] live=new boolean[1000];
	
	private boolean displayKeyPresses=false;
	
	public Keyboard() {
		Window.addKeyListener(this);
		for (int i=0; i<live.length; i++) {
			downLastUpdate[i]=downThisUpdate[i]=live[i]=false;
		}
	}
	
	public void update() {
		for (int i=0; i<downLastUpdate.length; i++) {
			downLastUpdate[i]=downThisUpdate[i];
			downThisUpdate[i]=live[i];
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (displayKeyPresses) {
			System.out.println(e.getKeyCode());
		}
		live[e.getKeyCode()]=true;
	}

	public void keyReleased(KeyEvent e) {
		live[e.getKeyCode()]=false;
	}

	public void keyTyped(KeyEvent e) {
	}
	
	public boolean getKeyPressed(int keycode) {
		return downThisUpdate[keycode]&&!downLastUpdate[keycode];
	}
	
	public boolean getKeyReleased(int keycode) {
		return !downThisUpdate[keycode]&&downLastUpdate[keycode];
	}
	
	public boolean getKeyDown(int keycode) {
		return downThisUpdate[keycode];
	}
	
	/**
	 * Use this when finding the keycode of a certain key
	 * @param displayKeysPressed
	 */
	public void setDisplayKeycodeMessages(boolean displayKeysPressed) {
		displayKeyPresses=displayKeysPressed;
	}

}
