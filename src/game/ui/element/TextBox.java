package game.ui.element;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * TextBox to write some text in.
 * Created by Jannes Peters on 2/27/2015.
 */
public class TextBox extends UIElement {
    private static final Color COLOR_BACKGROUND = new Color(0f, 0f, 0f, 0.6f);
    private static final long CURSOR_BLINK_INTERVAL = (long)(Util.NANO_SECOND_SECOND * 0.42d);

    private String hint, text = "";
    private boolean isCursorVisible = false;
    private long timeSinceLastCursorBlinkChange = 0;
    private Font font;
    private int inputLength;

    /**
     * An array which contains all valid chars that can be written.
     */
    private static final char[] VALID_CHARS = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Initiates the element with the given parameters.
     *
     * @param x              - calculated X coordinate on the screen.
     * @param y              - calculated Y coordinate on the screen.
     * @param width          - width of the element.
     * @param action         - action ID
     * @param actionReceiver - actionReceiver for events
     * @param hint - hint that's displayed when you typed nothing
     */
    @SuppressWarnings("all")
    public TextBox(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, String hint) {
        this(x, y, width, height, action, actionReceiver, hint, 20);
    }

    /**
     * Initiates the element with the given parameters.
     *
     * @param x              - calculated X coordinate on the screen.
     * @param y              - calculated Y coordinate on the screen.
     * @param width          - width of the element.
     * @param action         - action ID
     * @param actionReceiver - actionReceiver for events
     * @param hint - hint that's displayed when you typed nothing
     */
    public TextBox(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, String hint, int inputLength) {
        super(x, y, width, height, action, actionReceiver);
        if (hint == null) hint = "";
        this.hint = hint;
        this.inputLength = inputLength;
        this.font = Fonts.getDefaultFont().deriveFont((float)(height - Util.PADDING * 2));
    }

    private boolean isCharValid(char c) {
        for (char validChar : VALID_CHARS) {
            if (c == validChar) return true;
        }
        return false;
    }

    /**
     * Gets the current written text.
     * @return the text
     */
    public String getText() {
        return this.text;
    }

    @Override
    protected void handleKeyEvent(KeyEvent event) {
        event.consume();
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            String oldText = this.text;
            switch (event.getKeyCode()) {
                case KeyEvent.VK_BACK_SPACE:
                case KeyEvent.VK_DELETE:
                    if (this.text.length() > 0) {
                        this.text = this.text.substring(0, this.text.length() - 1); //remove last character
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    super.hasFocus = false;
                    break;
                default:
                    if (text.length() != this.inputLength) {
                        if (this.isCharValid(event.getKeyChar())) this.text += event.getKeyChar();
                    }
            }
            if (!oldText.equals(text)) {
                super.actionReceiver.performAction(this, super.action);
            }
        }
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        if (!this.hasFocus) {
            this.timeSinceLastCursorBlinkChange = 0;
            this.isCursorVisible = true;
            return;
        }
        this.timeSinceLastCursorBlinkChange += timeScale;
        if (this.timeSinceLastCursorBlinkChange >= TextBox.CURSOR_BLINK_INTERVAL) {
            this.isCursorVisible = !this.isCursorVisible;
            this.timeSinceLastCursorBlinkChange = 0;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        //draw background
        g.setColor(TextBox.COLOR_BACKGROUND);
        g.fillRect(super.x, super.y, super.width, super.height);

        //draw border
        g.setColor(super.getBorderColor());
        g.drawRect(super.x, super.y, super.width, super.height);
        g.drawRect(super.x + 1, super.y + 1, super.width - 2, super.height - 2);

        //draw text
        g.setColor(Color.WHITE);
        g.setFont(this.font);
        if (text.equals("") && !super.hasFocus) {
            g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(), g.getColor().getBlue(), 123));
            g.drawString(hint, super.x + 2 + Util.PADDING, super.y + Util.calculateCenterPosition(super.height, Util.getFontHeight(g)) + Util.getFontHeight(g));
        } else {
            g.drawString(text, super.x + 2 + Util.PADDING, super.y + Util.calculateCenterPosition(super.height, Util.getFontHeight(g)) + Util.getFontHeight(g));
        }

        //draw cursor
        if (this.isCursorVisible && this.hasFocus) {
            g.fillRect((int)(super.x + 2 + Util.PADDING + g.getFontMetrics().getStringBounds(this.text, g).getWidth()), super.y + Util.calculateCenterPosition(super.height, this.font.getSize()), 2, this.font.getSize());
        }
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {}
}
