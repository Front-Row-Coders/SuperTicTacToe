package SuperTicTacToe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A game board stone that the player can place/click.
 * @author Jonathan
 * @version 1.0
 */
public class Stone extends JButton implements ActionListener
{
	/**
	 * Serial code for serialization (not used).
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The default width of a stone.
	 */
	public static final int DEFAULT_WIDTH = 36;
	/**
	 * The default height of a stone.
	 */
	public static final int DEFAULT_HEIGHT = 36;
	
	/**
	 * The color of this stone currently. The stone will be displayed as this color.
	 */
	private Color color;
	
	/**
	 * The empty state color for Stones.
	 */
	public static final Color EMPTY_STATE_COLOR = Color.GRAY;
	
	/**
	 * The location on the game board grid for this stone.
	 */
	private Location location;
	
	/**
	 * The status of this stone being placed/clicked on the game board grid
	 * this turn or not.
	 */
	private boolean wasPlacedThisTurn;
	
	/**
	 * The status of this stone being counted in a scoring before or not.
	 */
	private boolean isCounted;
	
	/**
	 * Creates a Stone with the empty state color and no location.
	 * Necessary for NetBeans UI placement which only uses a default constructor 
	 * for components. The location should be set after initialization.
	 */
	public Stone()
	{
		this(Stone.EMPTY_STATE_COLOR, null);
	}
	
	/**
	 * Creates a Stone with the empty state color and specific location.
	 * @param location The location on the game board grid of this stone.
	 */
	public Stone(Location location)
	{
		this(Stone.EMPTY_STATE_COLOR, location);
	}
	
	/**
	 * Creates a Stone with a specific color and specific location.
	 * @param color The color of this stone.
	 * @param location The location of this stone on the game board grid.
	 */
	public Stone(Color color, Location location)
	{
		super();
		if(color == null)
		{
			color = Stone.EMPTY_STATE_COLOR;
		}
		/*
		if(location == null)
		{
			throw new IllegalArgumentException("location is null");
		}
		*/
		this.color = color;
		this.location = location;
		this.wasPlacedThisTurn = false;
		this.isCounted = false;
		
		//Perform necessary component setup.
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setText("");
		
		if(this.color.equals(EMPTY_STATE_COLOR))
		{
			setFocusable(true);
			setEnabled(true);
		}
		else
		{
			setFocusable(false);
			setEnabled(false);
		}
		
		addActionListener(this);
	}

	/**
	 * UI method for getting the preferred size of this component.
	 * @return The preferred size of this component.
	 */
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.getWidth(), this.getHeight());
	}

	/**
	 * UI method for painting this component on the UI. This method
	 * paints the Stone the color of this stone after calling its super method
	 * which paints this as a button.
	 * @param g 
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D graphics = (Graphics2D)g;
		
		//Draw the color. 
		graphics.setColor(this.getColor());
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	/**
	 * Gets the status if this Stone was placed this turn.
	 * @return True if it was placed this turn, else false.
	 */
	public boolean isPlacedThisTurn()
	{
		return this.wasPlacedThisTurn;
	}
	
	/**
	 * Sets whether this Stone was placed this turn or not.
	 * @param placedStatus The status of this stone.
	 */
	public void setPlacedThisTurn(boolean placedStatus)
	{
		this.wasPlacedThisTurn = placedStatus;
	}
	
	/**
	 * Gets whether this Stone was counted before in scoring or not.
	 * @return The status of this stone.
	 */
	public boolean isCounted()
	{
		return this.isCounted;
	}
	
	/**
	 * Sets whether this Stone was counted in scoring already or not.
	 * @param counted 
	 */
	public void setIsCounted(boolean counted)
	{
		this.isCounted = counted;
	}
	
	/**
	 * Sets the color of this Stone and then forces repaint of the UI for this
	 * component. 
	 * @param color The color for this stone. If null, sets the Stone to
	 * the empty state color.
	 */
	public void setColor(Color color)
	{
		if(color == null)
		{
			this.color = Stone.EMPTY_STATE_COLOR;
		}
		else
		{
			this.color = color;
		}
		
		this.repaint();
	}
	
	/**
	 * Gets the current color of this Stone.
	 * @return The color of this Stone.
	 */
	public Color getColor()
	{
		return this.color;
	}
	
	/**
	 * Gets whether this stone is an empty spot or not.
	 * @return True if this Stone's color is equal to the empty state color, 
	 * else false.
	 */
	public boolean isEmptySpot()
	{
		return this.color.equals(Stone.EMPTY_STATE_COLOR);
	}
	
	/**
	 * Sets the location of this Stone. Important to be called when using
	 * NetBeans UI designing as that require a default constructor to be called.
	 * @param loc The location on the game board grid of this Stone.
	 */
	public void setStoneLocation(Location loc)
	{
		this.location = loc;
	}
	
	/**
	 * Gets the location of this Stone on the game board grid.
	 * @return The location of this Stone.
	 */
	public Location getStoneLocation()
	{
		return this.location;
	}

	/**
	 * The actionPerformed event handler for this Stone. If this Stone is
	 * clicked, then it send the location for this Stone to Game through its
	 * move method.
	 * @param e ActionEvent information.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this)
		{
			JPanel panel = UIWindow.getInstance().getCurrentPanel();
			if(panel instanceof GridPanel)
			{
				GridPanel gridPanel = (GridPanel)panel;
				
				gridPanel.getGame().move(this.location);
			}
			else
			{
				throw new IllegalStateException("Invalid program state");
			}
		}
	}
}
