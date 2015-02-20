package game.object.tower;

import game.framework.Util;
import game.object.enemy.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Tower {
    /**
     * 0 based (in UI 1 level higher)
     */
    protected int level = 0;
    /**
     * reference to textures in <class>game.framework.Textures</class>
     */
    protected BufferedImage[] textures;
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
        g.drawImage(textures[level], x, y, null);
    }

    /**
     * Called once a frame before draw(). Make all calculations here!
     * @param timeDiff - time difference in nanos
     * @param enemies - all enemies currently on the map
     */
    public void update(long timeDiff, ArrayList<Enemy> enemies) {
        timeSinceLastShot += timeDiff;
        if ( (Util.NANO_SECOND_SECOND / this.timeSinceLastShot) <= fireRatePerLevel[this.level]) {
            fire();
        }
    }

    protected abstract void fire();
}
