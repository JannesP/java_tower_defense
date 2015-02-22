package game.framework.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {
    private static volatile int percentLoaded = 0;

    //UI Elements
	public static BufferedImage button_main_menu;

    //Towers
    public static BufferedImage castleTexture;

    //Backgrounds
    public static BufferedImage[] backgrounds = new BufferedImage[Maps.LEVEL_COUNT];

    //Effects

    /**
     * loads all images in a new thread
     */
	public static void loadImages() {
        getSetPercentLoaded(0);
        Thread thread = new Thread(() -> {
            try {
                //Buttons
                button_main_menu = loadImage("assets/img/buttons/menu.png");

                //Backgrounds
                backgrounds[0] = loadImage("assets/maps/001.png");

                //Towers
                castleTexture = loadImage("assets/img/tower/castle.png");


                getSetPercentLoaded(100);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
        thread.setName("imageLoaderThread");
        thread.start();
	}

    private static BufferedImage loadImage(String path) throws IOException {
        System.out.println("Loading " + path + " ...");
        return ImageIO.read(new File(path));
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
