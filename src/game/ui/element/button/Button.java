package game.ui.element.button;

import game.framework.input.IUIActionReceiver;
import game.ui.element.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Button base. For use refer to <code>ImageButton</code>, <code>MultiImageButton</code> or <code>TextButton</code>.
 * Created by Jannes Peters on 2/25/2015.
 */
public abstract class Button extends UIElement {
    protected BufferedImage backgroundImage;
    protected int spriteY;

    protected Button(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, BufferedImage backgroundImage) {
        super(x, y, width, height, action, actionReceiver);
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void handleKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER && event.getID() == KeyEvent.KEY_PRESSED) super.actionReceiver.performAction(this, super.action);
    }

    @Override
    protected void clicked(MouseEvent event) {
        super.actionReceiver.performAction(this, super.action);
        super.hasFocus = false;
    }

    @Override
	public void draw(Graphics2D g) {
        if (!super.isMouseOver) spriteY = 0;
        if (super.hasFocus || super.isMouseOver) spriteY = backgroundImage.getHeight() / 3;
        if (super.isMouseOver && super.isMouseDown) spriteY = backgroundImage.getHeight() / 3 * 2;

		g.drawImage(backgroundImage, x, y, width + x, height + y, 0, spriteY, backgroundImage.getWidth(), backgroundImage.getHeight() / 3 + spriteY, null);
	}
}
