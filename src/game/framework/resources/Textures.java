package game.framework.resources;

import game.framework.Util;
import game.framework.screens.SplashLoadScreen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class that loads and holds all textures.
 */
public class Textures {
    public static final int ELEMENTS = 9;

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
            disabledButtonBackground = Util.createGreyScaledImage(buttonBackground, 0, 0, buttonBackground.getWidth(), buttonBackground.getHeight() / 3);
            SplashLoadScreen.elementLoaded();

            //Towers
            castleTexture = loadImage("assets/img/tower/castle.png");
            lightTowerTexture = loadImage("assets/img/tower/light_tower.png");
            heavyTowerTexture = loadImage("assets/img/tower/heavy_tower.png");

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


}
