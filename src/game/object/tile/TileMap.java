package game.object.tile;

import game.framework.Map;
import game.framework.Window;
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

    public static final int DEFAULT_TILE_SIZE = 30;
    public static double tileSize = 30;
    public static final int WIDTH = 33;
    public static final int HEIGHT = 20;

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
                        tileMap[x][y] = new Tile((int)(x * tileSize), (int)(y * tileSize));
                        break;
                    case ROAD:
                        tileMap[x][y] = new Tile((int)(x * tileSize), (int)(y * tileSize), true);
                        break;
                    case NOT_BUILDABLE:
                        tileMap[x][y] = new Tile((int)(x * tileSize), (int)(y * tileSize), false, false);
                        break;
                    case ENTRANCE:
                        tileMap[x][y] = new Tile((int)(x * tileSize), (int)(y * tileSize), true, false, true);
                        break;
                    case CASTLE:
                        tileMap[x][y] = new Tile((int)(x * tileSize), (int)(y * tileSize), false, true);
                        tileMap[x][y].setTileObject(new Castle());
                        break;
                }
            }
        }
        return tileMap;
    }

    public TileMap(Map map, BufferedImage background) {
        this(TileMap.createTileMap(map), background);
    }

    public TileMap(Tile[][] tileMap, BufferedImage background) {
        this.background = background;
        this.tileMap = tileMap;
    }

    /**
     * Returns the tile at the given x and y with the matrix coordinates!
     * @param x - matrix x
     * @param y - matrix y
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
        resX = (int)(x / TileMap.tileSize);
        resY = (int)(y / TileMap.tileSize);

        return getTile(resX, resY);
    }

    private boolean doCoordinatesFit(int x, int y) {
        return !((x >= tileMap.length) || (x < 0) || (y >= tileMap[0].length) || (y < 0));
    }

    public void realign() {
        TileMap.tileSize = (double)TileMap.DEFAULT_TILE_SIZE * Window.currentScale;
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                tileMap[x][y].realign((int)(x * TileMap.tileSize), (int)(y * TileMap.tileSize));
            }
        }
    }

    public void update(long timeDiff, ArrayList<Enemy> enemies) {
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                tileMap[x][y].update(timeDiff, enemies);
            }
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.background, 0, 0, (int)(Map.WIDTH * TileMap.tileSize), (int)(Map.HEIGHT * TileMap.tileSize), null);

        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                tileMap[x][y].draw(g);
            }
        }
    }

    public Point getCastleCoordinates() throws Exception {
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                if (tileMap[x][y].tower instanceof Castle) {
                    return new Point(x, y);
                }
            }
        }
        throw new Exception("The loaded map is incomplete, castle missing!");
    }
}
