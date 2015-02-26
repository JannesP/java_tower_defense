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
public class UIElementContainer implements IPaintableUpdatableObject{
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
            for (UIElement element : elements) {
                element.handleKeyInput(events);
            }
        }

        @Override
        public void update(long timeDiff) {
            for (UIElement element : elements) {
                element.update(timeDiff);
            }
        }

        @Override
        public void draw(Graphics2D g) {
            for (UIElement element : elements) {
                element.draw(g);
            }
        }

        @Override
        public void realign(int width, int height, Graphics2D g) {
            for (UIElement element : elements) {
                element.realign(width, height, g);
            }
        }
}
