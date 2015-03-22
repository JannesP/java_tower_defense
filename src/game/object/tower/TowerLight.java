package game.object.tower;

import game.framework.resources.Textures;

import java.awt.image.BufferedImage;

/**
 *
 * Created by Addy on 23.02.2015.
 */
public class TowerLight extends Tower {

    public static final BufferedImage texture = Textures.lightTowerTexture;
    public static final double[] fireRatePerLevel = new double[] {0.6, 0.7, 0.8, 0.9, 1};
    public static final int[] costPerLevel = new int[] {50, 50, 75, 100, 150};
    public static final int[] damagePerLevel = new int[] {2, 4, 6, 8, 10};
    public static final int[] rangePerLevel = new int[] {80, 100, 120, 140, 160};
    public static final int[] sellValue = new int[] {0, 0, 0, 0, 0};
    public static final float[] critRate = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public TowerLight(int playerId) {
        super(playerId);
    }

    @Override
    public int getId() {
        return Tower.TOWER_LIGHT;
    }

    @Override
    public BufferedImage getTexture() {
        return texture;
    }

    @Override
    protected void fire() { }
}
