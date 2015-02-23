package game.framework;

import game.object.tile.TileMap;

import javax.swing.*;

/**
 * Main game.tower.tile.window of the game
 *
 * @author Jannes Peters
 */
public class Window extends JFrame {
    private static final long serialVersionUID = 7446192599263749847L;

    @SuppressWarnings("Reserved for later!")
    public static final double ASPECT_RATIO = (double) TileMap.WIDTH / (double) TileMap.HEIGHT;

    public static double currentScale = 1;

    private static int frameHeight;
    private static int frameWidth;

    Surface surface;

    public Window() {
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setResizable(false);
        surface = new Surface();
        add(surface);
    }

    public void init() {
        Window.frameWidth = super.getWidth() - super.getContentPane().getWidth();
        Window.frameHeight = super.getHeight() - super.getContentPane().getHeight();
        super.setSize((int) ((TileMap.WIDTH * TileMap.DEFAULT_TILE_SIZE + Window.frameWidth) * currentScale), (int) ((TileMap.HEIGHT * TileMap.DEFAULT_TILE_SIZE + Window.frameHeight) * currentScale));

        setLocationRelativeTo(null);
    }

    /**
     * @return the Surface on which is drawn
     */
    public Surface getSurface() {
        return surface;
    }

}
