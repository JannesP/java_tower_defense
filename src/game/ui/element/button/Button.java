package game.ui.element.button;

import game.framework.input.IUIActionReceiver;
import game.ui.element.MouseOverTrackingUIElement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Button base. For use refer to <code>ImageButton</code>, <code>MultiImageButton</code> or <code>TextButton</code>.
 * Created by Jannes Peters on 2/25/2015.
 */
public abstract class Button extends MouseOverTrackingUIElement {
	protected STATE state = STATE.NORMAL;
    protected BufferedImage backgroundImage;
    protected int spriteY;

    protected Button(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, BufferedImage backgroundImage) {
        super(x, y, width, height, action, actionReceiver);
        this.backgroundImage = backgroundImage;
    }

    public enum STATE{
		NORMAL,
		HOVER,
		PRESSED
	}
	
	public void setState(STATE state) {
		this.state = state;
	}

    /**
     * Gets the current state of the button.
     * @return - Button.STATE that's currently set
     */
	public STATE getState() {
		return state;
	}
	
	@Override
	public void update(long timeDiff) {}

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {
        for (MouseEvent e : events) {
            this.isMouseOver = this.isEventInBounds(e);
            handleMouseEvent(e);
        }
    }

    @Override
    protected void handleMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_DRAGGED || event.getID() == MouseEvent.MOUSE_MOVED) {
            if (this.state == Button.STATE.PRESSED) {
                return;
            }
            if (isMouseOver) {
                this.state = Button.STATE.HOVER;
            }
            else this.state = Button.STATE.NORMAL;
        } else if (event.getID() == MouseEvent.MOUSE_PRESSED && event.getButton() == MouseEvent.BUTTON1) {
            if (isMouseOver) this.state = Button.STATE.PRESSED;
            else this.state = Button.STATE.NORMAL;
        } else if (event.getID() == MouseEvent.MOUSE_RELEASED && event.getButton() == MouseEvent.BUTTON1) {
            if (this.state == Button.STATE.PRESSED && isMouseOver) {
                actionReceiver.performAction(this, this.action);    //send action to listener
                this.state = Button.STATE.HOVER;
                return;
            }
            if (isMouseOver) this.state = Button.STATE.HOVER;
            else this.state = Button.STATE.NORMAL;
        }
    }

    @Override
	public void draw(Graphics2D g) {
        switch (state){
            case NORMAL:
                spriteY = 0;
                break;
            case HOVER:
                spriteY = backgroundImage.getHeight() / 3;
                break;
            case PRESSED:
                spriteY = backgroundImage.getHeight() / 3 * 2;
                break;
        }
		g.drawImage(backgroundImage, x, y, width + x, height + y, 0, spriteY, backgroundImage.getWidth(), backgroundImage.getHeight() / 3 + spriteY, null);
	}

}
