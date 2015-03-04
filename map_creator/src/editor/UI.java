package editor;

import java.awt.*;

/**
 * Created by Jannes Peters on 02.03.2015.
 */
public class UI {
    public static void draw(Graphics2D graphics2D) {
        graphics2D.setFont(graphics2D.getFont().deriveFont(25f).deriveFont(Font.BOLD));
        graphics2D.setColor(Color.ORANGE);
        graphics2D.drawString("Modus: " + Window.selectedMode.toString(), 15, 25);
        String selection;
        if (Window.selectedMode == Window.EditMode.MAP) {
            selection = Window.selectedTileType.toString();
        } else {
            selection =  String.valueOf(Window.pathOverlay.getCurrentPath());
        }
        graphics2D.drawString("Auswahl: " + selection, 15, 55);
    }
}
