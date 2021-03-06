package game.framework.screens.menu;

import game.framework.BackgroundMusicPlayer;
import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;
import game.framework.screens.ScreenBase;
import game.framework.screens.ingame.ScreenInGameOptions;
import game.ui.container.UIElementContainer;
import game.ui.element.DropDownMenu;
import game.ui.element.Label;
import game.ui.element.Slider;
import game.ui.element.UIElement;
import game.ui.element.button.ButtonText;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ScreenOption extends ScreenBase implements IUIActionReceiver {

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            case UIElement.SLIDER_VOLUME:
                BackgroundMusicPlayer.setVolume(((Slider) sender).getValue());
                break;
            case UIElement.BUTTON_CREDITS:
                super.requestScreen(new ScreenCredit("creditScreen", this.width, this.height, super.graphics2D));
                super.unload();
                break;
            case UIElement.BUTTON_BACK:
                super.requestScreen(new ScreenMainTitle("mainScreen", this.width, this.height, super.graphics2D));
                super.unload();
            break;
            case UIElement.BUTTON_TEST:
                super.requestScreen(new ScreenInGameOptions("testScreen", this.width, this.height, super.graphics2D, this));
                super.unload();
                break;
            default:
                System.out.println("BUTTON FAILURE! Action: '" + buttonAction + "' not defined in " + this.getClass().toString());
        }
    }

    private UIElementContainer uiElementContainer;

    public ScreenOption(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        g.setFont(Fonts.defaultFont);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        int SliderCenterX = Util.calculateCenterPosition(this.width, 300);
        int SliderY = 50;
        String[] scales = new String[] {"990x600", "660x400"}; //scaling 33x20
        ArrayList<UIElement> elements = new ArrayList<>();
        elements.add(new Slider(SliderCenterX, SliderY, 300, 50, UIElement.SLIDER_VOLUME, this, 0, 1, 0.5));
        elements.add(new Label((SliderCenterX - 100), (SliderY + 30), 0, "Volume"));
        elements.add(new MenuButton(menuButtonCenterX, 220, "Credits", g, UIElement.BUTTON_CREDITS, this));
        elements.add(new MenuButton(menuButtonCenterX, 280, "Back", g, UIElement.BUTTON_BACK, this));
        elements.add(new MenuButton(menuButtonCenterX, 400, "TestScreen", g, UIElement.BUTTON_TEST, this));
        elements.add(new DropDownMenu(SliderCenterX,140,300,50,UIElement.DROPDOWN_SELECTED ,this , scales));
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