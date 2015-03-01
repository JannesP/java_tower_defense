package game.object.tile;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jannes Peters on 2/28/2015.
 */
public class Path {
    private ArrayList<Point> wayPoint = new ArrayList<>();


    public Path(TileMap map) {


    }

    private TileCoordinate findNextTile() {

    }

    public class TileCoordinate {
        public int x, y;
        public TileCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
