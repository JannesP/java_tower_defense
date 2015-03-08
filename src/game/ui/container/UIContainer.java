package game.ui.container;

import game.drawable.IPaintableUpdatableObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 3/8/2015.
 */
public interface UIContainer extends IPaintableUpdatableObject {
    public void handleKeyInput(ArrayList<KeyEvent> events);
    public void handleMouseInput(ArrayList<MouseEvent> events);
    public int getHeight();
}
