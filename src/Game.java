import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
         * between calls to actionPerformed for gameTimer.
         */
	public static final short SECOND = 1000;
        
        /**
         * The message sent to actionPerformed to indicate a gameTimer event.
         */
	public static final String TIMER_EVENT_COMMAND = "TIMER_EVENT";
        
        /**
         * The default time limit for a game. Used to set timeLimit if no
         * other value is given.
         */
	public static final int DEFAULT_TIME_LIMIT = 30*60;
        
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
         * It then initializes playerTwo as the AI and sets up the gameTimer.
         * Finally, if it is the AI's turn, Game calls AI to make a move. 
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
		
		this.gameTimer = new Timer(SECOND, this);
		this.gameTimer.setActionCommand(TIMER_EVENT_COMMAND);
		
		this.isPlayerOneTurn = isPlayerOneTurn;
		
		this.isGameOver = false;
		
		if(!this.isPlayerOneTurn)
		{
			((AI)this.playerTwo).makeMove();
			this.isPlayerOneTurn = true;
		}
	}
	
        /**
         * Parametrized constructor to be called when the gameplay mode is
         * Multiplayer. Similar to the Single Player mode constructor, except
         * that both players are human and will be initialized either with
         * objects from History or as new Players if they play as guests.
         * @param username1 the player ID of the first human player
         * @param username2 the player ID of the second human player
         * @param isPlayerOneTurn flag indicating which player goes first
         */
	public Game(String username1, String username2, boolean isPlayerOneTurn)
	{	
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
		
		this.gameTimer = new Timer(SECOND, this); // call with delay (timeLimit)
		this.gameTimer.setActionCommand(TIMER_EVENT_COMMAND);
		
		this.isGameOver = false;
		
	}

        /**
         * Close-out method to be called when the game has ended. This method
         * sets the flag isGameOver to true and notifies the UI to end the game
         * and display the winner.
         */
	private void gameOver()
	{
		isGameOver = true;
		//tells UI game is over
		//send UI getWinner()
	}
        
        public Color getCurrentPlayersColor()
	{
		return (this.isPlayerOneTurn?PLAYER_ONE_COLOR:PLAYER_TWO_COLOR);
	}
				
	/**
         * Method to handle player moves. Called from UI with the location of 
         * a game board position clicked by a human player. This method checks
         * whether the move is valid; if it is, asks the appropriate player
         * (based on the isPlayerOneTurn flag) to make the move; if one of the
         * players is the AI, calls the AI to make a move; updates each
         * player's score if necessary. If the grid becomes full, calls
         * gameOver().
         * @param location the position selected by a human player through the
         *                 GridPanel
         * @return a flag indicating whether the move has successfully been made
         */
	public boolean move(Location location)
	{
		if(!this.isMoveValid(location))
		{
			return false;
		}
		else
		{
			if(isPlayerOneTurn)
			{
				if(!playerOne.makeMove(location))
				{
					System.err.println("Failed to make player one move");
				}

                                // Set this to false once playerOne has completed the move.
				isPlayerOneTurn = false; 
                                this.getCurrentGridPanel().setTurnColor(PLAYER_TWO_COLOR);
				
                                // If playerOne scored a point, update its score attribute.
				if(wasPointScored())
				{
					playerOne.increaseScore();
				}
				
                                // If the game board has become full as a result of the move, call gameOver().
                                if(this.isGridFull())
                                {
                                    this.gameOver();
                                }
                                else // If there are still empty positions on the game board, check if the second player is the AI.
                                {
                                    // If the second player is the AI, call it to make a move.
                                    if(this.playerTwo instanceof AI)
                                    {
                                            ((AI)this.playerTwo).makeMove();
                                            
                                            // Set this back to true once playerTwo has completed the move.
                                            isPlayerOneTurn = true;
                                            this.getCurrentGridPanel().setTurnColor(PLAYER_ONE_COLOR);

                                            // If playerTwo scored a point, update its score attribute.
                                            if(wasPointScored())
                                            {
                                                playerTwo.increaseScore();
                                            }
                                    }
                                }
			}
			else
			{
				if(!playerTwo.makeMove(location))
				{
					System.err.println("Failed to make player two move");
				}

                                // Set this back to true once playerTwo has completed the move.
				isPlayerOneTurn = true;
				
                                // If playerTwo scored a point, update its score attribute.
				if(wasPointScored())
				{
					playerTwo.increaseScore();
				}
			}

                        // If the game board has become full as a result of the move, call gameOver().
			if(this.isGridFull())
			{
				this.gameOver();
			}

			return true;
		}
	}

        /**
         * A method which compares the score attributes of the players to
         * determine the winner of the game. Used by gameOver() to
         * send the winner to be displayed by the UI.
         * @return the player ID of the winner, or the empty string if there
         *         has been a tie
         */
	public String getWinner()
	{
                
		if(!this.isGameOver)
		{
                        // If the game has not ended, return null to signify an error (there cannot be a winner yet).
			return null;
		}
                else 
		{
                        // If the game is over, return the player ID of the winner, or the empty string if there was a tie.
                    
			if(playerOne.getScore() > playerTwo.getScore())
				return playerOne.getUsername();
			else if(playerOne.getScore() < playerTwo.getScore())
				return playerTwo.getUsername();
			else
				return "";
		}
	}
        
        /**
         * A method to be called if a player forfeits the game. This method
         * increases the losses attribute of the player who forfeited and
         * calls gameOver().
         */
	public void forfeit()
	{
		if(isPlayerOneTurn)
			playerOne.increaseLosses();
		else
			playerTwo.increaseLosses();
		
		this.gameOver();
	}

        /**
         * A method to determine if a move at the location parameter is valid.
         * First checks if the game is over; if not, it gets the
         * current GridPanel and asks it to check that the spot is open.
         * @param location the location of the desired move
         * @return a flag indicating whether the move at location can be made
         * @see GridPanel#isSpotOpen(Location) 
         */
	public boolean isMoveValid(Location location)
	{
		if(this.isGameOver)
		{
			return false;
		}

		GridPanel panel = this.getCurrentGridPanel();
		if(panel != null)
		{
			return panel.isSpotOpen(location);
		}
		return false;
	}
	
        /**
         * A method to check if the game board is full (has no empty spots
         * left). Gets the current GridPanel and asks it to check the grid.
         * @return a flag indicating whether the grid is full
         * @see GridPanel#isGridFull() 
         */
	public boolean isGridFull()
	{
		GridPanel panel = this.getCurrentGridPanel();
		if(panel != null)
		{
			return panel.isGridFull();
		}
		return false;
	}
        
        /**
         * A method to check if either player has scored a point. Used by the
         * move() function to check if the appropriate player's score 
         * attribute needs to be updated.
         * @return a flag indicating whether a point has been scored
         */
	public boolean wasPointScored()
	{
		// UI
		return false;
	}

        /**
         * Getter function for playerOne.
         * @return the object representing the first player
         */
	public Player getPlayerOne()
	{
		return this.playerOne;
	}
        
        /**
         * Getter function for playerTwo.
         * @return the object representing the second player
         */
	public Player getPlayerTwo()
	{
		return this.playerTwo; 
	}
        
        /**
         * Getter function for isPlayerOneTurn.
         * @return a flag indicating whether it is playerOne's turn
         */
	public boolean getIsPlayerOneTurn()
	{
		 return this.isPlayerOneTurn;
	}
        
        /**
         * Getter function for isGameOver.
         * @return a flag indicating whether the game is over
         */
	public boolean getIsGameOver()
	{
	 	 return this.isGameOver;
	}
	
        /**
         * Gets the current gridPanel. Allows isMoveValid() and isGridFull()
         * to use GridPanel methods.
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
			//Do timer second event.
			timeLimit--;
			
			if(timeLimit <= 0)
			{
				//Game over.
				this.gameOver();
			}
		}
		
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}