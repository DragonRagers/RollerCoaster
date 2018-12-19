package roller.coaster;

public class VerticalLoop extends Track {
	public float radius;
	
	public VerticalLoop(Point i, float r, int dir) { //dir is direction, 0 - x, 1 - y (unimplemented assuming y)
		init = i;
		radius = r;
		end.y = init.y+2*radius; 
		formTrack();
	}
	
	private void formTrack() {
		//			{<0, r*t/5, 0>								0<t<5
		//f(t)=		{<(t-5)/(2pi), r + rcos(t-5), rsin(t-5)>		5<t<5+2pi
		//			{<1, r + r*t/5, 0>							5+2pi<t<10+2pi	
	}
	
	private Point calcPosition(double arcLength) {
		if (arcLength < radius) {
			return Point.add(init, new Point(0,arcLength,0));
		} else if (arcLength < radius + radius * 2 * Math.PI) {
			return Point.add(init, new Point(0,radius + radius * Math.cos(reParam(arcLength)), radius * Math.sin(reParam(arcLength))));
		}
		return null;
	}

	private double reParam(double arclength) { 
		return arclength/Math.sqrt((1/Math.pow(Math.PI,2))+Math.pow(radius,2)); 
	}
	
	private void calcLength() {
		
	}
	
	@Override
	public void drawTrack() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String args[]) { //testing shit
		for (double i = 0; i < 20; i+=.1) {
			System.out.println(calcPosition(i));
		}
	}
	
}
