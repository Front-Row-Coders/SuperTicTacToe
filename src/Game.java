
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game implements ActionListener
{

	private final Player playerOne;
	private final Player playerTwo;
	private boolean isPlayerOneTurn;
	private boolean isGameOver;
	private Timer gameTimer;
	private final int initialTimeLimit;
	private int timeLimit;

	public static final short SECOND = 1000;
	public static final String TIMER_EVENT_COMMAND = "TIMER_EVENT";
	public static final int DEFAULT_TIME_LIMIT = 30*60;
	public static final boolean DEFAULT_PLAYER_TURN = true;
	public static final SkillLevel DEFAULT_AI_SKILL_LEVEL = SkillLevel.Easy;
	public static final Color PLAYER_ONE_COLOR = Color.BLUE;
	public static final Color PLAYER_TWO_COLOR = Color.RED;
	//Reserved Usernames.
	public static final String AI_NAME = "AI";
	public static final String GUEST_NAME = "Guest";
	
	public Game()
	{
		this(GUEST_NAME, DEFAULT_AI_SKILL_LEVEL, DEFAULT_TIME_LIMIT, DEFAULT_PLAYER_TURN);
	}
	public Game(String username1, SkillLevel skillLevel, int timeLimit, boolean isPlayerOneTurn)
	{
		// Call to History, find players, assign references to the existing
		// Player objects so updates to stats will be automatically reflected
		// in History 
		
		if(username1 == null)
			throw new IllegalArgumentException("username1 is null");
		
		if(username1.equals(GUEST_NAME))
		{
			this.playerOne = new Player(GUEST_NAME);
			this.playerOne.setColor(PLAYER_ONE_COLOR);
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
		
		if(timeLimit < 0)
		{
			throw new IllegalArgumentException("timeLimit is below 0");
		}
		
		this.playerTwo = new AI(skillLevel);
		
		this.timeLimit = this.initialTimeLimit = timeLimit;
		
		this.gameTimer = new Timer(SECOND, this); // call with delay (timeLimit)
		this.gameTimer.setActionCommand(TIMER_EVENT_COMMAND);
		
		this.isPlayerOneTurn = isPlayerOneTurn;
		
		this.isGameOver = false;
		
	}
	
	public Game(String username1, String username2, boolean isPlayerOneTurn, int timeLimit)
	{
		// Call to History, find players, assign references to the existing
		// Player objects so updates to stats will be automatically reflected
		// in History 
		// playerOne = new Player(username1);
		// playerTwo = new PLayer(username2); 
		
		if(username1 == null)
			throw new IllegalArgumentException("username1 is null");
		
		if(timeLimit < 0)
		{
			throw new IllegalArgumentException("timeLimit is below 0");
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
			throw new IllegalArgumentException("username2 is null");
		
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
		
		this.isPlayerOneTurn = isPlayerOneTurn;

		this.timeLimit = this.initialTimeLimit = timeLimit;
		
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
				
	
	public boolean move(Location location)
	{
		if(!this.isMoveValid(location))
			return false; // error
		else
		{
			if(isPlayerOneTurn)
			{

				playerOne.makeMove(location);

				isPlayerOneTurn = false;
				
				if(wasPointScored())
					playerOne.increaseScore();
				
			}
			else
			{
				playerTwo.makeMove(location);

				isPlayerOneTurn = true;
				
				if(wasPointScored());
					playerTwo.increaseScore();

			}

			if(isGridFull())
				gameOver();

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
	public void forfeit()
	{
		if(isPlayerOneTurn)
			playerOne.increaseLosses(); // update stats in player object in list
		else
			playerTwo.increaseLosses();
		
		gameOver();
	}

	public boolean isMoveValid(Location location)
	{
		//return(Location exists in board AND Location is  NOT taken by another stone);
		return false;
	}
	
	public boolean isGridFull()
	{
		// UI
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
				
			}
		}
		
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}