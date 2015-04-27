
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 * @version 1.0
 */
public class Stone extends JButton implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WIDTH = 36;
	public static final int DEFAULT_HEIGHT = 36;
	
	private Color color;
	
	public static final Color EMPTY_STATE_COLOR = Color.GRAY;
	
	private Location location;
	
	private boolean wasPlacedThisTurn;
	
	public Stone()
	{
		this(Stone.EMPTY_STATE_COLOR, null);
	}
	
	public Stone(Location location)
	{
		this(Stone.EMPTY_STATE_COLOR, location);
	}
	
	public Stone(Color color, Location location)
	{
		super();
		if(color == null)
		{
			throw new IllegalArgumentException("color is null");
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

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.getWidth(), this.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D graphics = (Graphics2D)g;
		
		//Draw the color. 
		graphics.setColor(this.getColor());
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public boolean isPlacedThisTurn()
	{
		return this.wasPlacedThisTurn;
	}
	
	public void setPlacedThisTurn(boolean placedStatus)
	{
		this.wasPlacedThisTurn = placedStatus;
	}
	
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
	
	public Color getColor()
	{
		return this.color;
	}
	
	public boolean isEmptySpot()
	{
		return this.color.equals(Stone.EMPTY_STATE_COLOR);
	}
	
	public void setStoneLocation(Location loc)
	{
		this.location = loc;
	}
	
	public Location getStoneLocation()
	{
		return this.location;
	}

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
