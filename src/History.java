public class History
{
	private List<Player> history;
	public History ()
	{
		history = new ArrayList<Player>();
		
	}
	public void displayHistory(Player player)
	{
		if (player == null)
			throw new Exception("Player argument is null");
		
		//create history panel
	}
	public void loadHistory()
	{

		history.clear();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("Players.txt"));
			String line;
			while ((line = reader.readLine()) != null)
			{
				Scanner parser = new Scanner(line);
				String username = parser.next();
				int wins = parser.nextInt();
				int losses = parse.nextInt();
				int ties = parser.nextInt();
				Player player = new Player(username,wins, losses,ties);
				history.add(player);
			}
				reader.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
	}	
	public void saveHistory()
	{
		if(history.isempty())
			throw new Exception ("List is empty");
		try
		{
			Player content;
 
			File file = new File("Players.txt");
 
			// if file doesn't exist, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			PrintWriter bw = new PrintWriter(fw);
			for (Player player: history)
			{
				bw.print(player.getUsername());
				bw.print(" ");
				bw.print(player.getWins());
				bw.print(" ");
				bw.print(player.getLosses());
				bw.print(" ");
				bw.println(player.getTies());		
			}
			bw.close();
		}	
	}
	public getPlayer(String username)
	{
		for (Player player: history)
			{
				if (player.getUsername().equals(username))
				{
					return player;
				}			
			}
			return null;
	}
	public boolean createNewPlayer(String username)
	{
		if (getPlayer(username)!=null)
		{
			return false;
		}
		Player player = new Player(username);;
		history.add(player);
		return true;
	}
		
}