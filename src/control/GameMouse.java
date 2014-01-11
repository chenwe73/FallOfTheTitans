package control;

import frame.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
//import java.awt.event.MouseWheelEvent;
import object.Position;
import thread.Engine;

// The mouse listener class for game component
public class GameMouse extends MouseAdapter
{
	public Position mouse = new Position(10000,10000); // position of the mouse
	
	// The mouse pressed method for the game frame
	public void mousePressed(MouseEvent e)
	{
		if (Engine.over)
		{
			Main.game.setVisible(false);
			Main.credit.setVisible(true);
		}
		if (e.getButton() == MouseEvent.BUTTON1)
			Engine.mousePressed = 1;
		else if (e.getButton() == MouseEvent.BUTTON3)
			Engine.mousePressed = 1.5;
	}
	
	// The mouse released method for the game frame
	public void mouseReleased(MouseEvent e)
	{
		Engine.mousePressed = 0;
	}
	
	// The mouse mouseMoved method for the game frame
	public void mouseMoved(MouseEvent e)
	{
		mouse.teleportCoord(e.getX(), e.getY());
	}
	
	// The mouse dragged method for the game frame
	public void mouseDragged(MouseEvent e)
	{
		mouse.teleportCoord(e.getX(), e.getY());
	}
	/* For Special Access only
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		double zoom = 0.9;
		if (e.getWheelRotation() > 0)
			Position.zoom(1/zoom);
		if (e.getWheelRotation() < 0)
			Position.zoom(zoom);
		Main.game.repaint();
	}*/
}