package editor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Jannes Peters on 2/19/2015.
 */
public class MapIO {

    public static final byte ROW_SEPARATOR = Byte.MAX_VALUE;

    public static void saveMap(Component parent, Map map) {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(parent);
        fc.setMultiSelectionEnabled(false);

        if (returnVal != JFileChooser.APPROVE_OPTION) return;

        String fileName = fc.getSelectedFile().getName();
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

            for (int x = 0; x < matrix.length; x++) {
                fileOutputStream.write(matrix[x]);
                fileOutputStream.write(ROW_SEPARATOR);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
