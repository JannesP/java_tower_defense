package game.framework.resources;

import game.framework.Map;
import game.framework.screens.SplashLoadScreen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Jannes Peters on 2/21/2015.
 */
public class Maps {
    public static final int ELEMENTS = 1;

    public static final byte ROW_SEPARATOR = Byte.MAX_VALUE;

    public static final int LEVEL_COUNT = 1;

    public static Map[] maps = new Map[LEVEL_COUNT];

    public static void loadMaps() {

        try {
            maps[0] = loadMap("assets/maps/001.map");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static Map loadMap(String filePath) throws IOException{
        System.out.println("Loading " + filePath + " ...");
        Map map;
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        byte[][] matrix = new byte[Map.WIDTH][Map.HEIGHT];
        int nextValue = fileInputStream.read();
        int currentX = 0;
        int currentY = 0;
        while (nextValue != -1) {
            if (nextValue == ROW_SEPARATOR) {
                currentX++;
                currentY = 0;
                nextValue = fileInputStream.read();
                continue;
            }
            matrix[currentX][currentY++] = (byte)nextValue;
            nextValue = fileInputStream.read();
        }

        map = new Map(matrix);
        SplashLoadScreen.elementLoaded();
        return map;
    }

}
