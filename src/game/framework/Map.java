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
    private Point[][] paths;

    public Map(byte[][] matrix, Point[][] paths) {
        if (matrix == null || matrix[0] == null || matrix.length != Map.WIDTH || matrix[0].length != Map.HEIGHT || paths == null || paths.length == 0) throw new IllegalArgumentException();
        this.matrix = matrix;
        this.paths = paths;
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

}
