
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
		this(GUEST_NAME, DEFAULT_AI_SKILL_LEVEL, DEFAULT_PLAYER_TURN);
	}
	
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
}