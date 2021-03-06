package battleship;

import java.util.List;

/**
 * Basic implementation implementation of the
 * @link Player interface
 */
public class BasePlayer implements Player {
    private String name;
    private BattleField field;
    private List<Ship> fleet;
    private Player foe;


    public BasePlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Ship> getFleet() {
        return fleet;
    }

    @Override
    public BattleField getField() {
        return field;
    }

    @Override
    public void setFleet(List<Ship> fleet) {
        this.fleet = fleet;
    }

    @Override
    public void setField(BattleField field) {
        this.field = field;
    }

    @Override
    public Player getFoe() {
        return foe;
    }

    @Override
    public void setFoe(Player foe) {
        this.foe = foe;
    }
}
