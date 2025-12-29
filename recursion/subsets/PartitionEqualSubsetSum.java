package recursion.subsets;

import java.util.Arrays;

/**
 * LeetCode 416: Partition Equal Subset Sum
 *
 * Brute-force recursive solution (baseline implementation).
 * This version is intentionally unoptimized and serves as
 * the conceptual foundation before introducing memoization or DP.
 */
public class PartitionEqualSubsetSum {

    /**
     * Determines whether the array can be partitioned into
     * two subsets with equal sum.
     */
    public boolean canPartition(int[] numbers) {

        // Edge case: empty array can be trivially partitioned
        if (numbers == null || numbers.length == 0) {
            return true;
        }

        int totalSum = 0;
        for (int value : numbers) {
            totalSum += value;
        }

        // If total sum is odd, equal partition is impossible
        if (totalSum % 2 != 0) {
            return false;
        }

        int targetSum = totalSum / 2;

        // Try to find a subset whose sum equals half of total sum
        //return hasSubsetWithTargetSum(numbers, 0, totalSum / 2);
        /*
         * memo[index][sum]:
         *   -1 -> state not computed
         *    0 -> false (subset not possible)
         *    1 -> true  (subset possible)
         */
        int[][] memo = new int[numbers.length][targetSum + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Base DP invariant: sum = 0 is always achievable
        for (int i = 0; i < numbers.length; i++) {
            memo[i][0] = 1;
        }

        hasSubsetWithTargetSum(numbers, 0, targetSum, memo);

        /*
         * The question we are answering:
         * "Starting from index 0, can we form targetSum?"
         */
        return memo[0][targetSum] == 1;
    }

    /**
     * Recursive helper to check if a subset with the given target sum exists,
     * starting from a specific index.
     * 
     * Time Complexity: O(n * targetSum) due to memoization.
     * Space Complexity: O(n * targetSum) for the memoization table.
     * 
     */
    private int hasSubsetWithTargetSum(
            int[] numbers,
            int startIndex,
            int targetSum,
            int[][] memo
    ) {

        // Success condition: exact target achieved
        if (targetSum == 0) {
            return 1;
        }

        // Failure conditions
        if (startIndex >= numbers.length || targetSum < 0) {
            return 0;
        }

        // Return cached result if already computed
        if (memo[startIndex][targetSum] != -1) {
            return memo[startIndex][targetSum];
        }

        // Try including each element starting from startIndex
        for (int i = startIndex; i < numbers.length; i++) {
            if (targetSum >= numbers[i]) {
                if (hasSubsetWithTargetSum(
                        numbers,
                        i + 1,
                        targetSum - numbers[i],
                        memo
                ) == 1) {
                    memo[startIndex][targetSum] = 1;
                    return 1; // early exit on success
                }
            }
        }

        // All choices failed
        memo[startIndex][targetSum] = 0;
        return 0;
    }

    /**
     * Recursive helper to determine if a subset with given target sum exists.
     *
     * @param numbers     input array
     * @param startIndex  current index in the array
     * @param targetSum   remaining sum to be formed
     * 
     * Time Complexity: O(2^n) in the worst case, as each element can be either included or excluded.
     * Space Complexity: O(n) due to the recursion stack.
     * 
     * TLE on large inputs without optimizations.
     */
    private boolean hasSubsetWithTargetSum(int[] numbers, int startIndex, int targetSum) {

        // Success condition: exact target achieved
        if (targetSum == 0) {
            return true;
        }

        // Failure conditions
        if (startIndex >= numbers.length || targetSum < 0) {
            return false;
        }

        // Try including each element starting from current index
        for (int i = startIndex; i < numbers.length; i++) {
            if (hasSubsetWithTargetSum(numbers, i + 1, targetSum - numbers[i])) {
                return true;
            }
        }

        return false;
    }
}
