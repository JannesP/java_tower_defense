package game.ui.element.button;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button class for use with toggle functions or other things.
 * Created by Jannes Peters on 2/25/2015.
 */
public class MultiImageButton extends Button {

    protected static final int PADDING = 2;

    protected BufferedImage image;
    protected int imgDrawSize;
    protected int imageX = 0, imageY = 0;
    protected int currentIndex = 0;
    protected int spriteCount;

    /**
     * Creates a new button with text on it.
     * @param x - x position
     * @param y - y position
     * @param width - width of the whole button
     * @param height - height of the whole button
     * @param backgroundImage - background sprite (normal, hover and active in one image on top of each other)
     * @param image - image to draw on the button
     * @param spriteCount - count of the horizontally aligned sprites
     * @param g - current graphics for calculating text bounds
     */
    public MultiImageButton(int x, int y, int width, int height, BufferedImage backgroundImage, IUIActionReceiver actionReceiver, BufferedImage image, int spriteCount, Graphics2D g, int action){
        super(x, y, width, height, action, actionReceiver, backgroundImage);
        this.spriteCount = spriteCount;
        this.setImage(image, g);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawImage(image, this.imageX, this.imageY, this.imageX + this.imgDrawSize, this.imageY + this.imgDrawSize, (image.getWidth() / spriteCount) * currentIndex, 0, (image.getWidth() / spriteCount) * (currentIndex + 1), image.getHeight(), null);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        imgDrawSize = super.width - (PADDING * 2);
        imageX = x + Util.calculateCenterPosition(super.width, imgDrawSize);
        imageY = y + Util.calculateCenterPosition(super.height, imgDrawSize);
    }

    public void setImageIndex(int index) {
        if (index >= spriteCount || index < 0) throw new IllegalArgumentException("index (" + index + ") not in bounds!");
        this.currentIndex = index;
    }

    private void setImage(BufferedImage image, Graphics2D g) {
        this.image = image;
        realign(width, height, g);
    }
}
