package abstraction;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.RenderingHints;

@SuppressWarnings("serial")
// The abstract frame class for all component frames
public abstract class AbstractFrame extends JComponent
{
	protected static int w = 1000; // universal frame width
	protected static int h = 800; // universal frame height
	
	// The constructor for the abstract frame
	public AbstractFrame()
	{
		setSize(w,h);
		setLayout(null);
		setVisible(false);
		setFocusable(true);
	}
	
	// To display better graphics
	public void paint(Graphics g)
	{
		((Graphics2D)g).setRenderingHint
		(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	// The overriden method for using keyboard
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		this.requestFocus();
	}
	
	// To set the universal frame size for all frames
	public static void setUniversalSize(int width, int height)
	{
		AbstractFrame.w = width;
		AbstractFrame.h = height;
	}
}