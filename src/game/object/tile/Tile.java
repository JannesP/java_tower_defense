package game.object.tile;

import game.object.enemy.Enemy;
import game.object.tower.Castle;
import game.object.tower.Tower;

import java.awt.*;
import java.util.ArrayList;

/**
 * The basic tile class
 */
public class Tile {

	protected Tower tower;
    protected int x, y;
    protected boolean isRoad, isBuildable, isEntrance;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y, boolean isRoad) {
        this(x, y, isRoad, (isRoad ? false : true));
    }

    public Tile(int x, int y, boolean isRoad, boolean isBuildable) {
        this(x, y);
        this.isRoad = isRoad;
        if (this.isRoad) {
            this.isBuildable = false;
        }
    }

    public Tile(int x, int y, boolean isRoad, boolean isBuildable, boolean isEntrance) {
        this(x, y, isRoad, isBuildable);
        this.isEntrance = isEntrance;
    }

    public void setTileObject(Tower tower) {
        this.tower = tower;
        this.tower.realign(this.x, this.y);
    }

	public void update(long timeDiff, ArrayList<Enemy> enemies) {
		if (this.tower != null) {
            this.tower.update(timeDiff, enemies);
        }
	}

    public void draw(Graphics2D g) {
        if (this.tower != null) {
            this.tower.draw(g);
        }
    }

    public void realign(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public boolean isBuildable() {
        return isBuildable;
    }

    public boolean hasCastle() {
        return (tower != null && tower instanceof Castle);
    }
}
