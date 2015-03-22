package game.object.tower;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;
import game.object.enemy.Enemy;
import game.object.tower.shot.Shot;
import game.object.tower.shot.TestShot;

import java.awt.image.BufferedImage;


/**
 * Created by Addy on 23.02.2015.
 */
public class TowerHeavy extends Tower{

    public static final double[] fireRate = new double[] {0.3, 0.4, 0.5, 0.6, 0,8};
    public static final int[] cost = new int[] {125, 75, 125, 150, 200};
    public static final int[] damage = new int[] {10, 15, 20, 30, 40};
    public static final int[] range = new int[] {90, 105, 120, 140, 160};
    public static final int[] sellValue = new int[] {0, 0, 0, 0, 0};
    public static final float[] critRate = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public TowerHeavy(int ownerId, Vector2d center) {
        super(ownerId, Textures.heavyTowerTexture, center, sellValue, critRate, range, damage, fireRate, cost);
    }

    @Override
    public Shot getShot(Vector2d start, Enemy destinationEnemy, float speed, int damage) {
        return new TestShot(super.center, destinationEnemy, speed, damage);
    }

    @Override
    public int getId() {
        return Tower.TOWER_HEAVY;
    }

    @Override
    public BufferedImage getTexture() {
        return texture;
    }

    public static int getRange(int level) {
        return range[level];
    }
}
