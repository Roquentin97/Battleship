package battleship;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassicBattleship extends Battleship {

    @Override
    protected void addShips() {
        fleetA = new ArrayList<>();
        addShips(fleetA);
        playerA.setFleet(fleetA);

        fleetB = new ArrayList<>();
        addShips(fleetB);
        playerB.setFleet(fleetB);
    }

    @Override
    protected void createBattlefield() {
        fieldA = new BattleField();
        playerA.setField(fieldA);

        fieldB = new BattleField();
        playerB.setField(fieldB);
    }

    // predefines classic fleet and adds it to given battlefield
    private void addShips(List<Ship> fleet) {
        new Ship("Aircraft Carrier", 5, fleet);
        new Ship("Battleship",4, fleet);
        new Ship("Submarine", 3, fleet);
        new Ship("Cruiser", 3, fleet);
        new Ship("Destroyer", 2, fleet);
    }


}
