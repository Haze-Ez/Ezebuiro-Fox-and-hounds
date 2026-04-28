package foxandthehounds.game_logic;

import java.util.logging.Logger;

public class Table {

    private static final Logger logger = Logger.getLogger(Table.class.getName());
    private int SIZE;
    private char[][] board;

    public void setSIZE(int size) {
        logger.info("Setting board size to: " + size);
        this.SIZE = size;
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.'; // Initialize the board with empty cells
            }
        }
        logger.info("Board initialized with size " + size + "x" + size);
    }

    public char[][] getBoard() {
        logger.fine("Accessing board state.");
        return board;
    }

    public int getSIZE() {
        logger.fine("Getting board size: " + SIZE);
        return SIZE;
    }

    public void initializePieces(Figure fox, Figure[] hounds) {
        logger.info("Initializing pieces on the board.");
        board[fox.getRow()][fox.getCol()] = 'F'; // Place the fox
        logger.info("Fox placed at position (" + fox.getRow() + ", " + fox.getCol() + ")");

        for (int i = 0; i < hounds.length; i++) {
            Figure hound = hounds[i];
            board[hound.getRow()][hound.getCol()] = 'H'; // Place hounds
            logger.info("Hound " + i + " placed at position (" + hound.getRow() + ", " + hound.getCol() + ")");
        }
    }

    public void updatePiecePosition(int oldRow, int oldCol, int newRow, int newCol, char piece) {
        logger.info("Updating position for piece '" + piece + "' from (" + oldRow + ", " + oldCol + ") to (" + newRow + ", " + newCol + ")");
        board[oldRow][oldCol] = '.'; // Clear the old position
        board[newRow][newCol] = piece; // Place the piece in the new position
        logger.fine("Board updated.");
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, Figure[] hounds, boolean isHound) {
        logger.info("Validating move from (" + currentRow + ", " + currentCol + ") to (" + newRow + ", " + newCol + ")");
        if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE) {
            logger.warning("Move out of bounds.");
            return false; // Out of bounds
        }

        if (board[newRow][newCol] != '.') {
            logger.warning("Target cell (" + newRow + ", " + newCol + ") is not empty.");
            return false; // Target cell is not empty
        }

        // Additional constraint: Ensure no other figure occupies the target cell
        for (Figure hound : hounds) {
            if (hound.getRow() == newRow && hound.getCol() == newCol) {
                logger.warning("Target cell occupied by a hound.");
                return false; // Hound occupies the cell
            }
        }

        if (isHound && newRow <= currentRow) {
            logger.warning("Hounds cannot move backward.");
            return false; // Hounds cannot move backward
        }

        logger.info("Move validated as valid.");
        return Math.abs(currentRow - newRow) == 1 && Math.abs(currentCol - newCol) == 1; // Diagonal movement
    }

    public void display() {
        logger.info("Displaying current board state.");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        logger.fine("Board displayed successfully.");
    }
}
