
import java.awt.Color;

/**
 *
 * @author Jonathan
 * @version 1.0
 */
public class Stone
{
	private final Color color;
	private final Location location;
	
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
	
	public Location getLocation()
	{
		return this.location;
	}
}
