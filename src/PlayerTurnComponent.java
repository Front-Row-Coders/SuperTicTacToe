
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Jonathan
 * @version 1.0
 */
public class PlayerTurnComponent extends JComponent
{
	private static final long serialVersionUID = 1L;
	
	private Color turnColor = Color.WHITE;
	
	public PlayerTurnComponent()
	{
		
	}
	
	public void setTurnColor(Color color)
	{
		if(color == null)
		{
			throw new IllegalArgumentException("color argument is null");
		}
		this.turnColor = color;
	}
	
	public Color getTurnColor()
	{
		return this.turnColor;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D)g;
		
		graphics2D.setColor(this.turnColor);
		graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
