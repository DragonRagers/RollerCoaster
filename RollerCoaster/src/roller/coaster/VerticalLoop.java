package roller.coaster;

public class VerticalLoop extends TrackPiece {
	public float radius;
	
	public VerticalLoop(Point i, float r, int dir) { //dir is direction, 0 - x, 1 - y (unimplemented assuming y)
		init = i;
		radius = r;
		end = Point.add(init, new Point(0,2*radius,0));
	}
	
	public void drawTrack() {
		
	}
	
	public double arcLength() {
		return 2 * radius + 2 * Math.PI * radius;
	}
	
	public Point calcPosition(double arcLength) {
		if (arcLength < radius) {
			return Point.add(init, new Point(0,arcLength,0));
		} else if (arcLength < radius + radius * 2 * Math.PI) {
			return Point.add(init, new Point((arcLength - radius)/(2*radius),radius + radius * Math.cos(3*Math.PI/2 + reParam(arcLength)),radius + radius * Math.sin(3*Math.PI/2 + reParam(arcLength))));
		}
		return Point.add(init, new Point(Math.PI,5 + arcLength - (radius + radius * 2 * Math.PI), 0));
	}

	private double reParam(double arclength) { 
		return (arclength-radius)/radius; 
	}
	

	public static void main(String args[]) { //testing shit
		VerticalLoop loop = new VerticalLoop(new Point(0,0,0), 5, 0);
		for (double i = 0; i < 90; i+=.2) {
			System.out.println(loop.calcPosition(i));
		}
	}
	
}
