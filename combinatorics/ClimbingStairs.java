package combinatorics;

/**
 * LeetCode Problem 70: Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 * 
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        //return countWaysUsingRecursion(n);

        /** int[] memo = new int[n + 1];
        memo[0] = 1;

        return countWaysUsingMemoziation(n, memo); */

        return countWaysBottomUpDP(n);
    }

    // O(n) time complexity
    // O(n) space complexity for dp array
    
    private int countWaysBottomUpDP(int totalSteps) {

        // If there are 0 or 1 steps, there is exactly one way to reach the top
        if (totalSteps <= 1) {
            return 1;
        }

        // dp[i] represents number of ways to reach step i
        int[] dp = new int[totalSteps + 1];

        // Base cases
        dp[0] = 1; // One way to stay at the ground
        dp[1] = 1; // One way to reach the first step

        // Build solution bottom-up
        for (int step = 2; step <= totalSteps; step++) {
            dp[step] = dp[step - 1] + dp[step - 2];
        }

        // Result stored at dp[totalSteps]
        return dp[totalSteps];
    }

    // O(n) time complexity
    // O(n) space complexity for memoization array

    private int countWaysUsingMemoization(int stepsRemaining, int[] memo) {
        // If result already computed, return it
        if (memo[stepsRemaining] != 0) {
            return memo[stepsRemaining];
        }

        // Count ways by taking one step
        int waysWithOneStep =
                countWaysUsingMemoization(stepsRemaining - 1, memo);

        // Count ways by taking two steps (only if valid)
        int waysWithTwoSteps = 0;
        if (stepsRemaining >= 2) {
            waysWithTwoSteps =
                    countWaysUsingMemoization(stepsRemaining - 2, memo);
        }

        // Store and return total number of ways
        memo[stepsRemaining] = waysWithOneStep + waysWithTwoSteps;
        return memo[stepsRemaining];
    }

    // Time Limit Exceeded due to exponential recursion
    // O(2^n) time complexity
    // O(n) space complexity due to recursion stack
    private int countWaysUsingRecursion(int stepsRemaining) {
        // Base case:
        // If no steps remain, one valid way (we've reached the top)
        if (stepsRemaining == 0) {
            return 1;
        }

        // Take one step
        int waysWithOneStep = countWaysUsingRecursion(stepsRemaining - 1);

        // Take two steps (only if possible)
        int waysWithTwoSteps = 0;
        if (stepsRemaining >= 2) {
            waysWithTwoSteps = countWaysUsingRecursion(stepsRemaining - 2);
        }

        // Total ways = ways from both choices
        return waysWithOneStep + waysWithTwoSteps;
    }

}
