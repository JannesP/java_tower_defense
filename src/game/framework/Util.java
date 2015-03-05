package game.framework;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Class for random junk helper functions.
 * Created by Jannes Peters on 2/18/2015.
 */
public final class Util {
    public static final double NANO_SECOND_SECOND = 1000000000d;
    public static final int PADDING = 5;

    /**
     * Calculates the position to draw the inner element in the center of the outer.
     * @param outerSize outer box
     * @param innerSize inner element to center
     * @return the relative position of the inner element to the outer box
     */
    public static int calculateCenterPosition(int outerSize, int innerSize) {
        return (outerSize - innerSize) / 2;
    }

    /**
     * Calculates the font overhead for Roboto.
     * @param graphics2D graphics with the FontMetrics
     * @return the pixels of overhead
     */
    public static int calculateFontOverhead(Graphics2D graphics2D) {
        return (int)(0.1d * (double)graphics2D.getFontMetrics().getFont().getSize());
    }

    public static int getFontHeight(Graphics2D graphics2D) {
        return graphics2D.getFontMetrics().getAscent() - calculateFontOverhead(graphics2D);
    }

    /**
     * Converts the first given and the three following bytes of the given <code>FileInputStream</code> into an integer.
     * @param fis - the <code>FileInputStream</code> to read the data from
     * @param firstValue - the first byte of data read from the integer to read
     * @return an integer
     */
    public static int readIntFromFileInputStream(FileInputStream fis, byte firstValue) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE/Byte.SIZE);
        buffer.put(buffer.capacity() - 1, firstValue);
        for (int i = 1; i < 4; i++) {   //read 4 bytes into ByteBuffer (4 byte = 1 int)
            try {
                buffer.put(i, (byte) fis.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.getInt();
    }
}
