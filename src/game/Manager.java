package game;

import game.framework.Input;
import game.framework.Textures;
import game.object.tile.window.Surface;
import game.object.tile.window.Window;
import screens.FpsScreen;
import screens.MainTitleScreen;
import screens.ScreenManager;

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
	
	long lastNanos = 0;
	
	public Manager() {
		Textures.loadImages();
		win = new Window();
		win.setVisible(true);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(16, 666667);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    if (!isExiting) {
                        update();
                    }
				}
			}
		});
		t.start();
		// ADD DEFAULT SCREENS
		Graphics2D g = (Graphics2D) win.getSurface().getGraphics();
		Surface s = win.getSurface();
		s.getGraphics().setFont(new Font("", 0, 26));
		screenManager = new ScreenManager(win);
		input = new Input(screenManager);
		win.addKeyListener(input);
		win.addMouseListener(input);
		s.addKeyListener(input);
		s.addMouseListener(input);
		win.addMouseMotionListener(input);
		s.addMouseMotionListener(input);
		win.addMouseWheelListener(input);
		s.addMouseWheelListener(input);
		
		screenManager.addScreen(new FpsScreen("fpsScreen", s.getBounds().getWidth(), s.getBounds().getHeight() ,g));
		screenManager.addScreen(new MainTitleScreen("titleScreen", (int)s.getBounds().getWidth(), (int)s.getBounds().getHeight(), g, win));
		
	}
	
	/**
	 * Method called to update the whole game and repaint everything. 
	 * Should be called 60 times in one second!
	 */
	private void update() {
		if (lastNanos == 0) {
			lastNanos = 16000000;
		}
		long timeDiff = System.nanoTime() - lastNanos;
		
		screenManager.update(timeDiff);
		win.getSurface().paint(screenManager);
		
		lastNanos = System.nanoTime();
	}
	
	public static void main(String[] args) {
		new Manager();
	}
}
