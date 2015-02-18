package game.menu;

import game.framework.Textures;
import screens.MainTitleScreen.BUTTON_ACTION;

import java.awt.*;

public class MenuButton extends game.ui.Button {

	BUTTON_ACTION action;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 50;

	public MenuButton(int x, int y, String text, Graphics2D g, BUTTON_ACTION action) {
		super(x, y, WIDTH, HEIGHT, Textures.button_main_menu, text, g);
		this.action = action;
	}
	
	public BUTTON_ACTION getAction() {
		return action;
	}
}
