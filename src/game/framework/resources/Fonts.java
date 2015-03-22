package game.framework.resources;

import game.framework.screens.menu.ScreenSplashLoad;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class that loads and holds all fonts.
 */
public class Fonts {
    public static final int ELEMENTS = 1;
    public static Font defaultFont = new Font("", Font.PLAIN, 26);
    public static Font statusBarFont = new Font("", Font.PLAIN, 16);
    public static Font fpsFont = new Font("Copperplate Gothic", Font.PLAIN, 26);

    public static Font getDefaultFont() {
        return defaultFont;
    }

    public static void loadFonts() {
        try {
            System.out.println("Loading assets/fonts/roboto.ttf ...");
            Font robotoFont = loadFont("assets/fonts/roboto.ttf");
            defaultFont = robotoFont.deriveFont(Font.PLAIN, 26f);
            statusBarFont = robotoFont.deriveFont(Font.PLAIN, 16f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static Font loadFont(String path) throws IOException, FontFormatException {
        ScreenSplashLoad.elementLoaded();
        return Font.createFont(Font.TRUETYPE_FONT, new File(path));
    }

}
