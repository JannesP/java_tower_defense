package game.ui;

import game.drawable.IPaintableUpdatableObject;
import game.framework.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Button implements IPaintableUpdatableObject {
	protected STATE state = STATE.NORMAL;
    protected BufferedImage image;
    protected int x, y, width, height, spriteY;
    protected int stringX, stringY;
    protected String text;
	
	/**
     * Creates a new button with the given parameters.
	 * @param image - Image
	 */
	public Button(int x, int y, int width, int height, BufferedImage image, String text, Graphics2D g){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		this.text = text;
		realign(width, height, g);
	}
	public enum STATE{
		NORMAL,
		HOVER,
		PRESSED
	}
	
	public void setState(STATE state) {
		this.state = state;
	}

    /**
     * Gets the current state of the button.
     * @return - Button.STATE that's currently set
     */
	public STATE getState() {
		return state;
	}

    /**
     * Get REKT ... ääähh
     * @return - the dimensions of the Button as a Rectangle
     */
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
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, width + x, height + y, 0, spriteY, image.getWidth(), image.getHeight() / 3 + spriteY, null);
		g.drawString(text, stringX, stringY);
	}

	@Override
	public void realign(int width, int height, Graphics2D g) {
        stringX = x + Util.calculateCenterPosition(this.width, g.getFontMetrics().stringWidth(text));
        stringY = y + Util.calculateCenterPosition(this.height, g.getFontMetrics().getHeight()) + g.getFontMetrics().getAscent();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
