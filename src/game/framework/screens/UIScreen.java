package game.framework.screens;

import game.framework.BackgroundMusicPlayer;
import game.framework.Manager;
import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.object.tile.TileMap;
import game.ui.container.StatusBar;
import game.ui.element.DropDownMenu;
import game.ui.element.Slider;
import game.ui.element.UIElement;
import game.ui.element.button.MultiImageButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Screen which displays the UI over the game.
 * Created by Jannes Peters on 23.02.2015.
 */
public class UIScreen extends BaseScreen implements IUIActionReceiver {

    private Rectangle bottomDrawBorder;

    private StatusBar statusBar;

    public UIScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);

        statusBar = new StatusBar(width, height, g, this);

        bottomDrawBorder = getBottomDrawBorder(width, height);
        super.zOrder = ScreenManager.ZOrder.UI;
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {
        statusBar.handleKeyInput(events);
    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {
        statusBar.handleMouseInput(events);
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        statusBar.update(timeScale, timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) bottomDrawBorder.getX(), (int) bottomDrawBorder.getY(), (int) bottomDrawBorder.getWidth(), (int) bottomDrawBorder.getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(Fonts.defaultFont);
        g.drawString("bottomDrawBorder", (int) bottomDrawBorder.getX() + Util.PADDING, (int) bottomDrawBorder.getY() + Util.PADDING + g.getFontMetrics().getMaxAscent());

        statusBar.draw(g);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (statusBar != null) {
            statusBar.realign(width, height, g);
        }
        bottomDrawBorder = getBottomDrawBorder(width, height);
    }

    private Rectangle getBottomDrawBorder(int width, int height) {
        int drawHeight = (int) (TileMap.tileSize * 3);
        int y = height - (int) (TileMap.tileSize * 3);
        return new Rectangle(0, y, width, drawHeight);
    }

    @Override
    public void performAction(UIElement sender, int actionId) {
        switch (actionId) {
            case UIElement.BUTTON_MUTE:
                if (BackgroundMusicPlayer.isPlaying()) {
                    BackgroundMusicPlayer.pause();
                    ((MultiImageButton) sender).setImageIndex(1);
                } else {
                    BackgroundMusicPlayer.play();
                    ((MultiImageButton) sender).setImageIndex(0);
                }
                break;
            case UIElement.SLIDER_VOLUME:
                BackgroundMusicPlayer.setVolume(((Slider)sender).getValue());
                break;
            case UIElement.DROPDOWN_SELECTED:
                String selectedItem = ((DropDownMenu)sender).getSelectedElement();
                Manager.targetFps = Integer.parseInt(selectedItem.replace(" FPS", ""));
                break;
            default:
                System.out.println("Event " + actionId + ", sent by " + sender.getClass().toString() + " to " + this.getClass().toString() + " is not implemented!");
                break;
        }
    }
}
