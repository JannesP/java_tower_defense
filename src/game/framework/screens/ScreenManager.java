package game.framework.screens;

import game.framework.Manager;
import game.framework.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manager class for all screens.
 */
public class ScreenManager {

	public final static int PADDING = 10;

    public void closeRequested() {
        closeRequested = true;
    }

    public enum ScreenState {
		ACTIVE,
		SHUTDOWN,
		HIDDEN
	}

    @SuppressWarnings("Saved for later.!")
    public enum ZOrder {
        BACKGROUND(0), MIDDLE(1), FOREGROUND(2), UI(3), OVER_UI(4), TOP_MOST(5);
        private int index;

        private ZOrder(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    private volatile boolean closeRequested = false;
	private Rectangle dimensions = null;
	private Window win;

    private boolean screensChanged = true;
	private ArrayList<ScreenBase> screens = new ArrayList<>();
	private ArrayList<ScreenBase> newScreens = new ArrayList<>();
	
	private static final ArrayList<MouseEvent> mouseEvents = new ArrayList<>();
	private static final ArrayList<KeyEvent> keyEvents = new ArrayList<>();
	
	public ScreenManager(Window win){
		this.win = win;
	}
	
	public void update(double timeScale, long timeDiff){
        // ADD REQUESTED SCREENS
        for (ScreenBase foundScreen : screens) {
            if (foundScreen.getRequestedScreens() != null) {
                Collections.addAll(newScreens, foundScreen.getRequestedScreens());
                foundScreen.clearRequestedScreens();
            }
        }

		// REMOVE DEAD SCREENS
		for (ScreenBase foundScreen : screens){
			if (foundScreen.getState() == ScreenState.SHUTDOWN){
                screens.remove(foundScreen);
                break;
			} else {
				foundScreen.focused = false;
			}
		}
		
		// ADD NEW SCREENS TO MANAGER LIST
		for (ScreenBase foundScreen : newScreens){
			screens.add(foundScreen);
            screensChanged = true;
		}
		
		newScreens.clear();
		
		// CHECK SCREEN FOCUS
		if (screens.size() > 0){
			for(int i = screens.size()-1; i >= 0; i--) {
				if (screens.get(i).grabFocus){
					screens.get(i).focused = true;
					break;
				}
			}
		}

		synchronized (keyEvents) {
            synchronized (mouseEvents) {
                // HANDLE INPUT FOR FOCUSED SCREEN
                for (ScreenBase foundScreen : screens) {
                    foundScreen.handleKeyInput(keyEvents);
                    foundScreen.handleMouseInput(mouseEvents);
                    if (foundScreen.isCloseGame()) {
                        Manager.closeRequested();
                    }
                }
                keyEvents.clear();
                mouseEvents.clear();
            }
        }

        // UPDATE ALL SCREENS
        for (ScreenBase foundScreen : screens) {
            foundScreen.update(timeScale, timeDiff);
        }

        //Handle game closing
        if (closeRequested) {
            for (ScreenBase foundScreen : screens) {
                foundScreen.closeRequested();
            }
            closeRequested = false;
        }

        if (screensChanged) {
            screens.sort((a, b) -> Integer.compare(a.getZOrder().getIndex(), b.getZOrder().getIndex()));
            screensChanged = false;
        }
	}
	
	public void draw(Graphics2D g) {
        if (dimensions == null || !dimensions.equals(win.getContentPane().getBounds())) {
            dimensions = win.getSurface().getBounds();
            setAlignments(dimensions, g);   //recalculate drawing positions for UI
        }
		for (ScreenBase foundScreen : screens){ //draw all screens
			if (foundScreen.getState() == ScreenState.ACTIVE){   //if screen should be drawn
				foundScreen.draw(g);    //draw screen
			}
		}
	}
	
	private void setAlignments(Rectangle rect, Graphics2D g) {
		for (ScreenBase foundScreen : screens) {
			foundScreen.realign(rect, g);
		}
		
	}

	public void addScreen(ScreenBase screen){
		newScreens.add(screen);
	}
	
	public void gotEvent(KeyEvent e) {
        synchronized (keyEvents) {
            keyEvents.add(e);
        }
	}
	
	public void gotEvent(MouseEvent e) {
        synchronized (mouseEvents) {
            mouseEvents.add(e);
        }
	}
	
}
