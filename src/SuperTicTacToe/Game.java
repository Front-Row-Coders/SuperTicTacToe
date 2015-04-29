package SuperTicTacToe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * This class is a control class for playing a game of Super Tic-Tac-Toe.
 * Attributes:
 *      AI_NAME: String
 *      DEFAULT_AI_SKILL_LEVEL: SkillLevel
 *      DEFAULT_PLAYER_TURN: boolean
 *      DEFAULT_TIME_LIMIT: int
 *      gameTimer: Timer
 *      GUEST_NAME: String
 *      initialTimeLimit: int
 *      isGameOver: boolean
 *      isPlayerOneTurn: boolean
 *      PLAYER_ONE_COLOR: Color
 *      PLAYER_TWO_COLOR: Color
 *      playerOne: Player
 *      playerTwo: Player
 *      SECOND: short
 *      timeLimit: int
 *      TIMER_EVENT_COMMAND: String
 * Methods:
 * 
 * @author alice
 */
public class Game implements ActionListener
{
	/**
	 * An instance of Player or AI representing one of two players in this
	 * game. If a Player instance, when it is playerOne's turn, Game sends
	 * the location of each move from UI to PlayerOne to be placed on the
	 * game board. If an AI instance, when it is playerOne's turn, Game
	 * asks PlayerOne to make a move.
	 */
	private final Player playerOne;
        
	/**
	 * An instance of Player or AI representing one of two players in this game.
	 * @see #playerOne
	 */
	private final Player playerTwo;
        
	/**
	 * A flag indicating whether it is playerOne's or playerTwo's turn to
	 * make a move in the game.
	 */
	private boolean isPlayerOneTurn;
        
	/**
	 * A flag indicating whether the game is over. Set to true when the
	 * time limit is reached, the game board becomes full, or a player
	 * forfeits or exits.
	 */
	private boolean isGameOver;
        
	/**
	 * A Timer object to enforce a time limit for each game. This time
	 * limit is determined by the value of DEFAULT_TIME_LIMIT. gameTimer is
	 * also used to update a countdown clock in GridPanel which displays
	 * the time remaining.
	 */
	private Timer gameTimer;
        
	/**
	 * The time limit to be enforced for this game.
	 */
	private int timeLimit;
    
	/**
	 * A variable to hold the length of one second. Used to set the delay
	 * between calls to actionPerformed for gameTimer and convert from milliseconds.
	 */
	public static final int SECOND = 1000;

	/**
	 * A variable to hold the length of one minute. Used to convert from milliseconds.
	 */
	public static final int MINUTE = SECOND * 60;

	/**
	 * A variable to hold the length of one hour. Used to convert from milliseconds.
	 */
	public static final int HOUR = MINUTE * 60;
	
	/**
	 * The message sent to actionPerformed to indicate a gameTimer event.
	 */
	public static final String TIMER_EVENT_COMMAND = "TIMER_EVENT";
        
	/**
	 * The default time limit for a game in milliseconds. Used to set timeLimit if no
	 * other value is given.
	 */
	public static final int DEFAULT_TIME_LIMIT = 30 * MINUTE;
        
	/**
	 * The default value for isPlayerOneTurn. Passed by the default
	 * constructor to the parametrized constructor.
	 */
	public static final boolean DEFAULT_PLAYER_TURN = true;
        
	/**
	 * The default skill level for the AI. 
	 */
	public static final SkillLevel DEFAULT_AI_SKILL_LEVEL = SkillLevel.Easy;
        
	/**
	 * The default stone color for playerOne. Used to set the color
	 * attribute of playerOne.
	 */
	public static final Color PLAYER_ONE_COLOR = Color.BLUE;
        
	/**
	 * The default stone color for playerTwo. Used to set the color
	 * attribute of playerTwo.
	 */
	public static final Color PLAYER_TWO_COLOR = Color.RED;
	
	//Reserved Usernames.
	/**
	 * Reserved username for the AI. Used to set the username attribute
	 * of an instance of AI. A player who is not the AI is not permitted
	 * to choose a string matching AI_NAME as a username.
	 */
	public static final String AI_NAME = "AI";
        
