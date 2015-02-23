package game.framework.screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 23.02.2015.
 */
public class UIScreen extends BaseScreen {
    public UIScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        super.zOrder = ScreenManager.ZOrder.UI;
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {

    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {

    }

    @Override
    public void update(long timeDiff) {

    }

    @Override
    public void draw(Graphics2D g) {

    }
}
