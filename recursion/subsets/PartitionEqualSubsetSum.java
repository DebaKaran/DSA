package recursion.subsets;

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

        // Try to find a subset whose sum equals half of total sum
        return hasSubsetWithTargetSum(numbers, 0, totalSum / 2);
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
