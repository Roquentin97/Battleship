package battleship;

import battleship.exceptions.IllegalCoordinatesException;
import battleship.exceptions.SquareOccupiedException;
import battleship.exceptions.TooCloseException;

public class BattleField {
    enum Square {
        FOG, OCCUPIED, MISS, HIT, ADJACENT
    }

    enum Target {
        MISS, HIT, REPEAT
    }

    private Square[][] field;
    private int side;

    public BattleField() {
        side = 10;
        field = new Square[side][side];
        initializeField();
    }

    public void addShip(Coordinates from, Coordinates to, Ship ship){
        if (from.getRow() == to.getRow()) {
            placeShipHorizontally(from, to, ship);
        } else if (from.getCol() == to.getCol()){
            placeShipVertically(from, to, ship);
        }
        else
            throw new IllegalCoordinatesException();
    }

    public Target shoot(Coordinates target) {
        int row = target.getRow() - 1;
        int col = target.getCol() - 1;

        switch (field[row][col]) {
            case FOG :
            case ADJACENT:
                field[row][col] = Square.MISS;
                return Target.MISS;
            case OCCUPIED:
                field[row][col] = Square.HIT;
                return Target.HIT;
            default:
                return Target.REPEAT;
        }
    }


    // places ship horizontal if given squares are available
    // sets adjacent squares to ADJACENT state;
    private void placeShipHorizontally(Coordinates from , Coordinates to, Ship ship) {
        int f, t, row;
        row = from.getRow() - 1;
        f = Math.min(from.getCol(), to.getCol()) - 1;
        t = Math.max(from.getCol(), to.getCol()) - 1;

        // check weather all needed squares are in appropriate state;
        // throws an Exception if not
        for (int col = f; col <= t; col++) {
            canHost(row, col);
        }

        for (int col = f; col <= t; col++) {
            ship.addOccupiedCell(new Coordinates(row + 1, col + 1));
        }

        // set squares and top and bottom adjacent rows to ADJACENT state
        if (row - 1 >= 0 && row + 1 < side) {

            // set end-adjacent squares
            if (f > 0) {
                field[row - 1][f - 1] = Square.ADJACENT;
                field[row][f - 1] = Square.ADJACENT;
                field[row + 1][f - 1] = Square.ADJACENT;
            }
            if (t + 1 < side) {
                field[row - 1][t + 1] = Square.ADJACENT;
                field[row][t + 1] = Square.ADJACENT;
                field[row + 1][t + 1] = Square.ADJACENT;
            }

            // set side-adjacent squares
            for (int col = f; col <= t; col++) {
                field[row][col] = Square.OCCUPIED;

                field[row - 1][col] = Square.ADJACENT;
                field[row + 1][col] = Square.ADJACENT;
            }
        }

        // if current row is in the very top
        else if (row > 0) {

            // set end-adjacent squares
            if (f > 0) {
                field[row - 1][f - 1] = Square.ADJACENT;
                field[row][f - 1] = Square.ADJACENT;
            }
            if (t + 1 < side) {
                field[row - 1][t + 1] = Square.ADJACENT;
                field[row][t + 1] = Square.ADJACENT;
            }

            // set side-adjacent squares
            for (int col = f; col <= t; col++) {
                field[row][col] = Square.OCCUPIED;
                field[row - 1][col] = Square.ADJACENT;
            }
        }

        // if current row is in the very bottom
        else if (row + 1 < side) {

            // set end-adjacent squares
            if (f > 0) {
                field[row][f - 1] = Square.ADJACENT;
                field[row + 1][f - 1] = Square.ADJACENT;
            }
            if (t + 1 < side) {
                field[row][t + 1] = Square.ADJACENT;
                field[row + 1][t + 1] = Square.ADJACENT;
            }

            // set side-adjacent squares
            for (int col = f; col <= t; col++) {
                field[row][col] = Square.OCCUPIED;
                field[row + 1][col] = Square.ADJACENT;
            }
        }

    }

    private void placeShipVertically(Coordinates from, Coordinates to, Ship ship) {
        int f, t, col;
        col = from.getCol() - 1;
        f = Math.min(from.getRow(), to.getRow()) - 1;
        t = Math.max(from.getRow(), to.getRow()) - 1;

        // check weather all needed squares are in appropriate state;
        // throws an Exception if not
        for (int row = f; row <= t; row++) {
            canHost(row, col);
        }

        for (int row = f; row <= t; row++) {
            ship.addOccupiedCell(new Coordinates(row + 1, col + 1));
        }

        // set squares and top and bottom adjacent rows to ADJACENT state
        if (col - 1 >= 0 && col + 1 < side) {

            // set end-adjacent squares
            if (f > 0) {
                field[f - 1][col - 1] = Square.ADJACENT;
                field[f - 1][col] = Square.ADJACENT;
                field[f - 1][col + 1] = Square.ADJACENT;
            }
            if (t + 1 < side) {
                field[t + 1][col - 1] = Square.ADJACENT;
                field[t + 1][col] = Square.ADJACENT;
                field[t + 1][col + 1] = Square.ADJACENT;
            }

            // set side-adjacent squares
            for (int row = f; row <= t; row++) {
                field[row][col] = Square.OCCUPIED;
                field[row][col - 1] = Square.ADJACENT;
                field[row][col + 1] = Square.ADJACENT;
            }
        }

        // if current row is in the very top
        else if (col > 0) {

            // set end-adjacent squares
            if (f > 0) {
                field[f - 1][col - 1] = Square.ADJACENT;
                field[f - 1][col] = Square.ADJACENT;
            }
            if (t + 1 < side) {
                field[t + 1][col - 1] = Square.ADJACENT;
                field[t + 1][col] = Square.ADJACENT;
            }

            // set side-adjacent squares
            for (int row = f; row <= t; row++) {
                field[row][col] = Square.OCCUPIED;
                field[row][col -1] = Square.ADJACENT;
            }
        }

        // if current row is in the very bottom
        else if (col + 1 < side) {

            // set end-adjacent squares
            if (f > 0) {
                field[f - 1][col] = Square.ADJACENT;
                field[f - 1][col + 1] = Square.ADJACENT;
            }
            if (t + 1 < side) {
                field[t + 1][col] = Square.ADJACENT;
                field[t + 1][col + 1] = Square.ADJACENT;
            }

            // set side-adjacent squares
            for (int row = f; row <= t; row++) {
                field[row][col] = Square.OCCUPIED;
                field[row][col + 1] = Square.ADJACENT;
            }
        }
    }

    private boolean canHost(int row, int col) {
        switch (field[row][col]) {
            case OCCUPIED:
                throw new SquareOccupiedException();
            case ADJACENT:
                throw new TooCloseException();
            case FOG:
                return true;
            default:
                return false;
        }
    }

    private void initializeField(){
        for (int row = 0 ; row < side; row++) {
            for (int col = 0; col < side; col++) {
                field[row][col] = Square.FOG;
            }
        }
    }

    private static void printField(BattleField field) {
        for (int row = 0; row < field.side; row++) {
            for (int col = 0; col < field.side; col++) {
                String square = "-";
                switch (field.field[row][col]) {
                    case FOG:
                        square = "F";
                        break;
                    case ADJACENT:
                        square = "A";
                        break;
                    case OCCUPIED:
                        square = "O";
                }
                System.out.print(square + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public Square[][] getField() {
        return field;
    }
}
