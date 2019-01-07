package roller.coaster;

public class Straight extends TrackPiece{
	public double length;
	public double theta;

	public Straight(Point i, double l, double m) {
		init = i;
		end = Point.add(init, new Point(0, l * Math.cos(theta), l * Math.sin(theta)));
		length = l;
		theta = Math.atan(m);
	}
	
	public void drawTrack() {
		// TODO Auto-generated method stub
		
	}

	public double arcLength() {
		return length;
	}

	@Override
	public Point calcPosition(double arcLength) {
		return Point.add(init, new Point(0, arcLength * Math.cos(theta), arcLength * Math.sin(theta)));
	}

}
