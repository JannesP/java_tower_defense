package game.framework;

import game.framework.resources.Fonts;
import game.framework.resources.Settings;
import game.framework.screens.FpsScreen;
import game.framework.screens.ScreenManager;
import game.framework.screens.SplashLoadScreen;
import game.object.tile.TileMap;
import javafx.embed.swing.JFXPanel;

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

        Thread thread = new Thread(() -> {
            long timeWaitingStarted = System.currentTimeMillis();
            while (win.getWidth() != (TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE + win.getFrameWidth()) * Settings.resolutionScale) {
                System.out.println(win.getWidth());
                System.out.println((TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE + win.getFrameWidth()) * Settings.resolutionScale);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Waited " + (System.currentTimeMillis() - timeWaitingStarted) + "ms for Surface sizing properly!");
            //add default screens
            screenManager.addScreen(new SplashLoadScreen("splashScreen", (int) s.getBounds().getWidth(), (int) s.getBounds().getHeight(), g));
            screenManager.addScreen(new FpsScreen("fpsScreen", (int) s.getBounds().getWidth(), (int) s.getBounds().getHeight() ,g));
            updateThread.start();
        });
        thread.setName("startThread");
        thread.start();
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
