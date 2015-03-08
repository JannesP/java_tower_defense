package game.object.enemy;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;

/**
 * Created by Addy on 23.02.2015.
 */
public class Runner extends Enemy {

    public Runner(Vector2d[] wayPoints) {
        super(Textures.runnerTexture, 5, 2.0d, 1, wayPoints);
    }
}
