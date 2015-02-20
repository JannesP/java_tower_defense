package game.framework;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {
    private static volatile int percentLoaded = 0;

    //UI Elements
	public static BufferedImage button_main_menu;

    //Towers
    public static BufferedImage[] arrowTowerTextures;

    //Effects

    /**
     * loads all images in a new thread
     */
	public static void loadImages() {
        getSetPercentLoaded(0);
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Loading assets/img/buttons/menu.png ...");
                button_main_menu = ImageIO.read(new File("assets/img/buttons/menu.png"));
                getSetPercentLoaded(100);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setName("imageLoaderThread");
        thread.start();
	}

    /**
     * Synchronized function, don't call on UI Thread!
     * @param percentLoaded - if -1 nothing will be changed
     * @return The percent loaded.
     */
    public static synchronized int getSetPercentLoaded(int percentLoaded) {
        if (percentLoaded != -1) {
            System.out.println("Loaded: " + percentLoaded + "%");
            Textures.percentLoaded = percentLoaded;
        }
        return Textures.percentLoaded;
    }
}
