import static org.junit.Assert.*;

import state.Position;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

    private Position position;

    @Before
    public void setUp() {
        position = new Position(1, 1);
    }

    @Test
    public void testGetRow() {
        assertEquals(1, position.getRow());
    }

    @Test
    public void testGetCol() {
        assertEquals(1, position.getCol());
    }

    @Test
    public void testGetUp() {
        Position newPosition = position.getUp();
        assertEquals(new Position(0, 1), newPosition);
    }

    @Test
    public void testGetDown() {
        Position newPosition = position.getDown();
        assertEquals(new Position(2, 1), newPosition);
    }

    @Test
    public void testGetLeft() {
        Position newPosition = position.getLeft();
        assertEquals(new Position(1, 0), newPosition);
    }

    @Test
    public void testGetRight() {
        Position newPosition = position.getRight();
        assertEquals(new Position(1, 2), newPosition);
    }

    @Test
    public void testGetDiagonalLeftUp() {
        Position newPosition = position.getDiagonalLeftUp();
        assertEquals(new Position(0, 0), newPosition);
    }

    @Test
    public void testGetDiagonalRightUp() {
        Position newPosition = position.getDiagonalRightUp();
        assertEquals(new Position(0, 2), newPosition);
    }

    @Test
    public void testGetDiagonalLeftDown() {
        Position newPosition = position.getDiagonalLeftDown();
        assertEquals(new Position(2, 0), newPosition);
    }

    @Test
    public void testGetDiagonalRightDown() {
        Position newPosition = position.getDiagonalRightDown();
        assertEquals(new Position(2, 2), newPosition);
    }

    @Test
    public void testSetUp() {
        position.setUp();
        assertEquals(new Position(0, 1), position);
    }

    @Test
    public void testSetDown() {
        position.setDown();
        assertEquals(new Position(2, 1), position);
    }

    @Test
    public void testSetLeft() {
        position.setLeft();
        assertEquals(new Position(1, 0), position);
    }

    @Test
    public void testSetRight() {
        position.setRight();
        assertEquals(new Position(1, 2), position);
    }

    @Test
    public void testSetDiagonalLeftUp() {
        position.setDiagonalLeftUp();
        assertEquals(new Position(0, 0), position);
    }

    @Test
    public void testSetDiagonalRightUp() {
        position.setDiagonalRightUp();
        assertEquals(new Position(0, 2), position);
    }

    @Test
    public void testSetDiagonalLeftDown() {
        position.setDiagonalLeftDown();
        assertEquals(new Position(2, 0), position);
    }

    @Test
    public void testSetDiagonalRightDown() {
        position.setDiagonalRightDown();
        assertEquals(new Position(2, 2), position);
    }
}

