package game.framework;

/**
 * Created by Jannes Peters on 2/18/2015.
 */
public final class Util {
    public static int calculateCenterPosition(int outerSize, int innerSize) {
        return (outerSize - innerSize) / 2;
    }
}