	/**
	 * Reserved username for a guest. Used to set the username of a 
	 * Player object if the player chooses to play the game as a guest. A
	 * player is not permitted to choose a string matching GUEST_NAME as a
	 * username.
	 */
	public static final String GUEST_NAME = "Guest";
	
	/**
	 * Default class constructor. Passes default values to the
	 * parametrized constructor for Single Player mode.
	 * @see #Game(java.lang.String, SkillLevel, boolean) 
	 */
	public Game()
	{
		this(GUEST_NAME, DEFAULT_AI_SKILL_LEVEL, DEFAULT_PLAYER_TURN);
	}
        
	/**
	 * Parametrized constructor to be called when the gameplay mode is
	 * Single Player. This constructor first initializes playerOne by 
	 * either creating a new Player with default values if username1 has
	 * the reserved value GUEST_NAME, or assigns a reference to the
	 * Player object corresponding with username1 from the History list.
	 * It then initializes the class attributes with default values.
	 * @param username1 the player ID of the human player
	 * @param skillLevel the skill level to set for the AI
	 * @param isPlayerOneTurn flag indicating which player goes first
	 */
	public Game(String username1, SkillLevel skillLevel, boolean isPlayerOneTurn)
	{
		// Call to History, find players, assign references to the existing
		// Player objects so updates to stats will be automatically reflected
		// in History 
		
		if(username1 == null)
		{
			throw new IllegalArgumentException("username1 is null");
		}
		
		if(username1.equals(GUEST_NAME))
		{
			this.playerOne = new Player(GUEST_NAME);
		}
		else
		{
			//Get the player one from History.
			Player player = UIWindow.getHistoryInstance().getPlayer(username1);
			if(player == null)
			{
				throw new IllegalArgumentException("username1 is invalid");
			}
			this.playerOne = player;
		}
		
		this.playerTwo = new AI(skillLevel);
		
		
		this.playerOne.setColor(PLAYER_ONE_COLOR);
		this.playerTwo.setColor(PLAYER_TWO_COLOR);
		
		this.timeLimit = DEFAULT_TIME_LIMIT;
		
		this.isPlayerOneTurn = isPlayerOneTurn;
		
		this.isGameOver = false;
		
		this.gameTimer = new Timer(SECOND, this); // call with delay (timeLimit)
		this.gameTimer.setActionCommand(TIMER_EVENT_COMMAND);
		this.gameTimer.start();
	}
	
	/**
	 * Parametrized constructor to be called when the gameplay mode is
	 * Multiplayer.
	 * @param username1 the player ID of the first human player
	 * @param username2 the player ID of the second human player
	 * @param isPlayerOneTurn flag indicating which player goes first
	 */
	public Game(String username1, String username2, boolean isPlayerOneTurn)
	{
		// Call to History, find players, assign references to the existing
		// Player objects so updates to stats will be automatically reflected
		// in History 
		// playerOne = new Player(username1);
		// playerTwo = new PLayer(username2); 
		
		if(username1 == null)
		{
			throw new IllegalArgumentException("username1 is null");
		}
		
		if(username1.equals(GUEST_NAME))
		{
			this.playerOne = new Player(GUEST_NAME);
		}
		else
		{
			//Get the player one from History.
			Player player = UIWindow.getHistoryInstance().getPlayer(username1);
			if(player == null)
			{
				throw new IllegalArgumentException("username1 is invalid");
			}
			this.playerOne = player;
			
		}
		
		if(username2 == null)
		{
			throw new IllegalArgumentException("username2 is null");
		}
		
		if(username2.equals(GUEST_NAME))
		{
			this.playerTwo = new Player(GUEST_NAME);
		}
		else
		{
			//Get the player one from History.
			Player player = UIWindow.getHistoryInstance().getPlayer(username2);
			if(player == null)
			{
				throw new IllegalArgumentException("username2 is invalid");
			}
			this.playerTwo = player;
		}
		
		this.playerOne.setColor(PLAYER_ONE_COLOR);
		this.playerTwo.setColor(PLAYER_TWO_COLOR);
		
		this.isPlayerOneTurn = isPlayerOneTurn;
		
		this.timeLimit = DEFAULT_TIME_LIMIT;
		
		this.isGameOver = false;
		
		this.gameTimer = new Timer(SECOND, this); // call with delay (timeLimit)
		this.gameTimer.setActionCommand(TIMER_EVENT_COMMAND);
		this.gameTimer.start();
	}
	
