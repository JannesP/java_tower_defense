package screens;


import screens.ScreenManager.SCREENSTATE;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class FpsScreen extends BaseScreen {

	private int x, y;
	
	public FpsScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
	}
	
	public FpsScreen(String name, double width, double height, Graphics2D g) {
		super(name, width, height, g);
	}

	long timeDiff;
	@Override
	public void update(long timeDiff) {
		this.timeDiff = timeDiff;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawString("FPS: " + String.valueOf((long)Math.floor(1d / ((double)timeDiff / 1000000000d))), x, y);
		
	}
	
	@Override
	public void realign(int width, int height, Graphics2D g) {
		super.realign(width, height, g);
		x = this.width - ScreenManager.PADDING - g.getFontMetrics().stringWidth("FPS: 60");
		y = ScreenManager.PADDING * 3;
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) {
		for (KeyEvent e : events){
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == 112){
					this.state = this.state == SCREENSTATE.ACTIVE ? SCREENSTATE.HIDDEN : SCREENSTATE.ACTIVE;
					
				}
			}
		}
		
	}

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) { }

	

	


}
