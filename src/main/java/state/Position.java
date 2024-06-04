package state;

import java.util.Objects;

/**
 * Represent the piece position
 */
public class Position {
    private int row;
    private int col;

    /**
     * Constructor of Position
     * @param row represent in which row is the piece
     * @param col represent in which column is the piece
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * this is a getter
     * @return with which row it is
     */
    public int getRow() {
        return row;
    }
    /**
     * this is a getter
     * @return with which column it is
     */
    public int getCol() {
        return col;
    }

    /**
     *  It changes the piece position to up but not actually
     * @return a new position which is moved up
     */
    public Position getUp() {
        return new Position(row - 1, col);
    }
    /**
     *  It changes the piece position to down but not actually
     * @return a new position which is moved down
     */
    public Position getDown() {
        return new Position(row + 1, col);
    }
    /**
     *  It changes the piece position to left but not actually
     * @return a new position which is moved left
     */
    public Position getLeft() {
        return new Position(row, col - 1);
    }
    /**
     *  It changes the piece position to right but not actually
     * @return a new position which is moved right
     */
    public Position getRight() {
        return new Position(row, col + 1);
    }
    /**
     *  It changes the piece position to diagonalLeftUp but not actually
     * @return a new position which is moved diagonalLeftUp
     */
    public Position getDiagonalLeftUp() {
        return new Position(row - 1, col - 1);
    }
    /**
     *  It changes the piece position to diagonalRightUp but not actually
     * @return a new position which is moved diagonalRightUp
     */
    public Position getDiagonalRightUp() {
        return new Position(row - 1, col + 1);
    }
    /**
     *  It changes the piece position to diagonalLeftDown but not actually
     * @return a new position which is moved diagonalLeftDown
     */
    public Position getDiagonalLeftDown() {
        return new Position(row + 1, col - 1);
    }
    /**
     *  It changes the piece position to diagonalRightDown but not actually
     * @return a new position which is moved diagonalRightDown
     */
    public Position getDiagonalRightDown() {
        return new Position(row + 1, col + 1);
    }

    /**
     * It is actually changes the piece position to up
     */
    public void setUp() {
        this.row--;
    }
    /**
     * It is actually changes the piece position to down
     */
    public void setDown() {
        this.row++;
    }
    /**
     * It is actually changes the piece position to left
     */
    public void setLeft() {
        this.col--;
    }
    /**
     * It is actually changes the piece position to right
     */
    public void setRight() {
        this.col++;
    }
    /**
     * It is actually changes the piece position to diagonalLeftUp
     */
    public void setDiagonalLeftUp() {
        this.row--;
        this.col--;
    }
    /**
     * It is actually changes the piece position to diagonalRightUp
     */
    public void setDiagonalRightUp() {
        this.row--;
        this.col++;
    }
    /**
     * It is actually changes the piece position to diagonalLeftDown
     */
    public void setDiagonalLeftDown() {
        this.row++;
        this.col--;
    }
    /**
     * It is actually changes the piece position to diagonalRightDown
     */
    public void setDiagonalRightDown() {
        this.row++;
        this.col++;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
