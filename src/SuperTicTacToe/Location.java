package SuperTicTacToe;
/**
 * Holds location data for stones and can be used to find adjacent locations.
 * @author Jonathan
 * @version 1.0
 */
public class Location
{
	/**
	 * The row coordinate value (y)
	 */
	private final int rowPos;
	/**
	 * The column coordinate value (x)
	 */
	private final int columnPos;
	
						//  UP,   UP_RIGHT,  RIGHT, DOWN_RIGHT, DOWN,  DOWN_LEFT, LEFT, UP_LEFT
	/**
	 * The possible directions for adjacent location finding.
	 */
	public enum DIRECTION {NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};
	
	/**
	 * An array of the direction values.
	 */
	public static final DIRECTION[] DIRECTION_VALUES = DIRECTION.values();
	
	/**
	 * The number of directions possible.
	 */
	public static final int NUM_OF_DIRECTIONS = DIRECTION.values().length;
	
	/**
	 * Creates a new location object with the specified location values.
	 * @param row The rowPos value (y)
	 * @param column The column value (x)
	 */
	public Location(int row, int column)
	{
		this.rowPos = row;
		this.columnPos = column;
	}
	
	/**
	 * Gets the row (y) position.
	 * @return 
	 */
	public int getRowPos()
	{
		return this.rowPos;
	}
	
	/**
	 * Gets the column (x) position.
	 * @return 
	 */
	public int getColumnPos()
	{
		return this.columnPos;
	}
	
	/**
	 * Gets the direction opposite of the given direction.
	 * @param direction The direction to get the opposite of.
	 * @return The opposite direction.
	 */
	public static DIRECTION getOppositeDirection(DIRECTION direction)
	{
		int oppositeValue = direction.ordinal() + 4;
		if(oppositeValue >= NUM_OF_DIRECTIONS)
		{
			oppositeValue -= NUM_OF_DIRECTIONS;
		}
		else if(oppositeValue < 0)
		{
			oppositeValue += NUM_OF_DIRECTIONS;
		}
		return DIRECTION_VALUES[oppositeValue];
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
				if(this.getRowPos()-1 >= 0)
				{
					return new Location(this.getRowPos()-1, this.getColumnPos());
				}
			break;
			case NORTH_EAST:
				if(this.getRowPos()-1 >= 0 && this.getColumnPos()-1 >= 0)
				{
					return new Location(this.getRowPos()-1, this.getColumnPos()-1);
				}
			break;
			case EAST:
				if(this.getColumnPos()-1 >= 0)
				{
					return new Location(this.getRowPos(), this.getColumnPos()-1);
				}
			break;
			case SOUTH_EAST:
				if(this.getRowPos()+1 < GridPanel.GRID_HEIGHT && this.getColumnPos()-1 >= 0)
				{
					return new Location(this.getRowPos()+1, this.getColumnPos()-1);
				}
			break;
			case SOUTH:
				if(this.getRowPos()+1 < GridPanel.GRID_HEIGHT)
				{
					return new Location(this.getRowPos()+1, this.getColumnPos());
				}
			break;
			case SOUTH_WEST:
				if(this.getRowPos()+1 < GridPanel.GRID_WIDTH && this.getColumnPos()+1 < GridPanel.GRID_HEIGHT)
				{
					return new Location(this.getRowPos()+1, this.getColumnPos()+1);
				}
			break;
			case WEST:
				if(this.getColumnPos()+1 < GridPanel.GRID_WIDTH)
				{
					return new Location(this.getRowPos(), this.getColumnPos()+1);
				}
			break;
			case NORTH_WEST:
				if(this.getRowPos()-1 >= 0 && this.getColumnPos()+1 < GridPanel.GRID_WIDTH)
				{
					return new Location(this.getRowPos()-1, this.getColumnPos()+1);
				}
			break;
		}
		
		return null;
	}
	
	/**
	 * Checks whether the given object is the same object, i.e. if this location
	 * and the given location have the same row and column value.
	 * @param other The object to check for equality to this one.
	 * @return True if equal, else false.
	 */
	@Override
	public boolean equals(Object other)
	{
		if(this == other)
		{
			return true;
		}
		
		if(other instanceof Location)
		{
			Location otherLoc = (Location)other;
			if(otherLoc.getRowPos() == this.getRowPos() && otherLoc.getColumnPos() == this.getColumnPos())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a hash code for this object based on row and column values.
	 * @return A hash code for this object.
	 */
	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 59 * hash + this.rowPos;
		hash = 59 * hash + this.columnPos;
		return hash;
	}
	
	/**
	 * Gets a string representation of this object. Ex: Location(0, 0)
	 * @return A string representation of this object.
	 */
	@Override
	public String toString()
	{
		return "Location("+this.getRowPos()+", "+this.getColumnPos()+")";
	}
}
