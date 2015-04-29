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
	/**
	 * The main/only JFrame (window) for this program.
	 */
	private final JFrame mainWindow;
	
	/**
	 * The current panel being displayed on this JFrame/window.
	 * Meant to be used with children classes of UIPanel but can be any JPanel.
	 */
	private JPanel currentPanel;
	
	/**
	 * The instance of history.
	 */
	private final History history;
	
	/**
	 * This holds the running instance of the UIWindow.
	 */
	private static UIWindow currentInstance;
	
	/**
	 * Creates a instance of UIWindow to display on the computer. Does not 
	 * make the window visible after construction. Must call show afterwards.
	 * @see #show()
	 */
	private UIWindow()
	{
		this.mainWindow = new JFrame("Super Tic-Tac-Toe");
		
		this.mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.mainWindow.addWindowListener(this);
		this.mainWindow.setResizable(false);
		
		this.currentPanel = (JPanel)this.mainWindow.getContentPane();
		
		this.history = new History();
		
		//Load initial menu panel.
		this.setCurrentPanel(new MenuPanel());
	}
	
	/**
	 * Gets the only instance of UIWindow.
	 * @return The instance of UIWindow.
	 */
	public static UIWindow getInstance()
	{
		if(currentInstance == null)
		{
			currentInstance = new UIWindow();
		}
		
		return currentInstance;
	}
	
	/**
	 * Get the only instance of History.
	 * @return The instance of History.
	 */
	public static History getHistoryInstance()
	{
		return getInstance().history;
	}
	
	/**
	 * Sets the current panel to be displayed and resized (packs) the window 
	 * based on the new panel.
	 * @param panel The panel to be displayed on the window.
	 */
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
	
	/**
	 * Gets the current panel being displayed on the window.
	 * @return 
	 */
	public JPanel getCurrentPanel()
	{
		return this.currentPanel;
	}
	
	/**
	 * Makes the window visible to the user if not already visible.
	 */
	public void show()
	{
		if(this.mainWindow != null && !this.mainWindow.isVisible())
		{
			this.mainWindow.setVisible(true);
		}
	}
	
	/**
	 * Causes the window to begin closing by sending an event to it as if the
	 * window's red close button has been clicked. Allows window event's to be
	 * called. 
	 */
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
	 * Performs any necessary setup the needs to run after the window has been 
	 * shown to the user. Currently this is just the loading of history.
	 * @param e 
	 */
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
	
	/**
	 * Performs any necessary closing operations that need to be run before
	 * the window closes completely. Currently just causes the window to be
	 * disposed thus completely closing the window.
	 * @param e 
	 */
	@Override
	public void windowClosing(WindowEvent e)
	{
		this.mainWindow.dispose();
	}

	/**
	 * Performs any necessary closing operations that need to be run before the
	 * program terminates but after the window has closed. Currently performs
	 * the save history operation.
	 * @param e 
	 */
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

	/**
	 * Performs any operations necessary when the window is minimized. Currently
	 * not used.
	 * @param e 
	 */
	@Override
	public void windowIconified(WindowEvent e)
	{
		//Not Used Currently.
	}

	/**
	 * Performs any operations necessary when the window is unminimized. Currently
	 * not used.
	 * @param e 
	 */
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		//Not Used Currently.
	}

	/**
	 * Performs any operations necessary when the window gains focus. Currently
	 * not used.
	 * @param e 
	 */
	@Override
	public void windowActivated(WindowEvent e)
	{
		//Not Used Currently.
	}

	/**
	 * Performs any operations necessary when the window losses focus. Currently
	 * not used.
	 * @param e 
	 */
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		//Not Used Currently.
	}

	/**
	 * The entry point for this program.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) 
	{
		UIWindow program = UIWindow.getInstance();
		program.show();
	}
}
