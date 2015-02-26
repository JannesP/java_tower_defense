package game.framework.input;

import game.ui.element.UIElement;

/**
 * Interface for button events.
 * Created by Jannes Peters on 2/25/2015.
 */
public interface IUIActionReceiver {
    public abstract void performAction(UIElement sender, int buttonAction);
}
