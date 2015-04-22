/**
 *
 * @author Jonathan
 * @version 1.0
 */
import java.awt.Color;
public class AI extends Player
{
	private final SkillLevel skillLevel;
	
	public AI()
	{
		super(Game.AI_NAME);
		this.skillLevel = SkillLevel.Hard;
	}
	
	public AI(SkillLevel level)
	{
		super(Game.AI_NAME);
		this.skillLevel = level;
	}
	
	public SkillLevel getSkillLevel()
	{
		return this.skillLevel;
	}
	
	public void makeMove()
	{
		Location moveLoc = null;
		switch(this.skillLevel)
		{
			case Easy: 
				moveLoc = this.determineNextEasyMove();
			break;
			case Medium: 
				moveLoc = this.determineNextMediumMove();
			break;
			case Hard: 
				moveLoc = this.determineNextHardMove();
			break;
			default:
				System.err.println("Invalid SkillLevel entered in AI.");
		}
		
		this.makeMove(moveLoc);
	}
	
	private Location determineNextEasyMove()
	{
		Stone[][] grid = {{null, null, null, null, null, null},
					 {null, null, null, null, null, null},
					 {null, null, null, null, null, null},
					 {null, null, null, null, null, null},
					 {null, null, null, null, null, null},
					 {null, null, null, null, null, null}};

		Location nextMove;
		if((nextMove = checkSides(grid))!=null)
			return nextMove;
		else if((nextMove = checkCorners(grid))!=null)
			return nextMove;
		else if((nextMove = checkOppositeCorner(grid))!=null)
			return nextMove;
		else if((nextMove = checkScorePoint(grid))!=null)
			return nextMove;
		else if((nextMove = checkFork(grid))!=null)
			return nextMove;
		else if((nextMove = checkBlockOpponentFork(grid))!=null)
			return nextMove;
                     
		return nextMove;
	}
	
	private Location determineNextMediumMove()
	{
		//Placeholder for medium moves.
		return null;
	}
	
	private Location determineNextHardMove()
	{
		//Placeholder for hard moves.
		return null;
	}
        
	private Location checkSides(Stone[][] grid)
	{        
		 int i;
		 // Check non-corners of top side
		 for(i=1; i<grid[0].length-1; i++)
		 {
			 if(grid[0][i]==null)
				 return grid[0][i].getStoneLocation();
		 }

		 // Check left and right sides in between top and bottom sides
		 for(i=1; i<grid.length-1; i++)
		 {
			 if(grid[i][0]==null)
				 return grid[i][0].getStoneLocation();
			 else if(grid[i][grid[i].length-1]==null)
				 return grid[i][grid[i].length-1].getStoneLocation();
		 }

		 // Check non-corners of bottom side
		 for(i=1; i<grid[grid.length-1].length-1; i++)
		 {
			 if(grid[grid.length-1][i]==null)
				 return grid[grid.length-1][i].getStoneLocation();
		 }

		 return null;
	}
	
	private Location checkCorners(Stone[][] grid)
	{
		int i;

		for(i=0; i<grid.length; i++)
		{
			if(grid[i][0]==null)
				return grid[i][0].getStoneLocation();
			else if(grid[i][grid[i].length-1]==null)
				return grid[i][grid[i].length-1].getStoneLocation();
		}

		return null;
	}
	
	private Location checkOppositeCorner(Stone[][] grid)
	{
		/* Check each corner for opponent
		   (There's definitely a more concise way to do this. 
		   I'll try to make my brain work and change it later.) */

		if(grid[0][0]!=null && grid[0][0].getColor()!=this.getColor())
		{
			if(grid[grid.length-1][grid[0].length-1]==null)
				return grid[grid.length-1][grid[0].length-1].getStoneLocation();
		}
		else if(grid[0][0]!=null && grid[0][grid[0].length-1].getColor()!=this.getColor())
		{
			if(grid[grid.length-1][0]==null)
				return grid[grid.length-1][0].getStoneLocation();
		}
		else if(grid[grid.length-1][0]!=null && grid[grid.length-1][0].getColor()!=this.getColor())
		{
			if(grid[0][grid[0].length-1]==null)
				return grid[0][grid[0].length-1].getStoneLocation();
		}
		else if(grid[grid.length-1][grid[0].length-1]!=null && grid[grid.length-1][grid[0].length-1].getColor()!=this.getColor())
		{
			if(grid[0][0]==null)
				return grid[0][0].getStoneLocation();
		}
		return null;
	}
	
