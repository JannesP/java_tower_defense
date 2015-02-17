package game.object.tile.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
/**
 * Main game.object.tile.window of the game
 * @author Jannes Peters
 *
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 7446192599263749847L;
	Surface surface;
	
	public Window() {
		setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 350));
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        surface = new Surface();
        add(surface);
        setSize(10, 250);
        setLocationRelativeTo(null);
	}
	
	public void close() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	/**
	 * @return the Surface on which is drawn
	 */
	public Surface getSurface() {
		return surface;
	}

}
