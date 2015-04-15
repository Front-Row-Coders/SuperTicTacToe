
import java.awt.Color;

/****

	Player.java

 ****/
 
 public class Player
 {
 	private final String username;
 	private int wins, losses, ties, score;
	private Color color;
	
 	Player(String username)
	{
 		this(username, 0, 0, 0);
 	}
 
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
 
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	/**
	 * Player method to make a move, called only by Game class after validating move.
	 * Note: should be done in a generic way so AI can use it for stone placement after determining a location.
	 * @param loc The location to make the move at.
	 * @return True if valid move (should always be true)
	 */
 	public boolean makeMove(Location loc)
	{
		if(loc == null)
		{
			throw new IllegalArgumentException("loc is null");
		}
		//Will create a Stone object that it will send to UI
 		if(false){	/* Add code for Location */
 			
 			
 			return true;
 		}
 		else
 			return false;
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
 	
 	public void clearScore()
	{
 		this.score = 0;
 	}	
 }