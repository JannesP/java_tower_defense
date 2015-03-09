package game.framework.screens;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Textures;
import game.ui.container.UIElementContainer;
import game.ui.element.Label;
import game.ui.element.UIElement;
import game.ui.element.button.TextButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class CreditScreen extends BaseScreen implements IUIActionReceiver {

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            case UIElement.BUTTON_BACK:
                super.requestScreen(new OptionScreen("optionScreen", this.width, this.height, super.graphics2D));
                super.state = ScreenManager.ScreenState.SHUTDOWN;
                break;
            default:
                System.out.println("BUTTON FAILURE! Action: '" + buttonAction + "' not defined in " + this.getClass().toString());
        }
    }

    private UIElementContainer uiElementContainer;

    public CreditScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        ArrayList<UIElement> elements = new ArrayList<>();
        String[] labelTexts = new String []{
                "Sound Artists:",
                "Fynn König, Julian Böteführ",
                "Creative Artists:",
                "Tjorven Hoppe, Niclas Kirstein, Lars Pfeiffer",
                "Producer:",
                "Jannes Peters, Adrian Kurth"
        };
        for(int i = 0 ; i < labelTexts.length; i++){
            int labelCenterX = Util.calculateCenterPosition(this.width,Util.getStringWidth(labelTexts[i],g) );
            elements.add(new Label(labelCenterX,(i * (Util.getFontHeight(g) + 20) + Util.getFontHeight(g) + 20),0,labelTexts[i]));
        }
        elements.add(new MenuButton(menuButtonCenterX, 310, "Back", g, UIElement.BUTTON_BACK, this));
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

    private class MenuButton extends TextButton {
        public static final int WIDTH = 200;
        public static final int HEIGHT = 50;

        public MenuButton(int x, int y, String text, Graphics2D g, int action, IUIActionReceiver actionReceiver) {
            super(x, y, WIDTH, HEIGHT, Textures.button_main_menu, actionReceiver, text, g, action);
        }
    }

}
