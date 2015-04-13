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
		switch(this.skillLevel)
		{
			case Easy: 
				this.determineNextEasyMove();
			break;
			case Medium: 
				this.determineNextMediumMove();
			break;
			case Hard: 
				this.determineNextHardMove();
			break;
			default:
				System.err.println("Invalid SkillLevel entered in AI.");
		}
	}
	
	@Override
	public boolean makeMove(Location loc)
	{
		this.makeMove();
		return true;
	}
	
	private void determineNextEasyMove()
	{
		//Placeholder for easy moves.
	}
	
	private void determineNextMediumMove()
	{
		//Placeholder for easy moves.
	}
	
	private void determineNextHardMove()
	{
		//Placeholder for easy moves.
	}
}
