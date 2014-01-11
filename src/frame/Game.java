package frame;

import abstraction.AbstractFrame;
import java.awt.Color;
import java.awt.Font;

import control.GameKey;
import control.GameMouse;
import java.awt.Graphics;

import object.Position;
import thread.Engine;

@SuppressWarnings("serial")
// The game component class that displays the game play
public class Game extends AbstractFrame
{
	public GameMouse mouse = new GameMouse(); // mouse/motion listener
	public GameKey key = new GameKey(); // key listener
	public Engine engine = new Engine(); // engine thread
	
	static long timeLast; // time of end of last loop (millisecond)
	static long timeNow; // time of end of current loop (millisecond)
	static double fpsscr; // screen FPS (Hz)
	
	// The constructor for the game component
	public Game()
	{
		super();
		//Position.setMPP(0.2);
		Position.setOrigin(0,800);
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseWheelListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	// Start a new game
	public void play()
	{
		engine = new Engine();
		engine.initialize();
		engine.start();
		for (int i = 0; i < Engine.startRock; i++)
			Engine.addRock();
	}
	
	// Resume the game
	public void resume()
	{
		engine = new Engine();
		engine.start();
	}
	
	// To display the game graphics
	public void paint(Graphics g)
	{
		super.paint(g);
	/* 	For Special Access only
	 	update the grid information
		g.setColor(Color.yellow);
		g.drawString(mouse.mouse.toString(),500,20);		
		g.drawString("FPS: "+thread.Engine.fps,10,20);
		g.drawString("FPSSCR: "+fpsscr,10,40);
		Position.drawAxis(g,100);*/
		for (int i = 0; i < Engine.rock.size(); i++)
		{
			g.setColor(Color.red);
			Engine.rock.get(i).drawFill(g);
			g.setColor(Color.black);
			Engine.rock.get(i).draw(g);
		}
		g.setColor(Color.red);
		g.fillRect(0,800-60,1000,60);
		g.setColor(Color.black);
		g.drawLine(0,800-60,1000,800-60);
		g.setColor(Color.red);
		Engine.base.drawFill(g);
		g.setColor(Color.black);
		Engine.base.draw(g);
		g.setColor(Color.yellow);
		Engine.hole.drawFill(g);
		g.setColor(Color.black);
		g.setColor(Color.blue);
		g.drawRect(450,800-65,100,20);
		g.fillRect(450,800-65,(int)(Engine.energy/20),20);
		for (int i = 0; i < Engine.ball.size(); i++)
		{
			g.setColor(Color.red);
			Engine.ball.get(i).drawFill(g);
			g.setColor(Color.black);
			Engine.ball.get(i).draw(g);
		}
		g.setColor(Color.blue);
		g.setFont(new Font("Serif",Font.BOLD,20));
		long t = Engine.totalTime/1000;
		g.drawString(String.format("%02d:%02d",t/60,t%60),10,25);
		if (Engine.over)
		{
			g.setColor(Color.blue);
			g.setFont(new Font("Serif",Font.BOLD,200));
			g.drawString("Game Over",0,400);
		}
		updatefps();
	}
	
	// update the game frame per second
	public static void updatefps ()
	{
		timeLast = timeNow;
		timeNow = System.currentTimeMillis();
		fpsscr = 1000.0 / (timeNow - timeLast);
	}
}