package stack_queue;

import java.util.Stack;

/**
 * LeetCode 84 — Largest Rectangle in Histogram
 *
 * Contribution approach using monotonic increasing stack of indices:
 * - prevLess[i] = index of the previous smaller bar (strictly smaller), or -1
 * - nextLess[i] = index of the next smaller bar (strictly smaller), or n
 * Area for bar i = heights[i] * (nextLess[i] - prevLess[i] - 1)
 *
 * This implementation uses pop-on <= to ensure each equal-height block
 * is handled consistently (no double counting).
 */
public final class LargestRectangleHistogram {

    private LargestRectangleHistogram() { /* utility */ }

    /** Public API */
    public int largestRectangleArea(int[] heights) {
        return largestRectangleAreaUsingStacks(heights);
    }

    /**
     * Two-pass monotonic stack solution:
     * - Build prevLess and nextLess arrays (indices)
     * - Compute max area across all bars
     *
     * Time:  O(n) — each index is pushed/popped at most once
     * Space: O(n) — prev/next arrays + stack
     */
    private int largestRectangleAreaUsingStacks(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        final int n = heights.length;
        if (n == 1) return heights[0];

        int[] prevLess = previousSmallerElements(heights); // index of prev smaller or -1
        int[] nextLess = nextSmallerElements(heights);     // index of next smaller or n

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nextLess[i] - prevLess[i] - 1;
            int area = heights[i] * width;
            if (area > maxArea) maxArea = area;
        }
        return maxArea;
    }

    /**
     * nextSmallerElements: for each i, index of first j > i where heights[j] < heights[i].
     * If none found, n is used as sentinel.
     *
     * Pop condition uses <= to enforce that equal heights to the right are popped,
     * making the next smaller strictly smaller. This choice pairs with previousSmallerElements
     * (which also uses <= in pop) to avoid double counting equal bars.
     */
    private static int[] nextSmallerElements(int[] heights) {
        int n = heights.length;
        int[] next = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            int h = heights[i];
            while (!st.isEmpty() && h <= heights[st.peek()]) {
                st.pop();
            }
            next[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return next;
    }

    /**
     * previousSmallerElements: for each i, index of last j < i where heights[j] < heights[i].
     * If none found, -1 is used as sentinel.
     *
     * Pop condition uses <= to make previous smaller strictly smaller.
     */
    private static int[] previousSmallerElements(int[] heights) {
        int n = heights.length;
        int[] prev = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (!st.isEmpty() && h <= heights[st.peek()]) {
                st.pop();
            }
            prev[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return prev;
    }
}

