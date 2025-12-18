package dp.subsequences;

import java.util.Arrays;

/**
 * GFG Problems: Subset Sum Problem
 * https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 * 
 */
class SubsetSum {

     static Boolean isSubsetSum(int[] numbers, int targetSum) {

        // Start recursion from the last index
        //return hasSubsetWithSum(numbers, numbers.length - 1, targetSum);

        // dp[index][sum] = -1 (uncomputed), 0 (false), 1 (true)
        int[][] dp = new int[numbers.length][targetSum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Start recursion from the last index
        return existsSubsetWithSum(numbers, numbers.length - 1, targetSum, dp) == 1;
    }

     /**
     * Top-down DP helper using memoization.
     *
     * @param numbers       Input array (all positive integers)
     * @param index         Current index being considered
     * @param remainingSum  Sum still required to reach target
     * @param dp            Memoization table
     * @return 1 if subset exists, 0 otherwise
     *
     * Time Complexity: O(n × targetSum)
     * Space Complexity: O(n × targetSum) for DP + O(n) recursion stack
     */
    private static int existsSubsetWithSum(
            int[] numbers,
            int index,
            int remainingSum,
            int[][] dp
    ) {

        // If required sum becomes 0, subset is found
        if (remainingSum == 0) {
            return 1;
        }

        // Pruning: numbers are positive, so negative sum is impossible
        if (remainingSum < 0) {
            return 0;
        }

        // Base case: only one element left
        if (index == 0) {
            return remainingSum == numbers[0] ? 1 : 0;
        }

        // Return cached result if already computed
        if (dp[index][remainingSum] != -1) {
            return dp[index][remainingSum];
        }

        // Choice 1: include current element
        int include = 0;
        if (numbers[index] <= remainingSum) {
            include = existsSubsetWithSum(
                    numbers,
                    index - 1,
                    remainingSum - numbers[index],
                    dp
            );
        }

        if(include == 1) {
            return dp[index][remainingSum] = include;
        }
        
        // Choice 2: exclude current element
        int exclude = existsSubsetWithSum(numbers, index - 1, remainingSum, dp);
        
        if(exclude == 1) {
            return dp[index][remainingSum] = exclude;
        }
        
        // Store and return result
       return dp[index][remainingSum] = 0;
    }

    /**
     * Recursive helper method to check for subset sum.
     *
     * @param numbers   Input array
     * @param index     Current index being considered
     * @param remainingSum Sum still needed to reach target
     * @return true if a valid subset exists, false otherwise
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) due to recursion stack
     *
     * Note: All test cases are passing, but this approach may lead to TLE for large inputs.
     */
    private static boolean hasSubsetWithSum(
            int[] numbers,
            int index,
            int remainingSum
    ) {
        // If required sum becomes 0, subset is found
        if (remainingSum == 0) {
            return true;
        }

        // Pruning: no need to continue if sum goes negative
        if (remainingSum < 0) {
            return false;
        }

        // Base case: only one element left
        if (index == 0) {
            return remainingSum == numbers[0];
        }

        // Choice 1: include current element
        if (numbers[index] <= remainingSum &&
            hasSubsetWithSum(
                    numbers,
                    index - 1,
                    remainingSum - numbers[index]
            )) {
            return true;
        }

        // Choice 2: exclude current element
        return hasSubsetWithSum(numbers, index - 1, remainingSum);
    }
}

