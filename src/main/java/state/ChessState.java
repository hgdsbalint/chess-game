package state;

import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;

import java.util.*;

import static state.Direction.getDirection;
/**
 * Contains the state of a chess
 */
public class ChessState implements TwoPhaseMoveState<Integer>, Cloneable {
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
    public Position[] positions;

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

    /**
     * ChessState's constructor
     * @param p1 positions array
     */
    public ChessState(Position[] p1) {
        positions = p1;
    }

    /**
     *  Gets a position and decide what piece
     * @param row it's piece row
     * @param col it's piece col
     * @return it returns the piece name from the position
     */
    public String getPiece(int row, int col) {
        for (int i = 0; i < positions.length; i++) {
            if (positions[i].getRow() == row && positions[i].getCol() == col) {
                switch (i) {
                    case KING:
                        return "King";
                    case BISHOP:
                        return "Bishop";
                    case BISHOP1:
                        return "Bishop1";
                    case ROCK:
                        return "Rock";
                    case ROCK1:
                        return "Rock1";
                }
            }
        }
        System.out.println("No piece at position (" + row + "," + col + ")");
        return "";
    }
    /**
     *  Checking that the pieces are in winning position
     * @return {@code true} when the pieces are in the winning positions {@code false} is not in a winning position
     */
    public boolean isGoal() {
        return positions[KING].equals(new Position(1, 2))
                && positions[BISHOP].equals(new Position(0, 1))
                && positions[BISHOP1].equals(new Position(0, 0))
                && ((positions[ROCK].equals(new Position(1, 0)) || positions[ROCK].equals(new Position(1, 1))))
                && ((positions[ROCK1].equals(new Position(1, 1)) || positions[ROCK1].equals(new Position(1, 0))));
    }

    /**
     *  It decides that the positon's empty
     * @param position thw position what we decide is's empty
     * @return {@code true} when the position is empty {@code false} when it's not empty
     */
    public boolean isEmpty(Position position) {
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
     * @return the direction where wanted to move
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
        return positions[piece].getRow() < BOARD_ROW - 1 && positions[piece].getCol() > 0 && isEmpty(positions[piece].getDiagonalLeftDown()) && (piece == KING || piece == BISHOP || piece == BISHOP1);
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
    private int calculateDestination(int piece, Direction direction) {
        switch (direction) {
            case Up:
                return piece - BOARD_COL;
            case Down:
                return piece + BOARD_COL;
            case Left:
                return piece - 1;
            case Right:
                return piece + 1;
            case DiagonalLeftDown:
                return piece + BOARD_COL - 1;
            case DiagonalLeftUp:
                return piece - BOARD_COL - 1;
            case DiagonalRightUp:
                return piece - BOARD_COL + 1;
            case DiagonalRightDown:
                return piece + BOARD_COL + 1;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    /**
     *  Decide that the piece can move to a specified direction
     * @param piece id of the piece
     * @return {@code true} if the piece can move to the direction {@code} other
     */
    @Override
    public boolean isLegalToMoveFrom(Integer piece) {
       for(Direction direction:Direction.values()){
           if(canMove(piece,direction)){
               return true;
           }
       }
       return false;
    }

    /**
     * Decide that the pieces in the goal state
     * @return {@code true} if the state is in goal position {@code false} other
     */
    @Override
    public boolean isSolved() {
        return isGoal();
    }

    /**
     *  Decide that the move is legal
     * @param move the piece move
     * @return {@code true} if the move is legal {@code false} other
     */
    @Override
    public boolean isLegalMove(TwoPhaseMove<Integer> move) {
        int piece = move.from();
        int fromRow = move.from() / BOARD_COL;
        int fromCol = move.from() % BOARD_COL;
        int row = move.to() / BOARD_COL;
        int col = move.to() % BOARD_COL;
        if (row > 0 || col > 0) {
            return canMove(piece, Objects.requireNonNull(getDirection(fromRow, fromCol, row, col)));
        }
        return false;
    }

    /**
     * Make a move if it's legal
     * @param move move what is going make
     */
    @Override
    public void makeMove(TwoPhaseMove<Integer> move) {
        if (isLegalMove(move)) {
            int fromRow = move.from() / BOARD_COL;
            int fromCol = move.from() % BOARD_COL;
            int toRow = move.to() / BOARD_COL;
            int toCol = move.to() % BOARD_COL;

            Direction direction = getDirection(fromRow, fromCol, toRow, toCol);

            if (canMove(move.from(), direction)) {
                move(move.from(), direction);
                //System.out.println(fromRow + " " + fromCol + " " + direction + " " + toRow + " " + toCol);

            }
        }
    }

    /**
     * A set collect the legal moves from the state
     * @return a set of legal moves
     */
    @Override
    public Set<TwoPhaseMove<Integer>> getLegalMoves() {
        Set<TwoPhaseMove<Integer>> legalMoves = new HashSet<>();
        for (int i = 0; i < positions.length; i++) {
            for (Direction direction : Direction.values()) {
                if (canMove(i, direction)) {
                    legalMoves.add(new TwoPhaseMove<>(i, calculateDestination(i, direction)));
                }
            }
        }
        return legalMoves;
    }

    /**
     * @return state's clone
     */
    @Override
    public ChessState clone() {
        ChessState copy;
        try {
            copy = (ChessState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        copy.positions = new Position[positions.length];
        for (int i = 0; i < positions.length; i++) {
            copy.positions[i] = new Position(positions[i].getRow(), positions[i].getCol());
        }

        return copy;
    }

    /**
     * @param o compare this object
     * @return {@code true} when the state is equals to an objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessState that = (ChessState) o;
        return Arrays.equals(positions, that.positions);
    }

    /**
     * @return Hash code of the state
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(positions);
    }

    /**
     * @return a string what is represents the state
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < positions.length; i++) {
            result += "Piece " + i + ": " + positions[i] + "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        ChessState state1 = new ChessState( new Position[]{
                new Position(0, 2), // King position
                new Position(0, 1), // Bishop position
                new Position(0, 0), // Bishop1 position
                new Position(1, 0), // Rock position
                new Position(1, 1)
        });
        new BreadthFirstSearch<TwoPhaseMove<Integer>>()
                .solveAndPrintSolution(state1);
    }
}
