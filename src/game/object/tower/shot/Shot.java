package game.object.tower.shot;

import game.object.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Shot {
    //TODO use Vector2d for any coordinates!
    protected float speed;
    protected int damage;

    public Shot(Enemy destinationEnemy) {
        //TODO Write Function
    }

    public void realign(int x, int y) {
        //TODO implement
    }

    public void draw(Graphics2D g) {

    }

    public void update(long timeDiff, ArrayList<Enemy> enemies) {

    }
}
