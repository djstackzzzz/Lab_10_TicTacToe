import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean keepPlaying;
        String currentPlayer;
        int moveCount;

        do {
            clearBoard(); // Reset board
            currentPlayer = "X"; // X always starts
            moveCount = 0;

            boolean gameOver = false;
            while (!gameOver) {
                display(); // Show current board

                // Get move input
                int row = SafeInput.getRangedInt(in, currentPlayer + " - Enter row [1-3]: ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(in, currentPlayer + " - Enter column [1-3]: ", 1, 3) - 1;

                // Check for valid move
                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Try again.");
                    row = SafeInput.getRangedInt(in, currentPlayer + " - Enter row [1-3]: ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, currentPlayer + " - Enter column [1-3]: ", 1, 3) - 1;
                }

                board[row][col] = currentPlayer;
                moveCount++;

                // Check for win or tie
                if (moveCount >= 5) {
                    if (isWin(currentPlayer)) {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameOver = true;
                    } else if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        gameOver = true;
                    }
                }

                // Switch players
                if (!gameOver) {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                }
            }

            // Ask if they want to play again
            keepPlaying = SafeInput.getYNConfirm(in, "Play again?");
        } while (keepPlaying);

        System.out.println("Thanks for playing!");
    }

    // ===== Helper Methods =====

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("\nCurrent Board:");
        for (int row = 0; row < ROWS; row++) {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
