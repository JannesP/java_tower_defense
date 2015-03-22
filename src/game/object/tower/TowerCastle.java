package game.object.tower;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;
import game.object.enemy.Enemy;
import game.object.tile.TileMap;
import game.object.tower.shot.Shot;
import game.object.tower.shot.TestShot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Castle which is basically a tower which renders over 4 Tiles.
 * Created by Jannes Peters on 2/21/2015.
 */
public class TowerCastle extends Tower {
    public static final double[] fireRate = new double[] {0.3, 0.4, 0.5, 0.6, 0,8};
    public static final int[] cost = new int[] {125, 75, 125, 150, 200};
    public static final int[] damage = new int[] {10, 15, 20, 30, 40};
    public static final int[] range = new int[] {90, 105, 120, 140, 160};
    public static final int[] sellValue = new int[] {0, 0, 0, 0, 0};
    public static final float[] critRate = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    private static int castlesCreated = 0;
    private boolean isFirstCastle = false;

    public TowerCastle(int ownerId, Vector2d center) {
        super(ownerId, Textures.castleTexture, center, sellValue, critRate, range, damage, fireRate, cost);
        if (TowerCastle.castlesCreated++ == 0) {
            isFirstCastle = true;
        } else if (TowerCastle.castlesCreated == 4) {
            TowerCastle.castlesCreated = 0;
        }
    }

    @Override
    public Shot getShot(Vector2d start, Enemy destinationEnemy, float speed, int damage) {
        return new TestShot(super.center, destinationEnemy, speed, damage);
    }

    @Override
    public void draw(Graphics2D g) {
        if (isFirstCastle) {
            g.drawImage(texture, x, y, (int) (x + TileMap.tileSize * 2), (int) (y + TileMap.tileSize * 2), texture.getHeight() * level, 0, texture.getHeight() * level + texture.getHeight(), texture.getHeight(), null);
        }
    }

    @Override
    public int getId() {
        return Tower.TOWER_CASTLE;
    }

    @Override
    public BufferedImage getTexture() {
        return null;
    }

    public static int getRange(int level) {
        return range[level];
    }
}
