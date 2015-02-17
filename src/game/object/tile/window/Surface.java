package game.object.tile.window;

import screens.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Graphical surface to draw things on.
 * @author Jannes Peters
 */
public class Surface extends JPanel{
	private static final long serialVersionUID = -5781572850886120486L;
	ScreenManager sm;

	/**
	 * Current Dimensions of the JPanel
	 */
	Rectangle dimensions = null;
	
	public Surface() {
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		BufferedImage bufferedImage = new BufferedImage((int)this.getBounds().getWidth(), (int)this.getBounds().getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D bbg = bufferedImage.createGraphics();
		bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bbg.setColor(Color.decode("#FF00D0"));
		bbg.fillRect(0, 0, (int)this.getBounds().getWidth(), (int)this.getBounds().getHeight());
		bbg.setColor(Color.BLACK);
		bbg.setFont(new Font("", 0, 26));
		
		if (sm == null) return;
		sm.draw((Graphics2D)bbg);
		g.drawImage(bufferedImage, 0, 0, null); 
	}
	

	/**
	 * Should be called when something has to be redrawn
	 */
	public void paint(ScreenManager sm) {
		this.sm = sm;
		paintComponent(getGraphics());
	}
	
	
	
}
