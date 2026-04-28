package foxandthehounds.game_logic;

import java.util.logging.Logger;

public class Game {
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    private final Table table;
    private final Fox fox;
    private final Hound[] hounds;

    public Game(int size) {
        logger.info("Initializing game with board size: " + size);
        table = new Table();
        table.setSIZE(size);

        fox = new Fox(size - 1, 0); // Fox starts at the bottom-left
        logger.info("Fox initialized at position (" + fox.getRow() + ", " + fox.getCol() + ")");

        // Dynamically calculate the hound positions
        int numberOfHounds = size / 2; // Number of hounds based on the board size
        hounds = new Hound[numberOfHounds];
        for (int i = 0; i < numberOfHounds; i++) {
            hounds[i] = new Hound(0, (2 * i) + 1); // Place hounds at every other column
            logger.info("Hound " + i + " initialized at position (" + hounds[i].getRow() + ", " + hounds[i].getCol() + ")");
        }

        table.initializePieces(fox, hounds); // Place initial pieces
        logger.info("Game setup complete.");
    }

    public void moveFox(char direction) {
        int oldRow = fox.getRow();
        int oldCol = fox.getCol();
        int newRow = oldRow;
        int newCol = oldCol;

        logger.info("Fox attempting to move from (" + oldRow + ", " + oldCol + ") with direction '" + direction + "'");

        switch (direction) {
            case 'q': newRow--; newCol--; break; // Up-left
            case 'e': newRow--; newCol++; break; // Up-right
            case 'a': newRow++; newCol--; break; // Down-left
            case 'd': newRow++; newCol++; break; // Down-right
            default:
                logger.warning("Invalid direction for fox: " + direction);
                System.out.println("Invalid direction. Use q, e, a, or d.");
                return;
        }

        if (table.isValidMove(oldRow, oldCol, newRow, newCol, hounds, false)) {
            fox.setPosition(newRow, newCol);
            table.updatePiecePosition(oldRow, oldCol, newRow, newCol, 'F');
            logger.info("Fox moved to (" + newRow + ", " + newCol + ")");
        } else {
            logger.warning("Invalid move for the fox to (" + newRow + ", " + newCol + ")");
            System.out.println("Invalid move for the fox!");
        }
    }

    public void moveHound(int houndIndex, char direction) {
        if (houndIndex < 0 || houndIndex >= hounds.length) {
            logger.warning("Invalid hound index: " + houndIndex);
            System.out.println("Invalid hound index.");
            return;
        }

        Hound hound = hounds[houndIndex];
        int oldRow = hound.getRow();
        int oldCol = hound.getCol();
        int newRow = oldRow;
        int newCol = oldCol;

        logger.info("Hound " + houndIndex + " attempting to move from (" + oldRow + ", " + oldCol + ") with direction '" + direction + "'");

        switch (direction) {
            case 'a': newRow++; newCol--; break; // Down-left
            case 'd': newRow++; newCol++; break; // Down-right
            default:
                logger.warning("Invalid direction for hound: " + direction);
                System.out.println("Invalid direction. Use a or d.");
                return;
        }

        if (table.isValidMove(oldRow, oldCol, newRow, newCol, hounds, true)) {
            hound.setPosition(newRow, newCol);
            table.updatePiecePosition(oldRow, oldCol, newRow, newCol, 'H');
            logger.info("Hound " + houndIndex + " moved to (" + newRow + ", " + newCol + ")");
        } else {
            logger.warning("Invalid move for hound " + houndIndex + " to (" + newRow + ", " + newCol + ")");
            System.out.println("Invalid move for the hound!");
        }
    }

    private boolean isFoxTrapped() {
        int foxRow = fox.getRow();
        int foxCol = fox.getCol();
        logger.info("Checking if fox is trapped at position (" + foxRow + ", " + foxCol + ")");

        int[][] directions = {
                {-1, -1}, // Up-left
                {-1, 1},  // Up-right
                {1, -1},  // Down-left
                {1, 1}    // Down-right
        };

        for (int[] direction : directions) {
            int newRow = foxRow + direction[0];
            int newCol = foxCol + direction[1];
            boolean validMove = table.isValidMove(foxRow, foxCol, newRow, newCol, hounds, false);
            logger.fine("Checking move to (" + newRow + ", " + newCol + "): " + (validMove ? "Valid" : "Invalid"));

            if (validMove) {
                logger.info("Fox is not trapped. Valid move found to (" + newRow + ", " + newCol + ")");
                return false; // At least one valid move exists
            }
        }
        logger.info("Fox is trapped. No valid moves.");
        return true; // No valid moves left
    }

    public boolean isGameOver() {
        if (fox.getRow() == 0) {
            logger.info("Game over: Fox wins by reaching the top row.");
            System.out.println("Congratulations! The Fox wins!");
            return true;
        }
        if (isFoxTrapped()) {
            logger.info("Game over: Hounds win by trapping the fox.");
            System.out.println("The Hounds win! The Fox is trapped!");
            return true;
        }
        return false;
    }

    public Fox getFox() {
        logger.fine("Getting fox details.");
        return fox;
    }

    public Hound[] getHounds() {
        logger.fine("Getting hound details.");
        return hounds;
    }

    public void displayTable() {
        logger.info("Displaying the current table state.");
        table.display();
    }
}
