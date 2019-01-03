package roller.coaster;

public abstract class Track {
	//All track pieces should be subclasses of this
	Point end; //end point of the track piece relative to beginning (aka assume beginning is (0,0,0))
	Point init;
	Double arcLength;
	
	public void setInit(Point p) {
		init = p;
	}
	
	public Point getEnd(Point p) {
		return end;
	}
	
	public abstract void drawTrack();
	
	
}
