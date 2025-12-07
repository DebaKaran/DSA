package stack_queue.monotonic;

import java.util.Arrays;

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
        return computeNextGreaterBruteForce(nums);
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
