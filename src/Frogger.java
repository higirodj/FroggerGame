
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


//Name: Julius D. Higiro
//Date: 23 October, 2013
//Filename: Frogger
/*Description: This is the driver class of a frogger game that consists of six classes. 
I have an abstract class named Auto that is the blue print for building different autos.
There is a Car and a Truck class, which are subclasses of the Auto class and these classes (Car and Truck)
implement the methods of the Auto class in order to produce the different types of autos.
There is a Frog class that renders the frog object that is the character in the game.
There is also an Obstacle class that renders objects such as snakes and birds that attempt to impede the
forward progress of the frog.
The objective of this game is to maneuver the frog through vehicle traffic without being struck and killed.
After crossing the two lanes, the frog will cross another lane. It will encounter an obstacle in the form
of a flying bird and several snakes. These obstacles will not kill the frog but they will attempt to push/keep
the frog out of their crossing lane. Be careful, the frog can be pushed back into the second lane
with vehicle traffic. Any time the frog is pushed back by the flying bird or any one of the snakes, another
vehicle is added to the other two crossing lanes. There are three safety zones, where the frog is safe from being struck
by on coming traffic.
*/
public class Frogger extends JPanel implements ActionListener, KeyListener {
	private static final int MOVE_RATE = 15;
	private ArrayList<Auto> autos = new ArrayList<Auto>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Random rnd = new Random();
	private Timer timer = new Timer(10, this);
	private JFrame screen = new JFrame("Frogger");
	private Frog frog;
	private Obstacle obstacle1;
	private Obstacle obstacle2;
	private Image backGroundImg = Toolkit.getDefaultToolkit().getImage("images/Background.png");
	private Image obsImg1 = Toolkit.getDefaultToolkit().getImage("images/snake2.gif");
	private Image obsImg2 = Toolkit.getDefaultToolkit().getImage("images/bird1.gif");
	private Image obsImg3 = Toolkit.getDefaultToolkit().getImage("images/gameover.jpg");;

	public Frogger() {
		screen.setBounds(50, 50, 700, 700);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screen.add(this);
		screen.setVisible(true);
		frog = new Frog(screen.getWidth() / 2, 645, 5, 5, 700,700);

		genAuto(1);
		addKeyListener(this);
		setFocusable(true);
		buildObstacles();
		timer.start();

	}

	// Builds the obstacles in the game, composed of a bird and snakes
	private void buildObstacles() {

		for(int i = 1; i< 20; i++) {
		obstacle1 = new Obstacle(obsImg1,screen.getWidth()*rnd.nextDouble(), 10*i, 35,15, 600,2, 5);
		obstacles.add(obstacle1);
		}
	    obstacle2 = new Obstacle(obsImg2,screen.getWidth()*rnd.nextDouble(), 135, 5,55, 600,2, 5);
	    obstacles.add(obstacle2);
	}

	// Simple method to generate a random number of cars and trucks
	// that drive along the road in both directions
	private void genAuto(int num) {
		for(int i = 0; i < num; i++) {

			if(rnd.nextBoolean())
				autos.add( new Car( rnd.nextDouble() * 500,
						   rnd.nextInt(120)+280,
				           screen.getHeight(),
				           (rnd.nextDouble() * Car.MAX_SPEED + 1) * (rnd.nextBoolean() ? 1 : -1)
				          ));

			else
				autos.add( new Truck( rnd.nextDouble() * 500,
						   rnd.nextInt(120)+480,
				           screen.getHeight(),
				           (rnd.nextDouble() * Truck.MAX_SPEED + 1) * (rnd.nextBoolean() ? 1 : -1)
				          ));

			}
	}

	// This is the timer method
	public void actionPerformed(ActionEvent e) {

		for(Auto a : autos) {
			a.drive();
			// When the frog is hit by auto, the game stops
			if( frog.didHit(a.getBounds()))
				timer.stop();
		}
		for(Obstacle b : obstacles) {
			b.moveObstacles();
			// When the frog is hit by obstacles, the frog is pushed
			// downwards and to the left. Also, another auto is added
			// to the two lanes. An increase in contact with the obstacles 
			// results in the increase of traffic and game difficulty.
		    if(frog.didHit(b.getBounds())) {
		    	frog.moveDown(80);frog.moveLeft(50);
		    	genAuto(1); Toolkit.getDefaultToolkit().beep();
		    }
		}

		screen.repaint();
	}

	// Draws the background, cars, trucks, bird and snakes using images
	// from a file
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(backGroundImg, -5, 0, this);
		frog.draw((Graphics2D)g, null, null);

		for(Auto a : autos)
			a.draw((Graphics2D)g, null, null );

		for(Obstacle b: obstacles)	{
			b.draw((Graphics2D)g, null, null);
		}
		for(Auto a : autos) {
			if (frog.didHit(a.getBounds()))
				g2.drawImage(obsImg3,250, 280, this);
		}
	}

	public static void main(String[] args) {
		new Frogger();
	}

	// Method that handles the pressing of the keys
	// Use the arrow keys to move the frog across the screen
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		//System.out.println(kc);
		if (kc > 36 && kc < 41) {
			if (kc == KeyEvent.VK_UP) {
		    	if(frog.moveUp(MOVE_RATE))	genAuto(2); // if frog is off screen generate 2 more autos
		    }
		    else if (kc == KeyEvent.VK_DOWN) {
		    	frog.moveDown(MOVE_RATE);
		    }
		    else if (kc == KeyEvent.VK_LEFT) {
		    	frog.moveLeft(MOVE_RATE);
		    }
		    else if (kc == KeyEvent.VK_RIGHT) {
		    	frog.moveRight(MOVE_RATE);
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
