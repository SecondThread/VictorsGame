package engine.networking.input;

import engine.networking.NetworkManager;

public class InputController {
	private Keyboard keyboard;
	private MouseController mouse;
	
	public InputController() {
		keyboard=new Keyboard();
		mouse=new MouseController();
		//keyboard.setDisplayKeycodeMessages(true);
	}
	
	public void update() {
		keyboard.update();
		String pressedValues="";
		pressedValues+=keyboard.getKeyDown(68)?"1":"0";
		pressedValues+=keyboard.getKeyDown(65)?"1":"0";
		pressedValues+=keyboard.getKeyDown(87)?"1":"0";
		pressedValues+=keyboard.getKeyDown(83)?"1":"0";
		pressedValues+=","+mouse.getX()+","+mouse.getY()+",";
		pressedValues+=keyboard.getKeyDown(32)?"1":"0";
		
		NetworkManager.sendKeyboardInputs(pressedValues);
	}
	
}
