package game.object.tower;

import game.framework.resources.Textures;

import java.awt.image.BufferedImage;


/**
 * Created by Addy on 23.02.2015.
 */
public class HeavyTower extends Tower{

    public static final BufferedImage texture = Textures.lightTowerTexture;
    public static final double[] fireRatePerLevel = new double[] {0.3, 0.4, 0.5, 0.6, 0,8};
    public static final int[] costPerLevel = new int[] {125, 75, 125, 150, 200};
    public static final int[] damagePerLevel = new int[] {10, 15, 20, 30, 40};
    public static final int[] rangePerLevel = new int[] {90, 105, 120, 140, 160};
    public static final int[] sellValue = new int[] {0, 0, 0, 0, 0};
    public static final float[] critRate = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public HeavyTower(int ownerId){
        super(ownerId);
    }

    @Override
    public int getId() {
        return Tower.TOWER_HEAVY;
    }

    @Override
    protected void fire() {}

    @Override
    public BufferedImage getTexture() {
        return texture;
    }
}
