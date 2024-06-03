package state;

public class ChessState {
    public static final int BOARD_ROW = 2;
    public static final int BOARD_COL = 3;
    public static final int KING = 0;
    public static final int BISHOP = 1;
    public static final int BISHOP1 = 2;
    public static final int ROCK = 3;
    public static final int ROCK1 = 4;
    protected Position positions[];

    public ChessState() {
        positions = new Position[]{
                new Position(0, 0), // King position
                new Position(0, 1), // Bishop position
                new Position(0, 2), // Bishop1 position
                new Position(1, 0), // Rock position
                new Position(1, 1)  // Rock1 position
        };
    }

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

    public boolean canMoveUp(int piece) {
        return positions[piece].getRow() > 0 && isEmpty(positions[piece].getUp()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }

    public boolean canMoveRight(int piece) {
        return positions[piece].getCol() < BOARD_COL - 1 && isEmpty(positions[piece].getRight()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }

    public boolean canMoveDown(int piece) {
        return positions[piece].getRow() < BOARD_ROW - 1 && isEmpty(positions[piece].getDown()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }

    public boolean canMoveLeft(int piece) {
        return positions[piece].getCol() > 0 && isEmpty(positions[piece].getLeft()) && (piece == KING || piece == ROCK || piece == ROCK1);
    }

    public boolean canMoveDiagonalLeftDown(int piece) {
        return positions[piece].getRow() < BOARD_ROW - 1
                && positions[piece].getCol() > 0
                && isEmpty(positions[piece].getDiagonalLeftDown())
                && (piece == KING || piece == BISHOP || piece == BISHOP1);
    }

    public boolean canMoveDiagonalLeftUp(int piece) {
        return positions[piece].getRow() > 0 && positions[piece].getCol() > 0 && isEmpty(positions[piece].getDiagonalLeftUp()) && (piece == KING || piece == BISHOP || piece == BISHOP1);
    }

    public boolean canMoveDiagonalRightUp(int piece) {
        return positions[piece].getRow() > 0 && positions[piece].getCol() < BOARD_COL - 1 && isEmpty(positions[piece].getDiagonalRightUp()) && (piece == KING|| piece == BISHOP || piece == BISHOP1);
    }

    public boolean canMoveDiagonalRightDown(int piece) {
        return positions[piece].getRow() < BOARD_ROW - 1 && positions[piece].getCol() < BOARD_COL - 1 && isEmpty(positions[piece].getDiagonalRightDown()) && (piece == KING || piece == BISHOP || piece == BISHOP1);
    }

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
    public void printBoard() {
        String[][] board = new String[BOARD_ROW][BOARD_COL];
        for(int i = 0; i < BOARD_ROW;i++){
            for(int j = 0; j < BOARD_COL; j++){
                board[i][j] = ". ";
            }
        }
        board[positions[KING].getRow()][positions[KING].getCol()] = "K ";
        board[positions[BISHOP].getRow()][positions[BISHOP].getCol()] = "B1";
        board[positions[BISHOP1].getRow()][positions[BISHOP1].getCol()] = "B2";
        board[positions[ROCK].getRow()][positions[ROCK].getCol()] = "R1";
        board[positions[ROCK1].getRow()][positions[ROCK1].getCol()] = "R2";

        for(int i = 0; i < BOARD_ROW; i++){
            for(int j = 0;j < BOARD_COL; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}
