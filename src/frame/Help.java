package frame;

import abstraction.AbstractFrame;
import java.awt.Color;
import java.awt.Font;

import component.Button;
import java.awt.Graphics;
import component.Listener;
import javax.swing.JComponent;

@SuppressWarnings("serial")
// The help component class that displays the help page
public class Help extends AbstractFrame  implements Listener
{
	Button back = new Button("Back To Menu",250,670,500,80,50,60); // back button
	
	// The constructor for help component
	public Help()
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
	
	// To display the help graphics
	public void paint(Graphics g)
	{
		super.paint(g);
		back.repaint();
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,160));
		g.drawString("Help",330,130);
		g.setFont(new Font("Serif",Font.BOLD,40));
		g.drawString("The goal of the game is to survive as long as possible",20,230);
		g.drawString("Do not let any meteoriods hit you or you will lose",20,280);
		g.drawString("Use mouse to shoot bullets at them to bounce them back",20,330);
		g.drawString("Hold onto mouse button to continue shooting bullets",20,380);
		g.drawString("Left click - small bullets   Right Click - big bullets",20,430);
		g.drawString("Ammo is refilled constantly but has maximum capacity",20,480);
		g.drawString("Use physics law to predict the movement of meteoriods",20,530);
		g.drawString("After the collistion with the bullets and the path",20,580);
		g.drawString("Press p or Esc to Pause. Good luck and have fun",20,630);
	}
}