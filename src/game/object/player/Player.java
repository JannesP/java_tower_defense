package game.object.player;

/**
 * Created by Jannes Peters on 3/8/2015.
 */
public class Player {
    protected String name;
    protected int gold = 0, specialResource = 0, mana = 0, maxMana, id;

    public Player(String name, int id, int maxMana) {
        this.name = name;
        this.id = id;
        this.maxMana = maxMana;
    }

    public int getId() {
        return id;
    }
}
