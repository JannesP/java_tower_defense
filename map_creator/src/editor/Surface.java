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

        BufferedImage img = this.map.createGridBitmap();

        g.drawImage(img, 0, 0, null);
	}

    public void paint(Map map) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, this);
        paint((int) mouseLocation.getX(), (int) mouseLocation.getY(), map);
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
