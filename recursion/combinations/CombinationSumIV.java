package recursion.combinations;

import java.util.Arrays;

public class CombinationSumIV {

    /**
     * LeetCode 377: Combination Sum IV
     * Baseline recursive solution (without memoization).
     */
    public int combinationSum4(int[] nums, int target) {
        //return countWaysRecursive(nums, target, 0);
        int[][] memo = new int[nums.length][target + 1];

        // Initialize memo table with -1 (uncomputed)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Base case: exactly one way to form sum = 0
        for (int i = 0; i < nums.length; i++) {
            memo[i][0] = 1;
        }

        return countWaysMemoized(nums, target, 0, memo);
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
