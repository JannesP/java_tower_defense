package game.framework.screens.menu;

import game.framework.resources.Fonts;
import game.framework.resources.Maps;
import game.framework.resources.Sounds;
import game.framework.resources.Textures;
import game.framework.screens.ScreenBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ScreenSplashLoad extends ScreenBase {

    private static volatile int percentLoaded = 0;
	
	public ScreenSplashLoad(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        Thread thread = new Thread(() -> {
            long startLoading = System.currentTimeMillis();
            Textures.loadImages();
            Maps.loadMaps();
            Fonts.loadFonts();
            Sounds.loadSounds();
            System.out.println("Loading took: " + (System.currentTimeMillis() - startLoading) + "ms");
        });
        thread.setName("loadingThread");
        thread.start();
    }

	@Override
	public void update(double timeScale, long timeDiff) {
        if (ScreenSplashLoad.getSetPercentLoaded(-1) == 100) {
            System.out.println("Finished loading. Requesting MainTitleScreen!");
            super.requestScreen(new ScreenMainTitle("titleScreen", width, height, super.graphics2D));
            super.unload();
        }
    }

	@Override
	public void draw(Graphics2D g) {
		g.drawRect(18, super.height - 36, super.width - 36, 20);
        g.fillRect(21, super.height - 33, (int)((ScreenSplashLoad.getSetPercentLoaded(-1) / 100d) * (double)(super.width - 34)), 15);
	}

    @Override
    public void closeRequested() {
        System.out.println("Close init by: " + this.getClass().toString());
        super.closeGame();
    }

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) { }

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) { }
    private static double elementsLoaded = 0;
    /**
     * Synchronized function, don't call on UI Thread!
     * @param percentLoaded - if -1 nothing will be changed
     * @return The percent loaded.
     */
    public static synchronized int getSetPercentLoaded(int percentLoaded) {
        if (percentLoaded != -1) {
            ScreenSplashLoad.percentLoaded = percentLoaded;
        }
        return ScreenSplashLoad.percentLoaded;
    }
    public static void elementLoaded(){
        getSetPercentLoaded((int) (++elementsLoaded / (double) (Fonts.ELEMENTS + Maps.ELEMENTS + Sounds.ELEMENTS + Textures.ELEMENTS)* 100d));
    }
}