	public void performPostSetup()
	{
		if(!this.isPlayerOneTurn && this.playerTwo instanceof AI)
		{
			((AI)this.playerTwo).makeMove();
			this.togglePlayersTurn();
		}
	}
	
	private void togglePlayersTurn()
	{
		this.isPlayerOneTurn = !this.isPlayerOneTurn;
		if(this.isPlayerOneTurn)
		{
			this.getCurrentGridPanel().setTurnColor(PLAYER_ONE_COLOR);
		}
		else
		{
			this.getCurrentGridPanel().setTurnColor(PLAYER_TWO_COLOR);
		}
	}
	
	public Color getCurrentPlayersColor()
	{
		return (this.isPlayerOneTurn?PLAYER_ONE_COLOR:PLAYER_TWO_COLOR);
	}
	
	public boolean move(Location location)
	{
		if(!this.isMoveValid(location))
		{
			return false; // error
		}
		else
		{
			if(isPlayerOneTurn)
			{
				if(!playerOne.makeMove(location))
				{
					System.err.println("Failed to make player one move");
				}

				int numPointsScored = getNumPointsScored();
				if(numPointsScored > 0)
				{
					for(int i = 0; i < numPointsScored; i++)
					{
						playerOne.increaseScore();
					}
					this.getCurrentGridPanel().updateScores(playerOne.getScore(), playerTwo.getScore());
				}
				
				this.togglePlayersTurn();
				
				if(isGridFull())
				{
					gameOver();
				}
				else
				{
					//Incase of AI being player two.
					if(this.playerTwo instanceof AI)
					{
						((AI)this.playerTwo).makeMove();
						
						numPointsScored = getNumPointsScored();
						if(numPointsScored > 0)
						{
							for(int i = 0; i < numPointsScored; i++)
							{
								playerTwo.increaseScore();
							}
							this.getCurrentGridPanel().updateScores(playerOne.getScore(), playerTwo.getScore());
						}
						
						this.togglePlayersTurn();
					}
				}
			}
			else
			{
				if(!playerTwo.makeMove(location))
				{
					System.err.println("Failed to make player two move");
				}

				int numPointsScored = getNumPointsScored();
				if(numPointsScored > 0)
				{
					for(int i = 0; i < numPointsScored; i++)
					{
						playerTwo.increaseScore();
					}
					this.getCurrentGridPanel().updateScores(playerOne.getScore(), playerTwo.getScore());
				}
				
				this.togglePlayersTurn();
			}

			if(this.isGridFull())
			{
				this.gameOver();
			}

			return true;
		}
	}
	
