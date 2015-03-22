package game.object.tile;

import game.drawable.IPaintableUpdatableObject;
import game.framework.math.Vector2d;
import game.object.enemy.Enemy;
import game.object.tower.Tower;
import game.object.tower.TowerCastle;

import java.awt.*;

/**
 * The basic tile class.
 * Created by Jannes Peters on XX/YY/2014 ... date was deleted!
 */
public class Tile implements IPaintableUpdatableObject{

    public static final int TEXTURE_SIZE = 60;
    protected Tower tower;
    protected int x, y;
    protected boolean isRoad, isBuildable, isEntrance;
    protected Vector2d center;

    public Tile(int x, int y) {
        this(x, y, false);
    }

    public Tile(int x, int y, boolean isRoad) {
        this(x, y, isRoad, !isRoad);
    }

    public Tile(int x, int y, boolean isRoad, boolean isBuildable) {
        this(x, y, isRoad, isBuildable, false);
    }

    public Tile(int x, int y, boolean isRoad, boolean isBuildable, boolean isEntrance) {
        this.x = x;
        this.y = y;
        this.isRoad = isRoad;
        this.isBuildable = isBuildable;
        this.isEntrance = isEntrance;
        this.center = new Vector2d(x + TileMap.tileSize / 2d, y + TileMap.tileSize / 2d);
    }

    public void setTileObject(Tower tower) {
        this.tower = tower;
        this.tower.realign(this.x, this.y);
    }

	public void update(double timeScale, long timeDiff, Enemy[] enemies) {
		if (this.tower != null) {
            this.tower.update(timeScale, timeDiff, enemies);
        }
	}

    @Override
    public void update(double timeScale, long timeDiff) {}

    public void draw(Graphics2D g) {
        if (this.tower != null) {
            this.tower.draw(g);
        }
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {}

    public void realign(int x, int y) {
        this.x = x;
        this.y = y;
        this.center = new Vector2d(x + TileMap.tileSize / 2d, y + TileMap.tileSize / 2d);
        if (this.tower != null) tower.realign(x, y);
    }

    public Vector2d getCenter() {
        return center;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public boolean isBuildable() {
        return (isBuildable && tower == null);
    }

    public boolean hasCastle() {
        return (tower != null && tower instanceof TowerCastle);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
