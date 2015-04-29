package SuperTicTacToe;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;

/**
 * The game board that the game is played on.
 * @author Jonathan
 */
public class GridPanel extends UIPanel implements ActionListener, AncestorListener
{
	private static final long serialVersionUID = 1L;
	
	private final Game gameInstance;
	private Stone[][] gridSpots;
	
	public static final int GRID_WIDTH = 6;
	public static final int GRID_HEIGHT = 6;
	
	/**
	 * 
	 */
	public GridPanel()
	{
		this(Game.GUEST_NAME, SkillLevel.Easy, true);
	}
	
	/**
	 * Creates new form GridPanel
	 * @param username1
	 * @param skillLevel
	 * @param isPlayerOneTurn
	 */
	public GridPanel(String username1, SkillLevel skillLevel, boolean isPlayerOneTurn)
	{
		initComponents();
		
		this.gridSpots = new Stone[GRID_HEIGHT][GRID_WIDTH];
		this.gameInstance = new Game(username1, skillLevel, isPlayerOneTurn);
		
		//Set the player score labels to their respective turn colors.
		this.lblPlayerOneName.setForeground(this.gameInstance.getPlayerOne().getColor());
		this.lblPlayerTwoName.setForeground(this.gameInstance.getPlayerTwo().getColor());
                this.lblPlayerOneName.setText(username1);
                this.lblPlayerTwoName.setText(Game.AI_NAME);
		this.setTurnColor(this.gameInstance.getCurrentPlayersColor());
		
		
		if(this.gameInstance == null)
		{
			throw new IllegalArgumentException("Game could not be created with set options");
		}
		
		//Setup the gridSpots.
		stone0.setStoneLocation(new Location(0, 0));
		this.gridSpots[0][0] = stone0;
		stone1.setStoneLocation(new Location(0, 1));
		this.gridSpots[0][1] = stone1;
		stone2.setStoneLocation(new Location(0, 2));
		this.gridSpots[0][2] = stone2;
		stone3.setStoneLocation(new Location(0, 3));
		this.gridSpots[0][3] = stone3;
		stone4.setStoneLocation(new Location(0, 4));
		this.gridSpots[0][4] = stone4;
		stone5.setStoneLocation(new Location(0, 5));
		this.gridSpots[0][5] = stone5;
		stone6.setStoneLocation(new Location(1, 0));
		this.gridSpots[1][0] = stone6;
		stone7.setStoneLocation(new Location(1, 1));
		this.gridSpots[1][1] = stone7;
		stone8.setStoneLocation(new Location(1, 2));
		this.gridSpots[1][2] = stone8;
		stone9.setStoneLocation(new Location(1, 3));
		this.gridSpots[1][3] = stone9;
		stone10.setStoneLocation(new Location(1, 4));
		this.gridSpots[1][4] = stone10;
		stone11.setStoneLocation(new Location(1, 5));
		this.gridSpots[1][5] = stone11;
		stone12.setStoneLocation(new Location(2, 0));
		this.gridSpots[2][0] = stone12;
		stone13.setStoneLocation(new Location(2, 1));
		this.gridSpots[2][1] = stone13;
		stone14.setStoneLocation(new Location(2, 2));
		this.gridSpots[2][2] = stone14;
		stone15.setStoneLocation(new Location(2, 3));
		this.gridSpots[2][3] = stone15;
		stone16.setStoneLocation(new Location(2, 4));
		this.gridSpots[2][4] = stone16;
		stone17.setStoneLocation(new Location(2, 5));
		this.gridSpots[2][5] = stone17;
		stone18.setStoneLocation(new Location(3, 0));
		this.gridSpots[3][0] = stone18;
		stone19.setStoneLocation(new Location(3, 1));
		this.gridSpots[3][1] = stone19;
		stone20.setStoneLocation(new Location(3, 2));
		this.gridSpots[3][2] = stone20;
		stone21.setStoneLocation(new Location(3, 3));
		this.gridSpots[3][3] = stone21;
		stone22.setStoneLocation(new Location(3, 4));
		this.gridSpots[3][4] = stone22;
		stone23.setStoneLocation(new Location(3, 5));
		this.gridSpots[3][5] = stone23;
		stone24.setStoneLocation(new Location(4, 0));
		this.gridSpots[4][0] = stone24;
		stone25.setStoneLocation(new Location(4, 1));
		this.gridSpots[4][1] = stone25;
		stone26.setStoneLocation(new Location(4, 2));
		this.gridSpots[4][2] = stone26;
		stone27.setStoneLocation(new Location(4, 3));
		this.gridSpots[4][3] = stone27;
		stone28.setStoneLocation(new Location(4, 4));
		this.gridSpots[4][4] = stone28;
		stone29.setStoneLocation(new Location(4, 5));
		this.gridSpots[4][5] = stone29;
		stone30.setStoneLocation(new Location(5, 0));
		this.gridSpots[5][0] = stone30;
		stone31.setStoneLocation(new Location(5, 1));
		this.gridSpots[5][1] = stone31;
		stone32.setStoneLocation(new Location(5, 2));
		this.gridSpots[5][2] = stone32;
		stone33.setStoneLocation(new Location(5, 3));
		this.gridSpots[5][3] = stone33;
		stone34.setStoneLocation(new Location(5, 4));
		this.gridSpots[5][4] = stone34;
		stone35.setStoneLocation(new Location(5, 5));
		this.gridSpots[5][5] = stone35;
	}
	
