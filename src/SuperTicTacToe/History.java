package SuperTicTacToe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Holds the player history.
 *
 * @author Jonathan
 */
public class History
{

	/**
	 * The list of players.
	 */
	protected final List<Player> history;

	public static final String STORAGE_FILE = "players.xml";

	public static final String ROOT_TAG_NAME = "playerList";

	public static final String PLAYER_TAG_NAME = "player";

	public static final String WINS_TAG_NAME = "wins";

	public static final String LOSSES_TAG_NAME = "losses";

	public static final String TIES_TAG_NAME = "ties";

	public static final String USERNAME_TAG_NAME = "username";

	/**
	 * Creates a History object.
	 */
	public History()
	{
		history = new ArrayList<>();
	}

	/*
	 //Does not work with HistoryPanel's implementation.
	 public void displayHistory(Player player)
	 {
	 if (player == null)
	 {
	 throw new IllegalArgumentException("Player argument is null");
	 }
		
	 //create history panel
	 UIWindow.getInstance().setCurrentPanel(new HistoryPanel(player));
	 }
	 */
	
	/**
	 * Loads the history of the players.
	 */
	public void loadHistory()
	{
		history.clear();

		try
		{
			File file = new File(STORAGE_FILE);
			//If file doesn't exist, then create it
			if (!file.exists())
			{
				file.createNewFile();
				return;
			}

			SAXBuilder jdomBuilder = new SAXBuilder();
			Document document = jdomBuilder.build(file);
			// Buffer Reader is replaced by document
			//BufferedReader reader = new BufferedReader(new FileReader(file));
			Element root = document.getRootElement();
			List<Element> players = root.getChildren(PLAYER_TAG_NAME);

			//String line;
			for (Element playerInfo : players)
			{
				//Scanner parser = new Scanner(line);
				try
				{
					String username = playerInfo.getChildText(USERNAME_TAG_NAME);
					int wins = Integer.parseInt(playerInfo.getChildText(WINS_TAG_NAME));
					int losses = Integer.parseInt(playerInfo.getChildText(LOSSES_TAG_NAME));
					int ties = Integer.parseInt(playerInfo.getChildText(TIES_TAG_NAME));
					Player player = new Player(username, wins, losses, ties);
					history.add(player);
				}
				catch (Exception e)
				{

				}
			}
			//reader.close();
		}
		catch (IOException | JDOMException e)
		{
			System.err.format("Exception occurred trying to read '%s'.\n", STORAGE_FILE);
			//e.printStackTrace();
		}
	}

	/**
	 * Saves the current history to an XML file.
	 */
	public void saveHistory()
	{
		/*
		 if(history.isEmpty())
		 {
		 return;
		 }
		 */

		try
		{
			File file = new File(STORAGE_FILE);

			// if file doesn't exist, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}
			Element root = new Element(ROOT_TAG_NAME);
			Document document = new Document(root);

			//FileWriter fw = new FileWriter(file.getAbsoluteFile());
			//PrintWriter bw = new PrintWriter(fw);
			for (Player player : history)
			{
				Element playerElement = new Element(PLAYER_TAG_NAME);

				Element usernameElement = new Element(USERNAME_TAG_NAME);
				usernameElement.addContent(player.getUsername());
				playerElement.addContent(usernameElement);

				Element winsElement = new Element(WINS_TAG_NAME);
				winsElement.addContent(Integer.toString(player.getWins()));
				playerElement.addContent(winsElement);

				Element lossesElement = new Element(LOSSES_TAG_NAME);
				lossesElement.addContent(Integer.toString(player.getLosses()));
				playerElement.addContent(lossesElement);

				Element tiesElement = new Element(TIES_TAG_NAME);
				tiesElement.addContent(Integer.toString(player.getTies()));
				playerElement.addContent(tiesElement);

				root.addContent(playerElement);

				/*
				 bw.print(" ");
				 bw.print(player.getUsername());
				 bw.print(" ");
				 bw.print(player.getWins());
				 bw.print(" ");
				 bw.print(player.getLosses());
				 bw.print(" ");
				 bw.println(player.getTies());
				 */
			}
			//bw.close();

			XMLOutputter xml = new XMLOutputter();
			xml.setFormat(Format.getPrettyFormat());

			try (PrintWriter pw = new PrintWriter(new FileWriter(file.getAbsoluteFile())))
			{
				pw.print(xml.outputString(document));
			}
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to write to '%s'.", STORAGE_FILE);
			//e.printStackTrace();
		}
	}

	/**
	 * Gets a player based on the specified username.
	 * @param username The username of the player to get.
	 * @return The player with that username or null if the username
	 * does not belong to any player.
	 */
	public Player getPlayer(String username)
	{
		for (Player player : history)
		{
			if (player.getUsername().equals(username))
			{
				return player;
			}
		}
		return null;
	}

	/**
	 * Creates a new player with the specified username.
	 * @param username The username for the player to have. Must not be
	 * a reserved username (Guest and AI) and must not belong
	 * to any other player already.
	 * @return True if the player is created successfully, else false.
	 */
	public boolean createNewPlayer(String username)
	{
		if (username == null)
		{
			return false;
		}

		username = username.trim();
		if (username.length() <= 0)
		{
			return false;
		}
		if (username.equalsIgnoreCase(Game.AI_NAME))
		{
			return false;
		}
		if (username.equalsIgnoreCase(Game.GUEST_NAME))
		{
			return false;
		}
		if (this.getPlayer(username) != null)
		{
			return false;
		}
		//Valid username, create it
		Player player = new Player(username);
		history.add(player);
		return true;
	}
}
