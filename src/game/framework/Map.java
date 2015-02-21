package game.framework;

import java.awt.*;

/**
 * Created by Jannes Peters on 2/19/2015.
 */
public class Map {
    public static final int TILE_SIZE = 30;
    public static final int WIDTH = 33;
    public static final int HEIGHT = 20;

    private byte[][] matrix;

    public Map() {
        matrix = new byte[Map.WIDTH][Map.HEIGHT];
    }

    public Map(byte[][] matrix) {
        if (matrix == null || matrix[0] == null || matrix.length != Map.WIDTH || matrix[0].length != Map.HEIGHT) throw new IllegalArgumentException("matrix didn't fit to Map class!");
        this.matrix = matrix;
    }

    public byte[][] getMatrix() {
        return matrix;
    }

    public enum TileType {
        NOTHING((byte) 0), ROAD((byte) 1), NOT_BUILDABLE((byte) 2), ENTRANCE((byte) 3), CASTLE((byte) 4);
        private static TileType[] values = null;
        private byte byteValue;

        private TileType(byte byteValue) {
            this.byteValue = byteValue;
        }

        public static TileType byByteValue(byte val) {
            if (TileType.values == null) values = TileType.values();
            for (TileType type : TileType.values) {
                if(type.byteValue == val) return type;
            }
            throw new IllegalArgumentException("val was not found!");
        }
    }

    public static Point resolvePointToMatrix(Point point) {
        Point newPoint = new Point();
        newPoint.setLocation(Math.floor(point.getX() / TILE_SIZE), 0);
        newPoint.setLocation(newPoint.getX(), Math.floor(point.getY() / TILE_SIZE));
        return newPoint;
    }

    public static Point resolvePixelToMatrix(int mouseX, int mouseY) {
        return new Point(mouseX / TILE_SIZE, mouseY / TILE_SIZE);
    }

    public void setTileAtPixel(int mouseX, int mouseY, TileType type) {
        setTile(mouseX / TILE_SIZE, mouseY / TILE_SIZE, type);
    }

    public void setTile(int x, int y, TileType type) {
        if (x < WIDTH && y < HEIGHT && x >= 0 && y >= 0) {
            matrix[x][y] = type.byteValue;
        }
    }

}
