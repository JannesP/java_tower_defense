package game.framework.screens;

import game.framework.Manager;
import game.framework.Util;
import game.framework.resources.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A screen that displays the current fps.
 */
public class FpsScreen extends BaseScreen {
	private int x, y;
    private int currIndex = 0;
    private float[] lastFps = new float[20];
    private int calcFps = 60;
    private double lastTargetFps = Manager.targetFps;

	public FpsScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        super.zOrder = ScreenManager.ZOrder.OVER_UI;
	}

	@Override
	public void update(double timeScale, long timeDiff) {
        if (super.state == ScreenManager.ScreenState.ACTIVE) {
            currIndex = (currIndex + 1) % lastFps.length;
            lastFps[currIndex] = (float) (Util.NANO_SECOND_SECOND / timeDiff);
            calcFps = calculateAverage(lastFps);
        }
	}

    public int calculateAverage(float[] array) {
        double sum = 0;
        for (float anArray : array) {
            sum += anArray;
        }
        return (int)(sum / array.length);
    }

	@Override
	public void draw(Graphics2D g) {
        g.setFont(Fonts.fpsFont);
        x = this.width - Util.PADDING - g.getFontMetrics().stringWidth(String.valueOf(calcFps));
        g.setColor(Color.LIGHT_GRAY);
		g.drawString(String.valueOf(calcFps), x, y);
	}

	@Override
	public void realign(int width, int height, Graphics2D g) {
		super.realign(width, height, g);
        g.setFont(Fonts.fpsFont);
        g.setColor(Color.WHITE);
		x = this.width - Util.PADDING - g.getFontMetrics().stringWidth(String.valueOf((int)Manager.targetFps));
		y = Util.PADDING + g.getFontMetrics().getHeight() / 2;
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) {
		for (KeyEvent e : events){
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == 112){
					this.state = this.state == ScreenManager.ScreenState.ACTIVE ? ScreenManager.ScreenState.HIDDEN : ScreenManager.ScreenState.ACTIVE;

				}
			}
		}

	}

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) { }






}
