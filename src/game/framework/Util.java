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

    /**
     * Returns the representation of the specified floating-point
     * value according to the IEEE 754 floating-point "double format"
     * bit layout, after first mapping -0.0 to 0.0. This method is
     * identical to Double.doubleToLongBits(double) except that an
     * integer value of 0L is returned for a floating-point value of
     * -0.0. This is done for the purpose of computing a hash code
     * that satisfies the contract of hashCode() and equals(). The
     * equals() method in each vecmath class does a pair-wise "=="
     * test on each floating-point field in the class (e.g., x, y, and
     * z for a Tuple3d). Since 0.0&nbsp;==&nbsp;-0.0 returns true, we
     * must also return the same hash code for two objects, one of
     * which has a field with a value of -0.0 and the other of which
     * has a cooresponding field with a value of 0.0.
     *
     * @param d an input double precision floating-point number
     * @return the integer bits representing that floating-point
     * number, after first mapping -0.0f to 0.0f
     */
    public static long doubleToLongBits(double d) {
        // Check for +0 or -0
        if (d == 0.0) {
            return 0L;
        }
        else {
            return Double.doubleToLongBits(d);
        }
    }
    public static int getStringWidth(String text, Graphics2D g){
        return (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
    }
}
