package game.framework.input;

import game.ui.button.Button;

/**
 * Interface for button events.
 * Created by Jannes Peters on 2/25/2015.
 */
public interface IButtonActionReceiver {
    public abstract void performButtonAction(Button sender, int buttonAction);
}
