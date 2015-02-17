package game.menu;

import screens.MainTitleScreen.BUTTON_ACTION;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton extends Button {

	BUTTON_ACTION action;

	public MenuButton(int x, int y, int width, int height, BufferedImage image,
			String text, Graphics2D g, BUTTON_ACTION action) {
		super(x, y, width, height, image, text, g);
		this.action = action;
	}
	
	public BUTTON_ACTION getAction() {
		return action;
	}
}
