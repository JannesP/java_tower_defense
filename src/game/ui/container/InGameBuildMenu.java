package game.ui.container;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.object.player.Player;
import game.object.tower.Tower;
import game.ui.element.UIElement;
import game.ui.element.button.ImageButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 3/8/2015.
 */
public class InGameBuildMenu implements UIContainer {

    private int x, y, width, height;

    private UIElementContainer uiElementContainer;
    private IUIActionReceiver parent;
    private Player player;

    public InGameBuildMenu(int x, int y, int width, int height, Player player, IUIActionReceiver actionReceiver) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ArrayList<UIElement> elements = new ArrayList<>();
        int[] availableTowers = player.getPossibleTowers();
        for (int i = 0; i < availableTowers.length; i++) {
            int towerId = availableTowers[i];
            BufferedImage cutLightTower  = Tower.getTexture(towerId).getSubimage(0, 0, Tower.getTexture(towerId).getHeight(), Tower.getTexture(towerId).getHeight());
            ImageButton button = new ImageButton(this.x + Util.PADDING + i * (this.height - Util.PADDING), this.y + Util.PADDING, this.height - 2 * Util.PADDING, this.height - 2 * Util.PADDING, actionReceiver, cutLightTower, UIElement.BUTTON_TOWER + towerId);
            elements.add(button);
        }

        uiElementContainer = new UIElementContainer(elements);
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {}

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {
        uiElementContainer.handleMouseInput(events);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        uiElementContainer.update(timeScale, timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.x, this.y, this.width, this.height);
        uiElementContainer.draw(g);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        uiElementContainer.realign(width, height, g);
    }

    /*@Override
    public void draw(Graphics2D g) {
        //draw tower
        for (int type : player.getPossibleTowers()) {
            boolean buildable
            switch (type) {
                case Tower.TOWER_LIGHT:

                    break;
                case Tower.TOWER_HEAVY:

                    break;
            }
        }
    }*/
}
