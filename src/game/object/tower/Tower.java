package game.object.tower;

import game.framework.Util;
import game.object.enemy.Enemy;
import game.object.tile.Tile;
import game.object.tile.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Default tower template.
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Tower {
    /**
     * 0 based (in UI 1 level higher)
     */
    protected int level = 0;
    protected static final int MAX_LEVEL = 5;
    /**
     * reference to textures in <class>game.framework.resources.Textures</class>
     */
    protected BufferedImage texture;
    /**
     * Calculated x, y to draw!
     */
    protected int x, y;
    /**
     * Time since last shot in nanos.
     */
    protected long timeSinceLastShot = 0;

    //stats
    protected int costPerLevel[];
    /**
     * In shots per second eg. 0.5 = 1 shot every 2 seconds
     */
    protected double fireRatePerLevel[];
    protected int damagePerLevel[];
    /**
     * in unscaled pixels
     */
    protected int rangePerLevel[];

    /**
     * Called with already calculated x and y! Just take and save them.
     */
    public void realign(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Called once a frame.
     * @param g - Graphics2D to draw on
     */
    public void draw(Graphics2D g) {
        g.drawImage(texture, x, y, (int)(x + TileMap.tileSize), (int)(y + TileMap.tileSize), Tile.TEXTURE_SIZE * level, 0, Tile.TEXTURE_SIZE * level + Tile.TEXTURE_SIZE, Tile.TEXTURE_SIZE, null);
    }

    /**
     * Called once a frame before draw(). Make all calculations here!
     * @param timeScale - time difference in nanos
     * @param enemies - all enemies currently on the map
     */
    public void update(double timeScale, long timeDiff, Enemy[] enemies) {
        timeSinceLastShot += timeDiff;
        if ( (Util.NANO_SECOND_SECOND / this.timeSinceLastShot) <= fireRatePerLevel[this.level]) {
            fire();
        }
    }

    protected abstract void fire();
}
