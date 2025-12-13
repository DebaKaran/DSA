package twopointers;

import java.util.Arrays;

/**
 * LeetCode 1877: Minimize Maximum Pair Sum in Array
 *
 * two pointers solution using sorting.
 */
public class MinimizeMaximumPairSum {

    // Public API method expected by LeetCode
    public int minPairSum(int[] nums) {
        return findMinimizedMaximumPairSum(nums);
    }

    /**
     * Pairs smallest and largest elements to minimize the maximum pair sum.
     *
     * Time Complexity:  O(n log n) due to sorting
     * (Sorting the array: O(n log n) and Two-pointer traversal: O(n))
     * Space Complexity: O(1) extra space (ignoring sorting internals)
     */
    private int findMinimizedMaximumPairSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // no elements, no pairs
        }

        int n = nums.length;

        // Edge case: only one pair
        if (n == 2) {
            return nums[0] + nums[1];
        }

        // Sort to enable pairing extremes
        Arrays.sort(nums);

        int left = 0;          // points to smallest element
        int right = n - 1;     // points to largest element
        int maxPairSum = 0;    // track the maximum among all pair sums

        // Pair smallest with largest, second smallest with second largest, etc.
        while (left < right) {
            int currentPairSum = nums[left] + nums[right];

            // Update the maximum pair sum encountered
            maxPairSum = Math.max(maxPairSum, currentPairSum);

            left++;
            right--;
        }

        return maxPairSum;
    }
}
