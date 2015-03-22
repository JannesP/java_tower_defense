package game.object.tower;

import game.framework.math.Vector2d;
import game.framework.resources.Textures;

import java.awt.image.BufferedImage;

/**
 * Created by Jannes Peters on 3/14/2015.
 */
public class TowerTypes {

    public static Tower getNewTowerById(int towerId, int ownerId, Vector2d center) {
        switch (towerId) {
            case Tower.TOWER_CASTLE:
                return new TowerCastle(ownerId, center);
            case Tower.TOWER_LIGHT:
                return new TowerLight(ownerId, center);
            case Tower.TOWER_HEAVY:
                return new TowerHeavy(ownerId, center);
            default:
                System.out.println("Tower with id: " + towerId + " is missing!");
                System.exit(-1);
        }
        return null;
    }

    public static BufferedImage getGreyTextureById(int towerId) {
        switch (towerId) {
            case Tower.TOWER_CASTLE:
                return Textures.greyCastleTexture;
            case Tower.TOWER_LIGHT:
                return Textures.greyLightTowerTexture;
            case Tower.TOWER_HEAVY:
                return Textures.greyHeavyTowerTexture;
            default:
                System.out.println("Texture for id: " + towerId + " is missing!");
                System.exit(-1);
        }
        return Textures.placeholder;
    }

    public static BufferedImage getTextureById(int towerId) {
        switch (towerId) {
            case Tower.TOWER_CASTLE:
                return Textures.castleTexture;
            case Tower.TOWER_LIGHT:
                return Textures.lightTowerTexture;
            case Tower.TOWER_HEAVY:
                return Textures.heavyTowerTexture;
            default:
                System.out.println("Texture for id: " + towerId + " is missing!");
                System.exit(-1);
        }
        return Textures.placeholder;
    }

    public static int getRange(int towerId, int level) {
        switch (towerId) {
            case Tower.TOWER_CASTLE:
                return TowerCastle.getRange(level);
            case Tower.TOWER_LIGHT:
                return TowerLight.getRange(level);
            case Tower.TOWER_HEAVY:
                return TowerHeavy.getRange(level);
            default:
                System.out.println("Texture for id: " + towerId + " is missing!");
                System.exit(-1);
        }
        return -1;
    }

}
