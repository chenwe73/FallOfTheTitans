package control;

import frame.Main;
import thread.Engine;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

//The key listener class for game component
public class GameKey extends KeyAdapter
{
	private int keyCode; // key code of the event
	
	// The key pressed method for the game frame
	public void keyPressed(KeyEvent e) 
	{
		keyCode = e.getKeyCode();
		Engine.keyPressed = true;
	}
	
	// The key pressed method for reading key code in the engine
	public void keyPressed()
	{
	//	int scroll = 10;
		switch (keyCode) 
		{
	/*	For Special Access only
	  	case KeyEvent.VK_UP:
			Position.moveOrigin(new Vector (0, scroll));
			break;
		case KeyEvent.VK_DOWN:
			Position.moveOrigin(new Vector (0, -scroll));
			break;
		case KeyEvent.VK_LEFT:
			Position.moveOrigin(new Vector (scroll, 0));
			break;
		case KeyEvent.VK_RIGHT:
			Position.moveOrigin(new Vector (-scroll, 0));
			break;*/
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_P:
			Main.game.engine.interrupt();
			Main.game.setVisible(false);
			Main.pause.setVisible(true);
			break;
		}
		Main.game.repaint();
		keyCode = Integer.MIN_VALUE;
	}
}