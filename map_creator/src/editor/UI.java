package editor;

import java.awt.*;

/**
 * Created by Jannes Peters on 02.03.2015.
 */
public class UI {
    public static void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.MAGENTA);
        graphics2D.drawString("Modus: " + Window.selectedMode.toString(), 15, 15);
        graphics2D.drawString("Auswahl: " + Window.selectedTileType.toString(), 15, 35);
    }
}
