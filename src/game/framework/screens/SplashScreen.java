package game.framework.screens;

import game.framework.resources.Maps;
import game.framework.resources.Textures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SplashScreen extends BaseScreen{

	private long timeUp = 0;
	
	public SplashScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        Textures.loadImages();
        Maps.loadMaps();
    }

	@Override
	public void update(long timeDiff) {
        timeUp += timeDiff;
        if (timeUp > 1000000000d) {
            System.out.println("Splash ran 3 seconds. Requesting MainTitleScreen!");
            super.requestScreen(new MainTitleScreen("titleScreen", (int) width, (int) height, super.graphics2D));
            super.unLoad();
        }
    }

	@Override
	public void draw(Graphics2D g) {
		g.drawRect(50, 50, super.width - 100, 100);
        g.fillRect(55, 55, (int)((float)(timeUp / 1000000000d) * (float)(super.width - 110)), 90);
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
}