	/**
	 * Creates new form GridPanel
	 * @param username1
	 * @param username2
	 * @param isPlayerOneTurn
	 */
	public GridPanel(String username1, String username2, boolean isPlayerOneTurn)
	{
		initComponents();
		
		this.gridSpots = new Stone[GRID_HEIGHT][GRID_WIDTH];
		this.gameInstance = new Game(username1, username2, isPlayerOneTurn);
		
		//Set the player score labels to their respective turn colors.
		this.lblPlayerOneName.setForeground(this.gameInstance.getPlayerOne().getColor());
		this.lblPlayerTwoName.setForeground(this.gameInstance.getPlayerTwo().getColor());
                this.lblPlayerOneName.setText(username1);
                this.lblPlayerTwoName.setText(username2);
		
		this.setTurnColor(this.gameInstance.getCurrentPlayersColor());
		
		if(this.gameInstance == null)
		{
			throw new IllegalArgumentException("Game could not be created with set options");
		}
		
		//Setup the gridSpots.
		stone0.setStoneLocation(new Location(0, 0));
		this.gridSpots[0][0] = stone0;
		stone1.setStoneLocation(new Location(0, 1));
		this.gridSpots[0][1] = stone1;
		stone2.setStoneLocation(new Location(0, 2));
		this.gridSpots[0][2] = stone2;
		stone3.setStoneLocation(new Location(0, 3));
		this.gridSpots[0][3] = stone3;
		stone4.setStoneLocation(new Location(0, 4));
		this.gridSpots[0][4] = stone4;
		stone5.setStoneLocation(new Location(0, 5));
		this.gridSpots[0][5] = stone5;
		stone6.setStoneLocation(new Location(1, 0));
		this.gridSpots[1][0] = stone6;
		stone7.setStoneLocation(new Location(1, 1));
		this.gridSpots[1][1] = stone7;
		stone8.setStoneLocation(new Location(1, 2));
		this.gridSpots[1][2] = stone8;
		stone9.setStoneLocation(new Location(1, 3));
		this.gridSpots[1][3] = stone9;
		stone10.setStoneLocation(new Location(1, 4));
		this.gridSpots[1][4] = stone10;
		stone11.setStoneLocation(new Location(1, 5));
		this.gridSpots[1][5] = stone11;
		stone12.setStoneLocation(new Location(2, 0));
		this.gridSpots[2][0] = stone12;
		stone13.setStoneLocation(new Location(2, 1));
		this.gridSpots[2][1] = stone13;
		stone14.setStoneLocation(new Location(2, 2));
		this.gridSpots[2][2] = stone14;
		stone15.setStoneLocation(new Location(2, 3));
		this.gridSpots[2][3] = stone15;
		stone16.setStoneLocation(new Location(2, 4));
		this.gridSpots[2][4] = stone16;
		stone17.setStoneLocation(new Location(2, 5));
		this.gridSpots[2][5] = stone17;
		stone18.setStoneLocation(new Location(3, 0));
		this.gridSpots[3][0] = stone18;
		stone19.setStoneLocation(new Location(3, 1));
		this.gridSpots[3][1] = stone19;
		stone20.setStoneLocation(new Location(3, 2));
		this.gridSpots[3][2] = stone20;
		stone21.setStoneLocation(new Location(3, 3));
		this.gridSpots[3][3] = stone21;
		stone22.setStoneLocation(new Location(3, 4));
		this.gridSpots[3][4] = stone22;
		stone23.setStoneLocation(new Location(3, 5));
		this.gridSpots[3][5] = stone23;
		stone24.setStoneLocation(new Location(4, 0));
		this.gridSpots[4][0] = stone24;
		stone25.setStoneLocation(new Location(4, 1));
		this.gridSpots[4][1] = stone25;
		stone26.setStoneLocation(new Location(4, 2));
		this.gridSpots[4][2] = stone26;
		stone27.setStoneLocation(new Location(4, 3));
		this.gridSpots[4][3] = stone27;
		stone28.setStoneLocation(new Location(4, 4));
		this.gridSpots[4][4] = stone28;
		stone29.setStoneLocation(new Location(4, 5));
		this.gridSpots[4][5] = stone29;
		stone30.setStoneLocation(new Location(5, 0));
		this.gridSpots[5][0] = stone30;
		stone31.setStoneLocation(new Location(5, 1));
		this.gridSpots[5][1] = stone31;
		stone32.setStoneLocation(new Location(5, 2));
		this.gridSpots[5][2] = stone32;
		stone33.setStoneLocation(new Location(5, 3));
		this.gridSpots[5][3] = stone33;
		stone34.setStoneLocation(new Location(5, 4));
		this.gridSpots[5][4] = stone34;
		stone35.setStoneLocation(new Location(5, 5));
		this.gridSpots[5][5] = stone35;
	}

