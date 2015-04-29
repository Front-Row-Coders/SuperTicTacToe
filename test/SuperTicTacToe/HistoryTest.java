package SuperTicTacToe;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ngan
 */
public class HistoryTest
{
	
	public HistoryTest()
	{
	}
	
	@BeforeClass
	public static void setUpClass()
	{
		
	}

	/**
	 * Test of saveHistory method, of class History.
	 */
	@Test
	public void testSaveHistory()
	{
		System.out.println("saveHistory");
		History instance = new History();
		Player testPlayer = new Player("joe");
		instance.history.add(testPlayer);
		
		instance.saveHistory();
		File file = new File(History.STORAGE_FILE);
		if (!file.exists() || file.length()<=0)
		{
			fail("Unsuccessfully saved file.");
		}
	}

	/**
	 * Test of loadHistory method, of class History.
	 */
	@Test
	public void testLoadHistory()
	{
		System.out.println("loadHistory");
		History instance = new History();
		
		if(!new File(History.STORAGE_FILE).exists())
		{
			instance.history.add(new Player("joe"));
			instance.saveHistory();
			instance.history.clear();
		}
		
		instance.loadHistory();
		
		if(instance.history.size() <= 0)
		{
			fail("Did not sucessfully loaded history");
		}
	}
	
	/**
	 * Test of getPlayer method, of class History.
	 */
	@Test
	public void testGetPlayer()
	{
		System.out.println("getPlayer");
		
		String username = "joe";
		History instance = new History();
		
		Player testPlayer = new Player(username);
		instance.history.add(testPlayer);
		
		Player expResult = testPlayer;
		Player result = instance.getPlayer(username);
		assertEquals(expResult, result);
	}

	/**
	 * Test of createNewPlayer method, of class History.
	 */
	@Test
	public void testCreateNewPlayer()
	{
		System.out.println("createNewPlayer");
		
		String username = "joe";
		History instance = new History();
		boolean expResult = true;
		boolean result = instance.createNewPlayer(username);
		assertEquals(expResult, result);
		
		boolean found = false;
		for (Player player: instance.history)
		{
			if (player.getUsername().equals("joe"))
			{
					found = true;
			}           
		}
		assertEquals(expResult, found);
	}
	
}
