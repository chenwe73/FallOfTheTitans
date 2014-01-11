package frame;

import abstraction.AbstractFrame;
import java.awt.Color;
import java.awt.Font;

import component.Button;
import java.awt.Graphics;
import component.Listener;
import javax.swing.JComponent;

@SuppressWarnings("serial")
// The menu component class that displays the main menu
public class Menu extends AbstractFrame implements Listener
{
	Button play = new Button("Play",350,230,300,80,87,57); // start game button
	Button help = new Button("Help",350,340,300,80,85,57); // instruction button
	Button quit = new Button("Quit",350,670,300,80,85,57); // exit window button
	Button option = new Button("Option",350,450,300,80,54,58); // setting button
	Button credit = new Button("Credit",350,560,300,80,56,60); // credit button
	
	// The constructor for menu component
	public Menu()
	{
		super();
		add(play);
		add(help);
		add(quit);
		add(option);
		add(credit);
		setVisible(true);
	}
	
	// To perform the actions of component objects
	public void actionPerformed(JComponent event)
	{
		setVisible(false);
		if (event == play)
		{
			Main.game.setVisible(true);
			Main.game.play();
		}
		else if (event == help)
			Main.help.setVisible(true);
		else if (event == option)
			Main.option.setVisible(true);
		else if (event == credit)
			Main.credit.setVisible(true);
		else if (event == quit)
			System.exit(0);
	}
	
	// To display the menu graphics
	public void paint(Graphics g)
	{
		super.paint(g);
		play.repaint();
		help.repaint();
		quit.repaint();
		credit.repaint();
		option.repaint();
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,120));
		g.drawString("Fall Of The Titans",30,150);
	}
}