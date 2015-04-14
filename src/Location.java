package SuperTicTacToe.src;

/**
 *
 * @author Jonathan
 * @version 1.0
 */
public class Location
{
	private final int xPos;
	private final int yPos;
	
	public Location(int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	
	public int getXPos()
	{
		return this.xPos;
	}
	
	public int getYPos()
	{
		return this.yPos;
	}
}
