import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

//Name: Julius D. Higiro
//Date: 23 October, 2013
//Filename: Obstacle
//Description: This class contains the blueprint used to build
//             the obstacles

public class Obstacle {
	
	private Rectangle2D.Double obs;
	private final double MAX_SPEED = 8.0;
	private double screenWidth, speed, width, height;
	private Image obsImg;
	
	public Obstacle(Image obsImg,double x, double y, double width, double height, double screenWidth, double speed, double maxSpeed) {
		this.obs = new Rectangle2D.Double(x, y, width, height);
		this.screenWidth = screenWidth;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.obsImg = obsImg;
		
	}
	
	// Used to draw image of obstacles from file
	public void draw(Graphics2D g, Rectangle2D x, Rectangle2D y) {
		g.drawImage(obsImg,(int)obs.x,(int)obs.y,null);  	
	}
	
	public Rectangle2D.Double getBounds() {
		return (Double)obs.getBounds2D();
	}
	
	// Returns true when frog hits an obstacle
	public boolean didHit(Rectangle2D opp) {
		return obs.intersects( opp );
	}
	
	public double getSpeed() {
		return speed;
	}
    
	// Set the speed so the obstacle does not exceed the max speed 
	public void setSpeed(double speed) {
		
		// Never let the speed of an obstacle be faster than the MAX_SPEED
		speed = (speed > 0) ? Math.min(speed, MAX_SPEED) : Math.max(speed, -MAX_SPEED);
		
		if(speed >= -MAX_SPEED && speed <= MAX_SPEED)
			this.speed = speed;
		else
			this.speed = 1;
	}
    
	// Moves obstacles towards the left
	public void moveObstacles() {
		
			obs.x -= speed;
			
			if(obs.x > screenWidth) {
				
				obs.x = -width;
			} else if(obs.x < -width) {
			
				obs.x = screenWidth;
			}
		}
	
}


