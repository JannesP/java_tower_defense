package game.framework;

import game.framework.resources.Fonts;
import game.framework.resources.Settings;
import game.framework.screens.FpsScreen;
import game.framework.screens.ScreenManager;
import game.framework.screens.SplashLoadScreen;
import game.object.tile.TileMap;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Manager that creates the game.tower.tile.window and update thread
 * @author Jannes Peters
 *
 */
public class Manager {

	private Window win;
	private ScreenManager screenManager;
    private static Thread updateThread;
	
	long lastNanos = 0;
	
	public Manager() {
        Thread.currentThread().setName("uiThread");

        @SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();

        win = new Window();

        updateThread = new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(16, 666667);
                } catch (InterruptedException e) {
                    break;
                }
            }
		});
        updateThread.setName("updateThread");

        screenManager = new ScreenManager(win);
        Input input = new Input(screenManager);
        Surface s = win.getSurface();

        //Add event listeners
        win.addWindowListener(input);
        s.addKeyListener(input);
        s.addMouseListener(input);
        s.addMouseMotionListener(input);
        s.addMouseWheelListener(input);
        win.setVisible(true);
        win.init();
		// setup graphics
		Graphics2D g = (Graphics2D) s.getGraphics();
		g.setFont(Fonts.getDefaultFont());
        s.requestFocus();

        this.initGame();
	}

    private void initGame() {
        if ((win.getWidth() == (TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE + win.getFrameWidth()) * Settings.resolutionScale)) {
            screenManager.addScreen(new SplashLoadScreen("splashScreen", (int) win.getSurface().getBounds().getWidth(), (int) win.getSurface().getBounds().getHeight(), (Graphics2D)win.getSurface().getGraphics()));
            screenManager.addScreen(new FpsScreen("fpsScreen", (int) win.getSurface().getBounds().getWidth(), (int) win.getSurface().getBounds().getHeight(), (Graphics2D)win.getSurface().getGraphics()));
            updateThread.start();
        } else {
            SwingUtilities.invokeLater(this::initGame);
        }
    }

	/**
	 * Method called to update the whole game and repaint everything. 
	 * Should be called 60 times in one second!
	 */
	private void update() {
		if (lastNanos == 0) {
			lastNanos = System.nanoTime();
		}
		long timeDiff = System.nanoTime() - lastNanos;
		
		screenManager.update(timeDiff);
		win.getSurface().paint(screenManager);
		
		lastNanos = System.nanoTime();
	}
	
	public static void main(String[] args) {
		new Manager();
	}

    public static void closeRequested() {
        if (updateThread != null && updateThread.isAlive()) {
            updateThread.interrupt();
        }
        System.exit(0);
    }
}
