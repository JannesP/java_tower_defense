package game.framework.screens;

import game.framework.resources.Fonts;
import game.framework.resources.Maps;
import game.framework.resources.Sounds;
import game.framework.resources.Textures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SplashScreen extends BaseScreen{

    private static volatile int percentLoaded = 0;
	
	public SplashScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        Thread thread = new Thread(() -> {
            long startLoading = System.currentTimeMillis();
            SplashScreen.getSetPercentLoaded(0);
            Textures.loadImages();
            SplashScreen.getSetPercentLoaded(25);
            Maps.loadMaps();
            SplashScreen.getSetPercentLoaded(50);
            Fonts.loadFonts();
            SplashScreen.getSetPercentLoaded(75);
            Sounds.loadSounds();
            SplashScreen.getSetPercentLoaded(100);
            System.out.println("Loading took: " + (System.currentTimeMillis() - startLoading));
        });
        thread.start();
    }

	@Override
	public void update(long timeDiff) {
        if (SplashScreen.getSetPercentLoaded(-1) == 100) {
            System.out.println("Finished loading. Requesting MainTitleScreen!");
            super.requestScreen(new MainTitleScreen("titleScreen", width, height, super.graphics2D));
            super.unLoad();
        }
    }

	@Override
	public void draw(Graphics2D g) {
		g.drawRect(50, 50, super.width - 100, 100);
        g.fillRect(55, 55, (int)((SplashScreen.getSetPercentLoaded(-1) / 100d) * (double)(super.width - 110)), 90);
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

    /**
     * Synchronized function, don't call on UI Thread!
     * @param percentLoaded - if -1 nothing will be changed
     * @return The percent loaded.
     */
    public static synchronized int getSetPercentLoaded(int percentLoaded) {
        if (percentLoaded != -1) {
            System.out.println("Loaded: " + percentLoaded + "%");
            SplashScreen.percentLoaded = percentLoaded;
        }
        return SplashScreen.percentLoaded;
    }
}
