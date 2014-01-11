package frame;

import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
// The only frame class that contains all components
public class Main extends JFrame
{
	public static final int FRAMEWIDTH = 1000; // default frame width
	public static final int FRAMEHEIGHT = 800; // default frame height
	
	public static Menu menu; // the menu page component
	public static Game game; // the game page component
	public static Help help; // the help page component
	public static Pause pause; // the pause page component
	public static Option option; // the option page component
	public static Credit credit; // the credit page component
	public static Background bkpic; // the background component
	
	// The constructor for the frame
	public Main()
	{
		super("Fall Of The Titans");
		this.setLayout(null);
		add(menu = new Menu());
		add(game = new Game());
		add(help = new Help());
		add(pause = new Pause());
		add(option = new Option());
		add(credit = new Credit());
		add(bkpic = new Background());
		Point hotSpot = new Point(16,16);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image cursor = kit.getImage("picture/cursor.gif");
		setCursor(kit.createCustomCursor(cursor,hotSpot,null));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setSize(FRAMEWIDTH+6,FRAMEHEIGHT+26);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}