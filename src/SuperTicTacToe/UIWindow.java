package SuperTicTacToe;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The window class for Super Tic-Tac-Toe
 * @author Jonathan
 */
public class UIWindow implements WindowListener
{
	private final JFrame mainWindow;
	
	private JPanel currentPanel;
	
	private final History history;
	
	/**
	 * This holds the running instance of the UIWindow.
	 */
	private static UIWindow currentInstance;
	
	private UIWindow()
	{
		this.mainWindow = new JFrame("Super Tic-Tac-Toe");
		
		this.mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.mainWindow.addWindowListener(this);
		
		this.currentPanel = (JPanel)this.mainWindow.getContentPane();
		
		this.history = new History();
		
		//Load initial menu panel.
		this.setCurrentPanel(new MenuPanel());
	}
	
	public static UIWindow getInstance()
	{
		if(currentInstance == null)
		{
			currentInstance = new UIWindow();
		}
		
		return currentInstance;
	}
	
	public static History getHistoryInstance()
	{
		return getInstance().history;
	}
	
	public final void setCurrentPanel(JPanel panel)
	{
		if(panel == null)
		{
			throw new IllegalArgumentException("panel is null");
		}
		
		this.currentPanel = panel;
		
		this.mainWindow.setContentPane(this.currentPanel);
		this.mainWindow.pack();
	}
	
	public JPanel getCurrentPanel()
	{
		return this.currentPanel;
	}
	
	public void show()
	{
		if(this.mainWindow != null && !this.mainWindow.isVisible())
		{
			this.mainWindow.setVisible(true);
		}
	}
	
	public void close()
	{
		if(this.mainWindow != null && this.mainWindow.isVisible())
		{
			//Acts like the red X (close) button on the form was clicked.
			this.mainWindow.getToolkit().getSystemEventQueue().postEvent(
					new WindowEvent(this.mainWindow, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) 
	{
		UIWindow program = UIWindow.getInstance();
		program.show();
	}

	@Override
	public void windowOpened(WindowEvent e)
	{		
		//Call history load function.
		try
		{
			this.history.loadHistory();
		}
		catch (Exception err)
		{
			System.err.println("Exception caught while loading history: "+err);
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		this.mainWindow.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		//Call history save function.
		try
		{
			this.history.saveHistory();
		}
		catch (Exception err)
		{
			System.err.println("Exception caught while saving history: "+err);
		}
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		//Not Used Currently.
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		//Not Used Currently.
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		//Not Used Currently.
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		//Not Used Currently.
	}

}
