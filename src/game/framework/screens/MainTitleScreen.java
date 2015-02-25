package game.framework.screens;

import game.framework.Util;
import game.framework.input.ButtonHandler;
import game.framework.input.IButtonActionReceiver;
import game.ui.button.Button;
import game.ui.button.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainTitleScreen extends BaseScreen implements IButtonActionReceiver{

    private class ButtonAction {
        public static final int START = 0;
        public static final int MULTIPLAYER = 1;
        public static final int EDITOR = 2;
        public static final int OPTIONS = 3;
        public static final int EXIT = 4;
    }

    @Override
    public void performButtonAction(int buttonAction) {
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

    private ButtonHandler buttonHandler;

	public MainTitleScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        ArrayList<Button> buttons = new ArrayList<>();
		buttons.add(new MenuButton(menuButtonCenterX, 20, "Play", g, ButtonAction.START));
		buttons.add(new MenuButton(menuButtonCenterX, 80, "Multiplayer", g, ButtonAction.MULTIPLAYER));
		buttons.add(new MenuButton(menuButtonCenterX, 160, "Options", g, ButtonAction.OPTIONS));
		buttons.add(new MenuButton(menuButtonCenterX, 220, "Editor", g, ButtonAction.EDITOR));
		buttons.add(new MenuButton(menuButtonCenterX, 280, "Exit", g, ButtonAction.EXIT));
        buttonHandler = new ButtonHandler(buttons, this);

	}

	@Override
	public void update(long timeDiff) {
        buttonHandler.update(timeDiff);
	}

	@Override
	public void draw(Graphics2D g) {
        buttonHandler.draw(g);
	}

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (buttonHandler != null) {
            for (Button b : buttonHandler.getButtons()) {
                b.setX(Util.calculateCenterPosition(this.width, MenuButton.WIDTH));
                b.realign(width, height, g);
            }
        }
    }

	@Override
	public void realign(Rectangle rect, Graphics2D g) {
        realign(rect.width, rect.height, g);
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) {
        buttonHandler.handleKeyInput(events);
    }

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) {
        buttonHandler.handleMouseInput(events);
    }

    @Override
    public void closeRequested() {
        System.out.println("Close init by: " + this.getClass().toString());
        super.closeGame();
    }

}
