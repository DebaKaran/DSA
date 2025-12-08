package stack_queue;

/**
 * LeetCode 2104 — Sum of Subarray Ranges
 *
 * Brute-force approach:
 * For each subarray [i..j], compute its minimum and maximum,
 * accumulate (max - min) into the result.
 */
public final class SubarrayRangeSumCalculator {

    private SubarrayRangeSumCalculator() { /* utility class */ }

    /** Public API */
    public long subArrayRanges(int[] nums) {
        return subArrayRangesBruteForce(nums);
    }

    /**
     * Brute-force implementation.
     *
     * Time:  O(n^2)
     * Space: O(1)
     */
    private long subArrayRangesBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        long total = 0L;
        final int n = nums.length;

        for (int start = 0; start < n; start++) {
            int minValue = nums[start];
            int maxValue = nums[start];

            // Expand subarray [start..end]
            for (int end = start; end < n; end++) {
                int value = nums[end];

                // Update running min and max
                minValue = Math.min(minValue, value);
                maxValue = Math.max(maxValue, value);

                total += (maxValue - minValue);
            }
        }

        return total;
    }
}

