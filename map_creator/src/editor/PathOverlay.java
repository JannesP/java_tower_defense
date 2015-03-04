package editor;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 02.03.2015.
 */
public class PathOverlay {
    private ArrayList<ArrayList<Point>> paths;
    private int selectedPath = 0;

    public PathOverlay() {
        paths = new ArrayList<>();
        paths.add(new ArrayList<>());
    }

    public PathOverlay(ArrayList<ArrayList<Point>> paths) {
        this.paths = paths;
    }

    public void draw (Graphics2D g) {
        if (paths.size() == 0) return;
        for (int i = 0; i < paths.get(selectedPath).size(); i++) {
            g.setColor(Color.RED);
            g.drawString(String.valueOf(i), (int)(paths.get(selectedPath).get(i).getX() * Map.TILE_SIZE), (int)(paths.get(selectedPath).get(i).getY() * Map.TILE_SIZE) + Map.TILE_SIZE);
        }
    }

    public void selectPath(int pathId) {
        if (pathId < paths.size() && pathId > -1) {
            this.selectedPath = pathId;
        }
    }

    public void addPoint(Point p) {
        paths.get(selectedPath).add(p);
    }

    public void addPoint(int x, int y) {
        paths.get(selectedPath).add(new Point(x, y));
    }

    public void removePoint(Point point) {
        for(Point p : paths.get(selectedPath)) {
            if (p.getX() == point.getX() && p.getY() == point.getY()) {
                paths.get(selectedPath).remove(p);
            }
        }
    }

    public void removePoint(int x, int y) {
        for(Point p : paths.get(selectedPath)) {
            if ((int)p.getX() == x && (int)p.getY() == y) {
                paths.get(selectedPath).remove(p);
            }
        }
    }

    public void nextPath() {
        paths.add(new ArrayList<>());
        this.selectedPath = paths.size() - 1;
    }

    public void clearPaths() {
        this.selectedPath = 0;
        paths.clear();
        paths.add(new ArrayList<>());
    }

    public int getCurrentPath() {
        return paths.size() + 1;
    }

    public ArrayList<ArrayList<Point>> getPaths() {
        return paths;
    }

    public int getSelectedPath() {
        return selectedPath;
    }
}
