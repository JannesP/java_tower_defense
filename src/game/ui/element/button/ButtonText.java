package game.ui.element.button;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button with text on it.
 * Created by Jannes Peters on 2/25/2015.
 */
public class ButtonText extends Button {
    protected String text;
    protected int stringX = 0, stringY = 0;
    protected Color textColor = Color.BLACK;
    /**
     * Creates a new button with text on it.
     * @param x - x position
     * @param y - y position
     * @param width - width of the whole button
     * @param height - height of the whole button
     * @param backgroundImage - background sprite (normal, hover and gamePaused in one image on top of each other)
     * @param text - text to draw on the button
     * @param g - current graphics for calculating text bounds
     */
    public ButtonText(int x, int y, int width, int height, BufferedImage backgroundImage, IUIActionReceiver actionReceiver, String text, Graphics2D g, int action){
        super(x, y, width, height, action, actionReceiver, backgroundImage);
        this.setText(text, g);
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setFont(Fonts.getDefaultFont());
        g.setColor(textColor);
        g.drawString(text, stringX, stringY);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        stringX = x + Util.calculateCenterPosition(super.width, g.getFontMetrics().stringWidth(this.text));
        stringY = super.getCenterFontY(g);
    }

    private void setText(String text, Graphics2D g) {
        this.text = text;
        realign(width, height, g);
    }
}
