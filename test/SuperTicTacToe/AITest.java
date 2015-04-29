package SuperTicTacToe;

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonathan
 */
public class AITest
{
	
	private static final Stone[][] DEFAULT_GRID = 
		{
			{new Stone(null, new Location(0, 0)), new Stone(null, new Location(0, 1)),new Stone(null, new Location(0, 2)), new Stone(null, new Location(0, 3)),new Stone(null, new Location(0, 4)), new Stone(null, new Location(0, 5))},
			{new Stone(null, new Location(1, 0)), new Stone(null, new Location(1, 1)),new Stone(null, new Location(1, 2)), new Stone(null, new Location(1, 3)),new Stone(null, new Location(1, 4)), new Stone(null, new Location(1, 5))},
			{new Stone(null, new Location(2, 0)), new Stone(null, new Location(2, 1)),new Stone(null, new Location(2, 2)), new Stone(null, new Location(2, 3)),new Stone(null, new Location(2, 4)), new Stone(null, new Location(2, 5))},
			{new Stone(null, new Location(3, 0)), new Stone(null, new Location(3, 1)),new Stone(null, new Location(3, 2)), new Stone(null, new Location(3, 3)),new Stone(null, new Location(3, 4)), new Stone(null, new Location(3, 5))},
			{new Stone(null, new Location(4, 0)), new Stone(null, new Location(4, 1)),new Stone(null, new Location(4, 2)), new Stone(null, new Location(4, 3)),new Stone(null, new Location(4, 4)), new Stone(null, new Location(4, 5))},
			{new Stone(null, new Location(5, 0)), new Stone(null, new Location(5, 1)),new Stone(null, new Location(5, 2)), new Stone(null, new Location(5, 3)),new Stone(null, new Location(5, 4)), new Stone(null, new Location(5, 5))},
		};
	
	public AITest()
	{
		
	}
	
	@BeforeClass
	public static void setUpClass()
	{
	}
	
	@AfterClass
	public static void tearDownClass()
	{
	}
	
	@Before
	public void setUp()
	{
	}
	
	@After
	public void tearDown()
	{
	}

	
	/**
	 * Test of makeMove method, of class AI.
	 */
	/* //Can not test due to implementation but not neccessary.
	@Test
	public void testMakeMove()
	{
		System.out.println("makeMove");
		AI instance = new AI();
		instance.makeMove();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	*/

	/**
	 * Test of determineNextEasyMove method, of class AI.
	 */
	@Test
	public void testDetermineNextEasyMove()
	{
		System.out.println("determineNextEasyMove");
		
		Stone[][] grid = getDefaultGrid();
		grid[0][0].setColor(Game.PLAYER_ONE_COLOR);
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = new Location(5, 5);
		Location result = instance.determineNextEasyMove(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of determineNextMediumMove method, of class AI.
	 */
	@Test
	public void testDetermineNextMediumMove()
	{
		System.out.println("determineNextMediumMove");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = new Location(0, 0);
		Location result = instance.determineNextMediumMove(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of determineNextHardMove method, of class AI.
	 */
	@Test
	public void testDetermineNextHardMove()
	{
		System.out.println("determineNextHardMove");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = new Location(0, 0);
		Location result = instance.determineNextHardMove(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of findEmptySpot method, of class AI.
	 */
	@Test
	public void testFindEmptySpot()
	{
		System.out.println("findEmptySpot");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = new Location(0, 0);
		Location result = instance.findEmptySpot(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkSides method, of class AI.
	 */
	@Test
	public void testCheckSides()
	{
		System.out.println("checkSides");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = new Location(0, 1);
		Location result = instance.checkSides(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkCorners method, of class AI.
	 */
	@Test
	public void testCheckCorners()
	{
		System.out.println("checkCorners");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = new Location(0, 0);
		Location result = instance.checkCorners(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkOppositeCorner method, of class AI.
	 */
	@Test
	public void testCheckOppositeCorner()
	{
		System.out.println("checkOppositeCorner");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = null;
		Location result = instance.checkOppositeCorner(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkScorePoint method, of class AI.
	 */
	@Test
	public void testCheckScorePoint()
	{
		System.out.println("checkScorePoint");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = null;
		Location result = instance.checkScorePoint(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkBlockOpponent method, of class AI.
	 */
	@Test
	public void testCheckBlockOpponent()
	{
		System.out.println("checkBlockOpponent");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = null;
		Location result = instance.checkBlockOpponent(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkFork method, of class AI.
	 */
	@Test
	public void testCheckFork_StoneArrArr()
	{
		System.out.println("checkFork");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = null;
		Location result = instance.checkFork(grid);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkFork method, of class AI.
	 */
	@Test
	public void testCheckFork_StoneArrArr_Color()
	{
		System.out.println("checkFork");
		Stone[][] grid = getDefaultGrid();
		
		Color playerColor = Game.PLAYER_ONE_COLOR;
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = null;
		Location result = instance.checkFork(grid, playerColor);
		assertEquals(expResult, result);
	}

	/**
	 * Test of findScoringOpportunity method, of class AI.
	 */
	@Test
	public void testFindScoringOpportunity()
	{
		System.out.println("findScoringOpportunity");
		Stone[][] grid = getDefaultGrid();
		
		Color color = Game.PLAYER_ONE_COLOR;
		Location expResult = null;
		Location result = AI.findScoringOpportunity(grid, color);
		assertEquals(expResult, result);
	}

	/**
	 * Test of countScoringOpportunities method, of class AI.
	 */
	@Test
	public void testCountScoringOpportunities()
	{
		System.out.println("countScoringOpportunities");
		Stone[][] grid = getDefaultGrid();
		
		Color color = Game.PLAYER_ONE_COLOR;
		int expResult = 0;
		int result = AI.countScoringOpportunities(grid, color);
		assertEquals(expResult, result);
	}

	/**
	 * Test of checkBlockOpponentFork method, of class AI.
	 */
	@Test
	public void testCheckBlockOpponentFork()
	{
		System.out.println("checkBlockOpponentFork");
		Stone[][] grid = getDefaultGrid();
		
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		Location expResult = null;
		Location result = instance.checkBlockOpponentFork(grid);
		assertEquals(expResult, result);
	}
	
		/**
	 * Test of countThreeInARow method, of class AI.
	 */
	@Test
	public void testCountThreeInARow()
	{
		System.out.println("countThreeInARow");
		Stone[][] grid = getDefaultGrid();
		
		Color color = Game.PLAYER_ONE_COLOR;
		AI instance = new AI();
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		int expResult = 0;
		int result = instance.countThreeInARow(grid, color);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getSkillLevel method, of class AI.
	 */
	@Test
	public void testGetSkillLevel()
	{
		System.out.println("getSkillLevel");
		AI instance = new AI(SkillLevel.Medium);
		instance.setColor(Game.PLAYER_TWO_COLOR);
		
		SkillLevel expResult = SkillLevel.Medium;
		SkillLevel result = instance.getSkillLevel();
		assertEquals(expResult, result);
	}
	
	private Stone[][] getDefaultGrid()
	{
		//Copy the orginal 
		Stone[][] grid = new Stone[DEFAULT_GRID.length][DEFAULT_GRID[0].length];
		for (int i = 0; i < DEFAULT_GRID.length; i++)
		{
			System.arraycopy(DEFAULT_GRID[i], 0, grid[i], 0, DEFAULT_GRID[i].length);
		}
		return grid;
	}
}
