package game.framework.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class that loads and holds all textures.
 */
public class Textures {

    //Icons
    public static BufferedImage iconSpeaker;
    public static BufferedImage dropDownArrow;

    //UI Elements
	public static BufferedImage button_main_menu;

    //Towers
    public static BufferedImage castleTexture;
    public static BufferedImage arrowTowerTexture;

    //Backgrounds
    public static BufferedImage[] backgrounds = new BufferedImage[Maps.LEVEL_COUNT];

    //Effects

    //Enemies
    public static BufferedImage runnerTexture;
    /**
     * loads all images in a new thread
     */
	public static void loadImages() {

        try {
            //Icons
            iconSpeaker = loadImage("assets/img/icons/speaker.png");

            //Buttons
            button_main_menu = loadImage("assets/img/buttons/menu.png");

            //Towers
            castleTexture = loadImage("assets/img/tower/castle.png");
            arrowTowerTexture = loadImage("assets/img/tower/arrow_tower.png");

            //Enemies
            runnerTexture = loadImage("assets/img/enemy/runner.png");
            //giantTexture = loadImage("assets/img/enemy/giant.png");

            //Backgrounds
            backgrounds[0] = loadImage("assets/maps/001.png");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
	}

    private static BufferedImage loadImage(String path) throws IOException {
        System.out.println("Loading " + path + " ...");
        return ImageIO.read(new File(path));
    }


}
