package screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SplashScreen extends BaseScreen{

	private long timeUp = 0;
	
	public SplashScreen(String name, double width, double height, Graphics2D g) {
		super(name, width, height, g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(long timeDiff) {
		timeUp += timeDiff;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) { }

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) { }
}
