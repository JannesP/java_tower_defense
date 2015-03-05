package game.framework.screens;

import game.object.enemy.Enemy;
import game.object.tile.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Screen that handles the game.
 * Created by Jannes Peters on 2/22/2015.
 */
public class GameScreen extends BaseScreen {
    private TileMap tileMap;
    private ArrayList<Enemy> enemies;

    public GameScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        super.requestScreen(new UIScreen("uiScreen", width, height, g));
        tileMap = new TileMap(0);
        tileMap.realign();
        enemies = new ArrayList<>();
        super.zOrder = ScreenManager.ZOrder.BACKGROUND;
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {

    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {

    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (tileMap != null) tileMap.realign();
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        tileMap.update(timeScale, timeDiff, enemies);
    }

    @Override
    public void draw(Graphics2D g) {
        tileMap.draw(g);
    }

    @Override
    public void closeRequested() {
        System.out.println("Close init by: " + this.getClass().toString());
        super.closeGame();
    }
}
