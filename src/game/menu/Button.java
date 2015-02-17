package game.menu;

import game.drawable.PaintableUpdatableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Button implements PaintableUpdatableObject {
	STATE state = STATE.NORMAL;
	BufferedImage image;
	int x, y, width, height, spriteY;
	int stringX, stringY;
	String text;
	
	/**
	 * @param image - Image
	 */
	public Button(int x, int y, int width, int height, BufferedImage image, String text, Graphics2D g){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		this.text = text;
		realign(g);
	}
	public enum STATE{
		NORMAL,
		HOVER,
		PRESSED
	}
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public STATE getState() {
		return state;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	
	@Override
	public void update(long timeDiff) {
		switch (state){
		case NORMAL:
			spriteY = 0;
			break;
		case HOVER:
			spriteY = image.getHeight() / 3;
			break;
		case PRESSED:
			spriteY = image.getHeight() / 3 * 2;
			break;
		}

		
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, x, y, width + x, height + y, 0, spriteY, image.getWidth(), image.getHeight() / 3 + spriteY, null);
		g.drawString(text, stringX, stringY);
	}

	@Override
	public void realign(Graphics2D g) {
		stringX = x + width / 2 - g.getFontMetrics().stringWidth(text) / 2;
		stringY = y + height / 2 - g.getFontMetrics().getHeight() / 2 + g.getFontMetrics().getAscent();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
