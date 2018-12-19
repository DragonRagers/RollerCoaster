package roller.coaster;

public class Point {
	//Used for position
	public double x, y, z;
	
	public Point(double a, double b, double c) {
		x = a;
		y = b;
		z = c;
	}
	
	public static Point add(Point a, Point b) {
		return new Point(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public String toString() {
		return String.format("%.1f, %.1f, %.1f", x, y, z);
	}
}
