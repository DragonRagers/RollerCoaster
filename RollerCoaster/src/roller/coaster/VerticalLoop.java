package roller.coaster;

public class Loop extends Track {
	public float radius;
	
	public Loop(float r) {
		radius = r;
		formTrack();
	}
	
	private void formTrack() {
		//			{<0, r*t/5, 0>								0<t<5
		//f(t)=		{<(t-5)/(2pi), r + rcos(t-5), rsin(t-5)>		5<t<5+2pi
		//			{<1, r + r*t/5, 0>							5+2pi<t<10+2pi	
	}

	private double reParam(double arclength) { 
		return arclength/Math.sqrt((1/Math.pow(Math.PI,2))+Math.pow(radius,2)); 
	}
	
	@Override
	public void drawTrack() {
		// TODO Auto-generated method stub
		
	}

	
	
}
