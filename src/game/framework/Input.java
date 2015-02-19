package game.framework;

import screens.ScreenManager;

import java.awt.event.*;

public class Input implements KeyListener, WindowListener, MouseListener, MouseMotionListener, MouseWheelListener{

    private ScreenManager sm;

    /**
     * Initiates a new Instance for receiving several input events.
     * @param sm - ScreenManager to report the input to.
     */
	public Input(ScreenManager sm) {
        this.sm = sm;
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		sm.gotEvent(e);
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		sm.gotEvent(e);
		e.consume();
	}
	
	@Override
	public void windowActivated(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void windowOpened(WindowEvent e) { }

	@Override
	public void windowClosing(WindowEvent e) {
        System.out.println("Window Closing Event!");
        sm.closeRequested();
    }

	@Override
	public void windowClosed(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) {
		sm.gotEvent(e);
		e.consume();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		sm.gotEvent(e);
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sm.gotEvent(e);
		e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mouseDragged(MouseEvent e) { 
		sm.gotEvent(e);
		e.consume(); 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		sm.gotEvent(e);
		e.consume();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		sm.gotEvent(e);
		e.consume();
	}

	
}