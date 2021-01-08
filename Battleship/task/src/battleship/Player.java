package battleship;

import java.util.List;

/**
 * Interface representing a player of the game
 */
public interface Player {

    public String getName();

    public void setName(String name);

    List<Ship> getFleet();

    BattleField getField();

    void setFleet(List<Ship> fleet);

    void setField(BattleField field);

    Player getFoe();

    void setFoe(Player player);

}

