package stack_queue.monotonic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * GFG — Next Greater Element (brute-force reference implementation).
 *
 * This class provides a correct, easy-to-read O(n^2) implementation.
 * Use this as a baseline; see optimizations below for faster approaches.
 */
public final class NextGreaterElementFinder {

    private NextGreaterElementFinder() { /* utility class */ }

    /**
     * Public API — return list of next greater elements for each position.
     * If no greater element exists to the right, the value is -1.
     */
    public ArrayList<Integer> nextLargerElement(int[] arr) {
       
        //return computeNextGreaterBruteForce(arr);
        return nextGreaterElementsUsingStack(arr);
    }
    
    // Optimized stack-based implementation.
    // Time: O(n) — each element pushed and popped at most once.
    // Space: O(n) — result list and stack can each hold up to n elements
    
    private static ArrayList<Integer> nextGreaterElementsUsingStack(int[] arr) {
        if (arr == null || arr.length == 0) return new ArrayList<>();

        final int n = arr.length;
        // initialize result with -1 (default when no next greater exists)
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, -1));

        // stack stores candidate values in strictly decreasing order (top = closest greater on right)
        Stack<Integer> stack = new Stack<>();

        // scan from right to left so stack top is the next greater candidate
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];

            // pop smaller-or-equal values — they can't be next greater for current or any element to the left
            while (!stack.isEmpty() && current >= stack.peek()) {
                stack.pop();
            }

            // if a greater value remains on stack, that's the next greater for index i
            if (!stack.isEmpty()) {
                result.set(i, stack.peek());
            } // else result[i] remains -1

            // push current as a candidate for elements to the left
            stack.push(current);
        }

        return result;
    }

    /**
     * Brute-force implementation.
     *
     * Time: O(n^2) — nested loops scanning to the right for each element.
     * Space: O(n) — result list of size n.
     * 5 test cases failed due to timeouts on large inputs.
     */
    private static ArrayList<Integer> computeNextGreaterBruteForce(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }

        final int n = arr.length;
        // Initialize result with -1 for all positions.
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, -1));

        // For each index i, scan to the right to find first element > arr[i].
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    result.set(i, arr[j]); // first larger element to the right
                    break;                 // stop scanning once found
                }
            }
        }

        return result;
    }
}

