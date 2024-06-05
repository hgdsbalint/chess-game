package state;

/**
 * Contains the state of a chess
 */
public class ChessState {
    /**
     * Number of the table row
     */
    public static final int BOARD_ROW = 2;
    /**
     * Number of the table column
     */
    public static final int BOARD_COL = 3;
    /**
     * Index of the King
     */
    public static final int KING = 0;
    /**
     * Index of the Bishop
     */
    public static final int BISHOP = 1;
    /**
     * Index of the other Bishop
     */
    public static final int BISHOP1 = 2;
    /**
     * Index of the Rock
     */
    public static final int ROCK = 3;
    /**
     * Index of the other Rock
     */
    public static final int ROCK1 = 4;
    protected Position positions[];

    /**
     * Initial the starting state
     */
    public ChessState() {
        positions = new Position[]{
                new Position(0, 0), // King position
                new Position(0, 1), // Bishop position
                new Position(0, 2), // Bishop1 position
                new Position(1, 0), // Rock position
                new Position(1, 1)  // Rock1 position
        };
    }
    public ChessState(Position[] p1) {
        positions = p1;
    }

    public String getPiece(int row, int col) {
        for (int i = 0; i < positions.length; i++) {
            if (positions[i].getRow() == row && positions[i].getCol() == col) {
                switch (i) {
                    case KING:
                        System.out.println("King");
                        return "King";
                    case BISHOP:
                        System.out.println("Bishop");
                        return "Bishop";
                    case BISHOP1:
                        System.out.println("Bishop1");
                        return "Bishop1";
                    case ROCK:
                        System.out.println("Rock");
                        return "Rock";
                    case ROCK1:
                        System.out.println("Rock1");
                        return "Rock1";
                }
            }
        }
        System.out.println("No piece at position (" + row + "," + col + ")");
        return "";
    }
    /**
     *
     * @return when the pieces are in the winning positions
     */
    public boolean isGoal() {
        // Checking that the pieces are in the winning positions
        return positions[KING].equals(new Position(1, 2))
                && positions[BISHOP].equals(new Position(0, 1))
                && positions[BISHOP1].equals(new Position(0, 0))
                && ((positions[ROCK].equals(new Position(1, 0)) || positions[ROCK].equals(new Position(1, 1))))
                && ((positions[ROCK1].equals(new Position(1, 1)) || positions[ROCK1].equals(new Position(1, 0))));
    }


    public boolean isEmpty(Position position) {
        // Checking that the positions is empty
        for (Position p : positions) {
            if (p.equals(position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * It decides that can move with the piece to a direction
     * @param piece index of the piece
     * @param direction one of the 8 possible direction where we wanted to move
     * @return
     */
    public boolean canMove(int piece, Direction direction) {
        return switch (direction) {
            case Up -> canMoveUp(piece);
            case Right -> canMoveRight(piece);
            case Down -> canMoveDown(piece);
            case Left -> canMoveLeft(piece);
            case DiagonalLeftDown -> canMoveDiagonalLeftDown(piece);
            case DiagonalLeftUp -> canMoveDiagonalLeftUp(piece);
            case DiagonalRightUp -> canMoveDiagonalRightUp(piece);
            case DiagonalRightDown -> canMoveDiagonalRightDown(piece);
        };
    }

    /**
     * @param piece index of the piece
     * @return with that the piece can move up
     */
    public boolean canMoveUp(int piece) {
        return positions[piece].getRow() > 0 && isEmpty(positions[piece].getUp()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move right
     */
    public boolean canMoveRight(int piece) {
        return positions[piece].getCol() < BOARD_COL - 1 && isEmpty(positions[piece].getRight()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move down
     */
    public boolean canMoveDown(int piece) {
        return positions[piece].getRow() < BOARD_ROW - 1 && isEmpty(positions[piece].getDown()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move left
     */
    public boolean canMoveLeft(int piece) {
        return positions[piece].getCol() > 0 && isEmpty(positions[piece].getLeft()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move diagonalLeftDown
     */
    public boolean canMoveDiagonalLeftDown(int piece) {
        return positions[piece].getRow() < BOARD_ROW - 1
                && positions[piece].getCol() > 0
                && isEmpty(positions[piece].getDiagonalLeftDown())
                && (piece == KING || piece == BISHOP || piece == BISHOP1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move diagonalLeftUp
     */
    public boolean canMoveDiagonalLeftUp(int piece) {
        return positions[piece].getRow() > 0 && positions[piece].getCol() > 0 && isEmpty(positions[piece].getDiagonalLeftUp()) && (piece == KING || piece == BISHOP || piece == BISHOP1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move diagonalRightUp
     */
    public boolean canMoveDiagonalRightUp(int piece) {
        return positions[piece].getRow() > 0 && positions[piece].getCol() < BOARD_COL - 1 && isEmpty(positions[piece].getDiagonalRightUp()) && (piece == KING|| piece == BISHOP || piece == BISHOP1);
    }
    /**
     * @param piece index of the piece
     * @return with that the piece can move diagonalRightDown
     */
    public boolean canMoveDiagonalRightDown(int piece) {
        return positions[piece].getRow() < BOARD_ROW - 1 && positions[piece].getCol() < BOARD_COL - 1 && isEmpty(positions[piece].getDiagonalRightDown()) && (piece == KING || piece == BISHOP || piece == BISHOP1);
    }

    /**
     * It will move in the specified direction if it can
     * @param piece index of the piece
     * @param direction one of the 8 possible direction where we wanted to move
     */
    public void move(int piece, Direction direction) {
        switch (direction) {
            case Up -> positions[piece].setUp();
            case Right -> positions[piece].setRight();
            case Down -> positions[piece].setDown();
            case Left -> positions[piece].setLeft();
            case DiagonalLeftDown -> positions[piece].setDiagonalLeftDown();
            case DiagonalLeftUp -> positions[piece].setDiagonalLeftUp();
            case DiagonalRightUp -> positions[piece].setDiagonalRightUp();
            case DiagonalRightDown -> positions[piece].setDiagonalRightDown();
        }
    }
}
