package game.object.enemy;

import game.drawable.IPaintableUpdatableObject;
import game.framework.math.Vector2d;
import game.framework.resources.Maps;
import game.object.tile.TileMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A wrapper for storing multiple EnemyWaves
 * Created by Jannes Peters on 3/7/2015.
 */
public class EnemyMap implements IPaintableUpdatableObject {

    public ArrayList<EnemyWave> waves = new ArrayList<>();

    public EnemyMap(int level, TileMap tileMap) {
        Vector2d[][] calculatedPositions = scalePaths(Maps.maps[level].getPaths(), tileMap);
        waves.add(new EnemyWave(calculatedPositions[0]));
    }

    private static Vector2d[][] scalePaths(Vector2d[][] paths, TileMap tileMap) {
        Vector2d[][] calculatedPositions = new Vector2d[paths.length][paths[0].length];
        for (int pathId = 0; pathId < paths.length; pathId++) {
            Vector2d scaledVector = paths[pathId][0].returnScaled(TileMap.tileSize);
            scaledVector.add(new Vector2d(0.5d * TileMap.tileSize, 0.5d * TileMap.tileSize)); //move in the middle
            calculatedPositions[pathId][0] = scaledVector;
            for (int pointId = 1; pointId < paths[0].length; pointId++) {
                //Vector2d scaledVector = paths[pathId][pointId].returnScaled(TileMap.tileSize);
                //scaledVector.add(new Vector2d(0.5d * TileMap.tileSize, 0.5d * TileMap.tileSize)); //move in the middle
                calculatedPositions[pathId][pointId] = tileMap.getTile(paths[pathId][pointId]).getCenter();
            }
        }
        return calculatedPositions;
    }

    /**
     *
     * @return
     */
    public Enemy[] getEnemies() {
        int size = 0;
        for (EnemyWave enemyWave : waves) {
            size += enemyWave.getEnemies().size();
        }
        Enemy[] enemies = new Enemy[size];

        int i = 0;
        for (EnemyWave enemyWave : waves) {
            for (Enemy enemy : enemyWave.getEnemies()) {
                enemies[i++] = enemy;
            }
        }
        Arrays.sort(enemies);
        return enemies;
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        for (EnemyWave wave : waves) {
            wave.update(timeScale, timeDiff);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        for (EnemyWave wave : waves) {
            wave.draw(g);
        }
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {
        for (EnemyWave wave : waves) {
            wave.realign(width, height, g);
        }
    }
}
