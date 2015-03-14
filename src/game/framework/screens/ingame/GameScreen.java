package game.framework.screens.ingame;

import game.framework.screens.BaseScreen;
import game.framework.screens.ScreenManager;
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
    private UIScreen uiScreen;
    public boolean gamePaused = false;

    public GameScreen(String name, int width, int height, Graphics2D g) {
        super(name, width, height, g);
        this.player = new Player("SPlayerName", 0, 100);
        uiScreen = new UIScreen("uiScreen", width, height, g, this, player);
        super.requestScreen(uiScreen);
        tileMap = new TileMap(0, player);
        enemyMap = new EnemyMap(0, tileMap);
        super.zOrder = ScreenManager.ZOrder.BACKGROUND;
    }

    @Override
    public void unload() {
        uiScreen.unload();
        super.unload();
    }

    @Override
    public void handleKeyInput(ArrayList<KeyEvent> events) {
        for (KeyEvent event : events) {
            if (event.getID() == KeyEvent.KEY_PRESSED && !gamePaused) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        super.requestScreen(new InGameOptionScreen("inGameOptionsScreen", this.width, this.height, super.graphics2D, this));
                        gamePaused = true;
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
        if(!gamePaused) {
            tileMap.update(timeScale, timeDiff, enemyMap.getEnemies());
            enemyMap.update(timeScale, timeDiff);
        }
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

    public Player getPlayer() {
        return player;
    }
}
