package game.framework.screens;

import game.drawable.IPaintableUpdatableObject;
import game.framework.screens.ScreenManager.ScreenState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Basic Screen.
 */
public abstract class BaseScreen implements IPaintableUpdatableObject {

    /**
     * Indicates the current status of the screen.
     */
    public ScreenState state = ScreenState.ACTIVE;

    private BaseScreen[] requestedScreens = null;
	protected String name = "";
    protected int width, height;
    protected ScreenManager.ZOrder zOrder = ScreenManager.ZOrder.MIDDLE;
    protected boolean focused = false;
    protected boolean grabFocus = true;
    protected Graphics2D graphics2D = null;
    protected boolean closeGame = false;

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
        this.graphics2D = g;
	}

    /**
     * Called when the screen should unload. Can be used to save settings, dispose graphics or something like that.
     * Set state to ACTIVE or HIDDEN to cancel shutdown.
     */
	public void unload(){
		state = ScreenState.SHUTDOWN;
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
        this.graphics2D = g;
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
		this(name, (int)width, (int)height, g);
	}

    /**
     * Should be overwritten when it should do something!
     */
    public void closeRequested() {  }

    /**
     * Requests a new screen to be displayed.
     * @param screen - the new screen
     */
    protected void requestScreen(BaseScreen screen) {
        //synchronized (ScreenManager.THREADLOCK_REQUESTED_SCREENS) {
            if (requestedScreens == null) {
                requestedScreens = new BaseScreen[1];
            } else {
                BaseScreen[] buffer = requestedScreens.clone();
                requestedScreens = new BaseScreen[buffer.length + 1];
                for (int i = 0; i < buffer.length; i++) {
                    requestedScreens[i] = buffer[i];
                }
            }
            requestedScreens[requestedScreens.length - 1] = screen;
        //}
    }

    /**
     * Returns all requested screens that should be loaded.
     * @return - null or array with screens
     */
    public BaseScreen[] getRequestedScreens() {
        //synchronized (ScreenManager.THREADLOCK_REQUESTED_SCREENS) {
            return requestedScreens;
        //}
    }

    /**
     * Clears all Screens that has been requested.
     */
    public void clearRequestedScreens() {
        //synchronized (ScreenManager.THREADLOCK_REQUESTED_SCREENS) {
            requestedScreens = null;
        //}
    }

    public ScreenManager.ZOrder getZOrder() {
        return zOrder;
    }

    /**
     * Checks if the screen gave its ok for closing the game.
     * @return true if the game should close
     */
    public boolean isCloseGame() {
        return  closeGame;
    }

    protected void closeGame() {
        closeGame = true;
    }
}
