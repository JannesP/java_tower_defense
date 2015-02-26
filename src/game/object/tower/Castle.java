package game.object.tower;

import game.framework.resources.Textures;
import game.object.tile.TileMap;

import java.awt.*;

/**
 * Castle which is basically a tower which renders over 4 Tiles.
 * Created by Jannes Peters on 2/21/2015.
 */
public class Castle extends Tower {
    private static boolean castleCreated = false;
    private boolean isFirstCastle = false;

    public Castle() {
        if (!Castle.castleCreated) {
            castleCreated = true;
            isFirstCastle = true;
        }
        super.texture = Textures.castleTexture;
        super.costPerLevel = new int[]{0, 100, 250, 500, 1000};
        super.fireRatePerLevel = new double[]{0.5, 0.6, 0.7, 0.8, 1};
        super.damagePerLevel = new int[]{20, 40, 80, 100, 200};
        super.rangePerLevel = new int[]{100, 110, 130, 140, 180};
    }

    @Override
    public void draw(Graphics2D g) {
        if (isFirstCastle) {
            g.drawImage(texture, x, y, (int) (x + TileMap.tileSize * 2), (int) (y + TileMap.tileSize * 2), texture.getHeight() * level, 0, texture.getHeight() * level + texture.getHeight(), texture.getHeight(), null);
        }
    }

    @Override
    protected void fire() {
    }
}
