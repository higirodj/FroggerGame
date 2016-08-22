import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

//Name: Julius D. Higiro
//Date: 23 October, 2013
//Filename: Auto
//Description: This class contains the blueprint used to build
//             the autos     

public abstract class Auto {

	private final double MAX_SPEED = 8.0;
	private double autoWidth;
	private double autoHeight;	
	private double speed;
	private int screenWidth;
	protected Rectangle2D.Double rec;
	
	public Auto(double x, double y, int screenWidth, double speed, double maxSpeed, double autoWidth, double autoHeight) {
		rec = new Rectangle2D.Double(x, y, autoWidth, autoHeight);
		this.autoWidth = autoWidth;
		this.autoHeight = autoHeight;
		this.screenWidth = screenWidth;
		setSpeed(speed);
	}

	public double getSpeed() {
		return speed;
	}
    
	// Set the speed so that autos do not exceed the maximum speed 
	public void setSpeed(double speed) {
		
		// Never let the speed of an auto be faster than the MAX_SPEED
		speed = (speed > 0) ? Math.min(speed, MAX_SPEED) : Math.max(speed, -MAX_SPEED);
		
		if(speed >= -MAX_SPEED && speed <= MAX_SPEED)
			this.speed = speed;
		else
			this.speed = 1;
	}
	
	// Method that moves the autos (left and right) across the screen
	public void drive() {
		rec.x += speed;
		
		if(rec.x > screenWidth) {
			rec.x = -autoWidth;
		} else if(rec.x < -autoWidth) {
			rec.x = screenWidth;
		}
	}
	
	public abstract void draw(Graphics2D g, Rectangle2D x, Rectangle2D y);
	
	// Method that returns true when the frog is hit by an auto
	public boolean didHit(Rectangle2D opp) {
		return rec.intersects( opp );
	}
	
	public Rectangle2D getBounds() {
		return rec.getBounds2D();
	}
	
}


