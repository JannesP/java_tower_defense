package game.framework;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {
	public static BufferedImage button_main_menu;

    //TODO outsource to new thread
    /**
     * loads all images in a new thread
     */
	public static void loadImages() {
		try {
			button_main_menu = ImageIO.read(new File("assets/img/buttons/menu.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
}
