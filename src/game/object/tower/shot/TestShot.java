package game.object.tower.shot;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;
import game.object.enemy.Enemy;

/**
 * Created by Jannes Peters on 3/22/2015.
 */
public class TestShot extends Shot {
    public TestShot(Vector2d start, Enemy destinationEnemy, float speed, int damage) {
        super(start, destinationEnemy, speed, damage, Textures.placeholder);
    }
}