	public Game getGame()
	{
		return this.gameInstance;
	}
	
	public Stone[][] getGridSpots()
	{
		Stone[][] copy = new Stone[6][6];
		
		System.arraycopy(this.gridSpots, 0, copy, 0, this.gridSpots.length);
		
		return copy;
	}
	
	/**
	 * Gets the stone location placed this turn.
	 * @return The location of the placed stone this turn or null if none.
	 */
	public Location getCurrentPlacedLocation()
	{
		for(Stone[] row : this.gridSpots)
		{
			for(Stone stone : row)
			{
				if(stone.isPlacedThisTurn())
				{
					return stone.getStoneLocation();
				}
			}
		}
		return null;
	}
	
	/*
	public boolean placeStone(Stone stone)
	{
		if(stone == null)
		{
			throw new IllegalArgumentException("stone is null");
		}
		
		Location loc = stone.getStoneLocation();
		int row = loc.getRowPos();
		int col = loc.getColumnPos();
		
		if(this.gridSpots[row][col] != null && !this.gridSpots[row][col].isEmptySpot())
		{
			return false;
		}
		
		//this.remove(this.gridSpots[row][col]);
		this.gridSpots[row][col] = stone;
		this.displayStone(stone);
		return true;
	}
	
	private void displayStone(Stone stone)
	{
		//TODO: Actually display the stone.
		//this.add(stone);
		
		this.repaint();
	}
	*/
	
