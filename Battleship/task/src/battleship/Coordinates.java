package battleship;


/**
 * Class representing coordinates on the field (2d array)
 */
class Coordinates {
    private int row;
    private int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setA(int row) {
        this.row = row;
    }

    public void setB(int col) {
        this.col = col;
    }

    public int findDistance(Coordinates coordinates) {
        int result = Integer.MIN_VALUE;

        if (col == coordinates.col) {
            result = row - coordinates.row;
        } else if (row == coordinates.row) {
            result = col - coordinates.col;
        } else {
            throw new UnsupportedOperationException();
        }

        return result > 0 ? result + 1 : -result + 1;


    }

    @Override
    public String toString() {
        return "{row: " + row + ", col: " + col + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Coordinates))
            return false;

        Coordinates c = (Coordinates) obj;
        return this.col == c.col && this.row == c.row;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
