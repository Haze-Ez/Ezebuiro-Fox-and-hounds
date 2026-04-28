
import foxandthehounds.game_logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    private Table table;
    private Figure fox;
    private Figure[] hounds;

    @BeforeEach
    void setUp() {
        table = new Table();
        table.setSIZE(8);
        fox = new Figure(7, 0) {};
        hounds = new Figure[]{
                new Figure(0, 1) {},
                new Figure(0, 3) {},
                new Figure(0, 5) {},
                new Figure(0, 7) {}
        };
        table.initializePieces(fox, hounds);
    }

    @Test
    void testSetSIZE() {
        table.setSIZE(6);
        assertEquals(6, table.getSIZE());
    }

    @Test
    void testInitializePieces() {
        table.initializePieces(fox, hounds);
        assertEquals('F', table.getBoard()[7][0]);
        assertEquals('H', table.getBoard()[0][1]);
    }

    @Test
    void testUpdatePiecePosition() {
        table.updatePiecePosition(7, 0, 6, 1, 'F');
        assertEquals('.', table.getBoard()[7][0]);
        assertEquals('F', table.getBoard()[6][1]);
    }

    @Test
    void testIsValidMove() {
        assertTrue(table.isValidMove(7, 0, 6, 1, hounds, false)); // Valid move
        assertFalse(table.isValidMove(7, 0, 8, 1, hounds, false)); // Out of bounds
        assertFalse(table.isValidMove(0, 1, 0, 3, hounds, true)); // Invalid hound move
    }
}
