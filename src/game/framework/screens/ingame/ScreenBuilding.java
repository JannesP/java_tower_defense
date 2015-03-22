package game.framework.screens.ingame;

import game.framework.Util;
import game.framework.screens.ScreenChild;
import game.framework.screens.ScreenManager;
import game.object.tile.Tile;
import game.object.tile.TileMap;
import game.object.tower.TowerTypes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Screen that handles the building of towers or other Objects.
 * Created by Jannes Peters on 3/22/2015.
 */
public class ScreenBuilding extends ScreenChild {
    private static final Color COLOR_NOT_BUILDABLE = new Color(180, 75, 50, 150);
    private static final Color COLOR_BUILDABLE = new Color(0, 150, 25, 150);
    private static final Color COLOR_OVERLAY = new Color(255, 255, 50, 100);
    private static final Color COLOR_RANGE_INNER = new Color(0, 100, 255, 100);
    private static final Color COLOR_RANGE_OUTER = new Color(0, 255, 255, 255);

    private int towerId;
    private Point currentMousePosition = null;

    public ScreenBuilding(String name, int width, int height, Graphics2D g, ScreenGame parent, int towerId) {
        super(name, width, height, g, parent);
        super.zOrder = ScreenManager.ZOrder.FOREGROUND;
        this.towerId = towerId;
        parent.setBuilding(true);
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {}

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {
        for (MouseEvent event : events) {
            currentMousePosition = event.getPoint();
            if (event.isConsumed()) return;
            TileMap tileMap = getParent().getTileMap();
            switch (event.getID()) {
                case MouseEvent.MOUSE_CLICKED:
                    if (event.getButton() == MouseEvent.BUTTON1) {
                        Tile tile = tileMap.getTileAtPixel(event.getPoint());
                        if (tile.isBuildable()) {
                            tile.setTileObject(TowerTypes.getNewTowerById(towerId, getParent().getPlayer().getId(), tile.getCenter()));
                        }
                    } else {
                        this.unload();
                    }
                    break;
            }
        }

    }

    public int getTowerId() {
        return towerId;
    }

    public void setTowerId(int towerId) {
        this.towerId = towerId;
    }

    @Override
    public void unload() {
        this.getParent().setBuilding(false);
        super.unload();
    }

    @Override
    public ScreenGame getParent() {
        return (ScreenGame)super.getParent();
    }

    @Override
    public void draw(Graphics2D g) {
        TileMap tileMap = getParent().getTileMap();

        //draw yellow mark
        Tile mouseOverTile = null;
        if (currentMousePosition != null) {
            mouseOverTile = tileMap.getTileAtPixel(currentMousePosition);
            fillTileWithColor(mouseOverTile, g, COLOR_OVERLAY);
        }

        //draw green/red marks
        for (int x = 0; x < TileMap.WIDTH; x++) {
            for (int y = 0; y < TileMap.HEIGHT; y++) {
                fillTileWithColor(x, y, g, tileMap.getTile(x, y).isBuildable() ? COLOR_BUILDABLE : COLOR_NOT_BUILDABLE);
            }
        }

        //draw the tower in the middle
        if (mouseOverTile != null) {
            g.drawImage(TowerTypes.getTextureById(this.towerId), mouseOverTile.getX(), mouseOverTile.getY(), mouseOverTile.getX() + (int) TileMap.tileSize, mouseOverTile.getY() + (int) TileMap.tileSize, 0, 0, TowerTypes.getTextureById(this.towerId).getHeight(), TowerTypes.getTextureById(this.towerId).getHeight(), null);

            //draw range circle
            Util.fillCenteredCircle(g, (int) mouseOverTile.getCenter().getX(), (int) mouseOverTile.getCenter().getY(), TowerTypes.getRange(towerId, 0) - 1, COLOR_RANGE_INNER);
            Util.drawCenteredCircle(g, (int) mouseOverTile.getCenter().getX(), (int) mouseOverTile.getCenter().getY(), TowerTypes.getRange(towerId, 0), COLOR_RANGE_OUTER);
        }

    }

    private void fillTileWithColor(Tile tile, Graphics2D g, Color color) {
        g.setColor(color);
        g.fillRect(tile.getX(), tile.getY(), (int)TileMap.tileSize, (int)TileMap.tileSize);
    }

    private void fillTileWithColor(int x, int y, Graphics2D g, Color color) {
        g.setColor(color);
        g.fillRect((int)(x * TileMap.tileSize), (int)(y * TileMap.tileSize), (int)TileMap.tileSize, (int)TileMap.tileSize);
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        if (!getParent().isBuilding()) {
            unload();
        }
    }
}