	private Location checkScorePoint(Stone[][] grid)
	{
		// if(at least 1 3-in-a-row in this.getColor())
		//      return location to complete that 4-in-a-row

		// TODO: account for 5/6 in a row

		return null;
	}
	
	private Location checkBlockOpponent(Stone[][] grid)
	{
		// if(at least 1 3-in-a-row in opponent's color)
		//      return location to complete that 4-in-a-row

		// TODO: account for 5/6 in a row

		return null;
	}
	
	private Location checkFork(Stone[][] grid)
	{
		Location nextMove;
		if((nextMove = checkFork(grid, this.getColor()))!=null)
			return nextMove;
		return null;
	}
	private Location checkFork(Stone[][] grid, Color playerColor)
	{
		/* For efficiency, can check if this is at least the (something)th
		   turn before checking any positions, since at least some turns would
		   need to have been taken before fork could be possible. 
		   Would need an attribute to track turns. */

		int i, j;

		/* Create copy of grid
		Going to come back and check if this is necessary 
		(i.e., does Java pass by reference/would grid be modified if I modified it here) */
		Stone[][] gridCopy = grid;

		// Check every empty position
		for(i=0; i<gridCopy.length; i++)
		{
			for(j=0; j<gridCopy.length; j++)
			{
				if(gridCopy[i][j]==null)
				{
					// Place stone on grid copy to check if this creates a fork
					gridCopy[i][j] = new Stone(playerColor, new Location(i,j));
					/* if(there are at least 2 3-in-a-rows for that color) // was a fork created
					 {    // Return the position that creates a fork
						  return grid[i][j].getLocation();
					 }
					*/
				}

			}
		}

		return null;
	}
        
        private int countThreeInARow(Stone[][] grid, Stone value){
            
            int counter = 0;
            
                // Rows
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 2; j++) {
                            if ((grid[i][j].getColor() == value.getColor()) && (grid[i][j].getColor() == grid[i][j + 1].getColor())
						&& (grid[i][j + 1].getColor() == grid[i][j + 2].getColor())) {
					counter++;

				}
			}
		}

		// Columns
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 2; j++) {
				if ((grid[j][i].getColor() == value.getColor()) && (grid[j][i].getColor() == grid[j + 1][i].getColor())
						&& (grid[j + 1][i].getColor() == grid[j + 2][i].getColor())) {
					counter++;

				}
			}
		}
                
                // Left to Right Diagonals
            		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i + 2 < grid.length && j + 2 < grid.length) {
					if ((grid[i][j].getColor() == value.getColor())&& (grid[i][j].getColor() == grid[i + 1][j + 1].getColor())
							&& (grid[i + 1][j + 1].getColor() == grid[i + 2][j + 2].getColor())) {
						counter++;
					}
				}
			}
		}
		
                // Right to Left Diagonals
		for (int i = 0; i < grid.length; i++) {
			for (int j = grid.length-1; j > 0; j--) {
				if (i + 2 < grid.length && j - 2 > -1) {
					if ((grid[i][j].getColor() == value.getColor()) && (grid[i][j].getColor() == grid[i + 1][j - 1].getColor())
							&& (grid[i + 1][j - 1].getColor() == grid[i + 2][j - 2].getColor())) {
						counter++;
					}
				}
			}
		}
		

            return counter;
        }
	
	private Location checkBlockOpponentFork(Stone[][] grid)
	{
		// Check if opponent can fork using checkFork with opponent color
		Location nextMove;
		Color opponentColor;

		if(this.getColor().equals(Game.PLAYER_ONE_COLOR))
			opponentColor = Game.PLAYER_TWO_COLOR;
		else
			opponentColor = Game.PLAYER_ONE_COLOR;

		if((nextMove = checkFork(grid, opponentColor))!=null)
			return nextMove;
		return null;
	}

	private GridPanel getCurrentGridPanel()
	{
		if(UIWindow.getInstance().getCurrentPanel() instanceof GridPanel)
		{
			return (GridPanel)UIWindow.getInstance().getCurrentPanel();
		}

		return null;
	}
}
