package game.object.enemy;

import game.framework.resources.Textures;

/**
 * Created by Addy on 23.02.2015.
 */
public class Giant extends Enemy {
    public Giant(){
        super.texture = Textures.runnerTexture;
        super.health = 25;
        super.moveSpeed = 20;
        super.goldToEarn = 8;
    }
}
