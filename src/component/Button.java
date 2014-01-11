package component;

import abstraction.AbstractComponent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
// The button object for user to click in the components
public class Button extends AbstractComponent
{
	private static Color pressedText = new Color(255,0,0);
	private static Color releasedText = new Color(0,0,255);
	private static Color background = new Color(255,255,0);
	private static Font buttonFont = new Font("Serif",Font.BOLD,64);
	
	// The constructor for the button with default text location and font
	public Button(String s, int x, int y, int w, int h)
	{
		super(s,x,y,w,h,0,0,buttonFont);
	}
	
	// The constructor for the button with stated text location and default font
	public Button(String s, int x, int y, int w, int h, int tx, int ty)
	{
		super(s,x,y,w,h,tx,ty,buttonFont);
	}
	
	// The constructor for the button with stated text location and font
	public Button(String s, int x, int y, int w, int h, int tx, int ty, Font f)
	{
		super(s,x,y,w,h,tx,ty,f);
	}
	
	// To display the button graphics
	public void paint(Graphics g)
	{
		super.paint(g);
		if (entered)
		{
			g.setColor(background);
			g.fillOval(5,5,w-10,h-10);
		}
		g.setFont(font);
		g.setColor(releasedText);
		if (pressed)
			g.setColor(pressedText);
		g.drawString(name,tx,ty);
	}
	
	// Set all colours of all states for the button
	public static void setColor(Color pt, Color rt, Color bg)
	{
		if (pt != null)
			pressedText = pt;
		if (rt != null)
			releasedText = rt;
		if (bg != null)
			background = bg;
	}
}