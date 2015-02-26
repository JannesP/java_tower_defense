package game.framework;

/**
 * Created by Jannes Peters on 2/19/2015.
 */
public class Map {
    public static final int TILE_SIZE = 30;
    public static final int WIDTH = 33;
    public static final int HEIGHT = 20;

    private byte[][] matrix;

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

}
