package state;

import java.util.Objects;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position getUp() {
        return new Position(row - 1, col);
    }

    public Position getDown() {
        return new Position(row + 1, col);
    }

    public Position getLeft() {
        return new Position(row, col - 1);
    }

    public Position getRight() {
        return new Position(row, col + 1);
    }

    public Position getDiagonalLeftUp() {
        return new Position(row - 1, col - 1);
    }

    public Position getDiagonalRightUp() {
        return new Position(row - 1, col + 1);
    }

    public Position getDiagonalLeftDown() {
        return new Position(row + 1, col - 1);
    }

    public Position getDiagonalRightDown() {
        return new Position(row + 1, col + 1);
    }

    public void setUp() {
        this.row--;
    }

    public void setDown() {
        this.row++;
    }

    public void setLeft() {
        this.col--;
    }

    public void setRight() {
        this.col++;
    }

    public void setDiagonalLeftUp() {
        this.row--;
        this.col--;
    }

    public void setDiagonalRightUp() {
        this.row--;
        this.col++;
    }

    public void setDiagonalLeftDown() {
        this.row++;
        this.col--;
    }

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
