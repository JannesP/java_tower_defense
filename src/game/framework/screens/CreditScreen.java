package game.framework.screens;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;
import game.ui.container.UIElementContainer;
import game.ui.element.UIElement;
import game.ui.element.button.TextButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class CreditScreen extends BaseScreen implements IUIActionReceiver {

    private class ButtonAction {

    }

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            /*case UIElement.BUTTON_START:
                super.requestScreen(new GameScreen("gameScreen", this.width, this.height, super.graphics2D));
                super.state = ScreenManager.SCREENSTATE.SHUTDOWN;
                break;
            case UIElement.BUTTON_MULTIPLAYER:
                System.out.println(buttonAction);
                break;
            case UIElement.BUTTON_EDITOR:
                System.out.println(buttonAction);
                break;
            case UIElement.BUTTON_OPTIONS:
                super.requestScreen(new OptionScreen("optionScreen", this.width, this.height, super.graphics2D));
                super.state = ScreenManager.SCREENSTATE.SHUTDOWN;
                break;*/
            case UIElement.BUTTON_BACK:
                super.requestScreen(new OptionScreen("optionScreen", this.width, this.height, super.graphics2D));
                super.state = ScreenManager.SCREENSTATE.SHUTDOWN;
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
        /*elements.add(new MenuButton(menuButtonCenterX, 20, "Play", g, UIElement.BUTTON_START, this));
        elements.add(new MenuButton(menuButtonCenterX, 80, "Multiplayer", g, UIElement.BUTTON_MULTIPLAYER, this));
        elements.add(new MenuButton(menuButtonCenterX, 160, "Options", g, UIElement.BUTTON_OPTIONS, this));
        elements.add(new MenuButton(menuButtonCenterX, 220, "Editor", g, UIElement.BUTTON_EDITOR, this));*/
        elements.add(new MenuButton(menuButtonCenterX, 310, "Back", g, UIElement.BUTTON_BACK, this));
        uiElementContainer = new UIElementContainer(elements);
    }

    @Override
    public void update(long timeDiff) {
        uiElementContainer.update(timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        uiElementContainer.draw(g);
        ArrayList<String> credits = new ArrayList<>();
        credits.add("Sound:");
        credits.add("Fynn König");
        credits.add("Creative Artists:");
        credits.add("Tjorven P. Hoppe, Niclas Kirstein, Lars Pfeiffer");
        credits.add("Programming:");
        credits.add("Jannes Peters, Adrian Kurth");
        g.setFont(Fonts.fpsFont);
        g.setColor(Color.BLACK);
        for(int i = 0; i < credits.size(); i++){
            String list = credits.get(i);
            int x = Util.calculateCenterPosition(this.width, g.getFontMetrics().stringWidth(list));
            g.drawString(list,x,(i * 50 + 30));
        }
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
