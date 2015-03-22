package game.framework.screens.ingame;

import game.framework.BackgroundMusicPlayer;
import game.framework.Manager;
import game.framework.input.IUIActionReceiver;
import game.framework.screens.ScreenChild;
import game.framework.screens.ScreenManager;
import game.object.player.Player;
import game.object.tile.TileMap;
import game.ui.container.InGameBuildMenu;
import game.ui.container.StatusBar;
import game.ui.element.DropDownMenu;
import game.ui.element.Slider;
import game.ui.element.UIElement;
import game.ui.element.button.ButtonMultiImage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Screen which displays the UI over the game.
 * Created by Jannes Peters on 23.02.2015.
 */
public class ScreenUI extends ScreenChild implements IUIActionReceiver {

    private final StatusBar statusBar;
    private final InGameBuildMenu buildMenu;
    private ScreenBuilding screenBuilding = null;

    public ScreenUI(String name, int width, int height, Graphics2D g, ScreenGame parent, Player player) {
        super(name, width, height, g, parent);

        statusBar = new StatusBar(width, height, g, this, player);
        Rectangle buildArea = getBuildMenuBounds(width, height);
        buildMenu = new InGameBuildMenu(buildArea.x, buildArea.y, buildArea.width, buildArea.height, player, this);

        super.zOrder = ScreenManager.ZOrder.UI;
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {
        statusBar.handleKeyInput(events);
        buildMenu.handleKeyInput(events);
    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {
        statusBar.handleMouseInput(events);
        buildMenu.handleMouseInput(events);
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        statusBar.update(timeScale, timeDiff);
        buildMenu.update(timeScale, timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        statusBar.draw(g);
        buildMenu.draw(g);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (statusBar != null) {
            statusBar.realign(width, height, g);
            buildMenu.realign(width, height, g);
        }
    }

    private static Rectangle getBuildMenuBounds(int width, int height) {
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
                    ((ButtonMultiImage) sender).setImageIndex(1);
                } else {
                    BackgroundMusicPlayer.play();
                    ((ButtonMultiImage) sender).setImageIndex(0);
                }
                break;
            case UIElement.SLIDER_VOLUME:
                BackgroundMusicPlayer.setVolume(((Slider)sender).getValue());
                break;
            case UIElement.DROPDOWN_SELECTED:
                String selectedItem = ((DropDownMenu)sender).getSelectedElement();
                Manager.targetFps = Integer.parseInt(selectedItem.replace(" FPS", ""));
                break;
            case UIElement.BUTTON_TOWER_LIGHT:
            case UIElement.BUTTON_TOWER_HEAVY:
                final int clickedTowerId = actionId - UIElement.BUTTON_TOWER;
                if (!((ScreenGame)getParent()).isBuilding()) {
                    this.screenBuilding = new ScreenBuilding("buildingScreen", super.width, super.height, graphics2D, (ScreenGame) getParent(), clickedTowerId);
                    super.requestScreen(screenBuilding);
                } else {
                    if (clickedTowerId == screenBuilding.getTowerId()) {
                        screenBuilding.unload();
                    } else {
                        screenBuilding.setTowerId(clickedTowerId);
                    }
                }
                break;
            default:
                System.out.println("Event " + actionId + ", sent by " + sender.getClass().toString() + " to " + this.getClass().toString() + " is not implemented!");
                break;
        }
    }
}
