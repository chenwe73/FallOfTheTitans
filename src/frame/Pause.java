package frame;

import abstraction.AbstractFrame;
import java.awt.Color;
import java.awt.Font;

import component.Button;
import java.awt.Graphics;
import component.Listener;
import javax.swing.JComponent;

@SuppressWarnings("serial")
// The pause component class that displays the pause page
public class Pause extends AbstractFrame  implements Listener
{
	Button back = new Button("Back To Menu",250,670,500,80,50,60); // back button
	Button resume = new Button("Resume",350,560,300,80,47,61); // resume game button
	
	// The constructor for pause component
	public Pause()
	{
		super();
		add(back);
		add(resume);
	}
	
	// To perform the actions of component objects
	public void actionPerformed(JComponent event)
	{
		setVisible(false);
		if (event == back)
			Main.menu.setVisible(true);
		if (event == resume)
		{
			Main.game.resume();
			Main.game.setVisible(true);
		}
	}
	
	// To display the pause page
	public void paint(Graphics g)
	{
		super.paint(g);
		back.repaint();
		resume.repaint();
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,160));
		g.drawString("Pause",300,130);
	}
}