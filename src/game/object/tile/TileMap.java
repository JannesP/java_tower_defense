package game.object.tile;

import game.framework.Map;
import game.framework.resources.Maps;
import game.framework.resources.Textures;
import game.object.enemy.Enemy;
import game.object.tower.Castle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 2/21/2015.
 */
public class TileMap {

    public static int tileSize = 30;
    public static int width = 33;
    public static int height = 20;

    private BufferedImage background;
    private Tile[][] tileMap;

    public TileMap(int level) {
        this(Maps.maps[level], Textures.backgrounds[level]);
    }

    private static Tile[][] createTileMap(Map map) {
        Tile[][] tileMap = new Tile[map.getMatrix().length][map.getMatrix()[0].length];
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                switch (Map.TileType.byByteValue(map.getMatrix()[x][y])) {
                    case NOTHING:
                        tileMap[x][y] = new Tile(x * tileSize, y * tileSize);
                        break;
                    case ROAD:
                        tileMap[x][y] = new Tile(x * tileSize, y * tileSize, true);
                        break;
                    case NOT_BUILDABLE:
                        tileMap[x][y] = new Tile(x * tileSize, y * tileSize, false, false);
                        break;
                    case ENTRANCE:
                        tileMap[x][y] = new Tile(x * tileSize, y * tileSize, true, false, true);
                        break;
                    case CASTLE:
                        tileMap[x][y] = new Tile(x * tileSize, y * tileSize, false, true);
                        tileMap[x][y].setTileObject(new Castle());
                        break;
                }
            }
        }
        return tileMap;
    }

    public TileMap(Map map, BufferedImage background) {
        this.background = background;
        this.tileMap = TileMap.createTileMap(map);
    }

    public TileMap(Tile[][] tileMap, BufferedImage background) {
        this.background = background;
        this.tileMap = tileMap;
    }

    /**
     * Returns the tile at the given x and y with the matrix coordinates!
     * @param x
     * @param y
     * @exception - Throws an exception if x or y doesn't fit for the tileMap!
     * @return - the <code>Tile</code> at the position
     */
    public Tile getTile(int x, int y) {
        if (doCoordinatesFit(x, y)) {
            throw new IllegalArgumentException("x or y is not in the bounds!");
        }
        return tileMap[x][y];
    }

    public Tile getTileAtPixel(int x, int y) {
        int resX, resY;
        resX = x / this.tileSize;
        resY = y / this.tileSize;

        return getTile(resX, resY);
    }

    private boolean doCoordinatesFit(int x, int y) {
        return !((x >= tileMap.length) || (x < 0) || (y >= tileMap[0].length) || (y < 0));
    }

    public void update(long timeDiff, ArrayList<Enemy> enemies) {
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                tileMap[x][y].update(timeDiff, enemies);
            }
        }
    }

    public void draw(Graphics2D g) {
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                tileMap[x][y].draw(g);
            }
        }
    }
}
