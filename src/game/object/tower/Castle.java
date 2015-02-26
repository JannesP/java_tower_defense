package game.object.tower;

import game.framework.resources.Textures;

/**
 * Created by Jannes Peters on 2/21/2015.
 */
public class Castle extends Tower {

    public Castle() {
        super.texture = Textures.castleTexture;
        super.costPerLevel = new int[]{0, 100, 250, 500, 1000};
        super.fireRatePerLevel = new double[]{0, 0, 0, 0, 0};
        super.damagePerLevel = new int[]{0, 0, 0, 0, 0};
        super.rangePerLevel = new int[]{0, 0, 0, 0, 0};
        super.critRate = 0.0f;
    }

    @Override
    protected void fire() {
    }
}