	/**
	 * Gets the number of points scored this turn.
	 * @return The number of points scored.
	 */
	public int getNumPointsScored()
	{
		int score = 0;
		GridPanel panel = this.getCurrentGridPanel();
		if(panel != null)
		{
			Location placedStoneLoc = panel.getCurrentPlacedLocation();
			Stone placedStone = panel.getStone(placedStoneLoc);
			Color placedStoneColor = panel.getStone(placedStoneLoc).getColor();
			if(placedStoneLoc != null)
			{
				//Debug code
				//System.out.println("Testing placed "+placedStoneLoc.toString());
				
				Location.DIRECTION[] directions = Location.DIRECTION_VALUES;
				for(int i = 0; i < directions.length / 2; i++)
				{
					Location.DIRECTION direction = directions[i];
					ArrayList<Stone> countedStones = new ArrayList<>();
					countedStones.add(placedStone);
					//Debug code
					//System.out.println("Testing Direction: "+direction.name());
					Location oldOtherLoc = placedStoneLoc;
					Location otherLoc;
					int count = 1;  //Counts itself as one count alway
					//Check in the direction given
					while((otherLoc = oldOtherLoc.getAdjacentLocation(direction)) != null)
					{
						//Debug code
						//System.out.println("\tTesting Other "+otherLoc.toString() + " | Old Loc: "+oldOtherLoc.toString());
						
						Stone otherStone = panel.getStone(otherLoc);
						if(otherStone.getColor().equals(placedStoneColor))
						{
							count++;
							countedStones.add(otherStone);
							//Debug code
							//System.out.println("\t***Same color stone found. Count increased: "+count);
						}
						else
						{
							//Debug code
							//System.out.println("\tNo Point scored in this direction.");
							break;
						}
						oldOtherLoc = otherLoc;
					}
					
					Location.DIRECTION oppositeDirection = Location.getOppositeDirection(direction);
					oldOtherLoc = placedStoneLoc; //Reset to the placed stone
					//Debug code
					//System.out.println("\t///Testing Opposite Direction: "+oppositeDirection.name() + " ///");
					//Check in the opposite direction given
					while((otherLoc = oldOtherLoc.getAdjacentLocation(oppositeDirection)) != null)
					{
						//Debug code
						//System.out.println("\tTesting Other "+otherLoc.toString() + " | Old Loc: "+oldOtherLoc.toString());
						
						Stone otherStone = panel.getStone(otherLoc);
						if(otherStone.getColor().equals(placedStoneColor))
						{
							count++;
							countedStones.add(otherStone);
							//Debug code
							//System.out.println("\t***Same color stone found. Count increased: "+count);
						}
						else
						{
							//Debug code
							//System.out.println("\tNo Point scored in this direction.");
							break;
						}
						oldOtherLoc = otherLoc;
					}
					
					if(count >= 4 && count < 7)
					{
						int scoreSubtraction = 0;
						int alreadyCounted = 0;
						for(Stone stone : countedStones)
						{
							if(stone.isCounted())
							{
								//Debug code
								//System.out.println("\t+++Stone already counted: "+stone.getStoneLocation());
								
								alreadyCounted++;
							}
							else
							{
								stone.setIsCounted(true);
							}
						}
						if(alreadyCounted > 3)
						{
							//Debug code
							//System.out.println("\t---Score substraction due to 4-in-a-row counted already");
							
							scoreSubtraction++;
						}
						if(alreadyCounted > 4)
						{
							//Debug code
							//System.out.println("\t---Score substraction due to 5-in-a-row counted already");
							
							scoreSubtraction++;
						}
						if(alreadyCounted > 5)
						{
							//Debug code
							//System.out.println("\t---Score substraction due to 6-in-a-row counted already");
							
							scoreSubtraction++;
						}
						
						score += count - 3 - scoreSubtraction;
						
						//Debug code
						//System.out.println("\t***Point Scored! Count: "+count + " Score: "+score+" ***");
						
						//break; //Turns off multi-directional scoring
					}
					else if(count >= 7)
					{
						System.err.println("Invalid count found. It is greater than 6: "+count);
					}
				}
			}
		}
		System.out.println();
		System.out.println();
		return score;
	}

	public String getWinner()
	{
		if(!this.isGameOver)
		{
			//error
			return null;
		}
		else
		{
			if(playerOne.getScore() > playerTwo.getScore())
			{
				return playerOne.getUsername();
			}
			else if(playerOne.getScore() < playerTwo.getScore())
			{
				return playerTwo.getUsername();
			}
			else
			{
				return ""; // Tie.
			}
		}
	}
	
	private void gameOver()
	{
		this.gameOver(false);
	}
	
	private void gameOver(boolean isForfeit)
	{
		isGameOver = true;
		//tells UI game is over
		GridPanel gridUI = this.getCurrentGridPanel();
		
		String usernameOne;
		String usernameTwo;
		if(!playerOne.isGuestPlayer() || !playerTwo.isGuestPlayer())
		{
			usernameOne = "\""+playerOne.getUsername()+"\"";
			if(playerTwo instanceof AI)
			{
				usernameTwo = AI_NAME;
			}
			else
			{
				usernameTwo = "\""+playerTwo.getUsername()+"\"";
			}
		}
		else
		{
			usernameOne = "One";
			usernameTwo = "Two";
		}
		
		if(isForfeit)
		{
			//Forfeit.
			//send UI forfeiter message
			if(isPlayerOneTurn)
			{
				//Player One forfieted
				gridUI.displayEndGame("Player "+usernameOne+" forfeited the game."+
						"\nPlayer "+usernameTwo+" Wins!");
			}
			else
			{
				//Player Two forfieted
				gridUI.displayEndGame("Player "+usernameTwo+" forfeited the game."+
						"\nPlayer "+usernameOne+" Wins!");
			}
		}
		else
		{
			//Regular win.
			//send UI getWinner()
			String winner = this.getWinner();
			if(winner != null)
			{
				if(winner.length() > 0)
				{
					if(winner.equals(this.playerOne.getUsername()))
					{
						this.playerOne.increaseWins();
						this.playerTwo.increaseLosses();
						gridUI.displayEndGame("Player "+usernameOne+" Wins!");
					}
					else
					{
						this.playerOne.increaseLosses();
						this.playerTwo.increaseWins();
						gridUI.displayEndGame("Player "+usernameTwo+" Wins!");
					}
				}
				else
				{
					this.playerOne.increaseTies();
					this.playerTwo.increaseTies();
					gridUI.displayEndGame("The game ends in a tie!");
				}
			}
			else
			{
				System.err.println("Invalid winner state");
			}
		}
	}
	
