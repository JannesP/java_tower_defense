package game.ui.element;

import game.drawable.IPaintableUpdatableObject;
import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.object.tower.Tower;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Base class for a basic UI element.
 * Created by Jannes Peters on 2/26/2015.
 */
public abstract class UIElement implements IPaintableUpdatableObject{

    public static final int BUTTON_START = 0;
    public static final int BUTTON_MULTIPLAYER = 1;
    public static final int BUTTON_OPTIONS = 2;
    public static final int BUTTON_EDITOR = 3;
    public static final int BUTTON_EXIT = 4;
    public static final int BUTTON_MUTE = 5;
    public static final int BUTTON_BACK = 6;
    public static final int BUTTON_CREDITS = 7;
    public static final int BUTTON_TEST = 8;
    public static final int BUTTON_SAVE = 9;
    public static final int BUTTON_LOAD = 10;
    public static final int BUTTON_RESUME = 11;

    public static final int SLIDER_VOLUME = 100;

    public static final int TEXTBOX_INPUT = 200;

    public static final int CHECKBOX_EXAMPLE = 300;

    public static final int DROPDOWN_SELECTED = 400;

    public static final int BUTTON_TOWER = 500;
    public static final int BUTTON_TOWER_LIGHT = BUTTON_TOWER + Tower.TOWER_LIGHT;
    public static final int BUTTON_TOWER_HEAVY = BUTTON_TOWER + Tower.TOWER_HEAVY;

    private static final IUIActionReceiver NULL_RECEIVER = new EmptyActionReceiver();

    protected static final Color COLOR_NORMAL = Color.decode("#ea9742");
    protected static final Color COLOR_HOVER = Color.decode("#ea7542");
    protected static final Color COLOR_ACTIVE = Color.decode("#ea5342");
    protected static final Color COLOR_FONT = Color.LIGHT_GRAY;


    protected IUIActionReceiver actionReceiver = null;
    protected int x, y, width, height, action;
    protected boolean consumesMouseEvents = true;
    protected boolean hasFocus = false;
    protected boolean isMouseOver = false;
    protected boolean isMouseDown = false;

    private boolean isDisabled = false;

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
        if (actionReceiver == null) {
            this.actionReceiver = NULL_RECEIVER;
        } else {
            this.actionReceiver = actionReceiver;
        }
    }

    protected void handleKeyEvent(KeyEvent event) {}

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    //Event listeners
    protected void handleMouseEvent(MouseEvent event) {}
    protected void mouseEntered() {}
    protected void mouseLeft() {}
    protected void clicked(MouseEvent event) {}

    public void handleMouseInput(ArrayList<MouseEvent> events) {
        for (MouseEvent e : events) {
            if (!e.isConsumed()) {
                boolean newMouseOver = this.isEventInBounds(e);
                if (this.isMouseOver != newMouseOver) {
                    this.isMouseOver = newMouseOver;
                    if (this.isMouseOver) mouseEntered();
                    else mouseLeft();
                }
                if (this.isMouseOver) {
                    if (e.getID() == MouseEvent.MOUSE_PRESSED) this.isMouseDown = true;
                }

                if (e.getID() == MouseEvent.MOUSE_RELEASED && this.isMouseDown) {
                    if (this.isMouseOver) {
                        this.hasFocus = true;
                        clicked(e);
                    }
                }

                if (e.getID() == MouseEvent.MOUSE_RELEASED) {
                    if (!this.isMouseOver && !this.isMouseDown) {
                        this.hasFocus = false;
                    }
                }

                if (e.getID() == MouseEvent.MOUSE_RELEASED) this.isMouseDown = false;

                handleMouseEvent(e);
            }
        }
    }

    public void handleKeyInput(ArrayList<KeyEvent> events) {
        for (KeyEvent event : events) {
            if (this.hasFocus) handleKeyEvent(event);
        }
    }

    public Color getBorderColor() {
        if (!this.isMouseOver && !this.hasFocus) {
            return UIElement.COLOR_NORMAL;
        } else if (this.hasFocus) {
            return UIElement.COLOR_ACTIVE;
        } else {    //isMouseOver == true
            return UIElement.COLOR_HOVER;
        }
    }

    @Override
    public void update(double timeScale, long timeDiff) {}

    /**
     * Get REKT ... ääähh
     * @return - the dimensions of the Button as a Rectangle
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public int getRightBorder() {
        return this.x + this.width;
    }
    public int getBottomBorder() {
        return this.y + this.height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void setHasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    public boolean hasFocus() {
        return hasFocus;
    }

    /**
     * Checks if the e.getX() and e.getY() are in bounds of the element.
     * @param e - event to check
     * @return true if the event is in bounds
     */
    public boolean isEventInBounds(MouseEvent e) {
        return (e.getY() >= this.y && e.getY() <= this.y + this.height && e.getX() >= this.x && e.getX() <= this.x + this.width);
    }

    private static class EmptyActionReceiver implements IUIActionReceiver {
        @Override
        public void performAction(UIElement sender, int buttonAction) {}
    }

    /**
     * Should be called after every <code>UIElement</code> has drawn its body
     * @param g - the corresponding Graphics element to draw on
     */
    public void drawOverlay(Graphics2D g) {}

    protected int getCenterFontY(Graphics2D g) {
        return this.y + Util.calculateCenterPosition(this.height, Util.getFontHeight(g)) + Util.getFontHeight(g);
    }
}
