package engine.entities;

public class Point {
	public double x, y;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(double x, double y) {
		this.x=x; this.y=y;
	}
	
	public double distance(Point other) {
		return Math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y));
	}
	
	public Point midpoint(Point other) {
		return new Point((x+other.x)/2, (y+other.y)/2);
	}
}
