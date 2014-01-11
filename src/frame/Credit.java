package frame;

import abstraction.AbstractFrame;
import java.awt.Color;
import java.awt.Font;

import component.Button;
import java.awt.Graphics;
import component.Listener;
import javax.swing.JComponent;

@SuppressWarnings("serial")
// The credit component class that displays the credit page
public class Credit extends AbstractFrame  implements Listener
{
	Button back = new Button("Back To Menu",250,670,500,80,50,60); // back button
	
	// The constructor for credit component
	public Credit()
	{
		super();
		add(back);
	}
	
	// To perform the actions of component objects
	public void actionPerformed(JComponent event)
	{
		setVisible(false);
		if (event == back)
			Main.menu.setVisible(true);
	}
	
	// To display the credit graphics
	public void paint(Graphics g)
	{
		super.paint(g);
		back.repaint();
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,160));
		g.drawString("Credit",250,130);
		g.setFont(new Font("Serif",Font.BOLD,80));
		g.drawString("Tommy Chen",480,400);
		g.drawString("Brad Wang",480,600);
		g.drawString("Software Engineer",80,300);
		g.drawString("Game Developer",80,500);
	}
}