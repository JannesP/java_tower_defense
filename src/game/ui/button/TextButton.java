package game.ui.button;

import game.framework.Util;
import game.framework.resources.Fonts;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button with text on it.
 * Created by Jannes Peters on 2/25/2015.
 */
public class TextButton extends Button {
    protected String text;
    protected int stringX = 0, stringY = 0;

    /**
     * Creates a new button with text on it.
     * @param x - x position
     * @param y - y position
     * @param width - width of the whole button
     * @param height - height of the whole button
     * @param backgroundImage - background sprite (normal, hover and active in one image on top of each other)
     * @param text - text to draw on the button
     * @param g - current graphics for calculating text bounds
     */
    public TextButton(int x, int y, int width, int height, BufferedImage backgroundImage, String text, Graphics2D g, int action){
        super(x, y, width, height, backgroundImage, g, action);
        this.setText(text, g);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setFont(Fonts.getDefaultFont());
        g.drawString(text, stringX, stringY);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        stringX = x + Util.calculateCenterPosition(super.width, g.getFontMetrics().stringWidth(this.text));
        stringY = y + Util.calculateCenterPosition(super.height, g.getFontMetrics().getHeight()) + g.getFontMetrics().getAscent();
    }

    private void setText(String text, Graphics2D g) {
        this.text = text;
        realign(width, height, g);
    }
}
