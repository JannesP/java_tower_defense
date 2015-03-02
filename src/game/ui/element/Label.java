package game.ui.element;

import game.framework.resources.Fonts;

import java.awt.*;

/**
 * Created by Jannes Peters on 2/26/2015.
 */
public class Label extends UIElement {
    //TODO check if working correctly
    protected String text = "";

    /**
     * Initiates the element with the given parameters. The text anchor is in the top left.
     * @param x - calculated X coordinate on the screen.
     * @param y - calculated Y coordinate on the screen.
     * @param action - action ID
     * @param text - the text to display
     */
    public Label(int x, int y, int action, String text) {
        super(x, y, 0, 0, action, null);
        this.consumesMouseEvents = false;
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(Fonts.getDefaultFont());
        g.drawString(text, this.x - (g.getFontMetrics().getHeight() / 2), this.y);
    }

    @Override
    public void update(long timeDiff) {}
    @Override
    public void realign(int width, int height, Graphics2D g) {}

    @Override
    protected void mouseLeft() {

    }
}
