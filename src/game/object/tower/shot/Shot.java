package game.object.tower.shot;

import game.framework.math.Vector2d;
import game.object.enemy.Enemy;
import game.object.tile.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Basic shot class.
 * Created by Jannes Peters on 2/20/2015.
 */
//TODO implement class!
public class Shot {
    protected static final double BASIC_SPEED = 8.0d;

    //use Vector2d for any coordinates!
    protected Vector2d currentLocation;
    protected Enemy destinationEnemy;
    protected float speed;
    protected int damage;
    protected boolean destroy = false;
    protected BufferedImage texture;

    public Shot(Vector2d start, Enemy destinationEnemy, float speed, int damage, BufferedImage texture) {
        this.texture = texture;
        this.currentLocation = new Vector2d(start);
        this.destinationEnemy = destinationEnemy;
        this.speed = speed;
        this.damage = damage;
    }

    public void realign(int x, int y) {
    }

    public void draw(Graphics2D g) {
        g.drawImage(texture, (int)currentLocation.getX(), (int)currentLocation.getY(), (int)(TileMap.tileSize / 5), (int)(TileMap.tileSize / 5), null);
    }

    public boolean shouldDestroy() {
        return destroy;
    }

    private void destroy() {
        destroy = true;
    }

    public void update(double timeScale, Enemy[] enemies) {
        currentLocation.add(new Vector2d(currentLocation, destinationEnemy.getLocation()).normalize().scale(BASIC_SPEED * speed * timeScale));
        if (new Vector2d(currentLocation, destinationEnemy.getLocation()).length() < (TileMap.tileSize - 5)) {
            destinationEnemy.hit(damage);
            destroy();
        }
    }
}