	public boolean isGridFull()
	{
		for(int row=0; row < this.gridSpots.length; row++)
		{
			for(int col=0; col < this.gridSpots[row].length; col++)
			{
				if(this.gridSpots[row][col].isEmptySpot())
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public Stone getStone(Location loc)
	{
		if(loc == null)
		{
			throw new IllegalArgumentException("loc is null");
		}
		
		return this.gridSpots[loc.getRowPos()][loc.getColumnPos()];
	}
	
	/**
	 * Changes all stone's to old status (not moved this turn).
	 * Is suppose to be called before place the new move.
	 */
	public void moveMade()
	{
		for(Stone[] row : this.gridSpots)
		{
			for(Stone stone : row)
			{
				stone.setPlacedThisTurn(false);
			}
		}
	}
	
	public void updateScores(int scoreOne, int scoreTwo)
	{
		this.lblPlayerOneScore.setText(Integer.toString(scoreOne));
		this.lblPlayerTwoScore.setText(Integer.toString(scoreTwo));
	}
	
	public boolean isSpotOpen(Location loc)
	{
		if(loc == null)
		{
			throw new IllegalArgumentException("loc is null");
		}
		
		return (this.gridSpots[loc.getRowPos()][loc.getColumnPos()] == null || 
				this.gridSpots[loc.getRowPos()][loc.getColumnPos()].isEmptySpot());
	}

	/*
	@Override
	public void repaint()
	{
		super.repaint(); //Call super version of repaint to paint regular components. 
		
		Graphics2D graphics = (Graphics2D)this.getGraphics();
		
		//Manually paint grid spots. 
		for(int row = 0; row < this.gridSpots.length; row++)
		{
			for(int col = 0; col < this.gridSpots.length; col++)
			{
				Stone gridSpot = this.gridSpots[row][col];
				
				if(gridSpot == null)
				{
					//Added empty stone.
					this.gridSpots[row][col] = new Stone(new Location(row, col));
					gridSpot = this.gridSpots[row][col];
				}
				
				pa
			}
		}
	}
	*/
	
	public void setTimerText(String text)
	{
		if(text == null)
		{
			text = "";
		}
		
		this.lblTimer.setText(text);
	}
	
	public final void setTurnColor(Color color)
	{
		if(color == null)
		{
			color = Color.WHITE;
		}
		
		this.playerTurnComponent.setTurnColor(color);
	}
	
	/**
	 * Display an end game message and return to the main menu afterwards.
	 * @param message The end game message to display.
	 */
	public void displayEndGame(String message)
	{
		if(message == null)
		{
			message = "Someone won the game.";
		}
		
		if(!this.gameInstance.getIsGameOver())
		{
			throw new IllegalStateException("displayEndGame is called while game is not over.");
		}
		
		//Turn off all the stones
		for(Stone[] stones : this.gridSpots)
		{
			for(Stone stone : stones)
			{
				stone.setEnabled(false);
			}
		}
		
		//Display a message to the user about the game status (Code waits here till they close the message)
		JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
		
		//End the game by going back to the main menu
		UIWindow.getInstance().setCurrentPanel(new MenuPanel());
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        lblPlayerOneScore = new javax.swing.JLabel();
        lblPlayerTwoScore = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnForfeit = new javax.swing.JButton();
        lblPlayerOneName = new javax.swing.JLabel();
        lblPlayerTwoName = new javax.swing.JLabel();
        stone0 = new SuperTicTacToe.Stone();
        stone1 = new SuperTicTacToe.Stone();
        stone2 = new SuperTicTacToe.Stone();
        stone3 = new SuperTicTacToe.Stone();
        stone4 = new SuperTicTacToe.Stone();
        stone5 = new SuperTicTacToe.Stone();
        stone11 = new SuperTicTacToe.Stone();
        stone10 = new SuperTicTacToe.Stone();
        stone9 = new SuperTicTacToe.Stone();
        stone8 = new SuperTicTacToe.Stone();
        stone7 = new SuperTicTacToe.Stone();
        stone6 = new SuperTicTacToe.Stone();
        stone17 = new SuperTicTacToe.Stone();
        stone16 = new SuperTicTacToe.Stone();
        stone15 = new SuperTicTacToe.Stone();
        stone14 = new SuperTicTacToe.Stone();
        stone13 = new SuperTicTacToe.Stone();
        stone12 = new SuperTicTacToe.Stone();
        stone23 = new SuperTicTacToe.Stone();
        stone22 = new SuperTicTacToe.Stone();
        stone21 = new SuperTicTacToe.Stone();
        stone20 = new SuperTicTacToe.Stone();
        stone19 = new SuperTicTacToe.Stone();
        stone18 = new SuperTicTacToe.Stone();
        stone29 = new SuperTicTacToe.Stone();
        stone28 = new SuperTicTacToe.Stone();
        stone27 = new SuperTicTacToe.Stone();
        stone26 = new SuperTicTacToe.Stone();
        stone25 = new SuperTicTacToe.Stone();
        stone24 = new SuperTicTacToe.Stone();
        stone31 = new SuperTicTacToe.Stone();
        stone30 = new SuperTicTacToe.Stone();
        stone35 = new SuperTicTacToe.Stone();
        stone33 = new SuperTicTacToe.Stone();
        stone34 = new SuperTicTacToe.Stone();
        stone32 = new SuperTicTacToe.Stone();
        playerTurnComponent = new SuperTicTacToe.PlayerTurnComponent();

        addAncestorListener(this);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Score");

        lblPlayerOneScore.setText("0");

        lblPlayerTwoScore.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Time Left");
        jLabel2.setToolTipText("");

        lblTimer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimer.setText("00:30:00");
        lblTimer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Turn");
        jLabel3.setToolTipText("");

        btnForfeit.setText("Forfeit");
        btnForfeit.addActionListener(this);

        lblPlayerOneName.setText(" ");

        stone0.setText("stone0");

        stone1.setText("stone1");

        stone2.setText("stone2");

        stone3.setText("stone3");

        stone4.setText("stone4");

        stone5.setText("stone5");

        stone11.setText("stone5");

        stone10.setText("stone4");

        stone9.setText("stone3");

        stone8.setText("stone2");

        stone7.setText("stone1");

        stone6.setText("stone0");

        stone17.setText("stone5");

        stone16.setText("stone4");

        stone15.setText("stone3");

        stone14.setText("stone2");

        stone13.setText("stone1");

        stone12.setText("stone0");

        stone23.setText("stone5");

        stone22.setText("stone4");

        stone21.setText("stone3");

        stone20.setText("stone2");

        stone19.setText("stone1");

        stone18.setText("stone0");

        stone29.setText("stone5");

        stone28.setText("stone4");

        stone27.setText("stone3");

        stone26.setText("stone2");

        stone25.setText("stone1");

        stone24.setText("stone0");

        stone31.setText("stone1");

        stone30.setText("stone0");

        stone35.setText("stone5");

        stone33.setText("stone3");

        stone34.setText("stone4");

        stone32.setText("stone2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(stone0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(stone6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stone11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stone24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stone12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stone18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stone30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnForfeit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playerTurnComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblPlayerOneName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblPlayerTwoName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblPlayerTwoScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblPlayerOneScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel3))
                                            .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlayerOneScore)
                            .addComponent(lblPlayerOneName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlayerTwoName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPlayerTwoScore))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTimer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stone0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stone6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stone12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerTurnComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stone18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stone24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stone30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForfeit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt)
    {
        if (evt.getSource() == btnForfeit)
        {
            GridPanel.this.btnForfeitActionPerformed(evt);
        }
    }

    public void ancestorAdded(javax.swing.event.AncestorEvent evt)
    {
        if (evt.getSource() == GridPanel.this)
        {
            GridPanel.this.formAncestorAdded(evt);
        }
    }

    public void ancestorMoved(javax.swing.event.AncestorEvent evt)
    {
    }

    public void ancestorRemoved(javax.swing.event.AncestorEvent evt)
    {
    }// </editor-fold>//GEN-END:initComponents

    private void btnForfeitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnForfeitActionPerformed
    {//GEN-HEADEREND:event_btnForfeitActionPerformed
        this.gameInstance.forfeit();
    }//GEN-LAST:event_btnForfeitActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt)//GEN-FIRST:event_formAncestorAdded
    {//GEN-HEADEREND:event_formAncestorAdded
		//Put code here that needs to run after screen is visible.
		this.gameInstance.performPostSetup();
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForfeit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblPlayerOneName;
    private javax.swing.JLabel lblPlayerOneScore;
    private javax.swing.JLabel lblPlayerTwoName;
    private javax.swing.JLabel lblPlayerTwoScore;
    private javax.swing.JLabel lblTimer;
    private SuperTicTacToe.PlayerTurnComponent playerTurnComponent;
    private SuperTicTacToe.Stone stone0;
    private SuperTicTacToe.Stone stone1;
    private SuperTicTacToe.Stone stone10;
    private SuperTicTacToe.Stone stone11;
    private SuperTicTacToe.Stone stone12;
    private SuperTicTacToe.Stone stone13;
    private SuperTicTacToe.Stone stone14;
    private SuperTicTacToe.Stone stone15;
    private SuperTicTacToe.Stone stone16;
    private SuperTicTacToe.Stone stone17;
    private SuperTicTacToe.Stone stone18;
    private SuperTicTacToe.Stone stone19;
    private SuperTicTacToe.Stone stone2;
    private SuperTicTacToe.Stone stone20;
    private SuperTicTacToe.Stone stone21;
    private SuperTicTacToe.Stone stone22;
    private SuperTicTacToe.Stone stone23;
    private SuperTicTacToe.Stone stone24;
    private SuperTicTacToe.Stone stone25;
    private SuperTicTacToe.Stone stone26;
    private SuperTicTacToe.Stone stone27;
    private SuperTicTacToe.Stone stone28;
    private SuperTicTacToe.Stone stone29;
    private SuperTicTacToe.Stone stone3;
    private SuperTicTacToe.Stone stone30;
    private SuperTicTacToe.Stone stone31;
    private SuperTicTacToe.Stone stone32;
    private SuperTicTacToe.Stone stone33;
    private SuperTicTacToe.Stone stone34;
    private SuperTicTacToe.Stone stone35;
    private SuperTicTacToe.Stone stone4;
    private SuperTicTacToe.Stone stone5;
    private SuperTicTacToe.Stone stone6;
    private SuperTicTacToe.Stone stone7;
    private SuperTicTacToe.Stone stone8;
    private SuperTicTacToe.Stone stone9;
    // End of variables declaration//GEN-END:variables
}
