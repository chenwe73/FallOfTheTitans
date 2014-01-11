package abstraction;

import java.awt.Font;
import java.awt.Graphics;
import component.Listener;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
//The abstract component class for all component objects
public abstract class AbstractComponent extends JComponent implements MouseListener
{
	protected int x; // the x of the component
	protected int y; // the y of the component
	protected int w; // the width of the component
	protected int h; // the height of the component
	protected String name; // the name of the component
	protected Font font; // the text font of the component
	protected boolean pressed; // the component-press state
	protected boolean entered; // the component-enter state
	protected int tx; // the location of the text in the component
	protected int ty; // the location of the text in the component
	
	// The constructor for the abstract component
	public AbstractComponent(String s, int x, int y, int w, int h, int tx, int ty, Font f)
	{
		name = s;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tx = tx;
		this.ty = ty;
		this.font = f;
		setOpaque(true);
		setBounds(x,y,w,h);
		addMouseListener(this);
	}
	
	// To display better graphics
	public void paint(Graphics g)
	{
		((Graphics2D)g).setRenderingHint
		(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	// Listen for the mouse pressed event
	public void mousePressed(MouseEvent e)
	{
		pressed = true;
		getParent().repaint(x,y,w,h);
	}

	// Listen for the mouse released event
	public void mouseReleased(MouseEvent e)
	{
		if (entered) // perform the action
			((Listener)getParent()).actionPerformed(this);
		pressed = false;
		getParent().repaint(x,y,w,h);
	}

	// Listen for the mouse entered event
	public void mouseEntered(MouseEvent e)
	{
		entered = true;
		getParent().repaint(x,y,w,h);
	}

	// Listen for the mouse exited event
	public void mouseExited(MouseEvent e)
	{
		entered = false;
		getParent().repaint(x,y,w,h);
	}
	
	// Listen for the mouse clicked event
	public void mouseClicked(MouseEvent e)
	{
		getParent().repaint(x,y,w,h);
	}
}