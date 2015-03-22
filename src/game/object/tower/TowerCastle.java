package game.object.tower;

import game.framework.resources.Textures;
import game.object.tile.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Castle which is basically a tower which renders over 4 Tiles.
 * Created by Jannes Peters on 2/21/2015.
 */
public class TowerCastle extends Tower {
    private static int castlesCreated = 0;
    private boolean isFirstCastle = false;

    public TowerCastle(int ownerId) {
        super(ownerId);
        if (TowerCastle.castlesCreated++ == 0) {
            isFirstCastle = true;
        } else if (TowerCastle.castlesCreated == 4) {
            TowerCastle.castlesCreated = 0;
        }
        super.texture = Textures.castleTexture;
        super.costPerLevel = new int[]{0, 100, 250, 500, 1000};
        super.fireRatePerLevel = new double[]{0, 0, 0, 0, 0};
        super.damagePerLevel = new int[]{0, 0, 0, 0, 0};
        super.rangePerLevel = new int[]{0, 0, 0, 0, 0};
        super.critRate = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    @Override
    public void draw(Graphics2D g) {
        if (isFirstCastle) {
            g.drawImage(texture, x, y, (int) (x + TileMap.tileSize * 2), (int) (y + TileMap.tileSize * 2), texture.getHeight() * level, 0, texture.getHeight() * level + texture.getHeight(), texture.getHeight(), null);
        }
    }

    @Override
    public int getId() {
        return Tower.TOWER_CASTLE;
    }

    @Override
    protected void fire() {
    }

    @Override
    public BufferedImage getTexture() {
        return null;
    }
}
