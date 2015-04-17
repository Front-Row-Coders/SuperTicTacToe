
import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Jonathan
 * @version 1.0
 */
public class Stone extends JButton
{
	private static final long serialVersionUID = 1L;
	
	private final Color color;
	
	public static final Color emptyStateColor = Color.GRAY;
	
	private final Location location;
	
	public Stone(Location location)
	{
		this(Stone.emptyStateColor, location);
	}
	
	public Stone(Color color, Location location)
	{
		if(color == null)
		{
			throw new IllegalArgumentException("color is null");
		}
		if(location == null)
		{
			throw new IllegalArgumentException("location is null");
		}
		this.color = color;
		this.location = location;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public boolean isEmptySpot()
	{
		return this.color.equals(Stone.emptyStateColor);
	}
	
	public Location getStoneLocation()
	{
		return this.location;
	}
}
