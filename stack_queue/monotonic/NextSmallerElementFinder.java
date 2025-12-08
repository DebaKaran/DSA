package stack_queue.monotonic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * GFG — Next Smaller Element
 *
 * Finds the first smaller element to the right of each element in the array.
 * If none exists, result[i] = -1.
 */
public final class NextSmallerElementFinder {

    private NextSmallerElementFinder() { /* utility class */ }

    /**
     * Public API.
     */
    public static ArrayList<Integer> nextSmallerElement(int[] arr) {
        return computeNextSmallerUsingStack(arr);
    }

    /**
     * Monotonic increasing stack solution.
     *
     * Scan from right to left. Maintain a stack of candidate elements that are smaller.
     * For each element:
     *   - Pop all stack elements >= current (they can't be the "next smaller").
     *   - The new stack top, if present, is the next smaller value.
     *   - Push current value as a candidate for elements to the left.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    private static ArrayList<Integer> computeNextSmallerUsingStack(int[] arr) {
        if (arr == null || arr.length == 0) return new ArrayList<>();

        final int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, -1));
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];

            // Remove elements that are >= current since they cannot be next smaller
            while (!stack.isEmpty() && stack.peek() >= current) {
                stack.pop();
            }

            // If stack still has elements, the top is the next smaller
            if (!stack.isEmpty()) {
                result.set(i, stack.peek());
            }

            // Push current as possible next-smaller candidate for future elements
            stack.push(current);
        }

        return result;
    }
}

