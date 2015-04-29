package SuperTicTacToe;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;

/**
 *
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
		this.gridSpots[0][0] = stone0;
		this.gridSpots[0][1] = stone1;
		this.gridSpots[0][2] = stone2;
		this.gridSpots[0][3] = stone3;
		this.gridSpots[0][4] = stone4;
		this.gridSpots[0][5] = stone5;
		this.gridSpots[1][0] = stone6;
		this.gridSpots[1][1] = stone7;
		this.gridSpots[1][2] = stone8;
		this.gridSpots[1][3] = stone9;
		this.gridSpots[1][4] = stone10;
		this.gridSpots[1][5] = stone11;
		this.gridSpots[2][0] = stone12;
		this.gridSpots[2][1] = stone13;
		this.gridSpots[2][2] = stone14;
		this.gridSpots[2][3] = stone15;
		this.gridSpots[2][4] = stone16;
		this.gridSpots[2][5] = stone17;
		this.gridSpots[3][0] = stone18;
		this.gridSpots[3][1] = stone19;
		this.gridSpots[3][2] = stone20;
		this.gridSpots[3][3] = stone21;
		this.gridSpots[3][4] = stone22;
		this.gridSpots[3][5] = stone23;
		this.gridSpots[4][0] = stone24;
		this.gridSpots[4][1] = stone25;
		this.gridSpots[4][2] = stone26;
		this.gridSpots[4][3] = stone27;
		this.gridSpots[4][4] = stone28;
		this.gridSpots[4][5] = stone29;
		this.gridSpots[5][0] = stone30;
		this.gridSpots[5][1] = stone31;
		this.gridSpots[5][2] = stone32;
		this.gridSpots[5][3] = stone33;
		this.gridSpots[5][4] = stone34;
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
		this.gridSpots[0][0] = stone0;
		this.gridSpots[0][1] = stone1;
		this.gridSpots[0][2] = stone2;
		this.gridSpots[0][3] = stone3;
		this.gridSpots[0][4] = stone4;
		this.gridSpots[0][5] = stone5;
		this.gridSpots[1][0] = stone6;
		this.gridSpots[1][1] = stone7;
		this.gridSpots[1][2] = stone8;
		this.gridSpots[1][3] = stone9;
		this.gridSpots[1][4] = stone10;
		this.gridSpots[1][5] = stone11;
		this.gridSpots[2][0] = stone12;
		this.gridSpots[2][1] = stone13;
		this.gridSpots[2][2] = stone14;
		this.gridSpots[2][3] = stone15;
		this.gridSpots[2][4] = stone16;
		this.gridSpots[2][5] = stone17;
		this.gridSpots[3][0] = stone18;
		this.gridSpots[3][1] = stone19;
		this.gridSpots[3][2] = stone20;
		this.gridSpots[3][3] = stone21;
		this.gridSpots[3][4] = stone22;
		this.gridSpots[3][5] = stone23;
		this.gridSpots[4][0] = stone24;
		this.gridSpots[4][1] = stone25;
		this.gridSpots[4][2] = stone26;
		this.gridSpots[4][3] = stone27;
		this.gridSpots[4][4] = stone28;
		this.gridSpots[4][5] = stone29;
		this.gridSpots[5][0] = stone30;
		this.gridSpots[5][1] = stone31;
		this.gridSpots[5][2] = stone32;
		this.gridSpots[5][3] = stone33;
		this.gridSpots[5][4] = stone34;
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
    private void initComponents() {

        stone1 = new Stone();
        stone0 = new Stone();
        stone2 = new Stone();
        stone3 = new Stone();
        stone4 = new Stone();
        stone5 = new Stone();
        stone6 = new Stone();
        stone7 = new Stone();
        stone8 = new Stone();
        stone9 = new Stone();
        stone10 = new Stone();
        stone11 = new Stone();
        stone12 = new Stone();
        stone13 = new Stone();
        stone14 = new Stone();
        stone15 = new Stone();
        stone16 = new Stone();
        stone17 = new Stone();
        stone18 = new Stone();
        stone19 = new Stone();
        stone20 = new Stone();
        stone21 = new Stone();
        stone22 = new Stone();
        stone23 = new Stone();
        stone24 = new Stone();
        stone25 = new Stone();
        stone26 = new Stone();
        stone27 = new Stone();
        stone28 = new Stone();
        stone29 = new Stone();
        stone30 = new Stone();
        stone31 = new Stone();
        stone32 = new Stone();
        stone33 = new Stone();
        stone34 = new Stone();
        stone35 = new Stone();
        jLabel1 = new javax.swing.JLabel();
        lblPlayerOneScore = new javax.swing.JLabel();
        lblPlayerTwoScore = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnForfeit = new javax.swing.JButton();
        playerTurnComponent = new PlayerTurnComponent();
        lblPlayerOneName = new javax.swing.JLabel();
        lblPlayerTwoName = new javax.swing.JLabel();

        addAncestorListener(this);

        stone1.setStoneLocation(new Location(0,1));

        stone0.setStoneLocation(new Location(0,0));

        stone2.setStoneLocation(new Location(0,2));

        stone3.setStoneLocation(new Location(0,3));

        stone4.setStoneLocation(new Location(0,4));

        stone5.setStoneLocation(new Location(0,5));

        stone6.setStoneLocation(new Location(1,0));

        stone7.setStoneLocation(new Location(1,1));

        stone8.setStoneLocation(new Location(1,2));

        stone9.setStoneLocation(new Location(1,3));

        stone10.setStoneLocation(new Location(1,4));

        stone11.setStoneLocation(new Location(1,5));

        stone12.setStoneLocation(new Location(2,0));

        stone13.setStoneLocation(new Location(2,1));

        stone14.setStoneLocation(new Location(2,2));

        stone15.setStoneLocation(new Location(2,3));

        stone16.setStoneLocation(new Location(2,4));

        stone17.setStoneLocation(new Location(2,5));

        stone18.setStoneLocation(new Location(3,0));

        stone19.setStoneLocation(new Location(3,1));

        stone20.setStoneLocation(new Location(3,2));

        stone21.setStoneLocation(new Location(3,3));

        stone22.setStoneLocation(new Location(3,4));

        stone23.setStoneLocation(new Location(3,5));

        stone24.setStoneLocation(new Location(4,0));

        stone25.setStoneLocation(new Location(4,1));

        stone26.setStoneLocation(new Location(4,2));

        stone27.setStoneLocation(new Location(4,3));

        stone28.setStoneLocation(new Location(4,4));

        stone29.setStoneLocation(new Location(4,5));

        stone30.setStoneLocation(new Location(5,0));

        stone31.setStoneLocation(new Location(5,1));

        stone32.setStoneLocation(new Location(5,2));

        stone33.setStoneLocation(new Location(5,3));

        stone34.setStoneLocation(new Location(5,4));

        stone35.setStoneLocation(new Location(5,5));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(stone0, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(stone6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(stone12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stone17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(stone18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(stone19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(stone20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(stone21, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(stone22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(stone23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(stone24, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stone25, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stone26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stone27, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stone28, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stone29, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel3))
                                    .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblPlayerOneName)
                                                .addComponent(lblPlayerTwoName))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblPlayerTwoScore, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                                .addComponent(lblPlayerOneScore, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)))
                                        .addComponent(jLabel1)))
                                .addComponent(playerTurnComponent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnForfeit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stone30, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone31, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone32, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone33, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone34, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stone35, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stone5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stone11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stone17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stone23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stone29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stone25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlayerOneScore)
                            .addComponent(lblPlayerOneName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlayerTwoScore)
                            .addComponent(lblPlayerTwoName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(lblTimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerTurnComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnForfeit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stone35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btnForfeit) {
            GridPanel.this.btnForfeitActionPerformed(evt);
        }
    }

    public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        if (evt.getSource() == GridPanel.this) {
            GridPanel.this.formAncestorAdded(evt);
        }
    }

    public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
    }

    public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
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
    private PlayerTurnComponent playerTurnComponent;
    private Stone stone0;
    private Stone stone1;
    private Stone stone10;
    private Stone stone11;
    private Stone stone12;
    private Stone stone13;
    private Stone stone14;
    private Stone stone15;
    private Stone stone16;
    private Stone stone17;
    private Stone stone18;
    private Stone stone19;
    private Stone stone2;
    private Stone stone20;
    private Stone stone21;
    private Stone stone22;
    private Stone stone23;
    private Stone stone24;
    private Stone stone25;
    private Stone stone26;
    private Stone stone27;
    private Stone stone28;
    private Stone stone29;
    private Stone stone3;
    private Stone stone30;
    private Stone stone31;
    private Stone stone32;
    private Stone stone33;
    private Stone stone34;
    private Stone stone35;
    private Stone stone4;
    private Stone stone5;
    private Stone stone6;
    private Stone stone7;
    private Stone stone8;
    private Stone stone9;
    // End of variables declaration//GEN-END:variables
}
