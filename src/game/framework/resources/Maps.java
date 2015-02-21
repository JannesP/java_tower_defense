package game.framework.resources;

import game.framework.Map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Jannes Peters on 2/21/2015.
 */
public class Maps {

    public static final byte ROW_SEPARATOR = Byte.MAX_VALUE;

    public static Map[] maps;

    public static void loadMaps() {
        maps = new Map[1];
        try {
            maps[0] = loadMap("assets/maps/001.map");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static Map loadMap(String filePath) throws IOException{
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
        return map;
    }

}
