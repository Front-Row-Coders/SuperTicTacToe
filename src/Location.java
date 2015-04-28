/**
 * Holds location data for stones and can be used to find adjacent locations.
 * @author Jonathan
 * @version 1.0
 */
public class Location
{
	/**
	 * The x coordinate value (column)
	 */
	private final int xPos;
	/**
	 * The y coordinate value (row)
	 */
	private final int yPos;
	
						//  UP,   UP_RIGHT,  RIGHT, DOWN_RIGHT, DOWN,  DOWN_LEFT, LEFT, UP_LEFT
	/**
	 * The possible directions for adjacent location finding.
	 */
	public enum DIRECTION {NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};
	
	/**
	 * Creates a new location object with the specified location values.
	 * @param x The x coordinate value (column)
	 * @param y The y coordinate value (row)
	 */
	public Location(int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	
	/**
	 * Gets the x (column) position.
	 * @return 
	 */
	public int getXPos()
	{
		return this.xPos;
	}
	
	/**
	 * Gets the y (row) position.
	 * @return 
	 */
	public int getYPos()
	{
		return this.yPos;
	}
	
	/**
	 * Gets the location adjacent to this location in the specified direction.
	 * @param direction The direction to get the adjacent location from.
	 * @return The adjacent location or null if none exists in that direction.
	 */
	public Location getAdjacentLocation(DIRECTION direction)
	{
		switch(direction)
		{
			case NORTH:
				if(this.getYPos()-1 >= 0)
				{
					return new Location(this.getXPos(), this.getYPos()-1);
				}
			break;
			case NORTH_EAST:
				if(this.getYPos()-1 >= 0 && this.getXPos()-1 >= 0)
				{
					return new Location(this.getXPos()-1, this.getYPos()-1);
				}
			break;
			case EAST:
				if(this.getYPos()-1 >= 0 && this.getXPos()-1 >= 0)
				{
					return new Location(this.getXPos()-1, this.getYPos()-1);
				}
			break;
			case SOUTH_EAST:
				if(this.getYPos()+1 < GridPanel.GRID_HEIGHT && this.getXPos()-1 >= 0)
				{
					return new Location(this.getXPos()-1, this.getYPos()+1);
				}
			break;
			case SOUTH:
				if(this.getYPos()+1 < GridPanel.GRID_HEIGHT)
				{
					return new Location(this.getXPos(), this.getYPos()+1);
				}
			break;
			case SOUTH_WEST:
				if(this.getYPos()+1 < GridPanel.GRID_HEIGHT && this.getXPos()+1 < GridPanel.GRID_WIDTH)
				{
					return new Location(this.getXPos()+1, this.getYPos()+1);
				}
			break;
			case WEST:
				if(this.getXPos()+1 < GridPanel.GRID_WIDTH)
				{
					return new Location(this.getXPos()+1, this.getYPos());
				}
			break;
			case NORTH_WEST:
				if(this.getYPos()-1 >= 0 && this.getXPos()+1 < GridPanel.GRID_WIDTH)
				{
					return new Location(this.getXPos()+1, this.getYPos()-1);
				}
			break;
		}
		
		return null;
	}
}
