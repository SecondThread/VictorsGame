package engine.networking.input;

import java.nio.channels.NetworkChannel;

import engine.networking.NetworkManager;
import samurAI.input.Keyboard;

public class InputController {
	private Keyboard keyboard;
	
	public InputController() {
		keyboard=new Keyboard();
	}
	
	public void update() {
		keyboard.update();
		String pressedValues="";
		if (keyboard.getKeyDown(68)) {//left
			pressedValues+="1";
		}
		else {
			pressedValues+="0";
		}
		if (keyboard.getKeyDown(65)) {//right
			pressedValues+="1";
		}
		else {
			pressedValues+="0";
		}
		if (keyboard.getKeyDown(87)) {//up
			pressedValues+="1";
		}
		else {
			pressedValues+="0";
		}
		if (keyboard.getKeyDown(83)) {//down
			pressedValues+="1";
		}
		else {
			pressedValues+="0";
		}
	
		NetworkManager.sendKeyboardInputs(pressedValues);
	}
	
}
