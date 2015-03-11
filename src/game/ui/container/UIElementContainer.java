package game.ui.container;

import game.drawable.IPaintableUpdatableObject;
import game.ui.element.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Container for handling all UI elements.
 * Created by Jannes Peters on 2/26/2015.
 */
public class UIElementContainer implements IPaintableUpdatableObject {
        private ArrayList<UIElement> elements;

        public UIElementContainer(ArrayList<UIElement> elements) {
            this.elements = elements;
        }

        public ArrayList<UIElement> getElements() {
            return elements;
        }

        public void handleMouseInput(ArrayList<MouseEvent> events) {
            for (UIElement element : elements) {
                element.handleMouseInput(events);
            }
        }

        public void handleKeyInput(ArrayList<KeyEvent> events) {
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getID() == KeyEvent.KEY_PRESSED) {
                    switch (events.get(i).getKeyCode()) {
                        case KeyEvent.VK_TAB:
                            if (events.get(i).isShiftDown()) {
                                setLastFocus();
                            } else {
                                setNextFocus();
                            }
                            events.remove(i--);
                            break;
                        case KeyEvent.VK_ESCAPE:
                            clearFocus();
                            break;
                    }


                }
            }
            for (UIElement element : elements) {
                element.handleKeyInput(events);
            }
        }

        private void setLastFocus() {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).hasFocus()) {
                    clearFocus();
                    elements.get((i - 1 + elements.size()) % elements.size()).setHasFocus(true);
                    return;
                }
            }
            if (!elements.isEmpty()) {
                elements.get(elements.size() - 1).setHasFocus(true);
            }
        }

        private void setNextFocus() {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).hasFocus()) {
                    clearFocus();
                    elements.get(++i % elements.size()).setHasFocus(true);
                    return;
                }
            }
            if (!elements.isEmpty()) {
                elements.get(0).setHasFocus(true);
            }
        }

        private void clearFocus() {
            for (UIElement element : elements) element.setHasFocus(false);
        }

        @Override
        public void update(double timeScale, long timeDiff) {
            for (UIElement element : elements) {
                element.update(timeScale, timeDiff);
            }
        }

        @Override
        public void draw(Graphics2D g) {
            for (UIElement element : elements) {
                element.draw(g);
            }
            for (UIElement element : elements) {
                element.drawOverlay(g);
            }
        }

        @Override
        public void realign(int width, int height, Graphics2D g) {
            for (UIElement element : elements) {
                element.realign(width, height, g);
            }
        }
}
