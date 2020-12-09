package battleship;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Ship {

    private String name;

    private int length;

    private int health;

    private List<Ship> fleet;

    public Ship (String name, int length) {
        this.name = name;
        this.length = length;
        health = length;
    }

    public Ship(String name, int length, List<Ship> fleet) {
        this(name, length);
        this.fleet = fleet;
        fleet.add(this);
    }

    public HashSet<Coordinates> cells = new HashSet<>();

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getHealth() {
        return health;
    }

    public HashSet<Coordinates> getCells() {
        return cells;
    }

    public void addOccupiedCell(Coordinates cell) {
        cells.add(cell);
    }

    public void decrementHealth() {
        health--;

        if (health < 1)
            fleet.remove(this);
    }

    public void setFleet(List<Ship> fleet) {
        this.fleet = fleet;
        fleet.add(this);
    }
}
