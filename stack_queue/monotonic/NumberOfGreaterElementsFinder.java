package stack_queue.monotonic;

import java.util.Arrays;

/**
 * GFG — Number of greater elements to the right (brute-force solution).
 *
 * For each query index q in indices[], count how many elements arr[j] (j > q)
 * are strictly greater than arr[q].
 */
public final class NumberOfGreaterElementsFinder {

    private NumberOfGreaterElementsFinder() { /* utility class */ }

    /**
     * Public API.
     * Returns an array result[] where result[i] is the number of elements
     * greater than arr[ indices[i] ] to its right.
     */
    public static int[] countNGE(int[] arr, int[] indices) {
        return countNGEBruteForce(arr, indices);
    }

    /**
     * Brute-force implementation.
     *
     * For each query index:
     *   - Scan the elements strictly to the right.
     *   - Count arr[j] > arr[pos].
     *
     * Time:  O(q * n)  where q = indices.length, n = arr.length
     * Space: O(q) for result array.
     */
    private static int[] countNGEBruteForce(int[] arr, int[] indices) {
        if (arr == null || arr.length == 0 ||
            indices == null || indices.length == 0) {
            return new int[0];
        }

        int q = indices.length;
        int[] result = new int[q];
        int n = arr.length;

        for (int i = 0; i < q; i++) {
            int pos = indices[i];
            int count = 0;

            // Scan strictly to the right of pos
            for (int j = pos + 1; j < n; j++) {
                if (arr[j] > arr[pos]) {
                    count++;
                }
            }

            result[i] = count;
        }

        return result;
    }
}
