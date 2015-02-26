package game.object.enemy;

import game.framework.resources.Textures;

/**
 * Created by Addy on 23.02.2015.
 */
public class Heavy extends Enemy {
    public Heavy(){
        super.texture = Textures.heavyTexture;
        super.health = 25;
        super.moveSpeed = 20; //TODO Try speed values
        super.goldToEarn = 8;
    }
}
