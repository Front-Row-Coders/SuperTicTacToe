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
         * game stats. Called from History to create Player object representing
         * a player whose information is stored in a file.
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
		Stone stone = new Stone(this.getColor(), loc);
		
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
		
		return gamePanel.placeStone(stone);
 	}
 
        /**
         * Clears the score attribute. Used to ensure this player has 0 points
         * at the start of every game.
         */
        public void clearScore()
	{
 		this.score = 0;
 	}
        
        /**
         * Increments the wins attribute. Called from Game to update the number
         * of wins this player has if, at the end of a game, Game determines
         * that this player won.
         */
        public void increaseWins()
	{
 		this.wins += 1;
 	}
 	
        /**
         * Increments the losses attribute. Serves same purpose as
         * increaseWins() if this player loses a game.
         * @see #increaseWins() 
         */
 	public void increaseLosses()
	{
 		this.losses += 1;
 	}
 	
        /**
         * Increments the ties attribute. Serves same purpose as
         * increaseWins() if this player ties in a game. This will be called
         * for both players in the game if a tie occurs.
         * @see #increaseWins() 
         */
 	public void increaseTies()
	{
 		this.ties += 1;
 	}
	
        /**
         * Increments the score attribute. Called from Game during a game
         * after any turn during which this player scores a point (see Game
         * for details).
         * @see Game#wasPointScored() 
         */
	public void increaseScore()
	{
		this.score += 1;
	}
        
        /**
         * Getter function for color.
         * @return this player's stone color
         */
	public Color getColor()
	{
		return this.color;
	}
        
        /**
         * Getter function for username.
         * @return this player's player ID
         */
 	public String getUsername()
	{
 		return this.username;
 	}
 	
        /**
         * Getter function for wins.
         * @return the number of games this player has won
         */
 	public int getWins()
	{
 		return this.wins;
 	}
 	
        /**
         * Getter function for losses.
         * @return the number of games this player has lost
         */
 	public int getLosses()
	{
 		return this.losses;
 	}
 	
        /**
         * Getter function for ties.
         * @return the number of games this player has tied in
         */
 	public int getTies()
	{
 		return this.ties;
 	}
 	
        /**
         * Getter function for score.
         * @return the number of points this player has. Should return zero
         *         if a game is not in progress.
         */
 	public int getScore()
	{
 		return this.score;
 	}
 	
        /**
         * Setter function for color.
         * @param color the color to set for this player's stones
         */
        public void setColor(Color color)
	{
		this.color = color;
	}
        
        /**
         * Setter function for wins.
         * @param wins 
         */
 	public void setWins(int wins)
	{
 		this.wins = wins;
 	}
 	
        /**
         * Setter function for losses.
         * @param losses 
         */
 	public void setLosses(int losses)
	{
 		this.losses = losses;
 	}
 	
        /**
         * Setter function for ties.
         * @param ties 
         */
 	public void setTies(int ties)
	{
 		this.ties = ties;
 	}
 	
        /**
         * Setter function for score.
         * @param score 
         */
 	public void setScore(int score)
	{
 		this.score = score;
 	}	
 }