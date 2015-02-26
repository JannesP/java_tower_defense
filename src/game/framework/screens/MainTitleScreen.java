package game.framework.screens;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Textures;
import game.ui.container.UIElementContainer;
import game.ui.element.UIElement;
import game.ui.element.button.TextButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainTitleScreen extends BaseScreen implements IUIActionReceiver {

    private class ButtonAction {
        public static final int START = 0;
        public static final int MULTIPLAYER = 1;
        public static final int OPTIONS = 2;
        public static final int EDITOR = 3;
        public static final int EXIT = 4;
    }

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            case ButtonAction.START:
                super.requestScreen(new GameScreen("gameScreen", this.width, this.height, super.graphics2D));
                super.state = ScreenManager.SCREENSTATE.SHUTDOWN;
                break;
            case ButtonAction.MULTIPLAYER:
                System.out.println(buttonAction);
                break;
            case ButtonAction.EDITOR:
                System.out.println(buttonAction);
                break;
            case ButtonAction.OPTIONS:
                System.out.println(buttonAction);
                break;
            case ButtonAction.EXIT:
                super.closeGame();
                break;
            default:
                System.out.println("BUTTON FAILURE! Action: '" + buttonAction + "' not defined in " + this.getClass().toString());
        }
    }

    private UIElementContainer uiElementContainer;

	public MainTitleScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        ArrayList<UIElement> elements = new ArrayList<>();
        elements.add(new MenuButton(menuButtonCenterX, 20, "Play", g, ButtonAction.START, this));
        elements.add(new MenuButton(menuButtonCenterX, 80, "Multiplayer", g, ButtonAction.MULTIPLAYER, this));
        elements.add(new MenuButton(menuButtonCenterX, 160, "Options", g, ButtonAction.OPTIONS, this));
        elements.add(new MenuButton(menuButtonCenterX, 220, "Editor", g, ButtonAction.EDITOR, this));
        elements.add(new MenuButton(menuButtonCenterX, 280, "Exit", g, ButtonAction.EXIT, this));
        uiElementContainer = new UIElementContainer(elements);
	}

	@Override
	public void update(long timeDiff) {
        uiElementContainer.update(timeDiff);
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

    private class MenuButton extends TextButton {
        public static final int WIDTH = 200;
        public static final int HEIGHT = 50;

        public MenuButton(int x, int y, String text, Graphics2D g, int action, IUIActionReceiver actionReceiver) {
            super(x, y, WIDTH, HEIGHT, Textures.button_main_menu, actionReceiver, text, g, action);
        }
    }

}
