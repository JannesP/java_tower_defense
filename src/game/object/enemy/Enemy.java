package game.object.enemy;

import game.drawable.IPaintableUpdatableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A basic, working enemy.
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Enemy implements IPaintableUpdatableObject {
    protected static final double BASIC_SPEED = 0.025;

    protected BufferedImage texture;
    protected int health;
    protected double moveSpeed; //TODO test move speeds
    protected int goldToEarn;
    protected Point[] wayPoints;

    protected Enemy(BufferedImage texture, int health, double moveSpeed, int goldToEarn, Point[] wayPoints) {
        this.texture = texture;
        this.health = health;
        this.moveSpeed = moveSpeed;
        this.goldToEarn = goldToEarn;
        this.wayPoints = wayPoints;
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void realign(int width, int height, Graphics2D g) {

    }
}
