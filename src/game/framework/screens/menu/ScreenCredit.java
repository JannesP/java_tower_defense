package game.framework.screens.menu;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;
import game.framework.screens.ScreenBase;
import game.ui.container.UIElementContainer;
import game.ui.element.Label;
import game.ui.element.UIElement;
import game.ui.element.button.ButtonText;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class ScreenCredit extends ScreenBase implements IUIActionReceiver {

    @Override
    public void performAction(UIElement sender, int buttonAction) {
        switch (buttonAction) {
            case UIElement.BUTTON_BACK:
                super.requestScreen(new ScreenOption("optionScreen", this.width, this.height, super.graphics2D));
                super.unload();
                break;
            default:
                System.out.println("BUTTON FAILURE! Action: '" + buttonAction + "' not defined in " + this.getClass().toString());
        }
    }

    private UIElementContainer uiElementContainer;

    public ScreenCredit(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
        ArrayList<UIElement> elements = new ArrayList<>();
        String[] labelTexts = new String []{
                "Project Lead and Main Programming:",
                "Jannes Peters",
                "Sound Artists:",
                "Fynn König, Julian Böteführ",
                "Creative Artists:",
                "Tjorven Hoppe, Niclas Kirstein, Lars Pfeiffer",
                "Menu Layout and Programming:",
                "Adrian Kurth"
        };
        final int SPACING = 10;
        int nextY = Util.calculateCenterPosition(height, labelTexts.length * (Util.getFontHeight(g) + (int)((double)SPACING * 1.5d)) + SPACING * 3 + MenuButton.HEIGHT);

        for(int i = 1 ; i < labelTexts.length + 1; i++){
            String text = labelTexts[i - 1];
            nextY += (i == 1) ? 0 : (Util.getFontHeight(g) + SPACING + (i % 2 * SPACING));
            int labelCenterX = Util.calculateCenterPosition(this.width,Util.getStringWidth(text, g));
            Label label = new Label(labelCenterX, nextY, 0, text);
            if (i % 2 == 1) {
                label.setFont(Fonts.getDefaultFont().deriveFont(Font.ITALIC));
            }
            elements.add(label);
        }
        elements.add(new MenuButton(menuButtonCenterX, nextY + SPACING * 3, "Back", g, UIElement.BUTTON_BACK, this));
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
