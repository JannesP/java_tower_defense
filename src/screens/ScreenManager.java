package screens;

import game.object.tile.window.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ScreenManager {
	
	public final static int PADDING = 10;
	
	public enum SCREENSTATE {
		ACTIVE,
		SHUTDOWN,
		HIDDEN
	}
	
	private Rectangle dimensions = null;
	private Window win;
	private ArrayList<BaseScreen> screens = new ArrayList<>();
	private ArrayList<BaseScreen> newScreens = new ArrayList<>();
	
	private ArrayList<MouseEvent> mouseEvents = new ArrayList<>();
	private ArrayList<KeyEvent> keyEvents = new ArrayList<>();
	
	public ScreenManager(Window win){
		this.win = win;
	}
	
	public void update(long timeDiff){
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
		synchronized (keyEvents) { 
			synchronized (mouseEvents) {
				for (BaseScreen foundScreen : screens){
					foundScreen.handleKeyInput(keyEvents);
					foundScreen.handleMouseInput(mouseEvents);
					foundScreen.update(timeDiff);
				}
				
				keyEvents.clear();
				mouseEvents.clear();
			} 
		}
		
	}
	
	public void draw(Graphics2D g) {
		if (dimensions == null || !dimensions.equals(win.getSurface().getBounds())) {
			dimensions = win.getSurface().getBounds();
			setAlignments(dimensions, g);
		}

		for (BaseScreen foundScreen : screens){
			if (foundScreen.state == SCREENSTATE.ACTIVE){
				foundScreen.draw(g);
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
	
	public void unLoadScreen(BaseScreen screen){
		for (BaseScreen foundScreen : screens){
			if(foundScreen.name.equals(screen.name)){
				foundScreen.unLoad();
				break;
			}
		}
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
