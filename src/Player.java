import java.awt.Color;
import javax.swing.JPanel;
 
/**
 * This class represents a player of Super Tic-Tac-Toe.
 * Attributes:
 *      color: Color
 *      losses: int
 *      score: int
 *      ties: int
 *      username: String
 *      wins: int
 * Methods:
 *      clearScore(): void
 *      getColor(): Color
 *      getLosses(): int
 *      getScore(): int
 *      getTies(): int
 *      getUsername(): String
 *      getWins(): int
 *      increaseLosses(): void
 *      increaseScore(): void
 *      increaseTies(): void
 *      increaseWins(): void
 *      makeMove(Location): boolean
 *      Player(String)
 *      Player(String, int, int, int)
 *      setColor(Color): void
 *      setLosses(int): void
 *      setScore(int): void
 *      setTies(int): void
 *      setWins(int): void   
 * @author Alice
 */
 public class Player
 {
        /**
         * The player ID of the Player object. Takes on reserved values "Guest"
         * if the player does not log in to a player account, "AI" if the
         * object is an instance of the AI subclass.
         */
 	private final String username;
        
        /**
         * The game stats of the player. Used to allow retrieval of
         * player history for any player.
         */
 	private int wins, losses, ties;
        
        /**
         * The current number of points the player has. Used to keep track
         * of the player's points during in-progress games and determine
         * the winner of each game. Set to 0 prior to the start of each game.
         */
        private int score;
        
        /**
         * The color of the player's game pieces. Used to paint the stones
         * placed by this player on the board.
         */
	private Color color;
	
        /**
         * Default class constructor.
         * @param username player ID of the player or reserved value
         */
 	Player(String username)
	{
 		this(username, 0, 0, 0);
 	}
 
        /**
         * Class constructor accepting values for the player username and
         * game stats. Called from History to create Player object from
         * stored values when that player logs in.
         * @param username player ID of the player or reserved value
         * @param wins     number of games won in the player's history
         * @param losses   number of games lost in the player's history
         * @param ties     number of games tied in the player's history
         */
 	Player(String username, int wins, int losses, int ties)
	{
		if(username == null)
		{
			throw new IllegalArgumentException("username is null");
		}
		
		if(username.length() <= 0)
		{
			throw new IllegalArgumentException("username's length is 0");
		}
		
		if(wins < 0 || losses < 0 || ties < 0)
		{
			throw new IllegalArgumentException("wins, losses or ties is less than 0 value");
		}
		
 		this.username = username;
 		this.wins = wins;
 		this.losses = losses;
 		this.ties = ties;
 		this.score = 0;
 	}
	
	/**
	 * Player method to make a move, called only by Game class after
         * validating move. Creates a new Stone object with the loc argument
         * and the color attribute, gets the current game board panel instance
         * and calls the panel's placeStone(Stone) method to place the stone.
         * 
         * Called by the AI subclass when that class has determined the next
         * move to be made by the AI.
	 * @param loc location of the stone to be placed
	 * @return    True if valid move (should always be true)
	 */
 	public boolean makeMove(Location loc)
	{
		if(loc == null)
		{
			throw new IllegalArgumentException("loc is null");
		}
		//Will create a Stone object that it will send to UI
		//Stone stone = new Stone(this.getColor(), loc);
		
		JPanel panel = UIWindow.getInstance().getCurrentPanel();
		GridPanel gamePanel;
		if(panel instanceof GridPanel)
		{
			gamePanel = (GridPanel)panel;
		}
		else
		{
			throw new IllegalStateException("Current panel is not an instance of GridPanel");
		}
		
		Stone stone = gamePanel.getStone(loc);
		stone.setColor(this.color);
		return true;
 	}
 
        public void clearScore()
	{
 		this.score = 0;
 	}
        
        public void increaseWins()
	{
 		this.wins += 1;
 	}
 	
 	public void increaseLosses()
	{
 		this.losses += 1;
 	}
 	
 	public void increaseTies()
	{
 		this.ties += 1;
 	}
	
	public void increaseScore()
	{
		this.score += 1;
	}
        
	public Color getColor()
	{
		return this.color;
	}
        
 	public String getUsername()
	{
 		return this.username;
 	}
 	
 	public int getWins()
	{
 		return this.wins;
 	}
 	
 	public int getLosses()
	{
 		return this.losses;
 	}
 	
 	public int getTies()
	{
 		return this.ties;
 	}
 	
 	public int getScore()
	{
 		return this.score;
 	}
 	
        public void setColor(Color color)
	{
		this.color = color;
	}
        
 	public void setWins(int wins)
	{
 		this.wins = wins;
 	}
 	
 	public void setLosses(int losses)
	{
 		this.losses = losses;
 	}
 	
 	public void setTies(int ties)
	{
 		this.ties = ties;
 	}
 	
 	public void setScore(int score)
	{
 		this.score = score;
 	}	
 }