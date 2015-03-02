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
        super.fireRatePerLevel = new double[]{0, 0, 0, 0, 0};
        super.damagePerLevel = new int[]{0, 0, 0, 0, 0};
        super.rangePerLevel = new int[]{0, 0, 0, 0, 0};
        super.critRate = 0.0f;
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
