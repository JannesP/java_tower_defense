package screens;

import screens.ScreenManager.SCREENSTATE;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class BaseScreen {

	public String name = "";
	int width, height;
	public SCREENSTATE state = SCREENSTATE.ACTIVE;
	public float zOrder;
	public boolean focused = false;
	public boolean grabFocus = true;
	
	public abstract void handleKeyInput(ArrayList<KeyEvent> events);
	
	public abstract void handleMouseInput(ArrayList<MouseEvent> events);
	
	public abstract void update(long timeDiff);
	
	public abstract void draw(Graphics2D g);
	
	public void realign(Rectangle rect, Graphics2D g) {
		realign((int) rect.getWidth(), (int) rect.getHeight(), g);
	}
	
	public void realign(int width, int height, Graphics2D g) {
		this.width = width;
		this.height = height;
	}
	
	public void unLoad(){
		state = SCREENSTATE.SHUTDOWN;
	}
	
	public BaseScreen(String name, int width, int height, Graphics2D g) {
		this.name = name;
		realign(width, height, g);
	}
	
	public BaseScreen(String name, double width, double height, Graphics2D g) {
		this.name = name;
		realign((int) width, (int) this.height, g);
	}
}
