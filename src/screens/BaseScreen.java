package screens;

import game.drawable.PaintableUpdatableObject;
import screens.ScreenManager.SCREENSTATE;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class BaseScreen implements PaintableUpdatableObject {

    /**
     * Indicates the current status of the screen.
     */
    public SCREENSTATE state = SCREENSTATE.ACTIVE;

	protected String name = "";
    protected int width, height;
    protected float zOrder;
    protected boolean focused = false;
    protected boolean grabFocus = true;

    /**
     * Function called once every frame with all Key Events to process.
     * Work from high to low.
     * @param events - List of Key Events
     */
	public abstract void handleKeyInput(ArrayList<KeyEvent> events);

    /**
     * Function called once every frame with all Mouse Events to process.
     * Work from high to low.
     * @param events - List of Mouse Events
     */
	public abstract void handleMouseInput(ArrayList<MouseEvent> events);

    /**
     * Called when the window resizes. Mainly to change position of UI elements.
     * @param rect - Bounds of the current Surface
     * @param g - Graphics that changed
     */
	public void realign(Rectangle rect, Graphics2D g) {
		realign((int) rect.getWidth(), (int) rect.getHeight(), g);
	}

    /**
     * Called when the window resizes. Mainly to change position of UI elements.
     * @param width - width of the new surface
     * @param height - height of the new surface
     * @param g - Graphics that changed
     */
	public void realign(int width, int height, Graphics2D g) {
		this.width = width;
		this.height = height;
	}

    /**
     * Called when the screen should unload. Can be used to save settings, dispose graphics or something like that.
     * Set state to ACTIVE or HIDDEN to cancel shutdown.
     */
	public void unLoad(){
		state = SCREENSTATE.SHUTDOWN;
	}

    /**
     * Creates a new screen based on the given parameters.
     * @param name - Name or ID of the screen
     * @param width - width of the screen
     * @param height - height of the screen
     * @param g - graphics on which it should be created
     */
	public BaseScreen(String name, int width, int height, Graphics2D g) {
		this.name = name;
		realign(width, height, g);
	}

    /**
     * Creates a new screen based on the given parameters.
     * @param name - Name or ID of the screen
     * @param width - width of the screen
     * @param height - height of the screen
     * @param g - graphics on which it should be created
     */
	public BaseScreen(String name, double width, double height, Graphics2D g) {
		this.name = name;
		realign((int) width, (int) height, g);
	}
}
