import state.ChessState;
import state.Direction;
import state.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    ChessState state1 = new ChessState(); // initial state

    ChessState state2 = new ChessState(new Position[]{
            new Position(0, 1),
            new Position(0, 2),
            new Position(2, 2),
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
}