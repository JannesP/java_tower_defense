package game.object.tile;

import game.Manager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The basic tile class
 */
public class Tile {

	protected BufferedImage image;
	
	public void update(long timeDiff) {
		// TODO Auto-generated method stub
		
	}

	public void paint(Graphics2D g, int x, int y) {
		g.drawImage(image, x, y, Manager.TILESIZE, Manager.TILESIZE, 0, 0, image.getWidth(), image.getHeight(), null);
		
	}

	public void realign(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
