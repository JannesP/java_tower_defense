package game.object.tower;

import game.framework.resources.Textures;

/**
 * Created by Jannes Peters on 2/21/2015.
 */
public class Castle extends Tower {

    public Castle() {
        super.texture = Textures.castleTexture;
        super.costPerLevel = new int[]{0, 100, 250, 500, 1000};
        super.fireRatePerLevel = new double[]{0.5, 0.6, 0.7, 0.8, 1};
        super.damagePerLevel = new int[]{20, 40, 80, 100, 200};
        super.rangePerLevel = new int[]{100, 110, 130, 140, 180};
    }

    @Override
    protected void fire() {
    }
}
