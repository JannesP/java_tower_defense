package game.drawable;

import java.awt.*;

public interface PaintableUpdatableObject {
	public void update(long timeDiff);
	public void paint(Graphics2D g);
	public void realign(Graphics2D g);
}
