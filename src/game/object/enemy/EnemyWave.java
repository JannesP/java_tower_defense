package game.object.enemy;

import game.drawable.IPaintableUpdatableObject;
import game.framework.math.Vector2d;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 3/7/2015.
 */
public class EnemyWave implements IPaintableUpdatableObject {

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyWave(Vector2d[] wayPoints) {
        enemies.add(new EnemyDummy(wayPoints));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void update(double timeScale, long timeDiff) {
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
