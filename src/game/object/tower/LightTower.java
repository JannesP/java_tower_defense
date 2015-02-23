package game.object.tower;

import game.framework.resources.Textures;

/**
 * Created by Addy on 23.02.2015.
 */
public class LightTower extends Tower {

    public LightTower() {
        super.texture = Textures.arrowTowerTexture;
        super.costPerLevel = new int[]{50, 50, 75, 100, 150};
        super.fireRatePerLevel = new double[]{0.6, 0.7, 0.8, 0.9, 1};
        super.damagePerLevel = new int[]{2, 4, 6, 8, 10};
        super.rangePerLevel = new int[]{80, 100, 120, 140, 160};
    }

    @Override
    protected void fire() { }
}
