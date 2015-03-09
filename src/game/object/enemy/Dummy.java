package game.object.enemy;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;

/**
 * Simple example enemy.
 * Created by Jannes Peters on 3/7/2015.
 */
public class Dummy extends Enemy {
    /**
     * Basic constructor for a <code>Dummy</code>
     * @param wayPoints  - the wayPoints on which this enemy should follow in calculated x/y coordinates!
     */
    protected Dummy(Vector2d[] wayPoints) {
        super(Textures.runnerTexture, 100, 1.0d, 550, wayPoints);
    }
}