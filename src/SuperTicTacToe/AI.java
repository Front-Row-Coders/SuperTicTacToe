package SuperTicTacToe;

import java.awt.Color;

/**
 *This class extends the Player class to include decision-making
 * capabilities for the AI in Single Player gameplay mode.
 * Attributes:
 *      skillLevel: SkillLevel
 * Methods:
 *      AI()
 *      AI(SkillLevel)
 *      checkBlockOpponent(Stone[][]): Location
 *      checkBlockOpponentFork(Stone[][]): Location
 *      checkCorners(Stone[][]): Location
 *      checkFork(Stone[][]): Location
 *      checkFork(Stone[][], Color): Location
 *      checkOppositeCorner(Stone[][]): Location
 *      checkSides(Stone[][]): Location  
 *      checkScorePoint(Stone[][]): Location
 *      determineNextEasyMove(Stone[][]): Location
 *      determineNextHardMove(Stone[][]): Location
 *      determineNextMediumMove(Stone[][]): Location
 *      getCurrentGridPanel: GridPanel
 *      getSkillLevel(): skillLevel
 *      makeMove(): void
 * @author Jonathan
 * @author Alice
 * @version 1.0
 * @see #super
 */
public class AI extends Player
{
	/**
	 * An enum indicating the mode of game play. Used to determine which
	 * decision-making function will be called to determine the next move
	 * of the AI.
	 * @see SkillLevel
	 */
	private final SkillLevel skillLevel;
	
	/**
	 *Default class constructor. 
	 */
	public AI()
	{
		super(Game.AI_NAME);
		this.skillLevel = SkillLevel.Hard;
	}
	
	/**
	 *Class constructor accepting parameter for skill level of the AI.
	 * @param level specified AI skill level
	 */
	public AI(SkillLevel level)
	{
		super(Game.AI_NAME);
		this.skillLevel = level;
	}
	
	/**
	 *Overloads Player makeMove(Location) function. Gets the game board
	 * (gridSpots) from the current instance of gridPanel through 
	 * getCurrentGridPanel(). Based on skillLevel, passes the board to the 
	 * appropriate function to determine the next (Easy-, Medium-, or 
	 * Hard-mode) AI move. Passes move location to makeMove(Location) to be
	 * placed on the board.
	 * @see Player#makeMove(Location) 
	 */
	public void makeMove()
	{ 
		Location moveLoc = null;

		Stone [][] grid;// = new Stone[GridPanel.GRID_HEIGHT][GridPanel.GRID_WIDTH];
		GridPanel panel = this.getCurrentGridPanel();

		if(panel != null)
		{   
			grid = panel.getGridSpots();

			switch(this.skillLevel)
			{
					case Easy: 
							moveLoc = this.determineNextEasyMove(grid);
					break;
					case Medium: 
							moveLoc = this.determineNextMediumMove(grid);
					break;
					case Hard: 
							moveLoc = this.determineNextHardMove(grid);
					break;
					default:
							System.err.println("Invalid SkillLevel entered in AI.");
			}

			this.makeMove(moveLoc); // call to makeMove(Location loc) defined in Player
		}
		//else
		  //  return;
	}
        
	/**
         * Determines next move when skillLevel is set to Easy (Easy-mode).
         * Passes the current game board to each move-checking function until a
         * non-null location is returned (a valid move is found) in the 
         * Easy-mode priority order.
         * @param grid the current game board
         * @return location of next Easy-mode move
         */
	protected Location determineNextEasyMove(Stone[][] grid)
	{
                Location nextMove;   
                                 
                 if((nextMove = checkScorePoint(grid))!=null)
                     return nextMove;
                 else if((nextMove = checkOppositeCorner(grid))!=null)
                     return nextMove;
                 else if((nextMove = checkCorners(grid))!=null)
                     return nextMove;
                 else if((nextMove = checkSides(grid))!=null)
                     return nextMove;
                 else if((nextMove = checkBlockOpponent(grid))!=null)
                     return nextMove;
                 else if((nextMove = checkFork(grid))!=null)
                     return nextMove;
                 else if((nextMove = checkBlockOpponentFork(grid))!=null)
                     return nextMove;
                 else if((nextMove = findEmptySpot(grid))!=null)
                     return nextMove; 
                 else
                     return null;                
	}
	
