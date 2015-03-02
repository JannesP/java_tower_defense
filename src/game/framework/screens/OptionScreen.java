package game.framework.screens;

import game.framework.BackgroundMusicPlayer;
import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Textures;
import game.ui.container.UIElementContainer;
import game.ui.element.Label;
import game.ui.element.Slider;
import game.ui.element.UIElement;
import game.ui.element.button.TextButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class OptionScreen extends BaseScreen implements IUIActionReceiver {

    private class ButtonAction {

    }

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            case UIElement.SLIDER_VOLUME:
                BackgroundMusicPlayer.setVolume(((Slider) sender).getValue());
                break;
            /*case UIElement.BUTTON_MULTIPLAYER:
                System.out.println(buttonAction);
                break;
            case UIElement.BUTTON_EDITOR:
                System.out.println(buttonAction);
                break;
            case UIElement.BUTTON_OPTIONS:
                System.out.println(buttonAction);
                break;*/
            case UIElement.BUTTON_BACK:
                super.requestScreen(new MainTitleScreen("mainScreen",this.width,this.height,super.graphics2D));
                super.state = ScreenManager.SCREENSTATE.SHUTDOWN;
                break;
            default:
                System.out.println("BUTTON FAILURE! Action: '" + buttonAction + "' not defined in " + this.getClass().toString());
        }
    }

    private UIElementContainer uiElementContainer;

    public OptionScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        int SliderCenterX = Util.calculateCenterPosition(this.width,300 );
        int LabelCenterX = Util.calculateCenterPosition(this.width,25 );
        ArrayList<UIElement> elements = new ArrayList<>();
        elements.add(new Slider(SliderCenterX,20,300,50,UIElement.SLIDER_VOLUME,this,0, 1, 0.5 ));
        elements.add(new Label((SliderCenterX - 100 ),50,0,"Volume"));
        elements.add(new Label(LabelCenterX,120,0,this.width + "x" + this.height));
        elements.add(new MenuButton((LabelCenterX - MenuButton.WIDTH - 25), 120, "Smaller", g, UIElement.BUTTON_SMALLER, this));
        elements.add(new MenuButton((LabelCenterX + MenuButton.HEIGHT + 25),120 , "Greater", g, UIElement.BUTTON_GREATER, this));
        elements.add(new MenuButton(menuButtonCenterX, 280, "Back", g, UIElement.BUTTON_BACK, this));
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
