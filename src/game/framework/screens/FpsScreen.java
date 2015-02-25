package game.framework.screens;


import game.framework.Util;
import game.framework.resources.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class FpsScreen extends BaseScreen {

	private int x, y;

	public FpsScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        super.zOrder = ScreenManager.ZOrder.OVER_UI;
	}

	long timeDiff;
	@Override
	public void update(long timeDiff) {
		this.timeDiff = timeDiff;
	}

	@Override
	public void draw(Graphics2D g) {
        g.setFont(Fonts.fpsFont);
		g.drawString(String.valueOf((long)Math.floor(1d / ((double)timeDiff / 1000000000d))), x, y);

	}

	@Override
	public void realign(int width, int height, Graphics2D g) {
		super.realign(width, height, g);
        g.setFont(Fonts.fpsFont);
        g.setColor(Color.WHITE);
		x = this.width - Util.PADDING - g.getFontMetrics().stringWidth("60");
		y = Util.PADDING + g.getFontMetrics().getHeight() / 2;
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) {
		for (KeyEvent e : events){
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == 112){
					this.state = this.state == ScreenManager.SCREENSTATE.ACTIVE ? ScreenManager.SCREENSTATE.HIDDEN : ScreenManager.SCREENSTATE.ACTIVE;

				}
			}
		}

	}

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) { }






}
