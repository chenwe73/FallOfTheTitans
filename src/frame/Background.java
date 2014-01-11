package frame;

import abstraction.AbstractFrame;
import java.awt.Graphics;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
// The background component class that displays the background
public class Background extends AbstractFrame
{
	static ImageIcon background = new ImageIcon("picture/background1.jpg"); // background image
	
	// The constructor for background component
	public Background()
	{
		super();
		setVisible(true);
	}
	
	// To display the background graphics
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(background.getImage(),0,0,w,h,this);
	}
}