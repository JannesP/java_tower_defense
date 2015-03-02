package game.framework.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {

    //Icons
    public static BufferedImage iconSpeaker;

    //UI Elements
	public static BufferedImage button_main_menu;

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
            //Icons
            iconSpeaker = loadImage("assets/img/icons/speaker.png");

            //Buttons
            button_main_menu = loadImage("assets/img/buttons/menu.png");
                //Towers
                castleTexture = loadImage("assets/img/tower/castle.png");
                lightTowerTexture = loadImage("assets/img/tower/light_tower.png");
                heavyTowerTexture = loadImage("assets/img/tower/heavy_tower.png");

            //Towers
            castleTexture = loadImage("assets/img/tower/castle.png");
            arrowTowerTexture = loadImage("assets/img/tower/arrow_tower.png");
                //Enemies
                runnerTexture = loadImage("assets/img/enemy/runner.png");
                heavyTexture = loadImage("assets/img/enemy/heavy.png");

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
