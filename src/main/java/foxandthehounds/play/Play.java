package foxandthehounds.play;

import foxandthehounds.game_logic.Game;
import foxandthehounds.game_logic.Table;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Play {
    public static void main(String[] args) {

        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.SEVERE);
    Scanner sc = new Scanner(System.in);
    boolean validTableSize = false;
    int tableSize = 0;

        // Keep asking for a valid table size until it is correct
        while (!validTableSize) {
            System.out.println("Enter a table size (4-8):");
            try {
                tableSize = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (tableSize < 4 || tableSize > 8) {
                    throw new IllegalArgumentException("Table size must be between 4 and 8.");
                }
                validTableSize = true;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 4 and 8.");

            }
        }


            Game game = new Game(tableSize);

            while (true) {
                System.out.println("Current board:");
                game.displayTable();

                System.out.println("Fox's turn: Enter new row and column (e.g., q,e,a,d):");
                System.out.println("q(up left), e(up right), a(down left), d(down right):");

                game.moveFox(sc.nextLine().charAt(0));
                System.out.println("Current board:");
                game.displayTable();

                if (game.isGameOver()) {
                    break;
                }

                // Hounds' turn
                int houndIndex = 0;
                char houndDirection = ' ';
                boolean validInput = false;

                while (!validInput) {
                    try {
                        System.out.println("Hounds' turn: Enter hound index (0-3) and direction (a/d):");
                        String input = sc.nextLine();
                        String[] parts = input.split(" ");
                        houndIndex = Integer.parseInt(parts[0]);
                        houndDirection = parts[1].charAt(0);
                        validInput = true;
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter in the format: <hound_index> <direction>");
                    }
                }

                game.moveHound(houndIndex, houndDirection);
//            game.displayTable(); // Display board after hound's move
            }

            System.out.println("Game over. Thanks for playing!");

        }
    }

