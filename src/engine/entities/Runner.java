package engine.entities;

import java.awt.Color;

import engine.ai.SoldierAI;

public class Runner extends Soldier {

	public Runner(double x, double y, Color color, SoldierAI ai) {
		super(x, y, color, ai);
		friction=.8;
		acceleration=0.19;
		isRunner=true;
	}
}
