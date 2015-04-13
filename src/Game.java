public class Game implements ActionListener
{

	private Player playerOne;
	private Player playerTwo;
	private boolean isPlayerOneTurn;
	private boolean isGameOver;
	private Timer gameTimer;
	private int timeLimit;


	public Game()
	{

		playerOne = new Player();

		playerTwo = new Player();

		isPlayerOneTurn = true;

		isGameOver = false;

		// timeLimit = some default

		gameTimer = new Timer(); // call with delay (timeLimit)

	}
	public Game(string username1, SkillLevel skillLevel, int timeLimit, boolean isPlayerOneTurn)
	{
		// Call to History, find players, assign references to the existing
		// Player objects so updates to stats will be automatically reflected
		// in History 

		playerTwo = new AI(skillLevel);

		this.timeLimit = timeLimit;

		gameTimer = new Timer(); // call with delay (timeLimit)
		
		this.isPlayerOneTurn = isPlayerOneTurn;

		isGameOver = false;
		
	}
	public Game(string username1, string username2, boolean isPlayerOneTurn, int timeLimit)
	{
		// Call to History, find players, assign references to the existing
		// Player objects so updates to stats will be automatically reflected
		// in History 
		// playerOne = new Player(username1);
		// playerTwo = new PLayer(username2); 

		this.isPlayerOneTurn = isPlayerOneTurn;

		this.timeLimit = timeLimit; 
		
		gameTimer = new Timer(); // call with delay (timeLimit)

		isGameOver = false;
		
	}

	private void gameOver()
	{
		isGameOver = true;
		//tells UI game is over
		//send UI getWinner()
	}	
				
	
	public boolean Move(Location location)
	{
		if(!isMoveValid)
			return false; // error
		else
		{
			if(isPlayerOneTurn)
			{

				playerOne.makeMove(location);

				isPlayerOneTurn = false;
				
				if(wasPointScored())
					playerOne.increasePoint();
				
			}
			else
			{
				playerTwo.makeMove(location);

				isPlayerOneTurn = true;
				
				if(wasPointScored());
					playerTwo.increasePoint(); 

			}

			if(isGridFull())
				gameOver();

			return true;
		}
	}

	public String getWinner()
	{
		if(!isGameOver)
			//error
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
	}
	public boolean isGridFull()
	{
		// UI
	}
	public boolean wasPointScored()
	{
		// UI
	}

	public Player getPlayerOne()
	{
		return playerOne;
	}
	public Player getPlayerTwo()
	{
		return playerTwo; 
	}
	public boolean getIsPlayerOnesTurn()
	{
		 return isPlayerOnesTurn;
	}
	public boolean getIsGameOver()
	{
	 	 return isGameOver;
	}
}