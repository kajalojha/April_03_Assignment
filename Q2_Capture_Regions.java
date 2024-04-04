package April_03_Assignment;
import java.util.Scanner;
public class Q2_Capture_Regions {
    public static void captureRegions(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        // Capture regions connected to borders
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][cols - 1] == 'O') {
                dfs(board, i, cols - 1);
            }
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[rows - 1][j] == 'O') {
                dfs(board, rows - 1, j);
            }
        }

        // Capture remaining 'O's and restore captured regions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // Capture
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O'; // Restore
                }
            }
        }
    }

    private static void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') {
            return;
        }

        board[row][col] = '#'; // Mark as visited/captured

        // Explore neighboring cells
        dfs(board, row + 1, col);
        dfs(board, row - 1, col);
        dfs(board, row, col + 1);
        dfs(board, row, col - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of columns:");
        int cols = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        char[][] board = new char[rows][cols];
        System.out.println("Enter the elements of the matrix (X or O):");
        for (int i = 0; i < rows; i++) {
            String rowString = scanner.nextLine();
            for (int j = 0; j < cols; j++) {
                board[i][j] = rowString.charAt(j);
            }
        }

        captureRegions(board);

        System.out.println("Updated matrix:");
        // Print the matrix in the desired format
        System.out.println("[");
        for (int i = 0; i < rows; i++) {
            System.out.print("  [");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
                if (j < cols - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println("]");
    }
}
