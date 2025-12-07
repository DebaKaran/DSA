package stack_queue.monotonic;

import java.util.ArrayList;
import java.util.Collections;

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
    public static ArrayList<Integer> nextGreaterElements(int[] arr) {
        return computeNextGreaterBruteForce(arr);
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

