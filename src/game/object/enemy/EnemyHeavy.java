package game.object.enemy;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;

/**
 * Created by Addy on 23.02.2015.
 */
public class EnemyHeavy extends Enemy {

    public EnemyHeavy(Vector2d[] wayPoints) {
        super(Textures.runnerTexture, 25, 1.0d, 8, wayPoints);
    }

}
