package editor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 2/19/2015.
 */
public class MapIO {

    public static final byte ROW_SEPARATOR = Byte.MAX_VALUE;
    public static final byte PATH_SEPARATOR = Byte.MAX_VALUE - 1;
    public static final byte FILE_SEPARATOR = Byte.MAX_VALUE - 2;

    public static void saveMap(Component parent, Map map) {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(parent);
        fc.setMultiSelectionEnabled(false);

        if (returnVal != JFileChooser.APPROVE_OPTION) return;

        File imageFile = new File(fc.getSelectedFile().getAbsolutePath() + ".png");
        File matrixFile = new File(fc.getSelectedFile().getAbsolutePath() + ".map");

        try {
            BufferedImage mapImage = map.createBitmap();
            BufferedImage buffer = new BufferedImage(mapImage.getWidth() * 2, mapImage.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = buffer.createGraphics();
            g.drawImage(mapImage, 0, 0, buffer.getWidth(), buffer.getHeight(), 0, 0, mapImage.getWidth(), mapImage.getHeight(), null);
            ImageIO.write(buffer, "png", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (!matrixFile.createNewFile()) {
                System.out.println("File creation failed: " + matrixFile.getAbsolutePath());
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(matrixFile);
            byte[][] matrix = map.getMatrix();

            for (byte[] aMatrix : matrix) {
                fileOutputStream.write(aMatrix);
                fileOutputStream.write(ROW_SEPARATOR);
            }

            ArrayList<ArrayList<Point>> paths = Window.pathOverlay.getPaths();

            for (int i = 0; i < paths.size(); i++) {
                fileOutputStream.write(PATH_SEPARATOR);
                for (int x = 0; x < paths.get(i).size(); x++) {
                    fileOutputStream.write(ByteBuffer.allocate(4).putInt((int) (paths.get(i).get(x).getX())).array());
                    fileOutputStream.write(ByteBuffer.allocate(4).putInt((int) (paths.get(i).get(x).getY())).array());
                }
            }

            fileOutputStream.write(FILE_SEPARATOR);

            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map loadMap(Component parent) throws Exception {
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        int returnVal = fc.showOpenDialog(parent);

        if (returnVal != JFileChooser.APPROVE_OPTION) throw new Exception("User cancelled load.");

        File matrixFile = fc.getSelectedFile();

        if (!matrixFile.exists()) throw new Exception("File doesn't exist!");

        Map map;
        FileInputStream fileInputStream = new FileInputStream(matrixFile);

        byte[][] matrix = new byte[Map.WIDTH][Map.HEIGHT];
        int nextValue = fileInputStream.read();
        int currentX = 0;
        int currentY = 0;
        while (nextValue != PATH_SEPARATOR && nextValue != -1) {    //create matrix
            if (nextValue == ROW_SEPARATOR) {
                currentX++;
                currentY = 0;
                nextValue = fileInputStream.read();
                continue;
            }
            matrix[currentX][currentY++] = (byte)nextValue;
            nextValue = fileInputStream.read();
        }

        ArrayList<ArrayList<Point>> paths = new ArrayList<>();
        int currPath = 0;
        while (nextValue != -1) {   //create paths
            if (nextValue == PATH_SEPARATOR) {
                currPath++;
                paths.add(new ArrayList<>());
            } else if (nextValue == FILE_SEPARATOR) {   //File ending
                fileInputStream.close();
                break;
            }
            int x, y;
            x = readIntFromFileInputStream(fileInputStream);
            y = readIntFromFileInputStream(fileInputStream);
            paths.get(currPath).add(new Point(x, y));

            nextValue = fileInputStream.read();
        }
        Window.pathOverlay = new PathOverlay(paths);
        map = new Map(matrix);
        return map;
    }

    public static int readIntFromFileInputStream(FileInputStream fis) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        for (int i = 0; i < 4; i++) {   //read 4 bytes into ByteBuffer (4 byte = 1 int)
            try {
                buffer.put(i, (byte) fis.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.getInt();
    }

}
