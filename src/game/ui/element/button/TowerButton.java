package game.ui.element.button;

import game.framework.input.IUIActionReceiver;
import game.object.tower.TowerTypes;
import game.ui.element.UIElement;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jannes Peters on 3/14/2015.
 */
public class TowerButton extends ImageButton {
    private BufferedImage disabledImage;

    public TowerButton(int x, int y, int width, int height, IUIActionReceiver actionReceiver, int towerId) {
        super(x, y, width, height, actionReceiver, TowerTypes.getTextureById(towerId).getSubimage(0, 0, TowerTypes.getTextureById(towerId).getHeight(), TowerTypes.getTextureById(towerId).getHeight()), UIElement.BUTTON_TOWER + towerId);
        this.disabledImage = TowerTypes.getGreyTextureById(towerId);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (super.isDisabled()) {
            g.drawImage(disabledImage, this.imageX, this.imageY, this.imageX + this.imgDrawSize, this.imageY + this.imgDrawSize, 0, 0, image.getWidth(), image.getHeight(), null);
        } else {
            g.drawImage(image, this.imageX, this.imageY, this.imageX + this.imgDrawSize, this.imageY + this.imgDrawSize, 0, 0, image.getWidth(), image.getHeight(), null);
        }
    }



}
