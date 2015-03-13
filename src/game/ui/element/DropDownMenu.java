package game.ui.element;

import game.framework.Util;
import game.framework.input.IUIActionReceiver;
import game.framework.resources.Fonts;
import game.framework.resources.Textures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Basic DropDown menu from which you can select data from.
 * Created by Jannes Peters on 03.03.2015.
 */
public class DropDownMenu extends UIElement {
    private static final Color COLOR_BACKGROUND = new Color(0f, 0f, 0f, 0.6f);

    public String[] entries;
    private int selectedIndex = 0;
    private int mouseOverIndex = -1;
    private boolean extended = false;
    private int originalWidth, originalHeight;
    private static final int BORDER_SIZE = 2;
    private Font font;

    /**
     * Initiates the element with the given parameters.
     *
     * @param x              - calculated X coordinate on the screen.
     * @param y              - calculated Y coordinate on the screen.
     * @param width          - width of the element.
     * @param height         - height of the element.
     * @param action         - action ID
     * @param actionReceiver - receiver that is called on event
     */
    public DropDownMenu(int x, int y, int width, int height, int action, IUIActionReceiver actionReceiver, String[] entries) {
        super(x, y, width, height, action, actionReceiver);
        this.entries = entries;
        this.originalHeight = super.height;
        this.originalWidth = super.width;
        this.font = Fonts.getDefaultFont().deriveFont((float)(height - Util.PADDING * 2));
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public String getSelectedElement() {
        return this.entries[this.selectedIndex];
    }

    private void resetBounds() {
        this.width = originalWidth;
        this.height = originalHeight;
    }

    private void shrink() {
        resetBounds();
        this.extended = false;
    }

    private void extend() {
        super.width = this.originalWidth;
        super.height = this.originalHeight + (entries.length * (super.height - DropDownMenu.BORDER_SIZE * 2));
        this.extended = true;
    }

    private void drawExtendedBorder(Graphics2D g) {
        g.setColor(UIElement.COLOR_ACTIVE);
        g.drawLine(x, y, x + originalWidth - 1, y); //top outer
        g.drawLine(x, y + 1, x + originalWidth - 2, y + 1); //top inner

        g.drawLine(x + originalWidth, y, x + originalWidth, y + originalHeight); //right outer
        g.drawLine(x + originalWidth - 1, y, x + originalWidth - 1, y + originalHeight); //right inner

        g.drawLine(x, y + originalHeight, x, y + 1); //left outer
        g.drawLine(x + 1, y + originalHeight, x + 1, y + 1); //left inner
    }

    @Override
    protected void clicked(MouseEvent event) {
        event.consume();
        if (this.extended) {
            int baseHeight = this.originalHeight;
            int itemHeight = this.originalHeight - DropDownMenu.BORDER_SIZE * 2;
            int relativeMouseY = event.getY() - super.y - baseHeight;
            if (relativeMouseY > 0) {
                this.selectedIndex = relativeMouseY / itemHeight;
                super.actionReceiver.performAction(this, super.getAction());
            }
            super.hasFocus = false;
            shrink();
        } else {
            extend();
        }

    }

    @Override
    protected void mouseLeft() {
        this.mouseOverIndex = -1;
    }

    @Override
    protected void handleMouseEvent(MouseEvent event) {
        if (this.isMouseOver) {
            event.consume();
            if (event.getID() == MouseEvent.MOUSE_MOVED || event.getID() == MouseEvent.MOUSE_DRAGGED) {
                if (this.extended) {
                    int baseHeight = this.originalHeight;
                    int itemHeight = this.originalHeight - DropDownMenu.BORDER_SIZE * 2;
                    int relativeMouseY = event.getY() - super.y - baseHeight;
                    if (relativeMouseY > 0) {
                        this.mouseOverIndex = relativeMouseY / itemHeight;
                    }
                }
            }
        }
    }

    @Override
    public void drawOverlay(Graphics2D g) {
        g.setFont(this.font);
        if (this.extended) {
            //background
            g.setColor(COLOR_BACKGROUND);
            g.fillRect(super.x + 2, super.y + 2, super.width - 4, super.height - 4);

            //selector
            g.drawImage(Textures.buttonBackground, super.x + super.width - DropDownMenu.BORDER_SIZE - (this.originalHeight - 2 * DropDownMenu.BORDER_SIZE), this.y + DropDownMenu.BORDER_SIZE, this.originalHeight - 2 * DropDownMenu.BORDER_SIZE + (super.x + super.width - DropDownMenu.BORDER_SIZE - (this.originalHeight - 2 * DropDownMenu.BORDER_SIZE)), this.y + DropDownMenu.BORDER_SIZE + (this.originalHeight - 2 * DropDownMenu.BORDER_SIZE), 0, 0, Textures.buttonBackground.getWidth(), Textures.buttonBackground.getHeight(), null);

            //entries
            int entryHeight = this.originalHeight - DropDownMenu.BORDER_SIZE * 2;
            int posX = super.x + Util.PADDING + DropDownMenu.BORDER_SIZE;
            for (int i = 0; i < entries.length; i++) {
                String entry = entries[i];
                int posY = super.y + Util.PADDING + (i + 1) * entryHeight;
                if (this.mouseOverIndex == i) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(UIElement.COLOR_FONT);
                }
                g.drawString(entry, posX, posY + Util.calculateCenterPosition(entryHeight, Util.getFontHeight(g)) + Util.getFontHeight(g));
                g.setColor(UIElement.COLOR_NORMAL);
                g.drawLine(super.x, posY, super.x + this.originalWidth, posY - DropDownMenu.BORDER_SIZE);
            }
            g.drawRect(super.x, super.y + this.originalHeight, super.width, super.height - this.originalHeight);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(this.font);

        if (this.extended) {
            drawExtendedBorder(g);
            g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(), g.getColor().getBlue(), 123));
        } else {
            //background
            g.setColor(COLOR_BACKGROUND);
            g.fillRect(super.x, super.y, super.width, super.height);

            //selector
            g.drawImage(Textures.buttonBackground, super.x + super.width - DropDownMenu.BORDER_SIZE - (this.originalHeight - 2 * DropDownMenu.BORDER_SIZE), this.y + DropDownMenu.BORDER_SIZE, this.originalHeight - 2 * DropDownMenu.BORDER_SIZE + (super.x + super.width - DropDownMenu.BORDER_SIZE - (this.originalHeight - 2 * DropDownMenu.BORDER_SIZE)), this.y + DropDownMenu.BORDER_SIZE + (this.originalHeight - 2 * DropDownMenu.BORDER_SIZE), 0, 0, Textures.buttonBackground.getWidth(), Textures.buttonBackground.getHeight(), null);

            //border
            g.setColor(UIElement.COLOR_NORMAL);
            g.drawRect(super.x, super.y, super.width, super.height);
            g.drawRect(super.x + 1, super.y + 1, super.width - 2, super.height - 2);
        }
        g.setColor(Color.WHITE);
        g.drawString(this.entries[this.selectedIndex], this.x + DropDownMenu.BORDER_SIZE + Util.PADDING, super.y + Util.calculateCenterPosition(this.originalHeight, Util.getFontHeight(g)) + Util.getFontHeight(g));
    }

    @Override
    public void update(double timeScale, long timeDiff) {
        if (!super.hasFocus() && this.extended) {
            shrink();
        } else if (super.hasFocus() && !this.extended){
            extend();
        }
    }

    @Override
    protected void handleKeyEvent(KeyEvent event) {
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    if (this.extended) {
                        this.mouseOverIndex = (this.mouseOverIndex + 1) % this.entries.length;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (this.extended) {
                        this.mouseOverIndex = (this.mouseOverIndex - 1 + this.entries.length) % this.entries.length;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (this.extended) {
                        if (this.mouseOverIndex != -1) this.selectedIndex = this.mouseOverIndex;
                        super.hasFocus = false;
                        shrink();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (this.extended) {
                        super.hasFocus = false;
                        shrink();
                    }
                    break;
            }
        }
    }

    @Override
    public void realign(int width, int height, Graphics2D g) {}

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
}
