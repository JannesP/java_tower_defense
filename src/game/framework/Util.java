package game.framework;

/**
 * Class for random junk helper functions.
 * Created by Jannes Peters on 2/18/2015.
 */
public final class Util {
    public static final double NANO_SECOND_SECOND = 1000000000d;
    public static final int PADDING = 5;

    public static int calculateCenterPosition(int outerSize, int innerSize) {
        return (outerSize - innerSize) / 2;
    }
}
