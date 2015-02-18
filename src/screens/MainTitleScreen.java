package screens;

import game.framework.Util;
import game.menu.MenuButton;
import game.ui.Button;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainTitleScreen extends BaseScreen {

	public enum BUTTON_ACTION {
		START,
		EXIT,
		EDITOR,
		OPTIONS,
		MULTIPLAYER
	}

    private ArrayList<MenuButton> buttons;
    private boolean gotInitialResize = false;

	public MainTitleScreen(String name, int width, int height, Graphics2D g) {
		super(name, width, height, g);
        int menuButtonCenterX = Util.calculateCenterPosition(this.width, MenuButton.WIDTH);
		buttons = new ArrayList<>();
		buttons.add(new MenuButton(menuButtonCenterX, 20, "Play", g, BUTTON_ACTION.START));
		buttons.add(new MenuButton(menuButtonCenterX, 80, "Multiplayer", g, BUTTON_ACTION.MULTIPLAYER));
		buttons.add(new MenuButton(menuButtonCenterX, 160, "Options", g, BUTTON_ACTION.OPTIONS));
		buttons.add(new MenuButton(menuButtonCenterX, 220, "Editor", g, BUTTON_ACTION.EDITOR));
		buttons.add(new MenuButton(menuButtonCenterX, 280, "Exit", g, BUTTON_ACTION.EXIT));
	}

	@Override
	public void update(long timeDiff) {
		for (Button b : buttons) {
			b.update(timeDiff);
		}
	}

	@Override
	public void draw(Graphics2D g) {
        if (!gotInitialResize) {
            for (Button b : buttons) {
                b.realign(width, height, g);
            }
        }
		for (Button b : buttons) {
			b.draw(g);
		}
	}

    @Override
    public void realign(int width, int height, Graphics2D g) {
        super.realign(width, height, g);
        if (buttons == null) return;
        for (Button b : buttons) {
            b.setX(Util.calculateCenterPosition(this.width, MenuButton.WIDTH));
            b.realign(width, height, g);
        }
    }

	@Override
	public void realign(Rectangle rect, Graphics2D g) {
        realign(rect.width, rect.height, g);
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
            super.closeGame();
			break;
		}
	}

    @Override
    public void closeRequested() {
        System.out.println("Close init by: " + this.getClass().toString());
        super.closeGame();
    }

}
