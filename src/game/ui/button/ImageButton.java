package game.ui.button;

import game.framework.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jannes Peters on 2/25/2015.
 */
public class ImageButton extends Button {
    protected BufferedImage image;
    protected int imageX = 0, imageY = 0;

    /**
     * Creates a new button with text on it.
     * @param x - x position
     * @param y - y position
     * @param width - width of the whole button
     * @param height - height of the whole button
     * @param backgroundImage - background sprite (normal, hover and active in one image on top of each other)
     * @param image - image to draw on the button
     * @param g - current graphics for calculating text bounds
     */
    public ImageButton(int x, int y, int width, int height, BufferedImage backgroundImage, BufferedImage image, Graphics2D g, String action){
        super(x, y, width, height, backgroundImage, g, action);
        this.setImage(image, g);
    }


    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawImage(image, imageX, imageY, null);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        imageX = x + Util.calculateCenterPosition(super.width, image.getWidth());
        imageY = y + Util.calculateCenterPosition(super.height, image.getHeight());
    }

    private void setImage(BufferedImage image, Graphics2D g) {
        this.image = image;
        realign(width, height, g);
    }
}
