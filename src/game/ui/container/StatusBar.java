package game.ui.container;

import game.drawable.IPaintableUpdatableObject;
import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;
import game.ui.element.*;
import game.ui.element.button.MultiImageButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Status bar is part of the UIScreen
 * Created by Jannes Peters on 2/25/2015.
 */
public class StatusBar implements IPaintableUpdatableObject {

    private Rectangle border;

    private UIElementContainer uiElementContainer;

    public StatusBar(int width, int height, Graphics2D g, IUIActionReceiver actionReceiver) {
        g.setFont(Fonts.statusBarFont);
        int calcHeight = g.getFontMetrics().getHeight() + (Util.PADDING * 2);
        border = new Rectangle(0, 0, width, calcHeight);

        ArrayList<UIElement> elements = new ArrayList<>();
        elements.add(new MultiImageButton(Util.PADDING, Util.PADDING, this.getHeight() - Util.PADDING * 2, this.getHeight() - Util.PADDING * 2, Textures.button_main_menu, actionReceiver, Textures.iconSpeaker, 2, g, UIElement.BUTTON_MUTE));
        ((MultiImageButton)elements.get(0)).setImageIndex(1);
        elements.add(new Slider(elements.get(0).getRightBorder() + Util.PADDING, Util.PADDING, 250, this.getHeight() - Util.PADDING * 2, UIElement.SLIDER_VOLUME, actionReceiver));
        elements.add(new TextBox(350 + Util.PADDING, Util.PADDING, 200, this.getHeight() - Util.PADDING * 2, UIElement.TEXTBOX_INPUT, actionReceiver, "Example TextBox", 50));
        elements.add(new CheckBox(elements.get(elements.size() - 1).getRightBorder() + Util.PADDING, Util.PADDING, this.getHeight() - Util.PADDING * 2, UIElement.CHECKBOX_EXAMPLE, actionReceiver, "Example CB", g.getFontRenderContext()));
        elements.add(new DropDownMenu(elements.get(elements.size() - 1).getRightBorder() + Util.PADDING, Util.PADDING, 200, this.getHeight() - Util.PADDING * 2, UIElement.DROPDOWN_SELECTED, actionReceiver, new String[] {"Entry 1", "Entry 2", "Entry 3", "Entry 4"}));
        uiElementContainer = new UIElementContainer(elements);
        realign(width, height, g);
    }

    @Override
    public void update(long timeDiff) {
        uiElementContainer.update(timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) border.getX(), (int) border.getY(), (int) border.getWidth(), (int) border.getHeight());

        uiElementContainer.draw(g);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        uiElementContainer.realign(width, height, g);
        g.setFont(Fonts.statusBarFont);
        int calcHeight = g.getFontMetrics().getHeight() + (Util.PADDING * 2);
        border = new Rectangle(0, 0, width, calcHeight);
    }

    public void handleKeyInput(ArrayList<KeyEvent> events) {
        uiElementContainer.handleKeyInput(events);
    }

    public void handleMouseInput(ArrayList<MouseEvent> events) {
        uiElementContainer.handleMouseInput(events);
    }

    public int getHeight() {
        return (int) border.getHeight();
    }
}
