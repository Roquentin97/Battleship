package battleship;

import java.util.List;

/**
 * Base abstract class for the came using Template Method Pattern
 * to specify main workflow
 */
public abstract class Battleship {

    protected BattleField fieldA;
    protected BattleField fieldB;

    protected List<Ship> fleetA;
    protected List<Ship> fleetB;

    protected Player playerA;
    protected Player playerB;

    public final void play() {
        beforeGameSetup();
        setupGame();
        afterGameSetup();

        battle();

        afterPlay();

    }

    private final void setupGame() {
        createBattlefield();
        addShips();
        placeShips();
    }

    private void placeShips() {
        beforeUserPlacesShips(playerA);
        fleetA.stream().forEach(
                ship -> placeToField(ship, fieldA)
        );
        afterUserPlacesShips(playerA);

        beforeUserPlacesShips(playerB);
        fleetB.stream().forEach(
                ship -> placeToField(ship, fieldB)
        );
        afterUserPlacesShips(playerB);
    }

    // abstract methods
    protected abstract void addShips();
    protected abstract void createBattlefield();
    protected abstract void placeToField(Ship ship, BattleField field);
    protected abstract void battle();

    // hooks
    protected void beforeGameSetup(){}
    protected void afterGameSetup(){}
    protected void beforeUserPlacesShips(Player player){}
    protected void afterUserPlacesShips(Player player){}
    protected void afterPlay(){}


    // getters and setters below
    public void setShipsA(List<Ship> ships) {
        fleetA = ships;
    }

    public void setShipsB(List<Ship> ships) {
        fleetB = ships;
    }

    public List<Ship> getShipsA() {
        return fleetA;
    }

    public List<Ship> getShipsB() {
        return fleetB;
    }

    public void setBattlefieldA(BattleField battlefieldA) {
        this.fieldA = battlefieldA;
    }

    public void setBattlefieldB(BattleField battlefieldB) {
        this.fieldB = battlefieldB;
    }

    public BattleField getBattlefieldA() {
        return  fieldA;
    }

    public BattleField getBattlefieldB() {
        return fieldB;
    }

    public Player getPlayerA() {return playerA;}

    public Player getPlayerB() {return playerB;}

    public void setPlayerA(Player player) {
        playerA = player;}

    public void setPlayerB(Player player) {
        playerB = player;}
}
