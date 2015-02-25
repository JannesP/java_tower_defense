package game.ui.button;

import game.drawable.IPaintableUpdatableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Button implements IPaintableUpdatableObject {
	protected STATE state = STATE.NORMAL;
    protected BufferedImage backgroundImage;
    protected int x, y, width, height, spriteY;
    protected String action;

    protected Button(int x, int y, int width, int height, BufferedImage backgroundImage, Graphics2D g, String action) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundImage = backgroundImage;
        this.action = action;
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
			spriteY = backgroundImage.getHeight() / 3;
			break;
		case PRESSED:
			spriteY = backgroundImage.getHeight() / 3 * 2;
			break;
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(backgroundImage, x, y, width + x, height + y, 0, spriteY, backgroundImage.getWidth(), backgroundImage.getHeight() / 3 + spriteY, null);
	}

	@Override
	public abstract void realign(int width, int height, Graphics2D g);

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

    public String getAction() {
        return action;
    }

}
