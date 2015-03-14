package game.object.tower;

import game.drawable.ITextured;
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
public abstract class Tower implements ITextured {
    public static final int TOWER_CASTLE = -1;
    public static final int TOWER_LIGHT = 1;
    public static final int TOWER_HEAVY = 2;

    /**
     * 0 based (in UI 1 level higher)
     */
    protected int level = 0;
    public static final int MAX_LEVEL = 4;
    /**
     * reference to textures in <class>game.framework.resources.Textures</class>
     */
    public static BufferedImage texture;
    /**
     * Calculated x, y to draw!
     */
    protected int x, y;
    /**
     * Time since last shot in nanos.
     */
    protected long timeSinceLastShot = 0;

    //stats
    public static int costPerLevel[];
    /**
     * In shots per second eg. 0.5 = 1 shot every 2 seconds
     */
    public static double fireRatePerLevel[];
    public static int damagePerLevel[];
    /**
     * as a factor
     */
    public static int rangePerLevel[];
    /**
     *  Critical hit rate in percent
     */
    public static float[] critRate;
    /**
     * The id of the owner.
     */
    protected int owner;

    protected Tower(int owner) {
        this.owner = owner;
    }

    /**
     * Called with already calculated x and y! Just take and save them.
     */
    protected int sellValue[];
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

    /**
     * Should return a unique id for the class.
     */
    public abstract int getId();

    protected abstract void fire();
}
