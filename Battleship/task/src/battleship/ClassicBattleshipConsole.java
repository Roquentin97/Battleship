package battleship;

import battleship.exceptions.IllegalCoordinatesException;
import battleship.exceptions.SquareOccupiedException;
import battleship.exceptions.TooCloseException;

import java.util.Optional;
import java.util.Scanner;

/**
 * Single console implementation of the ClassicBattleship
 */
public class ClassicBattleshipConsole extends ClassicBattleship {

    Scanner scanner;

    public ClassicBattleshipConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    //TODO:: replace with bundle
    private String nextTurnMessage = "Press Enter and pass the move to another player\n";

    @Override
    protected void placeToField(Ship ship, BattleField field) {


        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getLength());

        Coordinates from, to;

        while ((from = convertCoordinates(scanner.next())) == null) {
            System.out.println("\nError! Invalid coordinates given. Try again.\n");
        }

        while ((to = convertCoordinates(scanner.next())) == null) {
            System.out.println("\nError! Invalid coordinates given. Try again.\n");
        }

        while (from.getCol() != to.getCol() && from.getRow() != to.getRow()) {
            System.out.println("\nError! The ship must pe placed horizontally or vertically!\n\n" );

            from = convertCoordinates(scanner.next());
            to = convertCoordinates(scanner.next());
        }

        while (from.findDistance(to) != ship.getLength()) {
            System.out.println("\nError! The ship must occupy exactly " + ship.getLength() + " cells!\n\n" );

            from = convertCoordinates(scanner.next());
            to = convertCoordinates(scanner.next());
        }

        try {
            field.addShip(from, to, ship);
        } catch (IllegalCoordinatesException ex) {
            System.out.println("\nError! Incorrect coordinates. Try again\n");
            placeToField(ship, field);
            return;
        } catch (SquareOccupiedException ex) {
            System.out.println("\nError! You are trying to place ship in already occupied cells!\n");
            placeToField(ship, field);
            return;
        } catch (TooCloseException ex) {
            System.out.println("\nError! Your ship is too close to another one. Try again\n");
            placeToField(ship, field);
            return;
        }

        printRevealedField(field);
    }

    @Override
    protected void beforeUserPlacesShips(Player player) {
        System.out.println(player.getName() + ", place your ships on the game field\n");
        printRevealedField(player.getField());
    }

    @Override
    protected void afterUserPlacesShips(Player player){
        nextTurn();
    }

    @Override
    protected void battle() {
        while (!shoot(playerA) && !shoot(playerB));
    }

    protected boolean shoot(Player player) {
        Player foe = player.getFoe();
        newTurn(player);
        Coordinates target;
        while ((target = convertCoordinates(scanner.next())) == null) {
            System.out.println("Error! Illegal coordinates!");
        }
        BattleField.Target shoot = foe.getField().shoot(target);

        switch (shoot) {
            case HIT:
                System.out.println("You hit a ship!");
                Optional<Ship> targetShip = Optional.empty();
                for (Ship ship : fleetA) {
                    if (ship.getCells().contains(target)) {
                        targetShip = Optional.of(ship);
                        break;
                    }
                }
                targetShip.ifPresent(ship -> {
                    ship.decrementHealth();
                    if (ship.getHealth() < 1) {
                        System.out.println("You sank a ship!");
                    }
                });

                if (fleetA.isEmpty()) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    return true;
                }
                nextTurn();
                break;
            case MISS:
                System.out.println("You missed!\n");
                nextTurn();
                break;
            case REPEAT:
                System.out.println("Repeated shoot to the cell! Try again!\n");
                break;
            }
        return false;
    }

    protected void printRevealedField(BattleField field) {
        BattleField.Square[][] bField = field.getField();
        char coordinate = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (BattleField.Square[] row : bField) {
            System.out.print(coordinate++ + " ");
            for (BattleField.Square square : row) {
                switch (square) {
                    case OCCUPIED:
                        System.out.print("O ");
                        break;
                    case ADJACENT:
                    case FOG:
                        System.out.print("~ ");
                        break;
                    case HIT:
                        System.out.print("X ");
                        break;
                    case MISS:
                        System.out.print("M ");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    protected void printGameField(BattleField field) {
        BattleField.Square[][] bField = field.getField();
        char coordinate = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (BattleField.Square[] row : bField) {
            System.out.print(coordinate++ + " ");
            for (BattleField.Square square : row) {
                switch (square) {
                    case OCCUPIED:
                    case ADJACENT:
                    case FOG:
                        System.out.print("~ ");
                        break;
                    case HIT:
                        System.out.print("X ");
                        break;
                    case MISS:
                        System.out.print("M ");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private Coordinates convertCoordinates(String coordinates) {
        coordinates = coordinates.toUpperCase();

        // calculates using ASCII
        int row = coordinates.charAt(0) - 64;
        int col;
        try {
            col = Integer.parseInt(coordinates.substring(1));
        } catch (NumberFormatException ex) {
            return null;
        }
        if (row < 1 || row > 10 || col < 0 || col > 10 ) {
            return null;
        }

        return new Coordinates(row, col);

    }

    private void nextTurn() {
        System.out.println(nextTurnMessage);
        String ln = "";
        while (ln != null) {
            scanner.nextLine();
            if (scanner.hasNextLine())
                ln = null;
        }
    }

    private void newTurn(Player player) {
        printGameField(player.getFoe().getField());
        System.out.println("---------------------");
        printRevealedField(player.getField());

        System.out.println(player.getName() + ", it's your turn: \n");
    }
}