	public void forfeit()
	{
		if(isPlayerOneTurn)
		{
			playerOne.increaseLosses(); // update stats in player object in list
			playerTwo.increaseWins();
		}
		else
		{
			playerOne.increaseWins();
			playerTwo.increaseLosses();
		}
		
		this.gameOver(true);
	}

	public boolean isMoveValid(Location location)
	{
		if(this.isGameOver)
		{
			return false;
		}
		//return(Location exists in board AND Location is  NOT taken by another stone);
		GridPanel panel = this.getCurrentGridPanel();
		if(panel != null)
		{
			return panel.isSpotOpen(location);
		}
		return false;
	}
	
	public boolean isGridFull()
	{
		GridPanel panel = this.getCurrentGridPanel();
		if(panel != null)
		{
			return panel.isGridFull();
		}
		return false;
	}
	

	public Player getPlayerOne()
	{
		return this.playerOne;
	}
	public Player getPlayerTwo()
	{
		return this.playerTwo; 
	}
	public boolean getIsPlayerOnesTurn()
	{
		 return this.isPlayerOneTurn;
	}
	public boolean getIsGameOver()
	{
	 	 return this.isGameOver;
	}
	
	private GridPanel getCurrentGridPanel()
	{
		if(UIWindow.getInstance().getCurrentPanel() instanceof GridPanel)
		{
			return (GridPanel)UIWindow.getInstance().getCurrentPanel();
		}
		
		return null;
	}
	
	/**
	 * Converts milliseconds to a string representation of HH::MM:SS.
	 * @param milliseconds 
	 * @return A string representation of the time left.
	 */
	public static String getTimeString(int milliseconds)
	{
		StringBuilder time = new StringBuilder();
		
		if(milliseconds / HOUR > 0)
		{
			if(milliseconds / HOUR < 10)
			{
				time.append("0");
			}
			time.append((milliseconds / HOUR));
			time.append(":");
			milliseconds = milliseconds % HOUR;
		}
		else
		{
			time.append("00:");
		}
		
		if(milliseconds / MINUTE > 0)
		{
			if(milliseconds / MINUTE < 10)
			{
				time.append("0");
			}
			time.append((milliseconds / MINUTE));
			time.append(":");
			milliseconds = milliseconds % MINUTE;
		}
		else
		{
			time.append("00:");
		}
		
		if(milliseconds / SECOND > 0)
		{
			if(milliseconds / SECOND < 10)
			{
				time.append("0");
			}
			time.append((milliseconds / SECOND));
			//time.append(":");
			//milliseconds = milliseconds % SECOND;
		}
		else
		{
			time.append("00");
		}
		
		return time.toString();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command == null)
		{
			throw new NullPointerException("action command is null");
		}
		
		if(command.equals(TIMER_EVENT_COMMAND))
		{
			if(!this.isGameOver)
			{
				//Do timer second event.
				timeLimit -= SECOND;

				if(timeLimit <= 0)
				{
					this.gameTimer.stop();
					this.getCurrentGridPanel().setTimerText("0:0:0");

					//Game over.
					this.gameOver();
				}
				else
				{
					this.getCurrentGridPanel().setTimerText(getTimeString(timeLimit));
				}
			}
			else
			{
				this.gameTimer.stop();
			}
		}
		else
		{
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}
}
