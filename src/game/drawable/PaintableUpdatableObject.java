package game.drawable;

import java.awt.*;

public interface PaintableUpdatableObject {

    /**
     * Called once every frame.
     * @param timeDiff - Time difference till the last frame to scale game speed in nanoseconds.
     */
	public void update(long timeDiff);

    /**
     * Called once a frame to draw the screen.
     * @param g - Graphics2D to draw on
     */
	public void draw(Graphics2D g);

    /**
     * Called when the window resizes. Mainly to change position of UI elements.
     * @param width - width of the new surface
     * @param height - height of the new surface
     * @param g - Graphics that changed
     */
    public void realign(int width, int height, Graphics2D g);
}