        /**
         * Determines next move when skillLevel is set to Medium (Medium-mode).
         * Identical to determineNextEasyMove() except for the order in which
         * move-checking functions are called (Medium-mode priority order).
         * @param grid the current game board
         * @see #determineNextEasyMove() 
         * @return location of next Medium-mode move
         */
	protected Location determineNextMediumMove(Stone[][] grid)
	{
		Location nextMove;
                
                if((nextMove = checkScorePoint(grid))!=null)
                     return nextMove;
                else if((nextMove = checkBlockOpponent(grid))!=null)
                     return nextMove;
                else if((nextMove = checkOppositeCorner(grid))!=null)
                     return nextMove;
                else if((nextMove = checkCorners(grid))!=null)
                     return nextMove;
                else if((nextMove = checkFork(grid))!=null)
                     return nextMove;
                else if((nextMove = checkBlockOpponentFork(grid))!=null)
                     return nextMove;
                else if((nextMove = checkSides(grid))!=null)
                     return nextMove;
                else if((nextMove = findEmptySpot(grid))!=null)
                     return nextMove; 
                 else
                     return null; 
	}
	
        /**
         * Determines next move when skillLevel is set to Hard (Hard-mode).
         * Identical to determineNextEasyMove() except for the order in which
         * move-checking functions are called (Hard-mode priority order).
         * @param grid the current game board
         * @see #determineNextEasyMove()
         * @return location of next Hard-mode move
         */
	protected Location determineNextHardMove(Stone[][] grid)
	{
                Location nextMove;

                if((nextMove = checkScorePoint(grid))!=null)
                     return nextMove;
                else if((nextMove = checkBlockOpponent(grid))!=null)
                         return nextMove;
                else if((nextMove = checkFork(grid))!=null)
                         return nextMove;
                else if((nextMove = checkBlockOpponentFork(grid))!=null)
                     return nextMove;
                else if((nextMove = checkOppositeCorner(grid))!=null)
                     return nextMove;
                else if((nextMove = checkCorners(grid))!=null)
                     return nextMove;
                else if((nextMove = checkSides(grid))!=null)
                     return nextMove;
                else if((nextMove = findEmptySpot(grid))!=null)
                     return nextMove; 
                else
                     return null; 
	}
        
        protected Location findEmptySpot(Stone[][] grid)
        {
            for (Stone[] grid1 : grid) {
                for (Stone grid11 : grid1) {
                    if (grid11.isEmptySpot()) {
                        return grid11.getStoneLocation();
                    }
                }
            }
            
//            //Testing
//            for(int i=0; i<4; i++)
//            {
//                for(int j=0; j<grid.length; j++)
//                {
//                    if(grid[i][j].isEmptySpot())
//                        return grid[i][j].getStoneLocation();
//                }
//            }
            
            return null;
        }
        
        /**
         * Checks whether there is an empty spot on any side of the board,
         * excluding corners. 
         * @param grid the current game board
         * @return location of an empty side spot. If there are no empty side
         *         spots, returns null.
         */
	protected Location checkSides(Stone[][] grid)
	{        
		 int i;
                 
		 // Check non-corners of top side
		 for(i=1; i<grid[0].length-1; i++)
		 {
			 if(grid[0][i].isEmptySpot())
				 return grid[0][i].getStoneLocation();
		 }

		 // Check left and right sides in between top and bottom corners
		 for(i=1; i<grid.length-1; i++)
		 {
			 if(grid[i][0].isEmptySpot())
				 return grid[i][0].getStoneLocation();
			 else if(grid[i][grid[i].length-1].isEmptySpot())
				 return grid[i][grid[i].length-1].getStoneLocation();
		 }

		 // Check non-corners of bottom side
		 for(i=1; i<grid[grid.length-1].length-1; i++)
		 {
			 if(grid[grid.length-1][i].isEmptySpot())
				 return grid[grid.length-1][i].getStoneLocation();
		 }

		 return null;
	}
	
        /**
         * Checks whether there is an empty corner spot on the board.
         * @param grid the current game board
         * @return location of an empty corner spot. If there are no empty
         *         corner spots, returns null.
         */
	protected Location checkCorners(Stone[][] grid)
	{
		if(grid[0][0].isEmptySpot())
			return grid[0][0].getStoneLocation();
		else if(grid[0][grid.length-1].isEmptySpot())
			return grid[0][grid.length-1].getStoneLocation();
		else if(grid[grid.length-1][0].isEmptySpot())
			return grid[grid.length-1][0].getStoneLocation();
		else if(grid[grid.length-1][grid.length-1].isEmptySpot())
			return grid[grid.length-1][grid.length-1].getStoneLocation();

		return null;
	}
	
