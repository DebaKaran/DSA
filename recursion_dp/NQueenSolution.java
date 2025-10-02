import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueenSolution {
    // Leetcode Problem: 51
    // https://leetcode.com/problems/n-queens/description/

    public List<List<String>> solveNQueens(int n) {
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
    }

}
