package game.ui.element;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Jannes Peters on 2/26/2015.
 */
public class Slider extends UIElement {

    private float minValue, maxValue, value;
    private static Color separatorColor, separatorColor2, selectorColor, selectorColor2;

    /**
     * Initiates the slider with a range from 0f - 1f and a default value of 0f.
     * @param x - calculated X coordinate on the screen.
     * @param y - calculated Y coordinate on the screen.
     * @param width - width of the element.
     * @param height - height of the element.
     * @param action - action ID
     */
    protected Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver) {
        this(x, y, width, height, action, actionReceiver, 1f);
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
    protected Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, float maxValue) {
        this(x, y, width, height, action, actionReceiver, 0f, maxValue);
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
    protected Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, float minValue, float maxValue) {
        this(x, y, width, height, action, actionReceiver, minValue, maxValue, 0f);
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
    public Slider(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, float minValue, float maxValue, float initialValue) {
        super(x, y, width, height, action, actionReceiver);
        if (maxValue <= minValue) throw new IllegalArgumentException("maxValue(" + maxValue + ") is not bigger then minValue(" + minValue + ")");
        if (initialValue < minValue || initialValue > maxValue) throw new IllegalArgumentException("initialValue(" + initialValue + ") has to be between minValue and maxValue!");
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = initialValue;
        Slider.separatorColor = Color.decode("#b0913f");
        Slider.separatorColor2 = Color.decode("#76622a");
        Slider.selectorColor = Color.decode("#e0913f");
        Slider.selectorColor2 = Color.decode("#7c5023");
    }

    public float getValue() {
        return value;
    }

    @Override
    protected void handleMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_DRAGGED || event.getID() == MouseEvent.MOUSE_CLICKED) {
            this.value = (((float)(event.getX() - super.x) / (float)super.width) * (maxValue - minValue)) + minValue;
            super.handleMouseEvent(event);
        }
    }

    @Override
    public void draw(Graphics2D g) {    //TODO fix goofy drawing of selector
        //draw first separator
        g.setColor(Slider.separatorColor);
        g.fillRect(super.x, super.y, 3, super.height);
        g.setColor(Slider.separatorColor2);
        g.drawRect(super.x, super.y, 3, super.height);

        //draw last separator
        g.setColor(Slider.separatorColor);
        g.fillRect(super.x + super.width - 3, super.y, 3, super.height);
        g.setColor(Slider.separatorColor2);
        g.drawRect(super.x + super.width - 3, super.y, 3, super.height);

        //draw lines between
        g.setColor(Slider.separatorColor);
        for (int i = 1; i < 10; i++) {
            g.fillRect(super.x + (i * (super.width/ 10)) - 1, super.y + Util.calculateCenterPosition(super.height, super.height / 2), 2, super.height / 2);
        }

        //draw selector
        g.setColor(Slider.selectorColor);
        g.fillRect(super.x + (int)(this.value / (maxValue - minValue) * (double)super.width), super.y, 5, super.height);
        g.setColor(Slider.selectorColor2);
        g.drawRect(super.x + (int) (this.value / (maxValue - minValue) * (double) super.width), super.y, 5, super.height);

        g.setColor(Color.LIGHT_GRAY);
        g.drawString(this.value + "f", super.x + super.width + Util.PADDING, super.height + Util.PADDING);
    }

    @Override
    public void update(long timeDiff) {}
    @Override
    public void realign(int width, int height, Graphics2D g) {}
}
