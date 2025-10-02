import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueenSolution {
    // Leetcode Problem: 51
    // https://leetcode.com/problems/n-queens/description/

    /** public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize board with '.' (empty cells)
        for(int row = 0; row < n; row++) {
            Arrays.fill(board[row], '.');
        }
        
        // Start solving from column 0
        solveNQueens(0, n, board, result);
        return result;
    }
    
    // 1: Time Complexity

    // Worst-case: O(N!) — each queen placement branches into multiple valid rows recursively.

    // Each isSafe() call scans at most O(N) cells for row + diagonals.

    // So total complexity: O(N!∗N)

    // 2: Space Complexity

    // Board storage: O(N ^ 2)

    // Recursion stack: O(N)

    // Result storage: O(N∗N!) in the worst case (all solutions)

    private void solveNQueens(int col, int n, char[][] board, List<List<String>> result) {
        // Base case: all queens are placed → add current board to result
        if(col == n) {
            List<String> list = new ArrayList<>();
            for(char[] row: board) {
                StringBuilder sb = new StringBuilder();
                for(char ch : row) {
                    sb.append(ch);
                }
                list.add(sb.toString()); // convert char[] row to String
            }
            result.add(list);
            return;
        }

        // Try placing queen in every row of this column
        for(int row = 0; row < n; row++) {
            if(isSafe(row, col, board)) {
                // Place queen
                board[row][col] = 'Q';
                // Recurse for next column
                solveNQueens(col + 1, n, board, result);
                // Backtrack: remove queen
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(int row, int col, char[][] board) {
        // Check this row → if a queen already exists, return false
        for(char ch : board[row]) {
            if(ch == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        int rowDup = row;
        int colDup = col;
        while(rowDup >= 0 && colDup >= 0) {
            if(board[rowDup][colDup] == 'Q') return false;
            colDup--;
            rowDup--;
        }

        // Check lower-left diagonal
        rowDup = row;
        colDup = col;
        while(rowDup < board.length && colDup >= 0) {
            if(board[rowDup][colDup] == 'Q') return false;
            colDup--;
            rowDup++;
        }

        // If no conflicts found, it's safe to place a queen
        return true;
    } */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize board with empty cells '.'
        for (int row = 0; row < n; row++) {
            Arrays.fill(board[row], '.');
        }

        // Track which rows already have a queen
        boolean[] rowsOccupied = new boolean[n];

        // Track main diagonals (row + col)
        boolean[] mainDiagonal = new boolean[2 * n - 1];

        // Track anti-diagonals (row - col + n - 1)
        boolean[] antiDiagonal = new boolean[2 * n - 1];

        // Start backtracking from column 0
        solveNQueens(0, n, rowsOccupied, mainDiagonal, antiDiagonal, board, result);

        return result;
    }
    // Time Complexity: O(N!)

    // Each column places a queen in N possible rows, recursive depth = N → factorial growth.

    // Space Complexity: O(N²) for board + O(N) for rowsOccupied + O(2N-1) for each diagonal array → overall O(N²).

    // Recursion stack depth = N → O(N) additional space.
    private void solveNQueens(int col, int n, boolean[] rowsOccupied,
                              boolean[] mainDiagonal, boolean[] antiDiagonal,
                              char[][] board, List<List<String>> result) {

        // Base case: all columns are filled, add board to result
        if (col == n) {
            List<String> permutation = new ArrayList<>();
            for (char[] row : board) {
                StringBuilder sb = new StringBuilder();
                for (char ch : row) {
                    sb.append(ch);
                }
                permutation.add(sb.toString());
            }
            result.add(permutation);
            return;
        }

        // Try placing a queen in every row for this column
        for (int row = 0; row < n; row++) {
            // Check if the row and diagonals are free
            if (!rowsOccupied[row] && !mainDiagonal[row + col] && !antiDiagonal[row - col + n - 1]) {

                // Place queen
                board[row][col] = 'Q';
                rowsOccupied[row] = true;
                mainDiagonal[row + col] = true;
                antiDiagonal[row - col + n - 1] = true;

                // Recurse to next column
                solveNQueens(col + 1, n, rowsOccupied, mainDiagonal, antiDiagonal, board, result);

                // Backtrack: remove queen and mark arrays
                board[row][col] = '.';
                rowsOccupied[row] = false;
                mainDiagonal[row + col] = false;
                antiDiagonal[row - col + n - 1] = false;
            }
        }
    }

}
