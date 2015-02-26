package game.object.tower;

import game.framework.resources.Textures;


/**
 * Created by Addy on 23.02.2015.
 */
public class HeavyTower extends Tower{
    public HeavyTower(){
        super.texture = Textures.heavyTowerTexture;
        super.costPerLevel = new int[]{125, 75, 125, 150, 200};
        super.fireRatePerLevel = new double[]{0.3, 0.4, 0.5, 0.6, 0,8}; //TODO Balancing
        super.damagePerLevel = new int[]{10, 15, 20, 30, 40};
        super.rangePerLevel = new int[]{90, 105, 120, 140, 160};
        super.critRate = 0.0f;
        super.sellValue = new int[]{0,0,0,0,0};
    }
    @Override
    protected void fire() {

    }

}
