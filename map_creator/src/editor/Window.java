package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Jannes Peters on 2/19/2015.
 */
public class Window extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

    private boolean mousePressed = false;
    private Map.TileType selectedTileType = Map.TileType.NOTHING;
    private Map map;
    private Surface surface;


    public Window() {
        setTitle("Tower Defence editor.Map Creator!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        surface = new Surface();
        surface.setSize(Map.WIDTH * Map.TILE_SIZE, Map.HEIGHT * Map.TILE_SIZE);

        add(surface);
        super.setVisible(true);
        super.setSize(1006, 635);
        surface.setBackground(Color.PINK);
        setLocationRelativeTo(null);
        map = new Map();
        surface.paint(map);
        surface.addMouseListener(this);
        surface.addMouseMotionListener(this);
        surface.addKeyListener(this);
        surface.requestFocus();
    }

    private void changeTile(MouseEvent e) {
        map.setTileAtPixel(e.getX(), e.getY(), selectedTileType);
        surface.paint(e.getX(), e.getY(), this.map);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                this.selectedTileType = Map.TileType.NOTHING;
                System.out.println("Selecting: " + this.selectedTileType);
                break;
            case KeyEvent.VK_2:
                this.selectedTileType = Map.TileType.ROAD;
                System.out.println("Selecting: " + this.selectedTileType);
                break;
            case KeyEvent.VK_3:
                this.selectedTileType = Map.TileType.NOT_BUILDABLE;
                System.out.println("Selecting: " + this.selectedTileType);
                break;
            case KeyEvent.VK_E:
                this.selectedTileType = Map.TileType.ENTRANCE;
                System.out.println("Selecting: " + this.selectedTileType);
                break;
            case KeyEvent.VK_C:
                this.selectedTileType = Map.TileType.CASTLE;
                System.out.println("Selecting: " + this.selectedTileType);
                break;
            case KeyEvent.VK_L:
                try {
                    this.map = MapIO.loadMap(this);
                    surface.paint(this.map);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_S:
                MapIO.saveMap(this, map);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        changeTile(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        changeTile(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        surface.paint(e.getX(), e.getY(), this.map);
    }

    @Override
    public void mouseReleased(MouseEvent e) {   }
    @Override
    public void keyTyped(KeyEvent e) {  }
    @Override
    public void keyReleased(KeyEvent e) {   }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseClicked(MouseEvent e) {    }


}
