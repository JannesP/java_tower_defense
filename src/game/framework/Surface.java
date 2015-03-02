package game.framework;

import game.framework.resources.Fonts;
import game.framework.screens.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

/**
 * Graphical surface to draw things on.
 * @author Jannes Peters
 */
public class Surface extends JPanel{
	private static final long serialVersionUID = -5781572850886120486L;
	ScreenManager sm;
	
	public Surface() {
		super();
        super.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(0));
        super.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(0));
	}
	
	@Override
	public void paintComponent(Graphics g) {
        if (sm == null) return;

        //setup buffer image for pre-rendering
		BufferedImage bufferedImage = new BufferedImage((int)this.getBounds().getWidth(), (int)this.getBounds().getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D bbg = bufferedImage.createGraphics();
		bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bbg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		bbg.setColor(Color.decode("#FF00D0"));
		bbg.fillRect(0, 0, (int)this.getBounds().getWidth(), (int)this.getBounds().getHeight());
		bbg.setColor(Color.BLACK);
		bbg.setFont(Fonts.getDefaultFont());

		sm.draw(bbg);   //draw all screens
		bbg.dispose();
        g.drawImage(bufferedImage, 0, 0, null); //draw buffered image on surface
	}
	



	/**
	 * Should be called when something has to be redrawn
	 */
	public void paint(ScreenManager sm) {
		this.sm = sm;
		paintComponent(getGraphics());
	}
	
	
	
}
