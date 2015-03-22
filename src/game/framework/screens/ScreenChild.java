package game.framework.screens;

import java.awt.*;

/**
 * Created by AP43 on 10.03.2015.
 */
public abstract class ScreenChild extends ScreenBase {
    private ScreenBase parent;

    public ScreenChild(String name, int width, int height, Graphics2D g, ScreenBase parent) {
        super(name, width, height, g);
        this.parent = parent;
    }

    protected ScreenBase getParent() {
        return parent;
    }
}
