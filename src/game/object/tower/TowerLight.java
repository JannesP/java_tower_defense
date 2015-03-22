package game.object.tower;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;
import game.object.enemy.Enemy;
import game.object.tower.shot.Shot;
import game.object.tower.shot.TestShot;

import java.awt.image.BufferedImage;

/**
 *
 * Created by Addy on 23.02.2015.
 */
public class TowerLight extends Tower {

    public static final double[] fireRate = new double[] {1.3, 0.7, 0.8, 0.9, 1};
    public static final int[] cost = new int[] {50, 50, 75, 100, 150};
    public static final int[] damage = new int[] {2, 4, 6, 8, 10};
    public static final int[] range = new int[] {150, 180, 200, 200, 250};
    public static final int[] sellValue = new int[] {0, 0, 0, 0, 0};
    public static final float[] critRate = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public TowerLight(int ownerId, Vector2d center) {
        super(ownerId, Textures.lightTowerTexture, center, sellValue, critRate, range, damage, fireRate, cost);
    }

    @Override
    public int getId() {
        return Tower.TOWER_LIGHT;
    }

    @Override
    public Shot getShot(Vector2d start, Enemy destinationEnemy, float speed, int damage) {
        return new TestShot(super.center, destinationEnemy, speed, damage);
    }

    @Override
    public BufferedImage getTexture() {
        return texture;
    }

    public static int getRange(int level) {
        return range[level];
    }
}
