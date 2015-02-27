package game.ui.element;

import game.framework.input.IUIActionReceiver;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * UIElement that tracks if it got the mouse over itself.
 * Created by Jannes Peters on 2/26/2015.
 */
public class MouseOverTrackingUIElement extends UIElement {
    protected boolean isMouseOver = false;

    /**
     * Initiates the element with the given parameters.
     *
     * @param x              - calculated X coordinate on the screen.
     * @param y              - calculated Y coordinate on the screen.
     * @param width          - width of the element.
     * @param height         - height of the element.
     * @param action         - action ID
     * @param actionReceiver - action receiver for receiving action updates, can be null
     */
    protected MouseOverTrackingUIElement(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver) {
        super(x, y, width, height, action, actionReceiver);
    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {
        for (MouseEvent e : events) {
            this.isMouseOver = this.isEventInBounds(e);
            handleMouseEvent(e);
        }
    }

    @Override
    public void update(long timeDiff) {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void realign(int width, int height, Graphics2D g) {

    }
}
