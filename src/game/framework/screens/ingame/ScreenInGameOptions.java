package game.framework.screens.ingame;

import game.framework.BackgroundMusicPlayer;
import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;
import game.framework.screens.ScreenBase;
import game.framework.screens.ScreenChild;
import game.framework.screens.ScreenManager;
import game.framework.screens.menu.ScreenMainTitle;
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

public class ScreenInGameOptions extends ScreenChild implements IUIActionReceiver {
    private static final Color COLOR_BACKGROUND = new Color(0f,0f,0f, 0.85f);

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            case UIElement.SLIDER_VOLUME:
                BackgroundMusicPlayer.setVolume(((Slider) sender).getValue());
                break;
            case UIElement.BUTTON_RESUME:
                if (super.getParent() instanceof ScreenGame)
                    ((ScreenGame)super.getParent()).gamePaused = false;
                super.unload();
                break;
            case UIElement.BUTTON_BACK:
                super.requestScreen(new ScreenMainTitle("mainScreen", this.width, this.height, super.graphics2D));
                super.getParent().unload();
                super.unload();
                break;
            default:
                System.out.println("BUTTON FAILURE! Action: '" + buttonAction + "' not defined in " + this.getClass().toString());
        }
    }

    private UIElementContainer uiElementContainer;

    public ScreenInGameOptions(String name, int width, int height, Graphics2D g, ScreenBase parent) {
        super(name, width, height, g, parent);
        g.setFont(Fonts.defaultFont);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        int SliderCenterX = Util.calculateCenterPosition(this.width, 300);
        int SliderY = 210;
        String[] scales = new String[] {"990x600", "660x400"}; //scaling 33x20
        ArrayList<UIElement> elements = new ArrayList<>();
        elements.add(new Slider(SliderCenterX, SliderY, 300, 50, UIElement.SLIDER_VOLUME, this, 0, 1, 0.5));
        elements.add(new Label((SliderCenterX - 100), (SliderY + 30), 0, "Volume"));
        elements.add(new MenuButton(menuButtonCenterX, 90, "Save", g, UIElement.BUTTON_SAVE, this));
        elements.add(new MenuButton(menuButtonCenterX, 150, "Load", g, UIElement.BUTTON_LOAD, this));
        elements.add(new MenuButton(menuButtonCenterX, 30, "Resume", g, UIElement.BUTTON_RESUME, this));
        elements.add(new DropDownMenu(SliderCenterX,300,300,50,UIElement.DROPDOWN_SELECTED ,this , scales));
        elements.add(new MenuButton(menuButtonCenterX, 360, "Main Menu", g, UIElement.BUTTON_BACK, this));
        uiElementContainer = new UIElementContainer(elements);
        super.zOrder = ScreenManager.ZOrder.OVER_UI;
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        uiElementContainer.update(timeScale, timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        //draw black background
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, super.width, super.height);
        //draw UI elements
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