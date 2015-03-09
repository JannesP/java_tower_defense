package game.framework.screens;

import game.object.enemy.EnemyMap;
import game.object.player.Player;
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
    private EnemyMap enemyMap;
    private Player player;

    public GameScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        super.requestScreen(new UIScreen("uiScreen", width, height, g));
        this.player = new Player("SPlayerName", 0, 100);
        tileMap = new TileMap(0, player);
        enemyMap = new EnemyMap(0, tileMap);
        super.zOrder = ScreenManager.ZOrder.BACKGROUND;
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {
        for (KeyEvent event : events) {
            if (event.getID() == KeyEvent.KEY_PRESSED) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        super.requestScreen(new IngameOptionScreen("testScreen", this.width, this.height, super.graphics2D));
                        break;
                }
            }
        }
    }

    @Override
    public void handleMouseInput(ArrayList<MouseEvent> events) {

    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (tileMap != null) tileMap.realign();
        if (enemyMap != null) enemyMap.realign(width, height, g);
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        tileMap.update(timeScale, timeDiff, enemyMap.getEnemies());
        enemyMap.update(timeScale, timeDiff);
    }

    @Override
    public void draw(Graphics2D g) {
        tileMap.draw(g);
        enemyMap.draw(g);
    }

    @Override
    public void closeRequested() {
        System.out.println("Close init by: " + this.getClass().toString());
        super.closeGame();
    }
}
