package game.object.enemy;

import game.drawable.IPaintableUpdatableObject;
import game.framework.math.Vector2d;
import game.object.tile.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A basic, working enemy.
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Enemy implements IPaintableUpdatableObject {
    protected static final double BASIC_SPEED = 0.8d;

    protected BufferedImage texture;
    protected int health;
    protected double moveSpeed;
    protected double moveSpeedBonus;
    protected int goldToEarn;
    protected Vector2d[] wayPoints;
    protected int currentlyActiveWayPoint = 0;
    protected Vector2d directionVector, normalizedDirectionVector, lastPosition, movedVector;
    protected int x, y;

    /**
     * Basic constructor for an <code>Enemy</code>
     * @param texture - the texture for the enemy
     * @param health - the health
     * @param moveSpeed - the moveSpeed factor relative to the <code>Enemy.BASIC_SPEED</code>
     * @param goldToEarn - the gold you earn when killing this minion
     * @param wayPoints - the wayPoints on which this enemy should follow in calculated x/y coordinates!
     */
    protected Enemy(BufferedImage texture, int health, double moveSpeed, int goldToEarn, Vector2d[] wayPoints) {
        this.texture = texture;
        this.health = health;
        this.moveSpeed = moveSpeed;
        this.goldToEarn = goldToEarn;
        this.wayPoints = wayPoints;
        this.lastPosition = new Vector2d(wayPoints[0]);
        nextWayPoint();
    }

    /**
     * This function is for updating the way points!
     * @param wayPoints - the new scaled way points.
     */
    public void setWayPoints(Vector2d[] wayPoints) {
        if (this.wayPoints.length == wayPoints.length) {
            this.wayPoints = wayPoints;
        } else {
            throw new IllegalArgumentException("You shall not change the path. This function is only for scaling purposes!");
        }
    }

    /**
     *
     * @return the rest of length
     */
    public void nextWayPoint() {
        currentlyActiveWayPoint++;
        if (currentlyActiveWayPoint == wayPoints.length) currentlyActiveWayPoint = 1; //reset when at the end of the map
        Vector2d start = wayPoints[currentlyActiveWayPoint - 1];
        Vector2d destination = wayPoints[currentlyActiveWayPoint];
        directionVector = new Vector2d(start, destination);
        normalizedDirectionVector = (directionVector.newNormalized());
        movedVector = new Vector2d(0d, 0d);
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        double calcSpeed = (moveSpeed + moveSpeedBonus) * Enemy.BASIC_SPEED * timeScale;

        double leftMovement = directionVector.length() - movedVector.length();
        if (leftMovement < 0) {    //when going over the way point jump to the next
            nextWayPoint();
            movedVector.add(normalizedDirectionVector.returnScaled(leftMovement));
        }

        movedVector.add(normalizedDirectionVector.returnScaled(calcSpeed));
    }

    @Override
    public void draw(Graphics2D g) {
        Vector2d lastLocation = wayPoints[currentlyActiveWayPoint - 1];

        int dx1 = (int)(lastLocation.getX() + movedVector.getX()) - (int)(TileMap.tileSize / 2d);
        int dx2 = (int)(lastLocation.getX() + movedVector.getX()) + (int)(TileMap.tileSize / 2d);

        int dy1 = (int)(lastLocation.getY() + movedVector.getY()) - (int)(TileMap.tileSize / 2d);
        int dy2 = (int)(lastLocation.getY() + movedVector.getY()) + (int)(TileMap.tileSize / 2d);

        g.drawImage(this.texture, dx1, dy1, dx2, dy2, 0, 0, this.texture.getWidth(), this.texture.getHeight(), null);
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {}
}
