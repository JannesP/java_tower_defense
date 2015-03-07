package game.framework;

import game.framework.math.Vector2d;

import java.awt.*;

/**
 * A class that holds the read map data.
 * Created by Jannes Peters on 2/19/2015.
 */
public class Map {
    public static final int TILE_SIZE = 30;
    public static final int WIDTH = 33;
    public static final int HEIGHT = 20;

    private byte[][] matrix;
    private Vector2d[][] paths;

    public Map(byte[][] matrix, Point[][] paths) {
        if (matrix == null || matrix[0] == null || matrix.length != Map.WIDTH || matrix[0].length != Map.HEIGHT || paths == null || paths.length == 0) throw new IllegalArgumentException();
        this.matrix = matrix;

        this.paths = new Vector2d[paths.length][paths[0].length];
        for (int pathId = 0; pathId < paths.length; pathId++) {
            Point[] path = paths[pathId];
            for (int pointId = 0; pointId < path.length; pointId++) {
                this.paths[pathId][pointId] = new Vector2d(path[pointId]);
            }
        }
    }

    public byte[][] getMatrix() {
        return matrix;
    }

    public Vector2d[][] getPaths() {
        return paths;
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
