
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

//Name: Julius D. Higiro
//Date: 23 October, 2013
//Filename: Frog
//Description: This class contains the blueprint used to build
//             the frog

public class Frog {
	
	private Rectangle2D.Double rec;
	private double screenWidth, screenHeight;
	private Image frogImg;
	
	public Frog(double x, double y, double width, double height, double screenWidth, double screenHeight) {
		this.rec = new Rectangle2D.Double(x, y, width, height);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.frogImg = Toolkit.getDefaultToolkit().getImage("images/Frog_up.gif");
		
	}
	
	// Used to draw image of frog from file
	public void draw(Graphics2D g, Rectangle2D x, Rectangle2D y) {
		g.drawImage(frogImg,(int)rec.x,(int)rec.y,null);
	}
	
	public Rectangle2D.Double getBounds() {
		return (Double) rec.getBounds2D();
	}
	
	// Returns true when frog hits an object
	public boolean didHit(Rectangle2D obj) {
		return rec.intersects(obj);
	}
	
	// All booleans mean that the frog hit the wall
	public boolean moveUp(double units) {
		rec.y -= units;
		if(rec.y <= 0) { 
			rec.y = screenHeight - 50;	// moved past top of screen so reset frog to bottom
			return true;
		} else 
			return false;
	}
	public boolean moveDown(double units) {
		rec.y += units;
		if(rec.y >= screenHeight) { 
			rec.y = screenHeight - rec.height;
			return true;
		} else 
			return false;
	}
	public boolean moveLeft(double units) {
		rec.x -= units;
		if(rec.x <= 0) { 
			rec.x = 0;
			return true;
		} else 
			return false;
	}
	public boolean moveRight(double units) {
		rec.x += units;
		if(rec.y >= screenWidth) { 
			rec.y = screenWidth - rec.width;
			return true;
		} else 
			return false;

	}

}

