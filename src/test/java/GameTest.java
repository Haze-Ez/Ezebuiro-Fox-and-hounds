import foxandthehounds.game_logic.Game;
import foxandthehounds.game_logic.Hound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {


    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(8);
    }

    @Test
    void testFoxMoveUpLeft() {
        game.getFox().setPosition(4, 4); // Place fox in the middle of the board
        game.moveFox('q'); // Up-left
        assertEquals(3, game.getFox().getRow());
        assertEquals(3, game.getFox().getCol());
    }

    @Test
    void testFoxMoveUpRight() {
        game.getFox().setPosition(4, 4); // Place fox in the middle of the board
        game.moveFox('e'); // Up-right
        assertEquals(3, game.getFox().getRow());
        assertEquals(5, game.getFox().getCol());
    }

    @Test
    void testFoxMoveDownLeft() {
        game.getFox().setPosition(4, 4); // Place fox in the middle of the board
        game.moveFox('a'); // Down-left
        assertEquals(5, game.getFox().getRow());
        assertEquals(3, game.getFox().getCol());
    }

    @Test
    void testFoxMoveDownRight() {
        game.getFox().setPosition(4, 4); // Place fox in the middle of the board
        game.moveFox('d'); // Down-right
        assertEquals(5, game.getFox().getRow());
        assertEquals(5, game.getFox().getCol());
    }

    @Test
    void testHoundMoveDownLeft() {
        Hound hound = game.getHounds()[0];
        hound.setPosition(4, 4); // Place hound in the middle of the board
        game.moveHound(0, 'a'); // Down-left
        assertEquals(5, hound.getRow());
        assertEquals(3, hound.getCol());
    }

    @Test
    void testHoundMoveDownRight() {
        Hound hound = game.getHounds()[0];
        hound.setPosition(4, 4); // Place hound in the middle of the board
        game.moveHound(0, 'd'); // Down-right
        assertEquals(5, hound.getRow());
        assertEquals(5, hound.getCol());
    }


    @Test
    void testHoundInvalidMoveUpRight() {
        Hound hound = game.getHounds()[0];
        hound.setPosition(4, 4); // Place hound in the middle of the board
        game.moveHound(0, 'e'); // Up-right (invalid for hounds)
        assertEquals(4, hound.getRow()); // Should not change
        assertEquals(4, hound.getCol());
    }


    @Test
    void testHoundInvalidMoveUpLeft() {
        Hound hound = game.getHounds()[0];
        hound.setPosition(4, 4); // Place hound in the middle of the board
        game.moveHound(0, 'q'); // Up-left (invalid for hounds)
        assertEquals(4, hound.getRow()); // Should not change
        assertEquals(4, hound.getCol());
    }



    @Test
    void testIsGameOverFoxWins() {
        game.getFox().setPosition(0, 0);
        assertTrue(game.isGameOver());
    }


    @Test
    void testIsGameOverHoundsWin() {
        game.getFox().setPosition(2, 2); // Position fox at the center
        game.getHounds()[0].setPosition(1, 1); // Top-left diagonal
        game.getHounds()[1].setPosition(1, 3); // Top-right diagonal
        game.getHounds()[2].setPosition(3, 1); // Bottom-left diagonal
        game.getHounds()[3].setPosition(3, 3); // Bottom-right diagonal

        assertTrue(game.isGameOver(), "Hounds should win by trapping the fox.");
    }

}

