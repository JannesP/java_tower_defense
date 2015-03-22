package game.object.enemy;

import game.drawable.IPaintableUpdatableObject;
import game.framework.Util;
import game.framework.math.Vector2d;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 3/7/2015.
 */
public class EnemyWave implements IPaintableUpdatableObject {

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Vector2d[] wayPoints;

    public EnemyWave(Vector2d[] wayPoints) {
        this.wayPoints = wayPoints;
        enemies.add(new EnemyDummy(wayPoints));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    private long timeSinceLastShot = 0;

    @Override
    public void update(double timeScale, long timeDiff) {
        //remove dead
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (!enemy.isAlive()) {
                enemies.remove(i--);
            }
        }

        timeSinceLastShot += timeDiff;
        if ( (Util.NANO_SECOND_SECOND / this.timeSinceLastShot) <= 0.2d) {
            enemies.add(new EnemyDummy(wayPoints));
            timeSinceLastShot = 0;
        }

        for (Enemy e : enemies) {
            e.update(timeScale, timeDiff);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        for (Enemy e : enemies) {
            e.realign(width, height, g);
        }
    }
}
