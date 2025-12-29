package recursion.combinations;

import java.util.Arrays;

public class CombinationSumIV {

    /**
     * LeetCode 377: Combination Sum IV
     * Baseline recursive solution (without memoization).
     */
    public int combinationSum4(int[] nums, int target) {
        //return countWaysRecursive(nums, target, 0);

        /**int[][] memo = new int[nums.length][target + 1];

        // Initialize memo table with -1 (uncomputed)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Base case: exactly one way to form sum = 0
        for (int i = 0; i < nums.length; i++) {
            memo[i][0] = 1;
        }

        return countWaysMemoized(nums, target, 0, memo); */

        //return countWaysRecursive(nums, target);
        /**int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);

        // Base case: exactly one way to form sum = 0 (empty sequence)
        dp[0] = 1;

        return countWaysTopDown(nums, target, dp); */

        return countWaysBottomUp(nums, target);
    }

    /**
     * Bottom-up DP.
     *
     * dp[t] represents the number of ordered ways to form sum = t.
     * 
     * Time Complexity: O(n * target)
     * Space Complexity: O(target) for dp array
     */
    private int countWaysBottomUp(int[] nums, int target) {
        int[] dp = new int[target + 1];

        // Base case: one way to form sum 0 (empty sequence)
        dp[0] = 1;

        // Build solutions incrementally from sum = 1 to target
        for (int currentSum = 1; currentSum <= target; currentSum++) {

            // Try each number as the last element in the sequence
            for (int num : nums) {
                if (currentSum >= num) {
                   dp[currentSum] += dp[currentSum - num]; 
                }
            }
        }

        return dp[target];
    }

    /**
     * Top-down DP (memoized recursion).
     *
     * dp[t] represents the number of ordered ways to form sum = t.
     * 
     * Time Complexity: O(n * target)
     * Space Complexity: O(target) for dp array
     */
    private int countWaysTopDown(int[] nums, int remainingTarget, int[] dp) {

        // If already computed, reuse cached result
        if (dp[remainingTarget] != -1) {
            return dp[remainingTarget];
        }

        int totalWays = 0;

        // Try each number as the next element in the sequence
        for (int num : nums) {
            if (remainingTarget >= num) {
                totalWays += countWaysTopDown(
                        nums,
                        remainingTarget - num,
                        dp
                );
            }
        }

        // Cache and return
        return dp[remainingTarget] = totalWays;
    }

    /**
     * Recursive helper that counts the number of ways to form
     * the remaining target.
     *
     * NOTE:
     * This solution is correct but inefficient due to overlapping
     * subproblems. It must be optimized using memoization or DP.
     * 
     * Time Complexity: O(n^target) in worst case
     * Space Complexity: O(target) for recursion stack
     * 
     */

    /*
    * NOTE:
    * This solution intentionally does NOT use an index-based state.
    *
    * In Combination Sum IV, the order of numbers matters:
    *   [1,2] and [2,1] are counted as different sequences.
    *
    * Because every number can be chosen at every step,
    * the subproblem is defined solely by the remaining target sum.
    *
    * Using an index-based include/exclude approach is unnecessary here
    * and leads to a redundant or misleading state definition.
    *
    * Correct state:
    *   ways(target) = number of ordered ways to form `target`
    */

    //If order matters, index disappears.

    private int countWaysRecursive(int[] nums, int remainingTarget) {

        // Base case: exact sum achieved
        if (remainingTarget == 0) {
            return 1;
        }

        int totalWays = 0;

        // Try each number as the next element in the sequence
        for (int num : nums) {
            if (remainingTarget >= num) {
                totalWays += countWaysRecursive(
                        nums,
                        remainingTarget - num
                );
            }
        }

        return totalWays;
    }

    /**
     * Recursive helper with memoization.
     * Uses include/exclude logic (not ideal for this problem).
     * 
     * Time Complexity: O(n * target)
     * Space Complexity: O(n * target) for memoization table
     * 
     */
    private int countWaysMemoized(
            int[] nums,
            int remainingTarget,
            int index,
            int[][] memo
    ) {

        // No numbers left to consider
        if (index >= nums.length) {
            return 0;
        }

        // Target already achieved
        if (remainingTarget == 0) {
            return memo[index][0];
        }

        // Return cached result
        if (memo[index][remainingTarget] != -1) {
            return memo[index][remainingTarget];
        }

        int includeWays = 0;

        // Choice 1: include current number and restart from index 0
        if (nums[index] <= remainingTarget) {
            includeWays = countWaysMemoized(
                    nums,
                    remainingTarget - nums[index],
                    0,
                    memo
            );
        }

        // Choice 2: exclude current number
        int excludeWays = countWaysMemoized(
                nums,
                remainingTarget,
                index + 1,
                memo
        );

        return memo[index][remainingTarget] = includeWays + excludeWays;
    }

    /**
     * Recursive helper that attempts include/exclude decisions.
     * NOTE: This approach is not optimal and will TLE without memoization.
     * Time Complexity: O(2^(target * n)) in worst case
     * Space Complexity: O(target) for recursion stack
     * 
     */
    private int countWaysRecursive(int[] nums, int remainingTarget, int index) {

        // Base case: exact sum achieved
        if (remainingTarget == 0) {
            return 1;
        }

        // Base case: no numbers left to consider
        if (index >= nums.length) {
            return 0;
        }

        int includeWays = 0;

        // Choice 1: include current number (reset index to allow reuse)
        if (nums[index] <= remainingTarget) {
            includeWays = countWaysRecursive(
                    nums,
                    remainingTarget - nums[index],
                    0
            );
        }

        // Choice 2: exclude current number
        int excludeWays = countWaysRecursive(
                nums,
                remainingTarget,
                index + 1
        );

        return includeWays + excludeWays;
    }
}
