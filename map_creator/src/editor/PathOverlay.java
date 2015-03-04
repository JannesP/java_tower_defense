package editor;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 02.03.2015.
 */
public class PathOverlay {
    private ArrayList<ArrayList<Point>> paths;
    private ArrayList<Point> wayPoints = new ArrayList<>();

    public PathOverlay() {
        paths = new ArrayList<>();
    }

    public PathOverlay(ArrayList<ArrayList<Point>> paths) {
        this.paths = paths;
    }

    public void draw (Graphics2D g) {
        for (int i = 0; i < wayPoints.size(); i++) {
            g.setColor(Color.RED);
            g.drawString(String.valueOf(i), (int)(wayPoints.get(i).getX() * Map.TILE_SIZE), (int)(wayPoints.get(i).getY() * Map.TILE_SIZE) + Map.TILE_SIZE);
        }
    }

    public void addPoint(Point p) {
        wayPoints.add(p);
    }

    public void addPoint(int x, int y) {
        wayPoints.add(new Point(x, y));
    }

    public void removePoint(Point point) {
        for(Point p : wayPoints) {
            if (p.getX() == point.getX() && p.getY() == point.getY()) {
                wayPoints.remove(p);
            }
        }
    }

    public void removePoint(int x, int y) {
        for(Point p : wayPoints) {
            if ((int)p.getX() == x && (int)p.getY() == y) {
                wayPoints.remove(p);
            }
        }
    }

    public void nextPath() {
        paths.add(wayPoints);
        wayPoints = new ArrayList<>();
    }

    public void clearPaths() {
        paths.clear();
        wayPoints.clear();
    }

    public int getCurrentPath() {
        return paths.size() + 1;
    }

    public ArrayList<ArrayList<Point>> getPaths() {
        return paths;
    }
}
