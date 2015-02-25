package game.ui.button;

import game.framework.resources.Textures;

import java.awt.*;

public class MenuButton extends TextButton {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 50;

	public MenuButton(int x, int y, String text, Graphics2D g, int action) {
		super(x, y, WIDTH, HEIGHT, Textures.button_main_menu, text, g, action);
	}
}
