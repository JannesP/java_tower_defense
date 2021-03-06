package game.framework.input;

import game.framework.Manager;
import game.framework.screens.ScreenManager;

import java.awt.event.*;

/**
 * Input listener which receives all key and mouse events for further distribution.
 */
public class Input implements KeyListener, WindowListener, MouseListener, MouseMotionListener, MouseWheelListener{

    private ScreenManager screenManager;

    /**
     * Initiates a new Instance for receiving several input events.
     * @param screenManager - ScreenManager to report the input to.
     */
	public Input(ScreenManager screenManager) {
        this.screenManager = screenManager;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		screenManager.gotEvent(e);
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		screenManager.gotEvent(e);
		e.consume();
	}

    @Override
    public void keyTyped(KeyEvent e) { }

	@Override
	public void windowClosing(WindowEvent e) {
        System.out.println("Window Closing Event!");
        if (Manager.hasGameCrashed()) {
            System.out.println("Force closing game because of exception.");
            Manager.closeRequested();
        } else {
            screenManager.closeRequested();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) { }
    @Override
    public void windowActivated(WindowEvent e) { }
    @Override
    public void windowDeactivated(WindowEvent e) { }
	@Override
	public void windowClosed(WindowEvent e) { }
	@Override
	public void windowIconified(WindowEvent e) { }
	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) {
		screenManager.gotEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		screenManager.gotEvent(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		screenManager.gotEvent(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) { 
		screenManager.gotEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        screenManager.gotEvent(e);
    }

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		screenManager.gotEvent(e);
		e.consume();
	}

    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
