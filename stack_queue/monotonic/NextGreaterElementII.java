package stack_queue.monotonic;

import java.util.Arrays;
import java.util.Stack;

/**
 * LeetCode 503 — Next Greater Element II (brute-force circular implementation).
 *
 * For each element nums[i], find the first greater element to its right
 * in the circular array. If none exists, result[i] == -1.
 */
public final class NextGreaterElementII {

    private NextGreaterElementII() { /* utility class */ }

    /**
     * Public API.
     */
    public int[] nextGreaterElements(int[] nums) {
        //return computeNextGreaterBruteForce(nums);
        return computeNextGreaterUsingStack(nums);
    }

     /**
     * Two-pass solution using a monotonic decreasing stack.
     *
     * Pass 1 (right→left): prepare stack with future candidates (simulates circularity).
     * Pass 2 (right→left): resolve next greater element for each index.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    private int[] computeNextGreaterUsingStack(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);  // default when no greater exists

        Stack<Integer> stack = new Stack<>();

        // Pass 1: preload stack with decreasing elements to simulate circular array
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }

        // Pass 2: actual next-greater resolution for each index
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }

            // Push current element for future matches
            stack.push(nums[i]);
        }

        return result;
    }
    /**
     * Brute-force circular scan:
     * For each index i, scan the next n-1 positions (wrapping via modulo)
     * until a greater element is found or all elements checked.
     *
     * Time: O(n^2)
     * Space: O(n) for the result array
     */
    private int[] computeNextGreaterBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        final int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // default when no greater exists

        // For each position, scan up to n-1 subsequent positions (circularly)
        for (int i = 0; i < n; i++) {
            int current = nums[i];

            // check each candidate to the right, wrapping with modulo
            for (int step = 1; step < n; step++) {
                int idx = (i + step) % n;
                if (nums[idx] > current) {
                    result[i] = nums[idx];
                    break; // stop at first greater element
                }
            }
        }

        return result;
    }
}
