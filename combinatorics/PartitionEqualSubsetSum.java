package combinatorics;

import java.util.Arrays;

/**
 * LeetCode 416 – Partition Equal Subset Sum
 * Top-down dynamic programming (subset sum).
 */
class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {

        // Edge case: empty array can be trivially partitioned
        if (nums == null || nums.length == 0) {
            return true;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // If total sum is odd, equal partition is impossible
        if (totalSum % 2 != 0) {
            return false;
        }

        int targetSum = totalSum / 2;

        /*
         * dp[index][sum]:
         *   -1 -> state not computed
         *    0 -> false (cannot form sum)
         *    1 -> true  (can form sum)
         */
        int[][] dp = new int[nums.length][targetSum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Base invariant: sum = 0 is always achievable
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }

        // Can we form targetSum starting from index 0?
        canFormSubsetSumMemo(nums, 0, targetSum, dp);

        return dp[0][targetSum] == 1;
    }

    /**
     * Recursive helper to check if a subset with given target sum exists,
     * starting from a specific index.
     * 
     * Time Complexity: O(n * targetSum)
     * States = n × (totalSum / 2) and each state computed once due to memoization
     * Space Complexity: O(n * targetSum) for memoization table
     * 
     */
    private int canFormSubsetSumMemo(
            int[] nums,
            int index,
            int targetSum,
            int[][] dp
    ) {

        // Success: exact target achieved
        if (targetSum == 0) {
            return 1;
        }

        // Failure: out of bounds or sum exceeded
        if (index >= nums.length || targetSum < 0) {
            return 0;
        }

        // Return cached result
        if (dp[index][targetSum] != -1) {
            return dp[index][targetSum];
        }

        // Try picking each element starting from index
        for (int i = index; i < nums.length; i++) {
            if (targetSum >= nums[i]) {
                if (canFormSubsetSumMemo(
                        nums,
                        i + 1,
                        targetSum - nums[i],
                        dp
                ) == 1) {
                    dp[index][targetSum] = 1;
                    return 1; // early exit on success
                }
            }
        }

        // All choices failed
        dp[index][targetSum] = 0;
        return 0;
    }

    /**
     * Baseline recursive solution to check if a subset with the given target sum exists.
     *
     * This method is kept for learning and reference purposes to illustrate
     * the exponential nature of the naive approach.
     *
     * @param nums        input array
     * @param index       current index in the array
     * @param targetSum   remaining sum to be formed
     *
     * @return true if a subset exists that sums to targetSum, false otherwise
     *
     * Time Complexity:
     *   O(2^n) in the worst case, as each element can either be chosen or skipped,
     *   leading to an exponential number of recursive calls.
     *
     * Space Complexity:
     *   O(n) due to the recursion stack depth.
     *
     * Notes:
     *   - This approach will result in TLE for large inputs.
     *   - It is intentionally NOT used in the final solution.
     *   - Memoization or bottom-up DP is required for acceptable performance.
     */
    private boolean hasSubsetWithTargetSumRecursive(
            int[] nums,
            int index,
            int targetSum
    ) {

        // Success condition: exact target achieved
        if (targetSum == 0) {
            return true;
        }

        // Failure conditions
        if (index >= nums.length || targetSum < 0) {
            return false;
        }

        // Try picking each element starting from the current index
        for (int i = index; i < nums.length; i++) {
            if (hasSubsetWithTargetSumRecursive(
                    nums,
                    i + 1,
                    targetSum - nums[i]
            )) {
                return true;
            }
        }

        // No valid subset found
        return false;
    }

}
