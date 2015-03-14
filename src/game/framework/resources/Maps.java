package game.framework.resources;

import game.framework.Map;
import game.framework.Util;
import game.framework.screens.menu.SplashLoadScreen;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Class that loads and holds all maps.
 * Created by Jannes Peters on 2/21/2015.
 */
public class Maps {
    public static final int ELEMENTS = 1;

    public static final byte ROW_SEPARATOR = Byte.MAX_VALUE;
    public static final byte PATH_SEPARATOR = Byte.MAX_VALUE - 1;

    public static final int LEVEL_COUNT = 1;

    public static Map[] maps = new Map[LEVEL_COUNT];

    public static void loadMaps() {

        try {
            maps[0] = loadMap("assets/maps/001.map");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static Map loadMap(String filePath) throws Exception {
        System.out.println("Loading " + filePath + " ...");
        Map map;
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        //Load all tiles
        byte[][] matrix = new byte[Map.WIDTH][Map.HEIGHT];
        int nextValue = fileInputStream.read();
        int currentX = 0;
        int currentY = 0;
        while (nextValue != PATH_SEPARATOR && nextValue != -1) {
            if (nextValue == ROW_SEPARATOR) {
                currentX++;
                currentY = 0;
                nextValue = fileInputStream.read();
                continue;
            }
            matrix[currentX][currentY++] = (byte)nextValue;
            nextValue = fileInputStream.read();
        }

        //Load all paths
        ArrayList<ArrayList<Point>> paths = new ArrayList<>();
        int currPath = -1;
        while (nextValue != -1) {
            if (nextValue == PATH_SEPARATOR) {
                currPath++;
                paths.add(new ArrayList<>());
                nextValue = fileInputStream.read();
            }
            int x, y;
            x = Util.readIntFromFileInputStream(fileInputStream, (byte) nextValue);
            y = Util.readIntFromFileInputStream(fileInputStream, (byte) fileInputStream.read());
            paths.get(currPath).add(new Point(x, y));

            nextValue = fileInputStream.read();
        }
        fileInputStream.close();

        Point[][] pathArray;
        if (paths.size() != 0) {
            pathArray = new Point[paths.size()][paths.get(0).size() + 1];
            for (int pathId = 0; pathId < paths.size(); pathId++) {
                for (int pointId = -1; pointId < paths.get(0).size(); pointId++) {
                    if (pointId == -1) {
                        Point start = new Point((int)paths.get(pathId).get(0).getX() - 1, (int)paths.get(pathId).get(0).getY());
                        pathArray[pathId][0] = start;
                        continue;
                    }
                    pathArray[pathId][pointId + 1] = paths.get(pathId).get(pointId);
                }
            }
        } else {
            throw new Exception("The map " + filePath + " is missing the way points!");
        }

        map = new Map(matrix, pathArray);
        SplashLoadScreen.elementLoaded();
        return map;
    }

}
