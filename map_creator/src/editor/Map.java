package editor;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = TileType.NOT_BUILDABLE.byteValue;
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][matrix[0].length - 1] = TileType.NOT_BUILDABLE.byteValue;
            matrix[i][matrix[0].length - 2] = TileType.NOT_BUILDABLE.byteValue;
            matrix[i][matrix[0].length - 3] = TileType.NOT_BUILDABLE.byteValue;
        }
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

    public BufferedImage createGridBitmap() {
        BufferedImage image = createBitmap();
        Graphics2D g = image.createGraphics();
        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        return image;
    }

    public BufferedImage createBitmap() {
        BufferedImage image = new BufferedImage(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        for (int x = 0; x < Map.WIDTH; x++) {
            for (int y = 0; y < Map.HEIGHT; y++) {

                switch (TileType.byByteValue(matrix[x][y])) {
                    case NOTHING:
                        g.setPaint(Color.LIGHT_GRAY);
                        break;
                    case ROAD:
                        g.setPaint(Color.BLACK);
                        break;
                    case NOT_BUILDABLE:
                        g.setPaint(Color.RED);
                        break;
                    case ENTRANCE:
                        g.setPaint(Color.GREEN);
                        break;
                    case CASTLE:
                        g.setPaint(Color.BLUE);
                        break;
                }
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

            }
        }

        return image;
    }
}
