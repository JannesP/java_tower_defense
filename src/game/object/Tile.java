package game.object;

import game.object.enemy.Enemy;
import game.object.tower.Tower;

import java.awt.*;
import java.util.ArrayList;

/**
 * The basic tile class
 */
public class Tile {

	protected Tower tower;
    protected int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
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
}
