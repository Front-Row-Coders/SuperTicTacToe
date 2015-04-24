
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * Component that display a specified color for displaying player's turns.
 * @author Jonathan
 * @version 1.0
 */
public class PlayerTurnComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	
	private Color turnColor = Color.WHITE;
	
	/**
	 * Creates an instance of PlayerTurnComponent.
	 */
	public PlayerTurnComponent()
	{
		
	}
	
	/**
	 * Sets a new turn color.
	 * @param color A non-null color to be set.
	 */
	public void setTurnColor(Color color)
	{
		if(color == null)
		{
			throw new IllegalArgumentException("color argument is null");
		}
		this.turnColor = color;
	}
	
	/**
	 * Gets the current turn color.
	 * @return The current turn color.
	 */
	public Color getTurnColor()
	{
		return this.turnColor;
	}
	
	/**
	 * Paints the component the specified color. (Called by Java UI)
	 * @param g The graphics object.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D)g;
		
		graphics2D.setColor(this.turnColor);
		graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
