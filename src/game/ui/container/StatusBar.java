package game.ui.container;

import game.drawable.IPaintableUpdatableObject;
import game.framework.Util;
import game.framework.input.ButtonHandler;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;
import game.framework.screens.UIScreen;
import game.ui.element.button.Button;
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

    private ButtonHandler buttonHandler;

    public StatusBar(int width, int height, Graphics2D g, IUIActionReceiver buttonActionReceiver) {
        g.setFont(Fonts.statusBarFont);
        int calcHeight = g.getFontMetrics().getHeight() + (Util.PADDING * 2);
        border = new Rectangle(0, 0, width, calcHeight);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new MultiImageButton(Util.PADDING, Util.PADDING, this.getHeight() - Util.PADDING * 2, this.getHeight() - Util.PADDING * 2, Textures.button_main_menu, buttonActionReceiver, Textures.iconSpeaker, 2, g, UIScreen.ButtonAction.MUTE));
        ((MultiImageButton)buttons.get(0)).setImageIndex(1);
        buttonHandler = new ButtonHandler(buttons, buttonActionReceiver);
        realign(width, height, g);
    }

    @Override
    public void update(long timeDiff) {
        buttonHandler.update(timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) border.getX(), (int) border.getY(), (int) border.getWidth(), (int) border.getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(Fonts.statusBarFont);
        g.drawString("statusBarBorder", (int) border.getX() + Util.PADDING * 2 + (this.getHeight() - Util.PADDING * 2), (int) border.getY() + Util.PADDING + g.getFontMetrics().getMaxAscent());

        buttonHandler.draw(g);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        buttonHandler.realign(width, height, g);
        g.setFont(Fonts.statusBarFont);
        int calcHeight = g.getFontMetrics().getHeight() + (Util.PADDING * 2);
        border = new Rectangle(0, 0, width, calcHeight);
    }

    public void handleKeyInput(ArrayList<KeyEvent> events) {
        buttonHandler.handleKeyInput(events);
    }

    public void handleMouseInput(ArrayList<MouseEvent> events) {
        buttonHandler.handleMouseInput(events);
    }

    public int getHeight() {
        return (int) border.getHeight();
    }
}
