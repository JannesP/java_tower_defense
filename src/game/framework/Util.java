package game.framework;

import java.awt.*;
import java.awt.image.BufferedImage;
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
        return graphics2D.getFontMetrics().getAscent() - graphics2D.getFontMetrics().getDescent();
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

    //50-80ms
    /**
     * Completely self written image converter. Faster then any corresponding build in java function.
     * Be careful, no check is done if the src values are in the bounds!
     * @param original the image to create a grey copy of
     * @param srcX1 srcX1 of the original
     * @param srcY1 srcY1 of the original
     * @param srcX2 srcX2 of the original
     * @param srcY2 srcY2 of the original
     * @return a high quality grey copy of the image with alpha retained
     * @throws ArrayIndexOutOfBoundsException thrown when the src values are not in the bounds of the original.
     */
    public static BufferedImage createGreyScaledImage(BufferedImage original, int srcX1, int srcY1, int srcX2, int srcY2) {
        long startTime = System.currentTimeMillis();
        BufferedImage image = new BufferedImage(srcX2 - srcX1, srcY2 - srcY1, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, getGreyColor(original.getRGB(srcX1 + x, srcY1 + y)));
            }
        }

        System.out.println("Grey out image took " + (System.currentTimeMillis() - startTime) + "ms!");
        return image;
    }

    /**
     * Completely self written color converter.
     * @param rgba the rgba color which should be converted to a grey.
     * @return the calculated grey
     */
    public static int getGreyColor(int rgba) {
        //split color into its parts
        int a = (rgba >> 24) & 0xFF;
        int r = (rgba >> 16) & 0xFF;
        int g = (rgba >> 8) & 0xFF;
        int b = rgba & 0xFF;

        int calcGrey = (int)(0.21 * r + 0.6 * g + 0.19 * b);
        return (a << 24) | (calcGrey << 16) | (calcGrey << 8) | calcGrey;
    }

    //80-150ms
    /*
    public static BufferedImage createGreyScaledImage(BufferedImage original, int srcX1, int srcY1, int srcX2, int srcY2) {
        long startTime = System.currentTimeMillis();
        BufferedImage image = new BufferedImage(srcX2 - srcX1, srcY2 - srcY1, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = image.createGraphics();
        g.drawImage(original, 0, 0, image.getWidth(), image.getHeight(), srcX1, srcY1, srcX2, srcY2, null);
        g.dispose();
        BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                //rgb int is 0xAARRGGBB
                int alpha = original.getRGB(x, y) & 0xFF000000; //remove all colors stored in the original (make it new Color(0, 0, 0, a)
                int color = image.getRGB(x, y) & 0x00FFFFFF;    //remove the alpha channel of the copied grey image (make it new Color(r, g, b, 0)
                color |= alpha; //combine the values (alpha from alpha and rgb from color)
                image2.setRGB(x, y, color);
            }
        }

        System.out.println("Grey out image took " + (System.currentTimeMillis() - startTime) + "ms!");
        return image2;
    }
    */

    //300-500ms
    /*public static BufferedImage createGreyScaledImage(BufferedImage original, int srcX1, int srcY1, int srcX2, int srcY2) {
        long startTime = System.currentTimeMillis();
        BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        BufferedImage image = op.filter(original, null);
        System.out.println("Grey out image took " + (System.currentTimeMillis() - startTime) + "ns!");
        return image;
    }*/

    //5-15ms but without alpha retain
    /*public static BufferedImage createGreyScaledImage(BufferedImage original, int srcX1, int srcY1, int srcX2, int srcY2) {
        long startTime = System.currentTimeMillis();
        BufferedImage image = new BufferedImage(srcX2 - srcX1, srcY2 - srcY1, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = image.createGraphics();
        g.drawImage(original, 0, 0, image.getWidth(), image.getHeight(), srcX1, srcY1, srcX2, srcY2, null);
        g.dispose();
        System.out.println("Grey out image took " + (System.currentTimeMillis() - startTime) + "ns!");
        return image;
    }*/

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
