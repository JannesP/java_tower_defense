package game;

import game.framework.Input;
import game.object.tile.window.Surface;
import game.object.tile.window.Window;
import screens.FpsScreen;
import screens.ScreenManager;
import screens.SplashScreen;

import javax.swing.*;
import java.awt.*;

/**
 * Manager that creates the game.object.tile.window and update thread
 * @author Jannes Peters
 *
 */
public class Manager {
	public static final int TILESIZE = 20;

    public static boolean isExiting = false;

	private Window win;
	private ScreenManager screenManager;
	private Input input;
    private static Thread updateThread;
	
	long lastNanos = 0;
	
	public Manager() {
		win = new Window();
        win.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        Thread.currentThread().setName("uiThread");

        updateThread = new Thread(() -> {
            gameLoop : while (true) {
                try {
                    Thread.sleep(16, 666667);
                } catch (InterruptedException e) {
                    break gameLoop;
                }
                if (!isExiting) {
                    update();
                }
            }
		});
        updateThread.setName("updateThread");

        screenManager = new ScreenManager(win);
        input = new Input(screenManager);
        Surface s = win.getSurface();

        //Add event listeners
        win.addWindowListener(input);
        s.addKeyListener(input);
        s.addMouseListener(input);
        s.addMouseMotionListener(input);
        s.addMouseWheelListener(input);
        win.setVisible(true);

		// setup graphics
		Graphics2D g = (Graphics2D) s.getGraphics();
		s.getGraphics().setFont(new Font("", 0, 26));
        s.requestFocus();

        //add default screens
		screenManager.addScreen(new SplashScreen("splashScreen", s.getBounds().getWidth(), s.getBounds().getHeight(), g));
		screenManager.addScreen(new FpsScreen("fpsScreen", s.getBounds().getWidth(), s.getBounds().getHeight() ,g));

        updateThread.start();
		
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
