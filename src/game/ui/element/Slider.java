package game.ui.element;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * A simple slider control for setting something.
 * Created by Jannes Peters on 2/26/2015.
 */
public class Slider extends UIElement {

    private double minValue, maxValue, value, stepValue;
    private static Color selectorColor;

    private static final int SELECTOR_WIDTH = 5;

    /**
     * Initiates the slider with a range from 0f - 1f and a default value of 0f.
     * @param x - calculated X coordinate on the screen.
     * @param y - calculated Y coordinate on the screen.
     * @param width - width of the element.
     * @param height - height of the element.
     * @param action - action ID
     */
    public Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver) {
        this(x, y, width, height, action, actionReceiver, 1d);
    }

    /**
     * Initiates the slider with a minValue and initialValue of 0f.
     * @param x - calculated X coordinate on the screen.
     * @param y - calculated Y coordinate on the screen.
     * @param width - width of the element.
     * @param height - height of the element.
     * @param action - action ID
     * @param maxValue - maximum value, has to be greater then 0!
     */
    public Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, double maxValue) {
        this(x, y, width, height, action, actionReceiver, 0d, maxValue);
    }

    /**
     * Initiates the slider with an initialValue of 0f.
     * @param x - calculated X coordinate on the screen
     * @param y - calculated Y coordinate on the screen
     * @param width - width of the element
     * @param height - height of the element
     * @param action - action ID
     * @param minValue - minimum value of the element
     * @param maxValue - maximum value of the element, has to be bigger then minValue
     */
    public Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, double minValue, double maxValue) {
        this(x, y, width, height, action, actionReceiver, minValue, maxValue, 0d);
    }

    /**
     * Initiates the slider.
     * @param x - calculated X coordinate on the screen.
     * @param y - calculated Y coordinate on the screen.
     * @param width - width of the element.
     * @param height - height of the element.
     * @param action - action ID
     * @param minValue - minimum value of the element
     * @param maxValue - maximum value of the element, has to be bigger then minValue
     * @param initialValue - initial value of the element, has to be between minValue and maxValue
     */
    public Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, double minValue, double maxValue, double initialValue) {
        super(x, y, width, height, action, actionReceiver);
        if (maxValue <= minValue) throw new IllegalArgumentException("maxValue(" + maxValue + ") is not bigger then minValue(" + minValue + ")");
        if (initialValue < minValue || initialValue > maxValue) throw new IllegalArgumentException("initialValue(" + initialValue + ") has to be between minValue and maxValue!");
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = initialValue;
        this.stepValue = maxValue / 100d;
        Slider.selectorColor = Color.decode("#ffc790");
    }

    /**
     * Returns the value of the element.
     * @return the value
     */
    public double getValue() {
        return value;
    }

    @Override
    protected void handleKeyEvent(KeyEvent event) {
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_UP:
                    setValue(this.value + stepValue);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_DOWN:
                    setValue(this.value - stepValue);
                    break;
            }
        }
    }

    @Override
    protected void handleMouseEvent(MouseEvent event) {
        if (super.isMouseOver && (event.getID() == MouseEvent.MOUSE_DRAGGED || event.getID() == MouseEvent.MOUSE_CLICKED)) {
            setValue((((float) (event.getX() - super.x) / (float) super.width) * (maxValue - minValue)) + minValue);
            actionReceiver.performAction(this, super.action);
        }
    }

    public void setValue(double value) {
        if (value < this.minValue) {
            this.value = this.minValue;
        } else if (value > this.maxValue) {
            this.value = this.maxValue;
        } else {
            this.value = value;
        }
        if (Double.compare(this.value, value) == 0) super.actionReceiver.performAction(this, super.action);
    }

    @Override
    public void draw(Graphics2D g) {
        //draw first separator
        g.setColor(UIElement.COLOR_NORMAL);
        g.fillRect(super.x, super.y, 3, super.height);

        //draw last separator
        g.setColor(UIElement.COLOR_NORMAL);
        g.fillRect(super.x + super.width - 3, super.y, 3, super.height);

        //draw lines between
        g.setColor(UIElement.COLOR_NORMAL);
        for (int i = 1; i < 10; i++) {
            g.fillRect(super.x + (i * (super.width/ 10)) - 1, super.y + Util.calculateCenterPosition(super.height, super.height / 2), 2, super.height / 2);
        }

        //draw selector
        g.setColor(Slider.selectorColor);
        g.fillRect(super.x + (int)(this.value / (maxValue - minValue) * ((double)super.width - SELECTOR_WIDTH)), super.y, 5, super.height);
        g.setColor(this.getBorderColor());
        g.drawRect(super.x + (int) (this.value / (maxValue - minValue) * ((double) super.width - SELECTOR_WIDTH)), super.y, 5, super.height);
        g.drawRect(super.x + (int) (this.value / (maxValue - minValue) * ((double) super.width - SELECTOR_WIDTH)) + 1, super.y + 1, 5 - 2, super.height - 2);

        g.setColor(Color.LIGHT_GRAY);
        g.drawString((int)Math.floor(this.value * 100) + "%", super.x + super.width + Util.PADDING, super.height + Util.PADDING);
    }

    public Color getBorderColor() {
        if (!this.isMouseOver && !this.hasFocus) {
            return UIElement.COLOR_NORMAL;
        } else if (this.isMouseDown) {
            return UIElement.COLOR_ACTIVE;
        } else {    //isMouseOver == true
            return UIElement.COLOR_HOVER;
        }
    }

    @Override
    public void update(long timeDiff) {}
    @Override
    public void realign(int width, int height, Graphics2D g) {}

}
