package stack_queue;

/**
 * LeetCode 907 — Sum of Subarray Minimums
 *
 * Brute-force O(n^2) approach:
 * For each starting index i, iterate j=i..n-1,
 * maintain the running minimum, and accumulate the sum.
 */
public final class SubarrayMinimumSummer {

    private static final int MOD = 1_000_000_007;

    private SubarrayMinimumSummer() { /* utility class */ }

    /** Public API */
    public int sumSubarrayMins(int[] arr) {
        return sumSubarrayMinsBruteForce(arr);
    }

    /**
     * Brute-force solution.
     *
     * Time:  O(n^2)
     * Space: O(1)
     */
    private int sumSubarrayMinsBruteForce(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int n = arr.length;
        long total = 0;  // use long before applying modulo

        for (int start = 0; start < n; start++) {
            int currentMin = arr[start];

            // Extend subarray [start..end]
            for (int end = start; end < n; end++) {
                currentMin = Math.min(currentMin, arr[end]);
                total += currentMin;
                total %= MOD;
            }
        }

        return (int) total;
    }
}
