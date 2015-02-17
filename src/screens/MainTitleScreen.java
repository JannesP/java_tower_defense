package screens;

import game.framework.Textures;
import game.menu.Button;
import game.menu.MenuButton;
import game.object.tile.window.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainTitleScreen extends BaseScreen {

	public enum BUTTON_ACTION {
		START,
		EXIT,
		EDITOR,
		OPTIONS,
		MULTIPLAYER
	}
	
	ArrayList<MenuButton> buttons;
	private Window win;

	public MainTitleScreen(String name, int width, int height, Graphics2D g, Window win) {
		super(name, width, height, g);
		BufferedImage img = Textures.button_main_menu;
		this.win = win;
		buttons = new ArrayList<>();
		buttons.add(new MenuButton(20, 20, 200, 50, img, "Play", g, BUTTON_ACTION.START));
		buttons.add(new MenuButton(20, 80, 200, 50, img, "Multiplayer", g, BUTTON_ACTION.MULTIPLAYER));
		buttons.add(new MenuButton(20, 160, 200, 50, img, "Options", g, BUTTON_ACTION.OPTIONS));
		buttons.add(new MenuButton(20, 220, 200, 50, img, "Editor", g, BUTTON_ACTION.EDITOR));
		buttons.add(new MenuButton(20, 280, 200, 50, img, "Exit", g, BUTTON_ACTION.EXIT));
	}

	@Override
	public void update(long timeDiff) {
		for (Button b : buttons) {
			b.update(timeDiff);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (Button b : buttons) {
			b.draw(g);
		}
	}

	@Override
	public void realign(Rectangle rect, Graphics2D g) {
		super.realign(rect, g);
		for (Button b : buttons) {
			b.setX(this.width / 2 - b.getRect().width / 2);
			b.realign(rect.width, rect.height, g);
		}
	}

	@Override
	public void handleKeyInput(ArrayList<KeyEvent> events) {
		for (KeyEvent e : events) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				
				switch (e.getKeyCode()) {
				
				case KeyEvent.VK_DOWN:	//Arrow down
					for (int i = 0; i < buttons.size(); i++) {
						if (buttons.get(i).getState() == Button.STATE.HOVER) {
							for (Button b : buttons) {
								b.setState(Button.STATE.NORMAL);
							}
							buttons.get((i + 1) % buttons.size()).setState(Button.STATE.HOVER);
							break;
						} else {
							if (i == buttons.size() - 1) {
								buttons.get(0).setState(Button.STATE.HOVER);
							}
						}
					}
					break;
					
				case KeyEvent.VK_UP:	//Arrow up
					for (int i = 0; i < buttons.size(); i++) {
						if (buttons.get(i).getState() == Button.STATE.HOVER) {
							for (Button b : buttons) {
								b.setState(Button.STATE.NORMAL);
							}
							i = i == 0 ? buttons.size() - 1 : --i; 
							buttons.get(i).setState(Button.STATE.HOVER);
							break;
						} else {
							if (i == buttons.size() - 1) {
								buttons.get(0).setState(Button.STATE.HOVER);
							}
						}
					}
					break;
					
				case KeyEvent.VK_ENTER:	//Enter
					for (MenuButton b : buttons) {
						if (b.getState() == Button.STATE.HOVER) {
							performAction(b.getAction());
							break;
						}
					}
					break;
				}
			}
		}

	}

	@Override
	public void handleMouseInput(ArrayList<MouseEvent> events) {
		for (MouseEvent e : events) {
			if (e.getID() == MouseEvent.MOUSE_DRAGGED || e.getID() == MouseEvent.MOUSE_MOVED) {
				for (MenuButton b : buttons) {
					if (b.getState() == Button.STATE.PRESSED) {
						continue;
					}
					if (b.getRect().contains(e.getPoint())) {
						b.setState(Button.STATE.HOVER);
					}
					else b.setState(Button.STATE.NORMAL);
				}
			} else if (e.getID() == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseEvent.BUTTON1) {
				for (MenuButton b : buttons) {
					if (b.getRect().contains(e.getPoint())) b.setState(Button.STATE.PRESSED);
					else b.setState(Button.STATE.NORMAL);
				}
			} else if (e.getID() == MouseEvent.MOUSE_RELEASED && e.getButton() == MouseEvent.BUTTON1) {
				for (MenuButton b : buttons) {
					if (b.getState() == Button.STATE.PRESSED && b.getRect().contains(e.getPoint())) {
						performAction(b.getAction());
						b.setState(Button.STATE.HOVER);
						break;
					}
					if (b.getRect().contains(e.getPoint())) b.setState(Button.STATE.HOVER);
					else b.setState(Button.STATE.NORMAL);
				}
			}
		}
	}

	private void performAction(BUTTON_ACTION action) {
		switch (action) {
		case START:
			System.out.println(action);
			break;
		case MULTIPLAYER:
			System.out.println(action);
			break;
		case EDITOR:
			System.out.println(action);
			break;
			
		case OPTIONS:
			System.out.println(action);
			break;
			
		case EXIT:
			win.close();
			break;
		}
	}

}
