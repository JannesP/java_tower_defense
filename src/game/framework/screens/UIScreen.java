package game.framework.screens;

import game.framework.resources.Fonts;
import game.object.tile.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 23.02.2015.
 */
public class UIScreen extends BaseScreen {
    private Rectangle bottomDrawBorder;
    private Rectangle statusBarBorder;

    public UIScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        statusBarBorder = getStatusBarBorder(width, g);
        bottomDrawBorder = getBottomDrawBorder(width, height);
        super.zOrder = ScreenManager.ZOrder.UI;
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {

    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {

    }

    @Override
    public void update(long timeDiff) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) bottomDrawBorder.getX(), (int) bottomDrawBorder.getY(), (int) bottomDrawBorder.getWidth(), (int) bottomDrawBorder.getHeight());

        g.fillRect((int) statusBarBorder.getX(), (int) statusBarBorder.getY(), (int) statusBarBorder.getWidth(), (int) statusBarBorder.getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(Fonts.defaultFont);
        g.drawString("bottomDrawBorder", (int)bottomDrawBorder.getX() + Fonts.PADDING, (int)bottomDrawBorder.getY() + Fonts.PADDING + g.getFontMetrics().getMaxAscent());
        g.setFont(Fonts.statusBarFont);
        g.drawString("statusBarBorder", (int) statusBarBorder.getX() + Fonts.PADDING, (int) statusBarBorder.getY() + Fonts.PADDING + g.getFontMetrics().getMaxAscent());
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        statusBarBorder = getStatusBarBorder(width, g);
        bottomDrawBorder = getBottomDrawBorder(width, height);
    }

    private Rectangle getBottomDrawBorder(int width, int height) {
        int drawHeight = (int) (TileMap.tileSize * 3);
        int y = height - (int) (TileMap.tileSize * 3);
        return new Rectangle(0, y, width, drawHeight);
    }

    private Rectangle getStatusBarBorder(int width, Graphics2D g) {
        g.setFont(Fonts.statusBarFont);
        int height = g.getFontMetrics().getHeight() + (Fonts.PADDING * 2);
        return new Rectangle(0, 0, width, height);
    }

}
