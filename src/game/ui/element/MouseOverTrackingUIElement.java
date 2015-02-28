package game.ui.element;

import game.framework.input.IUIActionReceiver;

/**
 * UIElement that tracks if it got the mouse over itself.
 * Created by Jannes Peters on 2/26/2015.
 */
public abstract class MouseOverTrackingUIElement extends UIElement {


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


}