        /**
         * Checks whether there is an empty corner spot opposite from a corner
         * spot occupied by the opponent.
         * @param grid the current game board
         * @return location of an empty corner spot which is opposite to a
         *         corner occupied by the opponent. If there is no such spot,
         *         returns null.
         */
	protected Location checkOppositeCorner(Stone[][] grid)
	{
		if(!grid[0][0].isEmptySpot() && !grid[0][0].getColor().equals(this.getColor()))
		{
			if(grid[grid.length-1][grid[0].length-1].isEmptySpot())
				return grid[grid.length-1][grid[0].length-1].getStoneLocation();
		}
		else if(!grid[0][0].isEmptySpot() && !grid[0][grid[0].length-1].getColor().equals(this.getColor()))
		{
			if(grid[grid.length-1][0].isEmptySpot())
				return grid[grid.length-1][0].getStoneLocation();
		}
		else if(!grid[grid.length-1][0].isEmptySpot() && !grid[grid.length-1][0].getColor().equals(this.getColor()))
		{
			if(grid[0][grid[0].length-1].isEmptySpot())
				return grid[0][grid[0].length-1].getStoneLocation();
		}
		else if(!grid[grid.length-1][grid[0].length-1].isEmptySpot() && !grid[grid.length-1][grid[0].length-1].getColor().equals(this.getColor()))
		{
			if(grid[0][0].isEmptySpot())
				return grid[0][0].getStoneLocation();
		}
		return null;
	}
	
        /**
         * Checks whether a point can be scored by the AI. Looks for 
         * configurations of 3, 4, or 5 stones in a row which can be extended
         * to score a point.
         * @param grid the current game board
         * @return location of a spot which will complete a scoring
         *         configuration. If there is no such spot, returns null.
         */
	protected Location checkScorePoint(Stone[][] grid)
	{
		Location move = null;
		
		if((move=findScoringOpportunity(grid, this.getColor()))!=null)
			return move;

		return move;
	}
	
        /**
         * Checks whether a point can be scored by the opponent. Similarly to
         * checkScorePoint, looks for scoring configurations, but in the
         * opponent's color.
         * @param grid the current game board
         * @return location of a spot which will block the opponent from
         *         scoring a point by occupying a spot the opponent needs
         *         to complete a scoring configuration. If there is no such
         *         spot, returns null.
         * @see #checkScorePoint(Stone[][]) 
         */
	protected Location checkBlockOpponent(Stone[][] grid)
	{
                Color opponentColor;

		if(this.getColor().equals(Game.PLAYER_ONE_COLOR))
			opponentColor = Game.PLAYER_TWO_COLOR;
		else
			opponentColor = Game.PLAYER_ONE_COLOR;
                
		Location move = null;
		
		if((move=findScoringOpportunity(grid, opponentColor))!=null)
			return move;

		return move;
	}
	
        /**
         * Checks whether the AI can fork (create two different potential 
         * scoring configurations on the board). Calls the auxiliary function
         * checkFork(Stone[][], Color) with the AI's color.
         * @param grid the current game board
         * @return location of a spot which will create a fork. If there is no
         *         such spot, returns null.
         * @see #checkFork(Stone[][], java.awt.Color) 
         */
	protected Location checkFork(Stone[][] grid)
	{
		Location nextMove;
		if((nextMove = checkFork(grid, this.getColor()))!=null)
			return nextMove;
		return null;
	}
        
        /**
         * Checks whether the player with the color playerColor can fork
         * (create two different potential scoring configurations on the board).
         * Auxiliary for checkFork(Stone[][]) and
         * checkBlockOpponentFork(Stone[][]). Creates a dummy copy of the game
         * board and checks every empty spot by placing a stone and counting
         * scoring configurations. A forking location is found if a stone
         * placement results in at least 2 scoring configurations on the board.
         * @param grid the current game board
         * @param playerColor color of the player trying to fork
         * @return location of a spot which will create a fork. If there is no
         *         such spot, returns null.
         * @see #checkFork(Stone[][]) 
         * @see #checkBlockOpponentFork(Stone[][]) 
         */
	protected Location checkFork(Stone[][] grid, Color playerColor)
	{
		/* For efficiency, can check if this is at least the (something)th
		   turn before checking any positions, since at least some turns would
		   need to have been taken before fork could be possible. 
		   Would need an attribute to track turns. */

		int i, j;

		// Check every empty position
		for(i=0; i<grid.length; i++)
		{
			for(j=0; j<grid.length; j++)
			{
				if(grid[i][j].isEmptySpot())
				{
					// Place stone on grid copy to check if this creates a fork
					grid[i][j].setColor(playerColor);
					
					if(countScoringOpportunities(grid, playerColor)>=2) // was a fork created
						return grid[i][j].getStoneLocation();
                    
					// if fork was not created
					grid[i][j].setColor(null); // paint the stone empty
				}

			}
		}

		return null;
	}
        
