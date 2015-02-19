package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Graphical surface to draw things on.
 * @author Jannes Peters
 */
public class Surface extends JPanel{
	private static final long serialVersionUID = -5781572850886120486L;

    private Map map = null;

	public Surface() {
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
        if (this.map == null || this.getBounds().getHeight() == 0 || this.getBounds().getWidth() == 0) return;
        //setup buffer image for pre-rendering
		BufferedImage bufferedImage = new BufferedImage((int)this.getBounds().getWidth(), (int)this.getBounds().getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D bbg = bufferedImage.createGraphics();
		bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bbg.setColor(Color.decode("#FF00D0"));
		bbg.fillRect(0, 0, (int)this.getBounds().getWidth(), (int)this.getBounds().getHeight());
		bbg.setColor(Color.BLACK);
		bbg.setFont(new Font("", 0, 26));   //TODO select default font

		g.drawImage(this.map.createGridBitmap(), 0, 0, null); //draw buffered image on surface
	}

	/**
	 * Should be called when something has to be redrawn
	 */
	public void paint(int mouseX, int mouseY, Map map) {
        this.map = map;
		paintComponent(super.getGraphics());
        Graphics2D g = (Graphics2D) super.getGraphics();
        g.setPaint(new Color(255, 200, 0, 120));
        Point coordinates = Map.resolvePixelToMatrix(mouseX, mouseY);
        g.fillRect((int)coordinates.getX() * Map.TILE_SIZE, (int)coordinates.getY() * Map.TILE_SIZE, Map.TILE_SIZE, Map.TILE_SIZE);
	}
	
	
}
