package editor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jannes Peters on 2/19/2015.
 */
public class MapIO {

    public static void saveMap(Component parent, Map map) {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(parent);
        fc.setMultiSelectionEnabled(false);

        if (returnVal != JFileChooser.APPROVE_OPTION) return;

        String fileName = fc.getSelectedFile().getName();
        File imageFile = new File(fc.getSelectedFile().getAbsolutePath() + ".png");
        File matrixFile = new File(fc.getSelectedFile().getAbsolutePath() + ".map");

        try {
            ImageIO.write(map.createBitmap(), "png", imageFile);
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
                fileOutputStream.write(Byte.MAX_VALUE);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
