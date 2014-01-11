package frame;

import abstraction.AbstractFrame;
import java.awt.Color;
import java.awt.Font;

import component.Button;
import java.awt.Graphics;
import component.Listener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
// The option component class that displays the option page
public class Option extends AbstractFrame  implements Listener
{
	Button back = new Button("Back To Menu",250,670,500,80,50,60); // back button
	Button picture = new Button("Change Background",150,560,700,80,60,60); // change background image button
	
	static int background = 1;
	static int backgroundNum = 2;
	
	// The constructor for option component
	public Option()
	{
		super();
		add(back);
		add(picture);
	}
	
	// To perform the actions of component objects
	public void actionPerformed(JComponent event)
	{
		if (event == back)
		{
			setVisible(false);
			Main.menu.setVisible(true);
		}
		if (event == picture)
		{
			if (++background > backgroundNum)
				background = 0;
			Background.background = new ImageIcon("picture/background"+background+".jpg");
			repaint();
		}
	}
	
	// To display the option graphics
	public void paint(Graphics g)
	{
		super.paint(g);
		back.repaint();
		picture.repaint();
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,160));
		g.drawString("Option",240,130);
	}
}