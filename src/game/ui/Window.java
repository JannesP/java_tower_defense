package game.ui;

import game.framework.Surface;
import game.object.tile.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;

/**
 * Main game.tower.tile.window of the game
 *
 * @author Jannes Peters
 */
public class Window extends JFrame implements ComponentListener {
    private static final long serialVersionUID = 7446192599263749847L;

    public static final double ASPECT_RATIO = (double) TileMap.WIDTH / (double) TileMap.HEIGHT;

    public static double currentScale = 1;
    private static boolean programaticallyResized = false;

    private static int frameHeight;
    private static int frameWidth;

    private static Dimension oldDimensions;

    Surface surface;

    public Window() {
        setTitle("Test");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        surface = new Surface();
        add(surface);
    }

    public void init() {
        Window.frameWidth = super.getWidth() - super.getContentPane().getWidth();
        Window.frameHeight = super.getHeight() - super.getContentPane().getHeight();
        Window.oldDimensions = super.getSize();

        super.setSize(TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE + Window.frameWidth, TileMap.HEIGHT * TileMap.DEFAULT_TILE_SIZE + Window.frameHeight);

        super.addComponentListener(this);
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

    @Override
    public void componentResized(ComponentEvent e) {
        if (programaticallyResized) return;
        Window.programaticallyResized = true;
        JFrame component = (JFrame) e.getComponent();
        if (component.getContentPane().getHeight() == 0) return;
        if ((component.getContentPane().getWidth() / component.getContentPane().getHeight()) != Window.ASPECT_RATIO) {

            int widthDiff = (int) (component.getWidth() - oldDimensions.getWidth());
            int heightDiff = (int) (component.getHeight() - oldDimensions.getHeight());

            if (Math.abs(widthDiff) >= Math.abs(heightDiff)) {
                component.setSize(component.getContentPane().getWidth() + Window.frameWidth, (int) (component.getContentPane().getWidth() / ASPECT_RATIO + Window.frameHeight));
            } else {
                component.setSize((int) (component.getContentPane().getHeight() * ASPECT_RATIO + Window.frameWidth), component.getContentPane().getHeight() + Window.frameHeight);
            }
        }
        currentScale = (double)component.getContentPane().getWidth() / (double)(TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE);
        oldDimensions = component.getSize();
        Window.programaticallyResized = false;
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
