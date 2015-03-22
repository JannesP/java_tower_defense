package game.object.tower;

import game.drawable.ITextured;
import game.framework.Util;
import game.framework.math.Vector2d;
import game.object.enemy.Enemy;
import game.object.tile.TileMap;
import game.object.tower.shot.Shot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Default tower template.
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Tower implements ITextured {
    public static final int TOWER_CASTLE = -1;
    public static final int TOWER_LIGHT = 1;
    public static final int TOWER_HEAVY = 2;

    protected final Vector2d center;

    /**
     * 0 based (in UI 1 level higher)
     */
    protected int level = 0;
    public static final int MAX_LEVEL = 4;
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
    public final int[] cost;
    /**
     * In shots per second eg. 0.5 = 1 shot every 2 seconds
     */
    public final double[] fireRate;

    public final int[] damage;
    /**
     * as a factor
     */
    public final int[] range;
    /**
     *  Critical hit rate in percent
     */
    public final float[] critRate;

    protected final int[] sellValue;

    protected final ArrayList<Shot> shots = new ArrayList<>(10);

    /**
     * The id of the owner.
     */
    protected int owner;

    protected Tower(int owner, BufferedImage texture, Vector2d center, int[] sellValue, float[] critRate, int[] range, int[] damage, double[] fireRate, int[] cost) {
        this.owner = owner;
        this.sellValue = sellValue;
        this.critRate = critRate;
        this.range = range;
        this.damage = damage;
        this.fireRate = fireRate;
        this.cost = cost;
        this.texture = texture;
        this.center = center;
    }

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
        for (Shot shot : shots) {
            shot.draw(g);
        }

        g.drawImage(texture, x, y, (int)(x + TileMap.tileSize), (int)(y + TileMap.tileSize), texture.getHeight() * level, 0, texture.getHeight() * level + texture.getHeight(), texture.getHeight(), null);
    }

    /**
     * Called once a frame before draw(). Make all calculations here!
     * @param timeScale - time difference in nanos
     * @param enemies - all enemies currently on the map
     */
    public void update(double timeScale, long timeDiff, Enemy[] enemies) {
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            if (shot.shouldDestroy()) {
                shots.remove(i--);
            }
        }
        for (Shot shot : shots) {
            shot.update(timeScale, enemies);
        }

        timeSinceLastShot += timeDiff;
        if ( (Util.NANO_SECOND_SECOND / this.timeSinceLastShot) <= fireRate[this.level]) {
            if (fire(enemies)) this.timeSinceLastShot = 0;
        }
    }

    /**
     * Should return a unique id for the class.
     */
    public abstract int getId();

    public abstract Shot getShot(Vector2d start, Enemy destinationEnemy, float speed, int damage);

    protected boolean fire(Enemy[] enemies) {
        Enemy target = getFurthestTargetInRange(enemies);
        if (target != null) {
            shots.add(getShot(this.center, target, 1f, this.damage[this.level]));
            return true;
        }
        return false;
    }

    protected Enemy getFurthestTargetInRange(Enemy[] enemies) { //TODO check all towers for distance to jump over any that is not possible - not important!
        for (Enemy enemy : enemies) {
            if (Vector2d.getDistance(enemy.getLocation(), this.center) <= range[this.level]) {
                return enemy;
            }
        }
        return null;
    }
}
