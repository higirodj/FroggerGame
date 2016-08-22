import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

//Name: Julius D. Higiro
//Date: 23 October, 2013
//Filename: Truck
//Description: This class contains the blueprint used to build
//             the trucks 

public class Truck extends Auto  {

	public static final double MAX_SPEED = 3.0;
	private static final double TRUCK_WIDTH = 40.0;
	private static final double TRUCK_HEIGHT = 20.0;

	private Image truckImg= Toolkit.getDefaultToolkit().getImage("images/vehicle_3.jpg");;
	
	public Truck(double x, double y, int screenWidth, double speed) {
		super(x,y,screenWidth,speed, MAX_SPEED, TRUCK_WIDTH, TRUCK_HEIGHT);
	}
	
	// used to draw image of truck from file 
	public void draw(Graphics2D g, Rectangle2D x, Rectangle2D y) {
		g.drawImage(truckImg,(int)rec.x,(int)rec.y,null);
	}

}

