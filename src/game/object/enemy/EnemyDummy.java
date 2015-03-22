package game.object.enemy;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;

/**
 * Simple example enemy.
 * Created by Jannes Peters on 3/7/2015.
 */
public class EnemyDummy extends Enemy {
    /**
     * Basic constructor for a <code>Dummy</code>
     * @param wayPoints  - the wayPoints on which this enemy should follow in calculated x/y coordinates!
     */
    public EnemyDummy(Vector2d[] wayPoints) {
        super(Textures.runnerTexture, 20, 1.0d, 550, wayPoints);
    }
}
