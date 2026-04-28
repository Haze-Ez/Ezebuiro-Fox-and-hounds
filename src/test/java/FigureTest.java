import foxandthehounds.game_logic.Figure;
import foxandthehounds.game_logic.Fox;
import foxandthehounds.game_logic.Hound;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FigureTest {


        @Test
        void testFigurePosition() {
            Figure fox = new Fox(7, 0);
            assertEquals(7, fox.getRow());
            assertEquals(0, fox.getCol());

            fox.setPosition(6, 1);
            assertEquals(6, fox.getRow());
            assertEquals(1, fox.getCol());
        }

    @Test
    void testHoundPosition() {
        Hound hound = new Hound(0, 1);
        assertEquals(0, hound.getRow());
        assertEquals(1, hound.getCol());

        hound.setPosition(1, 2);
        assertEquals(1, hound.getRow());
        assertEquals(2, hound.getCol());
    }
    @Test
    void testSetRowForFox() {
        Fox fox = new Fox(7, 0);
        fox.setRow(6);
        assertEquals(6, fox.getRow());
    }

    @Test
    void testSetColForFox() {
        Fox fox = new Fox(7, 0);
        fox.setCol(1);
        assertEquals(1, fox.getCol());
    }

    @Test
    void testSetRowForHound() {
        Hound hound = new Hound(0, 1);
        hound.setRow(2);
        assertEquals(2, hound.getRow());
    }

    @Test
    void testSetColForHound() {
        Hound hound = new Hound(0, 1);
        hound.setCol(3);
        assertEquals(3, hound.getCol());
    }
    }

