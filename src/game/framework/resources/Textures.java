package game.framework.resources;

import game.framework.Util;
import game.framework.screens.menu.SplashLoadScreen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class that loads and holds all textures.
 */
public class Textures {
    public static final int ELEMENTS = 12;

    public static BufferedImage placeholder;

    //Icons
    public static BufferedImage iconSpeaker;
    public static BufferedImage dropDownArrow;

    //UI Elements
	public static BufferedImage buttonBackground;
    public static BufferedImage disabledButtonBackground;

    //Towers
    public static BufferedImage castleTexture;
    public static BufferedImage lightTowerTexture;
    public static BufferedImage heavyTowerTexture;

    public static BufferedImage greyCastleTexture;
    public static BufferedImage greyLightTowerTexture;
    public static BufferedImage greyHeavyTowerTexture;

    //Backgrounds
    public static BufferedImage[] backgrounds = new BufferedImage[Maps.LEVEL_COUNT];

    //Effects

    //Enemies
    public static BufferedImage runnerTexture;
    public static BufferedImage heavyTexture;

    /**
     * loads all images in a new thread
     */
	public static void loadImages() {

        try {
            placeholder = loadImage("assets/img/placeholder.png");

            //Icons
            iconSpeaker = loadImage("assets/img/icons/speaker.png");

            //Buttons
            buttonBackground = loadImage("assets/img/buttons/menu.png");
            disabledButtonBackground = createGreyCopy(buttonBackground, 0, 0, buttonBackground.getWidth(), buttonBackground.getHeight() / 3);

            //Towers
            castleTexture = loadImage("assets/img/tower/castle.png");
            lightTowerTexture = loadImage("assets/img/tower/light_tower.png");
            heavyTowerTexture = loadImage("assets/img/tower/heavy_tower.png");

            greyCastleTexture = createGreyCopy(castleTexture, 0, 0, castleTexture.getHeight(), castleTexture.getHeight());
            greyLightTowerTexture = createGreyCopy(lightTowerTexture, 0, 0, lightTowerTexture.getHeight(), lightTowerTexture.getHeight());
            greyHeavyTowerTexture = createGreyCopy(heavyTowerTexture, 0, 0, heavyTowerTexture.getHeight(), heavyTowerTexture.getHeight());

            //Enemies
            runnerTexture = loadImage("assets/img/enemy/runner.png");

            //Backgrounds
            backgrounds[0] = loadImage("assets/maps/001.png");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
	}

    private static BufferedImage loadImage(String path) throws IOException {
        System.out.println("Loading " + path + " ...");
        SplashLoadScreen.elementLoaded();
        return ImageIO.read(new File(path));
    }

    private static BufferedImage createGreyCopy(BufferedImage texture, int srcX, int srcY, int srcX2, int srcY2) {
        BufferedImage greyCopy = Util.createGreyScaledImage(texture, srcX, srcY, srcX2, srcY2);
        SplashLoadScreen.elementLoaded();
        return greyCopy;
    }


}
