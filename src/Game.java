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
		
		this.gameTimer = new Timer(SECOND, this); // call with delay (timeLimit)
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
		
		this.gameTimer = new Timer(SECOND, this); // call with delay (timeLimit)
		this.gameTimer.setActionCommand(TIMER_EVENT_COMMAND);
		
		this.isGameOver = false;
		
	}

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

				isPlayerOneTurn = false;
				this.getCurrentGridPanel().setTurnColor(PLAYER_TWO_COLOR);
				
				if(wasPointScored())
				{
					playerOne.increaseScore();
				}
				
				//Incase of AI being player two.
				if(this.playerTwo instanceof AI)
				{
					((AI)this.playerTwo).makeMove();
					isPlayerOneTurn = true;
				}
			}
			else
			{
				if(!playerTwo.makeMove(location))
				{
					System.err.println("Failed to make player two move");
				}

				isPlayerOneTurn = true;
				this.getCurrentGridPanel().setTurnColor(PLAYER_ONE_COLOR);
				
				if(wasPointScored())
				{
					playerTwo.increaseScore();
				}
			}

			if(this.isGridFull())
			{
				this.gameOver();
			}

			return true;
		}
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
				return playerOne.getUsername();
			else if(playerOne.getScore() < playerTwo.getScore())
				return playerTwo.getUsername();
			else
				return "";
		}
	}
<<<<<<< HEAD
	
	private void gameOver()
	{
		this.gameOver(false);
	}
	
	private void gameOver(boolean isForfiet)
	{
		isGameOver = true;
		//tells UI game is over
		GridPanel gridUI = this.getCurrentGridPanel();
		
		String usernameOne;
		String usernameTwo;
		if(!playerOne.isGuestPlayer() || !playerTwo.isGuestPlayer())
		{
			usernameOne = playerOne.getUsername();
			usernameTwo = playerTwo.getUsername();
		}
		else
		{
			usernameOne = "One";
			usernameTwo = "Two";
		}
		
		if(isForfiet)
		{
			//send UI forfeiter message
			if(isPlayerOneTurn)
			{
				//Player One forfieted
				if(!playerOne.isGuestPlayer() || !playerTwo.isGuestPlayer())
				{
					gridUI.displayEndGame("Player \""+this.playerOne.getUsername()+"\" forfieted the game."+
							"\nPlayer \""+this.playerTwo.getUsername()+"\" Wins!");
				}
				else
				{
					gridUI.displayEndGame("Player One forfieted the game."+
							"\nPlayer Two Wins!");
				}
			}
			else
			{
				//Player Two forfieted
				if(!playerOne.isGuestPlayer() || !playerTwo.isGuestPlayer())
				{
					gridUI.displayEndGame("Player \""+this.playerTwo.getUsername()+"\" forfieted the game."+
							"\nPlayer \""+this.playerOne.getUsername()+"\" Wins!");
				}
				else
				{
					gridUI.displayEndGame("Player Two forfieted the game."+
							"\nPlayer One Wins!");
				}
			}
		}
		else
		{
			//send UI getWinner()
			gridUI.displayEndGame("Player \""+this.getWinner()+"\" Wins!");
		}
	}
	
=======
>>>>>>> parent of a707db9... Added timer countdown code. Changed game over method implementation (gameOver() and gameOver(false) is for player win game over case and gameOver(true) is for forfeit game over case).
	public void forfeit()
	{
		if(isPlayerOneTurn)
			playerOne.increaseLosses(); // update stats in player object in list
		else
			playerTwo.increaseLosses();
		
		this.gameOver();
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
	public boolean wasPointScored()
	{
		// UI
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
<<<<<<< HEAD
	
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
=======
>>>>>>> parent of a707db9... Added timer countdown code. Changed game over method implementation (gameOver() and gameOver(false) is for player win game over case and gameOver(true) is for forfeit game over case).

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
		else
		{
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}
}