        protected static Location findScoringOpportunity(Stone[][] grid, Color color) 
		{

		//int returnValue;

		// Rows
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 3; j++) {
                            
                            
				if ((grid[i][j].getColor().equals(color)) || (color.equals(grid[i][j + 1].getColor()) || (grid[i][j + 2].getColor().equals(color)) || (grid[i][j + 3].getColor().equals(color)))) {

					if ((grid[i][j].isEmptySpot()) && (grid[i][j + 1].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color)) && (grid[i][j + 3].getColor().equals(color))) {
						return grid[i][j].getStoneLocation(); // grid location [i][j].getColor()
					} else if ((grid[i][j + 1].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color)) && (grid[i][j + 3].getColor().equals(color))) {
						return grid[i][j+1].getStoneLocation(); // grid location [i][j+1]
					} else if ((grid[i][j + 2].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i][j + 1].getColor().equals(color)) && (grid[i][j + 3].getColor().equals(color))) {
						return grid[i][j+2].getStoneLocation(); // grid location [i][j+2]
					} else if ((grid[i][j + 3].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i][j + 1].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color))) {
						return grid[i][j+3].getStoneLocation(); // grid location [i][j+3]
					}
				}
                                    
			}
		}

		// Columns
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 3; j++) {

                            if ((grid[j][i].getColor().equals(color)) || (color.equals(grid[j + 1][i].getColor())) || (grid[j + 2][i].getColor().equals(color)) || (grid[j + 3][i].getColor().equals(color))) {

					if ((grid[j][i].isEmptySpot()) && (grid[j + 1][i].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color)) && (grid[j + 3][i].getColor().equals(color))) {
						return grid[j][i].getStoneLocation(); // grid location [i][j].getColor()
					} else if ((grid[j + 1][i].isEmptySpot()) && (grid[j][i].getColor().equals(color)) && (grid[j + 2][i].getColor().equals(color)) && (grid[j + 3][i].getColor().equals(color))) {
						return grid[j+1][i].getStoneLocation(); // grid location [i][j+1]
					} else if ((grid[j + 2][i].isEmptySpot()) && (grid[j][i].getColor().equals(color)) && (grid[j + 1][i].getColor().equals(color)) && (grid[j + 3][i].getColor().equals(color))) {
						return grid[j+2][i].getStoneLocation(); // grid location [i][j+3]
					} else if ((grid[j + 3][i].isEmptySpot()) && (grid[j][i].getColor().equals(color)) && (grid[j + 1][i].getColor().equals(color)) && (grid[j + 2][i].getColor().equals(color))) {
						return grid[j+3][i].getStoneLocation(); // grid location [i][j+3]
					}
				}
			}
		}

		// Left to Right Diagonals
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i + 3 < grid.length && j + 3 < grid.length) {
                                    
                                if ((grid[i][j].getColor().equals(color)) || (color.equals(grid[i + 1][j + 1].getColor()) || (grid[i + 2][j + 2].getColor().equals(color)) || (grid[i + 3][j + 3].getColor().equals(color)))) {

					if ((grid[i][j].isEmptySpot()) && (grid[i + 1][j + 1].getColor().equals(color)) && (grid[i + 2][j + 2].getColor().equals(color)) && (grid[i + 3][j + 3].getColor().equals(color))) {
						return grid[i][j].getStoneLocation(); // grid location [i][j].getColor()
					} else if ((grid[i + 1][j + 1].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 2][j + 2].getColor().equals(color)) && (grid[i + 3][j + 3].getColor().equals(color))) {
						return grid[i+1][j+1].getStoneLocation(); // grid location [i][j+1]
					} else if ((grid[i + 2][j + 2].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j + 1].getColor().equals(color)) && (grid[i + 3][j + 3].getColor().equals(color))) {
						return grid[i+2][j+2].getStoneLocation(); // grid location [i][j+3]
					} else if ((grid[i + 3][j + 3].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j + 1].getColor().equals(color)) && (grid[i + 2][j + 2].getColor().equals(color))) {
						return grid[i+3][j+3].getStoneLocation(); // grid location [i][j+3]
					}
				}
                               
                            }
			}
		}

		// Right to Left Diagonals
		for (int i = 0; i < grid.length; i++) {
			for (int j = grid.length - 1; j > 0; j--) {
				if (i + 3 < grid.length && j - 3 > -1) {
                                    
                                     if ((grid[i][j].getColor().equals(color)) || (color.equals(grid[i + 1][j - 1].getColor()) || (grid[i + 2][j - 2].getColor().equals(color)) || (grid[i + 3][j - 3].getColor().equals(color)))) {

					if ((grid[i][j].isEmptySpot()) && (grid[i + 1][j - 1].getColor().equals(color)) && (grid[i + 2][j - 2].getColor().equals(color)) && (grid[i + 3][j - 3].getColor().equals(color))) {
						return grid[i][j].getStoneLocation(); // grid location [i][j].getColor()
					} else if ((grid[i + 1][j - 1].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 2][j - 2].getColor().equals(color)) && (grid[i + 3][j - 3].getColor().equals(color))) {
						return grid[i+1][j-1].getStoneLocation(); // grid location [i][j+1]
					} else if ((grid[i + 2][j - 2].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j - 1].getColor().equals(color)) && (grid[i + 3][j - 3].getColor().equals(color))) {
						return grid[i+2][j-2].getStoneLocation(); // grid location [i][j+3]
					} else if ((grid[i + 3][j - 3].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j - 1].getColor().equals(color)) && (grid[i + 2][j - 2].getColor().equals(color))) {
						return grid[i+3][j-3].getStoneLocation(); // grid location [i][j+3]
					}
				}
                            }
			}
		}

		return null;
	}
                
        protected static int countScoringOpportunities(Stone[][] grid, Color color) {

		int counter = 0;

		// Rows
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 3; j++) {
                            
                            
				if ((grid[i][j].getColor().equals(color)) || (color.equals(grid[i][j + 1].getColor()) || (grid[i][j + 2].getColor().equals(color)) || (grid[i][j + 3].getColor().equals(color)))) {

					if ((grid[i][j].isEmptySpot()) && (grid[i][j + 1].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color)) && (grid[i][j + 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i][j + 1].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color)) && (grid[i][j + 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i][j + 2].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i][j + 1].getColor().equals(color)) && (grid[i][j + 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i][j + 3].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i][j + 1].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color))) {
						counter++;
					}
				}
                                    
			}
		}

		// Columns
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 3; j++) {

                            if ((grid[j][i].getColor().equals(color)) || (color.equals(grid[j + 1][i].getColor()) || (grid[j + 2][i].getColor().equals(color)) || (grid[j + 3][i].getColor().equals(color)))) {

					if ((grid[j][i].isEmptySpot()) && (grid[j + 1][i].getColor().equals(color)) && (grid[i][j + 2].getColor().equals(color)) && (grid[j + 3][i].getColor().equals(color))) {
						counter++;
					} else if ((grid[j + 1][i].isEmptySpot()) && (grid[j][i].getColor().equals(color)) && (grid[j + 2][i].getColor().equals(color)) && (grid[j + 3][i].getColor().equals(color))) {
						counter++;
					} else if ((grid[j + 2][i].isEmptySpot()) && (grid[j][i].getColor().equals(color)) && (grid[j + 1][i].getColor().equals(color)) && (grid[j + 3][i].getColor().equals(color))) {
						counter++;
					} else if ((grid[j + 3][i].isEmptySpot()) && (grid[j][i].getColor().equals(color)) && (grid[j + 1][i].getColor().equals(color)) && (grid[j + 2][i].getColor().equals(color))) {
						counter++;
					}
				}
			}
		}

		// Left to Right Diagonals
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i + 3 < grid.length && j + 3 < grid.length) {
                                    
                                if ((grid[i][j].getColor().equals(color)) || (color.equals(grid[i + 1][j + 1].getColor()) || (grid[i + 2][j + 2].getColor().equals(color)) || (grid[i + 3][j + 3].getColor().equals(color)))) {

					if ((grid[i][j].isEmptySpot()) && (grid[i + 1][j + 1].getColor().equals(color)) && (grid[i + 2][j + 2].getColor().equals(color)) && (grid[i + 3][j + 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i + 1][j + 1].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 2][j + 2].getColor().equals(color)) && (grid[i + 3][j + 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i + 2][j + 2].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j + 1].getColor().equals(color)) && (grid[i + 3][j + 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i + 3][j + 3].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j + 1].getColor().equals(color)) && (grid[i + 2][j + 2].getColor().equals(color))) {
						counter++;
					}
				}
                               
                            }
			}
		}

		// Right to Left Diagonals
		for (int i = 0; i < grid.length; i++) {
			for (int j = grid.length - 1; j > 0; j--) {
				if (i + 3 < grid.length && j - 3 > -1) {
                                    
                                     if ((grid[i][j].getColor().equals(color)) || (color.equals(grid[i + 1][j - 1].getColor()) || (grid[i + 2][j - 2].getColor().equals(color)) || (grid[i + 3][j - 3].getColor().equals(color)))) {

					if ((grid[i][j].isEmptySpot()) && (grid[i + 1][j - 1].getColor().equals(color)) && (grid[i + 2][j - 2].getColor().equals(color)) && (grid[i + 3][j - 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i + 1][j - 1].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 2][j - 2].getColor().equals(color)) && (grid[i + 3][j - 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i + 2][j - 2].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j - 1].getColor().equals(color)) && (grid[i + 3][j - 3].getColor().equals(color))) {
						counter++;
					} else if ((grid[i + 3][j - 3].isEmptySpot()) && (grid[i][j].getColor().equals(color)) && (grid[i + 1][j - 1].getColor().equals(color)) && (grid[i + 2][j - 2].getColor().equals(color))) {
						counter++;
					}
				}
                            }
			}
		}

		return counter;
	}
	
        /**
         * Checks whether the opponent can fork (create two different potential
         * scoring configurations on the board). Similarly to
         * checkFork(Stone[][]), calls the auxiliary checkFork(Stone[][], Color)
         * function, but with the opponent's color.
         * @param grid the current game board
         * @return location of a spot which will block a forking opportunity
         *         for the opponent by occupying a spot the opponent needs to create
         *         a fork. If there is no such spot, returns null.
         * @see #checkFork(Stone[][], java.awt.Color) 
         */
	protected Location checkBlockOpponentFork(Stone[][] grid)
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

        /**
         * Gets the current gridPanel. Allows AI to get the current game board,
         * which is stored in the gridSpots attribute of gridPanel.
         * @return the current gridPanel instance. If the current panel is not
         *         an instance of gridPanel (that is, there is no game currently in
         *         progress), returns null.
         */
	private GridPanel getCurrentGridPanel()
	{
		if(UIWindow.getInstance().getCurrentPanel() instanceof GridPanel)
		{
			return (GridPanel)UIWindow.getInstance().getCurrentPanel();
		}

		return null;
	}
        
        /**
         *Getter method for skillLevel.
         * @return the skill level
         */
        public SkillLevel getSkillLevel()
	{
		return this.skillLevel;
	}
        
         /*
            Count all three in a rows for 
        */
        protected int countThreeInARow(Stone[][] grid, Color color){
            
            int counter = 0;
            
                // Rows
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 2; j++) {
                            if ((grid[i][j].getColor().equals(color)) && (grid[i][j].getColor() == grid[i][j + 1].getColor())
						&& (grid[i][j + 1].getColor() == grid[i][j + 2].getColor())) {
					counter++;

				}
			}
		}

		// Columns
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length - 2; j++) {
				if ((grid[j][i].getColor().equals(color)) && (grid[j][i].getColor() == grid[j + 1][i].getColor())
						&& (grid[j + 1][i].getColor() == grid[j + 2][i].getColor())) {
					counter++;

				}
			}
		}
                
                // Left to Right Diagonals
            		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i + 2 < grid.length && j + 2 < grid.length) {
					if ((grid[i][j].getColor().equals(color))&& (grid[i][j].getColor() == grid[i + 1][j + 1].getColor())
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
					if ((grid[i][j].getColor().equals(color)) && (grid[i][j].getColor() == grid[i + 1][j - 1].getColor())
							&& (grid[i + 1][j - 1].getColor() == grid[i + 2][j - 2].getColor())) {
						counter++;
					}
				}
			}
		}
		

            return counter;
        }
}
