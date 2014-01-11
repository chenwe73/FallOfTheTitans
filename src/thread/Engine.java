package thread;

import java.util.ArrayList;

import frame.Main;
import object.*;

public class Engine extends Thread
{
	static long timeLast; // time of end of last loop (milliseconds)
	static long timeNow; // time of end of current loop (milliseconds)
	static long sleepTime; // amount of sleep (milliseconds)
	public static double fpsCap = 60; // frame per second capacity (Hz)
	public static double fps = fpsCap; // frame per second (Hz)
	public static boolean keyPressed = false; // key pressed state
	public static double mousePressed = 0; // mouse pressed value for bullets
	public static Sphere base = new Sphere(new Position(500,60),1,60); // turret sphere
	public static Sphere hole = new Sphere(new Position(500,105),1,15); // shooting hole
	public static ArrayList<Sphere> ball = new ArrayList<Sphere>(0); // array of bullets
	public static ArrayList<Sphere> rock = new ArrayList<Sphere>(0); // array of meteoriods
	public static Plane[] plane = new Plane[4]; // array of deletion planes for delete spheres
	public static boolean over; // true if the game ends
	public static long totalTime; // total time playing
	public static long countTime; // count time for adding rock
	public static double shootTime = 200; // the shooting interval
	public static long lastShoot; // the time of last shooting
	public static double momentum = 100000; // the momentum of the meteoriods
	public static double energy = 2000; // the energy/ammo amount
	public static double energyCount = 0; // count time for refill energy
	public static double rockTime = 3000; // the adding rock interval
	public static double startRock = 3; // initial number of rocks
	
	// The main loop of the engine
	public void run()
	{
		try
		{
			while (true)
			{
				// add time for the counts
				energyCount += Math.round(1000.0 / fpsCap);
				countTime += Math.round(1000.0 / fpsCap);
				totalTime += Math.round(1000.0 / fpsCap);
				if (keyPressed)
					Main.game.key.keyPressed();
				if (energyCount > 200 && energy < 2000)
				{
					energy += 50; // add energy
					energyCount = 0;
				}
				if (mousePressed != 0 && energy > 100*Math.pow(Math.round(mousePressed),2) &&
					System.currentTimeMillis()-lastShoot > shootTime*Math.pow(mousePressed,3))
				{ // shoot the chosen bullet if enough enger and after time interval
					energy -= 100*Math.pow(Math.round(mousePressed),2);
					shoot(Main.game.mouse.mouse,mousePressed);
					lastShoot = System.currentTimeMillis();
				}
				// update all the spheres
				base.integrate();
				hole.integrate();
				for (int i = 0; i < ball.size(); i++)
				{
					Sphere bullet = ball.get(i);
					for (int j = 0; j < rock.size(); j++)
					{ // check and perform collision
						bullet .collide(rock.get(j));
					} // update with other forces
					bullet.applyGravity();
					bullet.applyFriction(1);
					bullet.integrate();
					// delete the bullet when 200 away from screen
					for (int j = 0; j < plane.length; j++)
						if (plane[j] != null && bullet.isReflect(plane[j]))
							ball.remove(i);
				}
				for (int i = 0; i < Engine.rock.size(); i++)
				{
					Sphere astoriod = rock.get(i);
					// update with other forces
					astoriod.applyGravity();
					astoriod.applyFriction(1);
					astoriod.integrate();
					// check the collision with turret
					if (base.isCollide(astoriod))
						gameOver();
					// delete the meteoriod when 200 away from screen
					for (int j = 0; j < plane.length; j++)
						if (plane[j] != null && astoriod.isReflect(plane[j]))
						{
							rock.remove(i);
							// successfully escape will increase momentum of new meteoriods
							// and decrease the time interval to create new meteoriods
							momentum += 200;
							if (rockTime > 1000)
								rockTime -= 50;
						}
				}
				// check time to add meteoriods
				if (countTime > rockTime)
				{
					countTime = 0;
					addRock();
				}
				Main.game.repaint();
				// check the time spent
				sleepTime = Math.round(1000.0 / fpsCap) - System.currentTimeMillis() + timeNow;
				if (sleepTime > 0) // have spare time
					Thread.sleep(sleepTime + 0); // + a value for artificial slow motion
				else; // running behind
				updatefps();
			}
		}
		catch (InterruptedException e)
		{
		};
	}
	
	// To update the game frame per second
	public static void updatefps ()
	{
		timeLast = timeNow;
		timeNow = System.currentTimeMillis();
		fps = 1000.0 / (timeNow - timeLast);
	}
	
	// Shoot and create new bullet
	public static void shoot(Position v, double size)
	{
		Vector velocity = hole.getPosition().difference(v).unit().multiple(300*size);
		ball.add(new Sphere(new Position(500,100),40*size,velocity,10*size,1));
	}
	
	// Create new rock and make it fall toward the turret
	public static void addRock()
	{
		double radius = Math.random()*50+50;
		double mass = Math.pow(radius,2)*Math.PI/25;
		double speed = momentum / mass;
		double position = Math.random()*(1000+800+4*radius);
		Position start;
		if (position < 400 + radius)
			start = new Position(-radius,800-position+radius);
		else if (position < 1400 + 3*radius)
			start = new Position(position-(400+radius)-radius,800+radius);
		else
			start = new Position(1000+radius,800-(position-(1400 + 3*radius))+radius);
		Vector velocity = start.difference(hole.getPosition()).unit().multiple(speed);
		rock.add(new Sphere(start,mass,velocity,radius,1));
	}
	
	// Reset everything when start a new game
	public void initialize()
	{
		plane[0] = new Plane(new Vector (0, 1), new Position(0,-200));
		plane[1] = new Plane(new Vector (0, -1), new Position(0,1000));
		plane[2] = new Plane(new Vector (1, 0), new Position(-200, 0));
		plane[3] = new Plane(new Vector (-1, 0), new Position(1200, 0));
		over = false;
		totalTime = 0;
		keyPressed = false;
		mousePressed = 0;
		base = new Sphere(new Position(500,60),1,60);
		hole = new Sphere(new Position(500,105),1,15);
		ball = new ArrayList<Sphere>(0);
		rock = new ArrayList<Sphere>(0);
		countTime = 0;
		shootTime = 200;
		lastShoot = 0;
		momentum = 100000;
		energy = 2000;
		energyCount = 0;
		rockTime = 3000;
		startRock = 3;
	}
	
	// End the engine to stop the game
	public void gameOver() throws InterruptedException
	{
		over = true;
		Main.game.repaint();
		this.interrupt();
	}
}