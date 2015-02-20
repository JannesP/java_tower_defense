package screens;

import game.Manager;
import game.window.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ScreenManager {

    public final static Object THREADLOCK_REQUESTED_SCREENS = new Object();
	public final static int PADDING = 10;

    public void closeRequested() {
        closeRequested = true;
    }

    public enum SCREENSTATE {
		ACTIVE,
		SHUTDOWN,
		HIDDEN
	}

    private volatile boolean closeRequested = false;
	private Rectangle dimensions = null;
	private Window win;
	private ArrayList<BaseScreen> screens = new ArrayList<>();
	private ArrayList<BaseScreen> newScreens = new ArrayList<>();
	
	private volatile ArrayList<MouseEvent> mouseEvents = new ArrayList<>();
	private volatile ArrayList<KeyEvent> keyEvents = new ArrayList<>();
	
	public ScreenManager(Window win){
		this.win = win;
	}
	
	public void update(long timeDiff){
        // ADD REQUESTED SCREENS
        for (BaseScreen foundScreen : screens) {
            if (foundScreen.getRequestedScreens() != null) {
                for (BaseScreen reqScreen : foundScreen.getRequestedScreens()) {
                    newScreens.add(reqScreen);
                }
                foundScreen.clearRequestedScreens();
            }
        }

		// REMOVE DEAD SCREENS
		for (BaseScreen foundScreen : screens){
			if (foundScreen.state == SCREENSTATE.SHUTDOWN){
				screens.remove(foundScreen);
			} else {
				foundScreen.focused = false;
			}
		}
		
		// ADD NEW SCREENS TO MANAGER LIST
		for (BaseScreen foundScreen : newScreens){
			screens.add(foundScreen);
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
		
		// HANDLE INPUT FOR FOCUSED SCREEN
        for (BaseScreen foundScreen : screens){
            foundScreen.handleKeyInput(keyEvents);
            foundScreen.handleMouseInput(mouseEvents);
            foundScreen.update(timeDiff);
            if (foundScreen.isCloseGame()) {
                Manager.closeRequested();
            }
        }
        keyEvents.clear();
        mouseEvents.clear();

        //Handle game closing
        if (closeRequested) {
            for (BaseScreen foundScreen : screens) {
                foundScreen.closeRequested();
            }
            closeRequested = false;
        }
	}
	
	public void draw(Graphics2D g) {
		if (dimensions == null || !dimensions.equals(win.getSurface().getBounds())) {   //check if dimensions changed
			dimensions = win.getSurface().getBounds();
			setAlignments(dimensions, g);   //recalculate drawing positions for UI
		}

		for (BaseScreen foundScreen : screens){ //draw all screens  //TODO use zOrder
			if (foundScreen.state == SCREENSTATE.ACTIVE){   //if screen should be drawn
				foundScreen.draw(g);    //draw screen
			}
		}
	}
	
	private void setAlignments(Rectangle rect, Graphics2D g) {
		for (BaseScreen foundScreen : screens) {
			foundScreen.realign(rect, g);
		}
		
	}

	public void addScreen(BaseScreen screen){
		newScreens.add(screen);
	}
	
	public void unLoadScreen(String screenName){
		for (BaseScreen foundScreen : screens){
			if(foundScreen.name.equals(screenName)){
				foundScreen.unLoad();
				break;
			}
		}
	}
	
	public void gotEvent(KeyEvent e) {
		keyEvents.add(e);
	}
	
	public void gotEvent(MouseEvent e) {
		mouseEvents.add(e);
	}
	
}
