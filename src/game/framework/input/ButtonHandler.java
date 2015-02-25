package game.framework.input;

import game.drawable.IPaintableUpdatableObject;
import game.ui.button.Button;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 2/25/2015.
 */
public class ButtonHandler implements IPaintableUpdatableObject{
    private IButtonActionReceiver receiver;
    private ArrayList<Button> buttons;

    public ButtonHandler(ArrayList<Button> buttons, IButtonActionReceiver receiver) {
        this.buttons = buttons;
        this.receiver = receiver;
    }




    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void handleMouseInput(ArrayList<MouseEvent> events) {
        for (MouseEvent e : events) {
            if (e.getID() == MouseEvent.MOUSE_DRAGGED || e.getID() == MouseEvent.MOUSE_MOVED) {
                for (Button b : buttons) {
                    if (b.getState() == Button.STATE.PRESSED) {
                        continue;
                    }
                    if (b.getRect().contains(e.getPoint())) {
                        b.setState(Button.STATE.HOVER);
                    }
                    else b.setState(Button.STATE.NORMAL);
                }
            } else if (e.getID() == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseEvent.BUTTON1) {
                for (Button b : buttons) {
                    if (b.getRect().contains(e.getPoint())) b.setState(Button.STATE.PRESSED);
                    else b.setState(Button.STATE.NORMAL);
                }
            } else if (e.getID() == MouseEvent.MOUSE_RELEASED && e.getButton() == MouseEvent.BUTTON1) {
                for (Button b : buttons) {
                    if (b.getState() == Button.STATE.PRESSED && b.getRect().contains(e.getPoint())) {
                        receiver.performButtonAction(b.getAction());    //send action to listener
                        b.setState(Button.STATE.HOVER);
                        break;
                    }
                    if (b.getRect().contains(e.getPoint())) b.setState(Button.STATE.HOVER);
                    else b.setState(Button.STATE.NORMAL);
                }
            }
        }
    }

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
                        for (Button b : buttons) {
                            if (b.getState() == Button.STATE.HOVER) {
                                receiver.performButtonAction(b.getAction());
                                break;
                            }
                        }
                        break;
                }
            }
        }

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
    public void realign(int width, int height, Graphics2D g) {
        for (Button b : buttons) {
            b.realign(width, height, g);
        }
    }
}
