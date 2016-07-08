package engine.background;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.game.Window;

public class TwoPlayerBackground implements Background {
	
	private int lineWidth=2;
	private int numberOfVerticalLines=16;
	private int numberOfHorizontalLines=9;
	
	private Color color1=new Color(30, 30, 32), color2=new Color(42, 44, 43);
	private Color color3=new Color(55, 65, 64), color4=new Color(217, 203, 158);
	private Color color5=new Color(220, 53, 34);
	
	private boolean[][] colorGrid={
			{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, }, 
			{false, false, true, true, true, true, true, true, true, true, true, true, true, true, false, false, },
			{false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, },
			{false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, },
			{true, true, false, true, true, true, true, true, true, true, true, true, true, false, true, true, },
			{false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, },
			{false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, },
			{false, false, true, true, true, true, true, true, true, true, true, true, true, true, false, false, },
			{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, },};
	
	public TwoPlayerBackground() {
		
	}
	
	public void render(Graphics2D g) {
		for (int xColorGrid=0; xColorGrid<colorGrid[0].length; xColorGrid++) {
			for (int yColorGrid=0; yColorGrid<colorGrid.length; yColorGrid++) {
				Color toDraw;
				if (colorGrid[yColorGrid][xColorGrid]) {
					toDraw=color3;
				}
				else {
					toDraw=color1;
				}
				g.setColor(toDraw);
				g.fillRect(xColorGrid*(Window.WIDTH/numberOfVerticalLines), yColorGrid*(Window.HEIGHT/numberOfHorizontalLines), Window.WIDTH/numberOfVerticalLines, Window.HEIGHT/numberOfHorizontalLines);
			}
		}
		g.setColor(color3);
		for (int i=1; i<numberOfVerticalLines; i++) {
			int startLocation=i*Window.WIDTH/numberOfVerticalLines-lineWidth/2;
			g.fillRect(startLocation, 0, lineWidth, Window.HEIGHT);
		}
		for (int i=1; i<numberOfHorizontalLines; i++) {
			int startLocation=i*Window.HEIGHT/numberOfHorizontalLines-lineWidth/2;
			g.fillRect(0, startLocation, Window.WIDTH, lineWidth);
		}
	}
	

}
