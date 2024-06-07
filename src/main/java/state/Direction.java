package state;

/**
 * Represent 8 directions
 */
public enum Direction {
    Up,
    Down,
    Left,
    Right,
    DiagonalLeftUp,
    DiagonalRightUp,
    DiagonalLeftDown,
    DiagonalRightDown;

    public static Direction getDirection(int startRow, int startCol, int targetRow, int targetCol) {
        int rowDiff = targetRow - startRow;
        int colDiff = targetCol - startCol;

        if (rowDiff < 0) {
            if (colDiff < 0) {
                return Direction.DiagonalLeftUp;
            } else if (colDiff == 0) {
                return Direction.Up;
            } else {
                return Direction.DiagonalRightUp;
            }
        } else if (rowDiff == 0) {
            if (colDiff < 0) {
                return Direction.Left;
            } else if (colDiff == 0) {
                return Right; // Same position
            } else {
                return Direction.Right;
            }
        } else {
            if (colDiff < 0) {
                return Direction.DiagonalLeftDown;
            } else if (colDiff == 0) {
                return Direction.Down;
            } else {
                return Direction.DiagonalRightDown;
            }
        }
    }
}