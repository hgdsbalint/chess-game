import com.sun.source.tree.AssertTree;
import state.ChessState;
import state.Direction;
import state.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    ChessState state1 = new ChessState(); // initial state

    ChessState state2 = new ChessState(new Position[]{
            new Position(0, 0),
            new Position(1, 2),
            new Position(0, 1),
            new Position(1, 0),
            new Position(1, 1)
    }); // random state
    ChessState state3 = new ChessState(new Position[]{
            new Position(1, 2),
            new Position(0, 1),
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1)
    });//goal state

    @Test
    public void testIsEmpty() {
        assertTrue(state1.isEmpty(new Position(2, 2)));
        assertFalse(state1.isEmpty(new Position(0, 0)));
    }

    @Test
    public void testIsGoal() {
        assertFalse(state1.isGoal());
        assertFalse(state2.isGoal());
        assertTrue(state3.isGoal());
    }

    @Test
    public void testCanMove() {
        assertTrue(state1.canMove(4, Direction.Right));
        assertTrue(state1.canMove(1, Direction.DiagonalRightDown));
        assertFalse(state1.canMove(0, Direction.DiagonalLeftUp));
        assertFalse(state1.canMove(2, Direction.Up));
        assertFalse(state1.canMove(3, Direction.Left));
    }

    @Test
    public void testCanMoveUp() {
        assertFalse(state1.canMoveUp(0));
        assertFalse(state1.canMoveUp(1));
        assertFalse(state1.canMoveUp(2));
        assertFalse(state1.canMoveUp(3));
        assertFalse(state1.canMoveUp(4));
    }

    @Test
    public void testCanMoveDown() {
        assertFalse(state1.canMoveDown(0));
        assertFalse(state1.canMoveDown(1));
        assertFalse(state1.canMoveDown(2));
        assertFalse(state1.canMoveDown(3));
        assertFalse(state1.canMoveDown(4));
    }

    @Test
    public void testCanMoveLeft() {
        assertFalse(state1.canMoveLeft(0));
        assertFalse(state1.canMoveLeft(1));
        assertFalse(state1.canMoveLeft(2));
        assertFalse(state1.canMoveLeft(3));
        assertFalse(state1.canMoveLeft(4));
    }

    @Test
    public void testCanMoveRight() {
        assertFalse(state1.canMoveRight(0));
        assertFalse(state1.canMoveRight(1));
        assertFalse(state1.canMoveRight(2));
        assertFalse(state1.canMoveRight(3));
        assertTrue(state1.canMoveRight(4));
    }
    @Test
    public void testCanMoveDiagonalLeftDown() {
        assertFalse(state1.canMoveDiagonalLeftDown(0));
        assertFalse(state1.canMoveDiagonalLeftDown(1));
        assertFalse(state1.canMoveDiagonalLeftDown(2));
        assertFalse(state1.canMoveDiagonalLeftDown(3));
        assertFalse(state1.canMoveDiagonalLeftDown(4));
    }
    @Test
    public void testCanMoveDiagonalRightUp() {
        assertFalse(state1.canMoveDiagonalRightUp(0));
        assertFalse(state1.canMoveDiagonalRightUp(1));
        assertFalse(state1.canMoveDiagonalRightUp(2));
        assertFalse(state1.canMoveDiagonalRightUp(3));
        assertFalse(state1.canMoveDiagonalRightUp(4));
    }
    @Test
    public void testCanMoveDiagonalLeftup() {
        assertFalse(state1.canMoveDiagonalRightUp(0));
        assertFalse(state1.canMoveDiagonalRightUp(1));
        assertFalse(state1.canMoveDiagonalRightUp(2));
        assertFalse(state1.canMoveDiagonalRightUp(3));
        assertFalse(state1.canMoveDiagonalRightUp(4));
    }
    @Test
    public void testCanMoveDiagonalRightDown() {
        assertFalse(state1.canMoveDiagonalRightDown(0));
        assertTrue(state1.canMoveDiagonalRightDown(1));
        assertFalse(state1.canMoveDiagonalRightDown(2));
        assertFalse(state1.canMoveDiagonalRightDown(3));
        assertFalse(state1.canMoveDiagonalRightDown(4));
    }
    @Test
    public void testMoveRight() {
        state1.move(4, Direction.Right);
        int row = state1.positions[4].getRow();
        int col = state1.positions[4].getCol();
        assertEquals(1,row);
        assertEquals(2,col);
    }
    @Test
    public void testMoveDown() {
        state1.move(0, Direction.Down);
        int row = state1.positions[0].getRow();
        int col = state1.positions[0].getCol();
        assertEquals(1,row);
        assertEquals(0,col);
    }
    @Test
    public void testMoveUp() {
        state1.move(4, Direction.Up);
        int row = state1.positions[4].getRow();
        int col = state1.positions[4].getCol();
        assertEquals(0,row);
        assertEquals(1,col);
    }
    @Test
    public void testMoveLeft() {
        state1.move(4, Direction.Left);
        int row = state1.positions[4].getRow();
        int col = state1.positions[4].getCol();
        assertEquals(1,row);
        assertEquals(0,col);
    }
    @Test
    public void testMoveDiagonalLeftUp() {
        state2.move(1, Direction.DiagonalLeftUp);
        int row = state1.positions[1].getRow();
        int col = state1.positions[1].getCol();
        assertEquals(0,row);
        assertEquals(1,col);
    }
    @Test
    public void testMoveDiagonalLeftDown() {
        state1.move(2, Direction.DiagonalLeftDown);
        int row = state1.positions[2].getRow();
        int col = state1.positions[2].getCol();
        assertEquals(1,row);
        assertEquals(1,col);
    }
    @Test
    public void testMoveDigonalRightUp() {
        state1.move(4, Direction.Right);
        int row = state1.positions[4].getRow();
        int col = state1.positions[4].getCol();
        assertEquals(1,row);
        assertEquals(2,col);
    }
    @Test
    public void testMoveDigonalRightDown() {
        state1.move(2, Direction.DiagonalRightDown);
        int row = state1.positions[2].getRow();
        int col = state1.positions[2].getCol();
        assertEquals(1,row);
        assertEquals(3,col);
    }

    @Test
    public void testGetPiece(){
        String piece = state1.getPiece(0,0);
        assertEquals("King", piece);
    }
}