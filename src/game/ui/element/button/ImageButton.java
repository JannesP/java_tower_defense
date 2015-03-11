package game.ui.element.button;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button class for use with not often changing icons.
 * Created by Jannes Peters on 2/25/2015.
 */
public class ImageButton extends Button {
    protected static final int PADDING = 1;

    protected BufferedImage image;
    protected int imgDrawSize;
    protected int imageX = 0, imageY = 0;

    /**
     * Creates a new button with text on it.
     * @param x - x position
     * @param y - y position
     * @param width - width of the whole button
     * @param height - height of the whole button
     * @param image - image to draw on the button
     */
    public ImageButton(int x, int y, int width, int height, IUIActionReceiver actionReceiver, BufferedImage image, int action){
        super(x, y, width, height, action, actionReceiver, Textures.button_main_menu);
        this.setImage(image);
    }


    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawImage(image, this.imageX, this.imageY, this.imageX + this.imgDrawSize, this.imageY + this.imgDrawSize, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        imgDrawSize = super.height - PADDING * 2;
        imageX = x + Util.calculateCenterPosition(super.width, imgDrawSize);
        imageY = y + Util.calculateCenterPosition(super.height, imgDrawSize);
    }

    private void setImage(BufferedImage image) {
        this.image = image;
        realign(width, height, null);
    }
}
