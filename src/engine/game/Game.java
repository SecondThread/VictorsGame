package engine.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.ai.PlayerAI;
import engine.background.Background;
import engine.background.TwoPlayerBackground;
import engine.entities.Bullet;
import engine.entities.Soldier;

public class Game {
	private Background background;
	private Color color1=new Color(220, 53, 34), color2=new Color(217, 203, 158);
	ArrayList<Soldier> soldiersAlive=new ArrayList<Soldier>();
	ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	
	PlayerAI left=new PlayerAI();
	PlayerAI right=new PlayerAI();
	
	public static Game getTwoPlayerGame() {
		return new Game(new TwoPlayerBackground());
	}
	
	private Game(Background background) {
		this.background=background;
		Soldier[] leftSoldiers=left.getStartFormation(true, color1);
		Soldier[] rightSoldiers=right.getStartFormation(false, color2);
		for (Soldier s:leftSoldiers)
			soldiersAlive.add(s);
		for (Soldier s:rightSoldiers)
			soldiersAlive.add(s);
	}
	
	public void update() {
		for (Soldier s:soldiersAlive) {
			s.update(soldiersAlive, bullets);
		}
		
		for (Bullet b:bullets) {
			b.update(soldiersAlive);
		}
		
		for (int i=0; i<soldiersAlive.size(); i++) {
			if (soldiersAlive.get(i).isDead()) {
				soldiersAlive.remove(i);
				i--;
			}
		}
		
		for (int i=0; i<bullets.size(); i++) {
			if (bullets.get(i).isDead()) {
				bullets.remove(i);
				i--;
			}
		}
	}
	
	public void render(BufferedImage toDrawOn) {
		Graphics2D g=(Graphics2D)(toDrawOn.getGraphics());
		background.render(g);
		for (Soldier s:soldiersAlive) {
			s.render(g);
		}
		for (Bullet b:bullets) {
			b.render(g);
		}
	}

}
