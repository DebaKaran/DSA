public class SudukoSolver {
    //Leetcode 37. Sudoku Solver
    //https://leetcode.com/problems/sudoku-solver/description/

    public void solveSudoku(char[][] board) {
        // Start recursive backtracking
        solve(board);
    }
    // Time Complexity:

    // In the worst case, the algorithm tries all possibilities for all cells:
    // O(9^(N×N)) where N=9 → O(9^81).

    // With pruning (isSafe), it’s much faster in practice and solves standard Sudoku efficiently.

    // Space Complexity:

    // O(N²) recursion depth in the worst case (N=9, so effectively constant).

    // Board is modified in-place → no extra space apart from recursion stack.
    private boolean solve(char[][] board) {
        // Traverse the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // If current cell is empty
                if (board[row][col] == '.') {
                    // Try placing digits 1 to 9
                    for (char i = '1'; i <= '9'; i++) {
                        if (isSafe(board, row, col, i)) {
                            // Place digit tentatively
                            board[row][col] = i;

                            // Recurse to next cell
                            if (solve(board)) {
                                return true; // solved
                            } else {
                                // Backtrack if placing `i` leads to dead end
                                board[row][col] = '.';
                            }
                        }
                    }
                    // If no digit 1–9 works, backtrack
                    return false;
                }
            }
        }
        // Board completely filled → solution found
        return true;
    }

    private boolean isSafe(char[][] board, int row, int col, char val) {
        // Check the row
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;

        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }

        // Safe to place value
        return true;
    }

}
