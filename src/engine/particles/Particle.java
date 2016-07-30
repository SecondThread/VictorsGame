package engine.particles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Particle {

	private double x, y, xVelocity, yVelocity;
	private double radius;
	private Color inside, outside;
	private double maxAlpha=0.5;
	private double fadePower=2, lifetimeFadePower=0.5;
	private double friction=0.995;
	private int lifetimeCounter=0, maxLifetimeCounter=50;
	private boolean fadeIn, fadeOut;
	
	public Particle(double radius, double x, double y, Color color) {
		this(radius, x, y, 0, 0, color, color, true, true, .5);
	}
	
	public Particle(double radius, double x, double y, Color inside, Color outside) {
		this(radius, x, y, 0, 0, inside, outside, true, true, .5);
	}
	
	public Particle(double radius, double x, double y, Color color, boolean fadeIn, boolean fadeOut) {
		this(radius, x, y, 0, 0, color, color, fadeIn, fadeOut, .5);
	}
	
	public Particle(double radius, double x, double y, double xVelocity, double yVelocity, Color inside, Color outside, boolean fadeIn, boolean fadeOut, double maxAlpha) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.xVelocity=xVelocity;
		this.yVelocity=yVelocity;
		this.inside=inside;
		this.outside=outside;
		this.fadeIn=fadeIn;
		this.fadeOut=fadeOut;
		this.maxAlpha=maxAlpha;
	}
	
	public void update() {
		x+=xVelocity;
		y+=yVelocity;
		xVelocity*=friction;
		yVelocity*=friction;
		lifetimeCounter++;
	}
	
	public boolean dead() {
		return lifetimeCounter>=maxLifetimeCounter;
	}
	
	public void render(Graphics2D g) {
		double lifetimeFadeInScalar=fadeIn?Math.pow(lifetimeCounter/(double)maxLifetimeCounter, lifetimeFadePower):1;
		double lifetimeFadeOutScalar=fadeOut?Math.pow(1-lifetimeCounter/(double)maxLifetimeCounter, lifetimeFadePower):1;
		double lifetimeScalar=Math.min(lifetimeFadeInScalar, lifetimeFadeOutScalar);
		double clarity=6;
		for (int i=1; i<clarity; i++) {
			Color color=colorLerp(outside, inside, i/clarity);
			Color toUse=new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(lifetimeScalar*255*maxAlpha/Math.pow(i+1, fadePower)));
			g.setColor(toUse);
			Ellipse2D circle=new Ellipse2D.Double(x-radius*i/clarity, y-radius*i/clarity, 2*radius*i/clarity, 2*radius*i/clarity);
			g.fill(circle);
		}
	}
	
	public Color colorLerp(Color first, Color second, double amount) {
		int r=(int)(first.getRed()*(1-amount)+second.getRed()*amount);
		int g=(int)(first.getGreen()*(1-amount)+second.getGreen()*amount);
		int b=(int)(first.getBlue()*(1-amount)+second.getBlue()*amount);
		int a=(int)(first.getAlpha()*(1-amount)+second.getAlpha()*amount);
		return new Color(r, g, b, a);
	}
}
