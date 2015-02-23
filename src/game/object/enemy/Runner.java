package game.object.enemy;

import game.framework.resources.Textures;

/**
 * Created by Addy on 23.02.2015.
 */
public class Runner extends Enemy {
    public Runner() {
        super.texture = Textures.runnerTexture;
        super.health = 5;
        super.moveSpeed = 50;
        super.goldToEarn = 1;
    }
}
