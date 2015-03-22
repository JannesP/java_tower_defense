package game.framework.screens.menu;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Textures;
import game.framework.screens.ScreenBase;
import game.framework.screens.ingame.ScreenGame;
import game.ui.container.UIElementContainer;
import game.ui.element.UIElement;
import game.ui.element.button.ButtonText;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Title screen which shows the main menu.
 */
public class ScreenMainTitle extends ScreenBase implements IUIActionReceiver {

    @Override
    public void performAction(UIElement sender, int actionId) {
        switch (actionId) {
            case UIElement.BUTTON_START:
                super.requestScreen(new ScreenGame("gameScreen", this.width, this.height, super.graphics2D));
                super.unload();
                break;
            case UIElement.BUTTON_OPTIONS:
                super.requestScreen(new ScreenOption("optionScreen", this.width, this.height, super.graphics2D));
                super.unload();
                break;
            case UIElement.BUTTON_EXIT:
                super.closeGame();
                break;
            default:
                System.out.println("Event " + actionId + ", sent by " + sender.getClass().toString() + " to " + this.getClass().toString() + " is not implemented!");
        }
    }

    private UIElementContainer uiElementContainer;

	public ScreenMainTitle(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        ArrayList<UIElement> elements = new ArrayList<>();
        elements.add(new MenuButton(menuButtonCenterX, 20, "Play", g, UIElement.BUTTON_START, this));
        elements.add(new MenuButton(menuButtonCenterX, 80, "Multiplayer", g, UIElement.BUTTON_MULTIPLAYER, this));
        elements.get(elements.size() - 1).setDisabled(true);
        elements.add(new MenuButton(menuButtonCenterX, 160, "Options", g, UIElement.BUTTON_OPTIONS, this));
        elements.add(new MenuButton(menuButtonCenterX, 220, "Editor", g, UIElement.BUTTON_EDITOR, this));
        elements.get(elements.size() - 1).setDisabled(true);
        elements.add(new MenuButton(menuButtonCenterX, 280, "Exit", g, UIElement.BUTTON_EXIT, this));
        uiElementContainer = new UIElementContainer(elements);
	}

	@Override
	public void update(double timeScale, long timeDiff) {
        uiElementContainer.update(timeScale, timeDiff);
	}

	@Override
	public void draw(Graphics2D g) {
        uiElementContainer.draw(g);
	}

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (uiElementContainer != null) {
            for (UIElement element : uiElementContainer.getElements()) {
                element.setX(Util.calculateCenterPosition(this.width, MenuButton.WIDTH));
                element.realign(width, height, g);
            }
        }
    }

	@Override
	public void realign(Rectangle rect, Graphics2D g) {
        realign(rect.width, rect.height, g);
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) {
        uiElementContainer.handleKeyInput(events);
    }

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) {
        uiElementContainer.handleMouseInput(events);
    }

    @Override
    public void closeRequested() {
        System.out.println("Close init by: " + this.getClass().toString());
        super.closeGame();
    }

    private class MenuButton extends ButtonText {
        public static final int WIDTH = 200;
        public static final int HEIGHT = 50;

        public MenuButton(int x, int y, String text, Graphics2D g, int action, IUIActionReceiver actionReceiver) {
            super(x, y, WIDTH, HEIGHT, Textures.buttonBackground, actionReceiver, text, g, action);
        }
    }

}
