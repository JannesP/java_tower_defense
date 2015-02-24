package game.framework;

import game.framework.resources.Settings;
import game.object.tile.TileMap;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Main game.tower.tile.Window of the game
 *
 * @author Jannes Peters
 */
public class Window extends JFrame implements ComponentListener {
    private static final long serialVersionUID = 7446192599263749847L;

    @SuppressWarnings("Reserved for later!")
    public static final double ASPECT_RATIO = (double) TileMap.WIDTH / (double) TileMap.HEIGHT;

    public static double currentScale = 1;

    private static int frameHeight;
    private static int frameWidth;

    private Surface surface;

    public Window() {
        super.setTitle("Tower Defence");
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        super.setSize(100, 100);
        JFrame.setDefaultLookAndFeelDecorated(true);
        super.addComponentListener(this);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setResizable(false);
        surface = new Surface();
        super.add(surface);
    }

    public void init() {
        Window.frameWidth = super.getWidth() - super.getContentPane().getWidth();
        Window.frameHeight = super.getHeight() - super.getContentPane().getHeight();
        super.setSize((int) ((TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE + Window.frameWidth) * Settings.resolutionScale), (int) ((TileMap.HEIGHT * TileMap.DEFAULT_TILE_SIZE + Window.frameHeight) * Settings.resolutionScale));

        setLocationRelativeTo(null);
    }

    public int getFrameWidth() {
        return super.getWidth() - super.getContentPane().getWidth();
    }

    /**
     * @return the Surface on which is drawn
     */
    public Surface getSurface() {
        return surface;
    }

    @Override
    public void componentResized(ComponentEvent e) {}
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}
}
