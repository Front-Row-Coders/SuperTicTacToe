package SuperTicTacToe.src;

/**
 *
 * @author Jonathan
 * @version 1.0
 */
public class AI extends Player
{
	private final SkillLevel skillLevel;
	
	public AI()
	{
		super(null);
		this.skillLevel = SkillLevel.Hard;
	}
	
	public AI(SkillLevel level)
	{
		super(null);
		this.skillLevel = level;
	}
	
	public SkillLevel getSkillLevel()
	{
		return this.skillLevel;
	}
	
	public void makeMove()
	{
		Location moveLoc = null;
		switch(this.skillLevel)
		{
			case Easy: 
				moveLoc = this.determineNextEasyMove();
			break;
			case Medium: 
				moveLoc = this.determineNextMediumMove();
			break;
			case Hard: 
				moveLoc = this.determineNextHardMove();
			break;
			default:
				System.err.println("Invalid SkillLevel entered in AI.");
		}
		
		this.makeMove(moveLoc);
	}
	
	private Location determineNextEasyMove()
	{
		//Placeholder for easy moves.
		return null;
	}
	
	private Location determineNextMediumMove()
	{
		//Placeholder for easy moves.
		return null;
	}
	
	private Location determineNextHardMove()
	{
		//Placeholder for easy moves.
		return null;
	}
}
