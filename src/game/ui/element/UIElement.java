package game.ui.element;

import game.drawable.IPaintableUpdatableObject;
import game.framework.input.IUIActionReceiver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Base class for a basic UI element.
 * Created by Jannes Peters on 2/26/2015.
 */
public abstract class UIElement implements IPaintableUpdatableObject{

    protected IUIActionReceiver actionReceiver = null;
    protected int x, y, width, height, action;
    protected boolean consumesMouseEvents = true;

    /**
     * Initiates the element with the given parameters.
     * @param x - calculated X coordinate on the screen.
     * @param y - calculated Y coordinate on the screen.
     * @param width - width of the element.
     * @param height - height of the element.
     * @param action - action ID
     */
    protected UIElement(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver) {
        if (width < 0 || height < 0) throw new IllegalArgumentException("width and height should be greater then 0!");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.action = action;
        this.actionReceiver = actionReceiver;
    }

    public void handleKeyInput(ArrayList<KeyEvent> events) {}

    public void handleMouseInput(ArrayList<MouseEvent> events) {
        for (MouseEvent e : events) {
            if (!e.isConsumed()) {
                if (this.isEventInBounds(e)) {
                    handleMouseEvent(e);
                }
            }
        }
    }

    protected void handleMouseEvent(MouseEvent event) {
        if (consumesMouseEvents && this.actionReceiver != null) {
            event.consume();
            this.actionReceiver.performAction(this, this.action);
        }
    }

    /**
     * Get REKT ... ääähh
     * @return - the dimensions of the Button as a Rectangle
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAction() {
        return action;
    }

    /**
     * Checks if the e.getX() and e.getY() are in bounds of the element.
     * @param e - event to check
     * @return true if the event is in bounds
     */
    public boolean isEventInBounds(MouseEvent e) {
        return (e.getY() >= this.y && e.getY() <= this.y + this.height && e.getX() >= this.x && e.getX() <= this.x + this.width);
    }
}
