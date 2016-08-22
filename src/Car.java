import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

//Name: Julius D. Higiro
//Date: 23 October, 2013
//Filename: Car
//Description: This class contains the blueprint used to build
//             the cars  

public class Car extends Auto {

	public static final double MAX_SPEED = 5.0;
	private static final double CAR_WIDTH = 15.0;
	private static final double CAR_HEIGHT = 10.0;
	private Image carImg= Toolkit.getDefaultToolkit().getImage("images/vehicle_2.jpg");;
	
	public Car(double x, double y, int screenWidth, double speed) {
		super(x,y,screenWidth,speed, MAX_SPEED, CAR_WIDTH, CAR_HEIGHT);
	}
	
	// Used to draw image of car from file
	public void draw(Graphics2D g, Rectangle2D x, Rectangle2D y) {	
		g.drawImage(carImg,(int)rec.x,(int)rec.y,null);
	}
	
}
