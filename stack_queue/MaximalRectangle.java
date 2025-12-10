package stack_queue;

import java.util.Stack;

/**
 * LeetCode 85 — Maximal Rectangle
 *
 * For each row, build a histogram of consecutive '1's ending at that row,
 * then reuse the single-pass Largest-Rectangle-In-Histogram algorithm.
 */
public final class MaximalRectangle {

    private MaximalRectangle() { /* utility */ }

    /** Public API */
    public int maximalRectangle(char[][] matrix) {
        return maximalRectangleUsingHistogramPerRow(matrix);
    }

    /**
     * Convert each row to a histogram (heights of consecutive '1's per column),
     * and compute the largest rectangle for each histogram row.
     *
     * Time:  O(rows * cols) — update histogram O(cols) per row + O(cols) histogram scan
     * Space: O(rows * cols) if using a full prefix matrix (as below)
     *        (see optimizations for O(cols) variant)
     * 
     * Based on Leetcode 84
     */
    public int maximalRectangleUsingHistogramPerRow(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        if (matrix[0] == null || matrix[0].length == 0) return 0;

        // Build the per-row histogram matrix (rows x cols) where histogram[r][c]
        // is the number of consecutive '1's ending at row r in column c.
        int[][] histogramMatrix = calculateHistogramMatrix(matrix);

        int max = 0;
        for (int row = 0; row < histogramMatrix.length; row++) {
            int area = computeLargestRectangleSinglePass(histogramMatrix[row]);
            if (area > max) max = area;
        }
        return max;
    }

    /**
     * Build the histogram matrix:
     * histogram[r][c] = (matrix[r][c] == '1') ? histogram[r-1][c] + 1 : 0
     *
     * Note: this mirrors your original prefix-sum intent but stores heights directly.
     */
    private int[][] calculateHistogramMatrix(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] histogram = new int[rows][cols];

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (matrix[r][c] == '1') {
                    histogram[r][c] = (r == 0) ? 1 : (histogram[r - 1][c] + 1);
                } else {
                    histogram[r][c] = 0;
                }
            }
        }
        return histogram;
    }

    /**
     * Single-pass largest-rectangle-in-histogram (LeetCode 84).
     * Stack stores indices of columns with increasing heights.
     *
     * Time: O(cols) for each row.
     * Space: O(cols) for the stack.
     */
    private int computeLargestRectangleSinglePass(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int topIndex = stack.pop();
                int rightBoundary = i;
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int width = rightBoundary - leftBoundary - 1;
                int area = heights[topIndex] * width;
                if (area > maxArea) maxArea = area;
            }
            stack.push(i);
        }

        // Clean up remaining bars
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            int rightBoundary = n;
            int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
            int width = rightBoundary - leftBoundary - 1;
            int area = heights[topIndex] * width;
            if (area > maxArea) maxArea = area;
        }

        return maxArea;
    }
}

