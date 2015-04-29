package SuperTicTacToe;

import java.io.File;

/**
 *
 * @author Ngan
 */
public class HistoryTestDriver
{
	public static void checkCreation()
	{
		History obj = new History();
		if(obj != null)
			System.out.println("History object was created sucessfully.");
		else
			System.err.println("History object was not created sucessfully");
	}

	public static void checkCreateNewPlayer()
	{
		History obj = new History();
		obj.createNewPlayer("joe");
		
		boolean found = false;
		for (Player player: obj.history)
            	{
                	if (player.getUsername().equals("joe"))
                	{
                    		found = true;
                	}           
            	}
		
		if(found)
		{
			System.out.println("New player was created.");
		}
		else
		{
			System.err.println("New player was not created sucessfully.");
		}
	}

	public static void checkGetPlayer()
	{
		History obj = new History();
		Player testPlayer = new Player("joe");
		obj.history.add(testPlayer);
		
		if(obj.getPlayer("joe")!=null)
		{
			System.out.println("Successfully got a player");
		}
		else
		{
			System.err.println("Did not successfully get a player");
		}
	}

	public static void checkSaveHistory()
	{
		History obj = new History();
		Player testPlayer = new Player("joe");
		obj.history.add(testPlayer);
		
		obj.saveHistory();
		File file = new File(History.STORAGE_FILE);
		if (file.exists() && file.length()>0)
		{
			System.out.println("Successfully saved file.");
		}
		else
		{
			System.err.println("Unsuccessfully saved file.");
		}
		
	}

	public static void checkLoadHistory()
	{
		History obj = new History();
		//Either call checkSaveHistory first when successful or manually create the file.
		obj.loadHistory();
		if(obj.history.size() > 0)
		{
			System.out.println("Sucessfully loaded history");
		}
		else
		{
			System.err.println("Did not sucessfully loaded history");
		}
	}
	
	

	public static void main(String[] args)
	{
		//Can run different cases here.
		checkCreation();
		checkCreateNewPlayer();
		checkGetPlayer();
		checkSaveHistory();
		checkLoadHistory();
		//checkDisplayHistory();
	}
}