package battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class specifying rules of the classic battleship game:
 * the number and size of participating ships and 10 X 10 field
 */
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
        fieldA = new BattleField(10);
        playerA.setField(fieldA);

        fieldB = new BattleField(10);
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
