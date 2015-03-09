package game.ui.element;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

/**
 * A Checkbox for the UI.
 * Created by Jannes Peters on 2/28/2015.
 */
public class CheckBox extends UIElement {
    protected String description;
    protected boolean isChecked = false;
    protected boolean isMouseDown = false;

    /**
     * Initiates the element with the given parameters.
     *
     * @param x              - calculated X coordinate on the screen.
     * @param y              - calculated Y coordinate on the screen.
     * @param height          - width of the element.
     * @param action         - action ID
     * @param actionReceiver - action receiver for receiving action updates, can be null
     */
    public CheckBox(int x, int y, int height, int action, IUIActionReceiver actionReceiver, String description, FontRenderContext frc) {
        this(x, y, height + Util.PADDING + (int)Fonts.getDefaultFont().getStringBounds(description, frc).getWidth(), height, action, actionReceiver, description);
    }

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
    public CheckBox(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, String description) {
        super(x, y, width, height, action, actionReceiver);
        this.description = description;
    }

    @Override
    protected void handleKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER && event.getID() == KeyEvent.KEY_PRESSED) {
            clicked(null);
        }
    }

    @Override
    protected void clicked(MouseEvent event) {
        this.isChecked = !this.isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public void draw(Graphics2D g) {
        if (super.width == 0) {
            super.width = (int)Fonts.getDefaultFont().getStringBounds(this.description, g.getFontRenderContext()).getWidth() + super.height + Util.PADDING;
        }

        //draw border
        g.setColor(super.getBorderColor());
        //noinspection SuspiciousNameCombination
        g.drawRect(super.x, super.y, super.height, super.height);
        g.drawRect(super.x + 1, super.y + 1, super.height - 2, super.height - 2);

        //draw checkedBox
        g.setColor(UIElement.COLOR_NORMAL);
        if (this.isChecked) {
            g.fillRect(super.x + 4, super.y + 4, super.height - 7, super.height - 7);
        }

        //draw description
        g.setColor(UIElement.COLOR_FONT);
        g.setFont(Fonts.getDefaultFont());
        g.drawString(this.description, super.x + super.height + Util.PADDING, super.getCenterFontY(g));
    }

    @Override
    public void update(double timeScale, long timeDiff) {}
    @Override
    public void realign(int width, int height, Graphics2D g) {}
}
