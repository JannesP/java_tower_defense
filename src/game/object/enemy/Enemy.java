package game.object.enemy;

import game.drawable.IPaintableUpdatableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jannes Peters on 2/20/2015.
 */
public abstract class Enemy implements IPaintableUpdatableObject {
    protected BufferedImage texture;
    protected int health;
    protected double moveSpeed; //TODO test move speeds
    protected int goldToEarn;

    @Override
    public void update(long timeDiff) {
        
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void realign(int width, int height, Graphics2D g) {

    }
}
