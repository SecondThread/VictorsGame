package engine.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.ai.PlayerAI;
import engine.background.Background;
import engine.background.FancyBackground;
import engine.background.TwoPlayerBackground;
import engine.entities.Bullet;
import engine.entities.Soldier;
import engine.networking.BigKahunaGame;
import engine.networking.entities.BigKahunaChangable;
import samurAI.SamurAI;
import samurAI.SamurAISniper;

public class Game {
	private Background background;
	private Color color1=new Color(220, 53, 34), color2=new Color(217, 203, 158);
	protected ArrayList<Soldier> soldiersAlive=new ArrayList<Soldier>();
	protected ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	
	PlayerAI left=new SamurAI();
	PlayerAI right=new SamurAISniper();
	
	public static Game getTwoPlayerGame() {
		Background background=new TwoPlayerBackground();
		if (Window.fancyMode) {
			background=new FancyBackground();
		}
		return new Game(background);
	}
	
	protected Game(Background background) {
		this.background=background;
		Soldier[] leftSoldiers=left.getStartFormation(true, 0);
		Soldier[] rightSoldiers=right.getStartFormation(false, 1);
		for (Soldier s:leftSoldiers)
			soldiersAlive.add(s);
		for (Soldier s:rightSoldiers)
			soldiersAlive.add(s);
	}
	
	public void update() {
		
		if (playersAreStillConnecting()) {
			updateConnectingSoldiers();
		}
		else {
			left.update(soldiersAlive, bullets);
			right.update(soldiersAlive, bullets);
			updateSoldiersAndBullets();
		}
		
		soldiersAlive.removeAll(BigKahunaGame.soldiersToRemove);
		soldiersAlive.addAll(BigKahunaGame.soldiersToAdd);
		BigKahunaGame.soldiersToAdd.clear();
		BigKahunaGame.soldiersToRemove.clear();
	}
	
	private boolean playersAreStillConnecting() {
		for (Soldier s:soldiersAlive) {
			if (s.getAI() instanceof BigKahunaChangable) {
				return true;
			}
		}
		return false;
	}
	
	private void updateSoldiersAndBullets() {
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
	
	private void updateConnectingSoldiers() {
		for (Soldier s:soldiersAlive) {
			if (s.getAI() instanceof BigKahunaChangable) {
				s.update(soldiersAlive, bullets);
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
		g.dispose();
	}

}
