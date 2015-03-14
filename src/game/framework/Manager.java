package game.framework;

import game.framework.input.Input;
import game.framework.resources.Settings;
import game.framework.screens.ScreenManager;
import game.framework.screens.menu.SplashLoadScreen;
import game.framework.screens.overlays.FpsScreen;
import game.object.tile.TileMap;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Manager that creates the game.tower.tile.window and update thread
 * @author Jannes Peters
 */
public class Manager implements Thread.UncaughtExceptionHandler{
    public static final long REFERENCE_NANO_DIFF = (long)(Util.NANO_SECOND_SECOND / 60);

    public static double targetFps = 60;
    public static long normalFrameNanoDiff = (long)(Util.NANO_SECOND_SECOND / targetFps);
    public static long nextSleep = (long) (Util.NANO_SECOND_SECOND / targetFps);

    private static boolean gameCrashed = false;

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
                if (Manager.nextSleep == 0) continue;
                try {
                    Thread.sleep(Manager.nextSleep / 1000000, (int) (Manager.nextSleep % 1000000));
                } catch (InterruptedException e) {
                    break;
                }
            }
		});
        updateThread.setName("updateThread");
        updateThread.setPriority(Thread.MAX_PRIORITY);
        updateThread.setUncaughtExceptionHandler(this);

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
        s.requestFocus();

        this.initGame();
	}

    public static boolean hasGameCrashed() {
        return (gameCrashed || updateThread == null || updateThread.isAlive());
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
	 * Should be called at least 60 times in one second!
	 */
	private void update() {
		if (lastNanos == 0) {
			lastNanos = System.nanoTime() - (long)(Util.NANO_SECOND_SECOND / targetFps);
		}
        normalFrameNanoDiff = (long)(Util.NANO_SECOND_SECOND / targetFps);
        final long timeDiff = System.nanoTime() - lastNanos;
		final double timeScale = (double)timeDiff / (double)REFERENCE_NANO_DIFF;

        lastNanos = System.nanoTime();

		screenManager.update(timeScale, timeDiff);
		win.getSurface().paint(screenManager);

        long updateNanos = System.nanoTime() - lastNanos;

        Manager.nextSleep = normalFrameNanoDiff - updateNanos;
        if (Manager.nextSleep < 1) {
            Manager.nextSleep = 0;
        }
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

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        if (t.getName().equals("updateThread")) Manager.gameCrashed = true;
    }
}
