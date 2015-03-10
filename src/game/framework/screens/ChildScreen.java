package game.framework.screens;

import java.awt.*;

/**
 * Created by AP43 on 10.03.2015.
 */
public abstract class ChildScreen extends BaseScreen {
    private BaseScreen parent;

    public ChildScreen(String name, int width, int height, Graphics2D g, BaseScreen parent) {
        super(name, width, height, g);
        this.parent = parent;
    }

    protected BaseScreen getParent() {
        return parent;
    }
}
